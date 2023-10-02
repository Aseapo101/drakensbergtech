package za.co.emerge.formgenerator.controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.fileparser.GeneratedPdfFormFile;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.FormGeneratorService;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 *FormGeneratorController - Controller exposing application REST end-points via Spring-Boot, ViewGeneratedPdfFilesService(), getGeneratedPdfList()
 *and generatePdfFile() which let user view the generated PDF file from a CSV file, retrieving all generated PDF files and
 *generating the given PDF file correspondingly.
 *
 */
@Controller
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
	@GetMapping(value ="/download/{id}")
	public ResponseEntity<byte []> ViewGeneratedPdfFiles(@PathVariable("id") String Id) 
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
						
					return new GeneratedPdfFormFile(pdfFormEntity.getDocumentName(),fileDownloadUri,pdfFormEntity.getDocument().length,"application/pdf");
						
			    	}).collect(Collectors.toList());
		
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
	public ModelAndView generatePdfFile(ModelMap model) 
	{
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");
		try 
		{
			Properties properties = new Properties();
			properties.load(inputStream);
			
			File file = new File(properties.getProperty("csv.input.directory"));
			Reader in = new FileReader(file);
			
			log.info("Generating the pdf file from the csv file : "+file.getName());
			
			pdfFormBuilder.process(new ReaderInputStream(in));
			
			model.addAttribute("showfiles_flag",false);
			model.addAttribute("message",FormGeneratorConstants.PDF_FILE_GENERATED_MESSAGE+file.getName());
			
			return new ModelAndView("/view_files", model);
		}
		catch (IOException e) 
		{
			throw new FormGeneratorServiceException(e.getMessage(), e);
		}
		//TODO: IMPLEMENT TRY WITH RESOURCE STATEMENT FOR AUTOCLOSEABLE
		finally
		{
			try 
			{
				if(inputStream != null)
					inputStream.close();
			} 
			catch (IOException e) 
			{
				log.error("Exception while trying to release properties inputstream resource");
				throw new FormGeneratorServiceException("Exception while trying to release properties inputstream resource",e);
			}
		}
	}
}
