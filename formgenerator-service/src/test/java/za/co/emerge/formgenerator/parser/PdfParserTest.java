package za.co.emerge.formgenerator.parser;

import java.util.ArrayList;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

class PdfParserTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
	}

	@Test
	void testCreatePDF() 
	{
		
		//input validation
		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
			.isThrownBy(() -> PDFParser.createPDF(null));
		//input validation
		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
			.isThrownBy(() -> PDFParser.createPDF(new ArrayList<IntelligentReportingCustomerDetails>()));
	}

}
