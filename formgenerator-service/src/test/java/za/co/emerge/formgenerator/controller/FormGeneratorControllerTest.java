package za.co.emerge.formgenerator.controller.test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.hibernate.engine.jdbc.ReaderInputStream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.given;
import org.springframework.http.MediaType;
import za.co.emerge.formgenerator.controller.FormGeneratorController;
import za.co.emerge.formgenerator.service.FormGeneratorService;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;

@WebMvcTest(FormGeneratorController.class)
public class FormGeneratorControllerTest 
{
	@MockBean
	private FormGeneratorService pdfFormBuilder;

	@MockBean
	private GeneratePdfFilesService pdfFileViewService;
	
	@Autowired
	private MockMvc mvc;
	
	@Test
	void generatePdfFile() throws Exception 
	{
	 
		
	}
	
	@Test
	void testPerformAccountDeposit() throws Exception {
		
		
	}

}
