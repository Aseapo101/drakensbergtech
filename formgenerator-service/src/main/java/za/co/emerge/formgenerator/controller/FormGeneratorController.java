package za.co.emerge.formgenerator.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.engine.jdbc.ReaderInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.parser.pojo.GeneratedPdfFormFile;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.FormGeneratorService;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 *FormGeneratorController - Controller exposing application REST end-points via Spring-Boot, ViewGeneratedPdfFilesService(), getGeneratedPdfList()
 *and generatePdfFile() which let user view the generated PDF file from a CSV file, retrieving all generated PDF files and
 *generating the given PDF file correspondingly.
 *
 */
@RestController
@RequestMapping("/")
public class FormGeneratorController 
{
	
	private static Logger log = LoggerFactory.getLogger(FormGeneratorController.class);
	
	@Autowired
	private FormGeneratorService pdfFormBuilder;

	@Autowired
	private GeneratePdfFilesService pdfFileViewService;
	
	/**
	 * ViewGeneratedPdfFiles (@PathVariable("id") String ID, Model model) - The method invokes the back-end service to fetch the said PDF file using provided document Id number
	 * @param Id - Database primary key of the PDF file to be viewed.
	 * 
	 * @return ResponseEntity<byte []> - Byte [] of the document to be viewed.
	 */
	@GetMapping("/download/{id}")
	public ResponseEntity<byte []> viewGeneratedPdfFiles(@PathVariable("id") String Id) 
	{
		try
		{
			PDFform pdfFormEntity = pdfFileViewService.downloadPdfFile(Long.valueOf(Id));
			
			return ResponseEntity.ok()
			        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFormEntity.getDocumentName() + "\"")
			        .body(pdfFormEntity.getDocument());
		}
		catch(Exception e)
		{
			log.error("System failure while downloading the Pdf file : "+e.getMessage());
			throw new FormGeneratorServiceException(e.getMessage(),e);
		}
	}
	
	/**
	 * getGeneratedPdfFileList(ModelMap model) - Retrieves all files generated from the provided CSV files.
	 * @param model - Spring UI org.springframework.ui.Model object
	 * @return ModelAndView - location of the view page and values to render in the browser.
	 */
	@GetMapping("/viewfiles")
	public ModelAndView getGeneratedPdfFileList(ModelMap model) 
	{
		Set<PDFform> pdfFormEntityObjectList = pdfFileViewService.getHistoricalPdfFiles();
		
		List<GeneratedPdfFormFile> fileDownloadUris = null;
		
		if(Optional.ofNullable(pdfFormEntityObjectList).isEmpty()) 
		{
			throw new FormGeneratorServiceException("No files generated as yet...");
		}
		else 
		{
			try
			{
				fileDownloadUris = pdfFormEntityObjectList.stream().map(pdfFormEntity -> 
				{
					String fileDownloadUri = ServletUriComponentsBuilder
			          .fromCurrentContextPath()
			          .path("/download/")
			          .path(String.valueOf(pdfFormEntity.getId()))
			          .toUriString();
						
					return new GeneratedPdfFormFile(pdfFormEntity.getDocumentName(),fileDownloadUri,pdfFormEntity.getDocument().length,"application/pdf",pdfFormEntity.getLocalDateTime());
						
			    	}).collect(Collectors.toList());
				
				fileDownloadUris.sort(new GeneratedPdfFormFile());
				
				model.addAttribute("showfiles_flag",true);
				model.addAttribute("files", fileDownloadUris);
				return new ModelAndView("/view_files", model);
			}
			catch(Exception e) 
			{
				log.error("System failure while retrieving PDF  files : "+e.getMessage());
				throw new FormGeneratorServiceException(e.getMessage(),e);
			}
		}
	}
	
	/**
	 * generatePdfFile (ModelMap model) - generate the PDF file by reading the CSV file provided from the given directory
	 * in the application.properties file.
	 * @param model - org.springframework.ui.ModelMap UI object.
	 * @return ModelAndView - location of the view page and values to render in the browser.
	 */
	@GetMapping("/generate")
	public ModelAndView generatePdfFile(ModelMap model, HttpServletRequest servletResponse) 
	{
		
		try(InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");) 
		{
			Properties properties = new Properties();
			properties.load(inputStream);
			
			File csvFile = new File(properties.getProperty("csv.input.directory"));
			Reader fileReader = new FileReader(csvFile);
			
			log.info("Generating the pdf file from the csv file : "+csvFile.getName());
			
			pdfFormBuilder.process(new ReaderInputStream(fileReader), servletResponse.getUserPrincipal());
			
			model.addAttribute("showfiles_flag",false);
			model.addAttribute("message",FormGeneratorConstants.PDF_FILE_GENERATED_MESSAGE+csvFile.getName());
			
			return new ModelAndView("/view_files", model);
		}
		catch (Exception e) 
		{
			log.error("Exception occurred while performing a PDF file generation request",e);
			throw new FormGeneratorServiceException(e.getMessage(),e);
		}
	}
}
