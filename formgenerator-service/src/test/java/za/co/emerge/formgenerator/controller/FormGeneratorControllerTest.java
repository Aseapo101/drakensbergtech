package za.co.emerge.formgenerator.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.List;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.parser.pojo.GeneratedPdfFormFile;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.FormGeneratorService;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;


@WebMvcTest(FormGeneratorController.class)
public class FormGeneratorControllerTest 
{
	@MockBean
	private FormGeneratorService pdfFormBuilder;

	@MockBean
	private GeneratedPdfFormFile f;
	
	@MockBean
	private GeneratePdfFilesService pdfFileViewService;
	
	@Autowired
	private MockMvc mvc;
	
	private static PDFform pdfEntity = null;
	
	@BeforeAll
	 public static void setUpBeforeClass() throws Exception 
	{
		pdfEntity = new PDFform();
		
		pdfEntity.setDocument(new byte [1000]);
		pdfEntity.setDocumentName("test_document");
		
		LocalDateTime dateTime = LocalDateTime.now();
		pdfEntity.setLocalDateTime(dateTime);
	}
	
	@Test
	public void ViewGeneratedPdfFiles() throws Exception
	{
		
		given(pdfFileViewService.downloadPdfFile(1L)).willReturn(pdfEntity);
		this.mvc.perform(get("/download/1")).andDo(print())
				.andExpectAll(status().isOk(),content().bytes(pdfEntity.getDocument()));
	}
	
	@Test
	public void getGeneratedPdfFileList() throws Exception 
	{
	
		PDFform pdfEntity1 = new PDFform();
		
		pdfEntity1.setDocument(new byte [2000]);
		pdfEntity1.setDocumentName("test_document_1");
		
		LocalDateTime dateTime = LocalDateTime.now();
		pdfEntity.setLocalDateTime(dateTime);
		
		List<PDFform> formList = List.of(pdfEntity,pdfEntity1);
		
		
		List<GeneratedPdfFormFile> generatedFileList = List.of(new GeneratedPdfFormFile(pdfEntity.getDocumentName(),"default_url",pdfEntity.getDocument().length,"application/pdf",pdfEntity.getLocalDateTime())
																,new GeneratedPdfFormFile(pdfEntity1.getDocumentName(),"default_url",pdfEntity1.getDocument().length,"application/pdf",pdfEntity1.getLocalDateTime())) ;
		
		//given(f.getDateTimeCreated()).willReturn(dateTime);
		given(pdfFileViewService.getHistoricalPdfFiles()).willReturn(new java.util.HashSet<PDFform> (formList));
		
		
		this.mvc.perform(get("/viewfiles")).andDo(print())
				.andExpectAll(status().isOk(),
				model().attribute("showfiles_flag", true),
				model().attribute("files", generatedFileList));
	}
	
	@Test
	public void generatePdfFile() throws Exception 
	{
	 
		this.mvc.perform(get("/generate")).andDo(print())
				.andExpectAll(status().isOk(),model().attribute("showfiles_flag", false),model().attribute("message", FormGeneratorConstants.PDF_FILE_GENERATED_MESSAGE + "test.csv"));
		
	}
	
}
