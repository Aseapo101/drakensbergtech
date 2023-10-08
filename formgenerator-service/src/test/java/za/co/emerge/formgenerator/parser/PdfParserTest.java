package za.co.emerge.formgenerator.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@ExtendWith(MockitoExtension.class)
class PdfParserTest 
{

	@InjectMocks
	private PDFParser pdfParser;
	
	private static List<IntelligentReportingCustomerDetails> customerReportingList = new ArrayList<>(2);//two elements
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		customerReportingList.add(new IntelligentReportingCustomerDetails("clientname2","companyname2","2","benefiaciary2"));
		customerReportingList.add(new IntelligentReportingCustomerDetails("clientname","companyname","4","benefiaciary"));
	}

	@Test
	void testCreatePDF() 
	{
		
		PdfReader pdfReader = null;
		
		try(FileOutputStream pdfFileOutput = new FileOutputStream(new File ("src/test/main/resources/unit_test_output.pdf")))
		{	
			//Generate PDF from stubbed data.
			byte[] methodReturnedPdfByteArray = pdfParser.createPDF(customerReportingList);
			
			pdfFileOutput.write(methodReturnedPdfByteArray);
			pdfFileOutput.flush();
			
			//Read the PDF produced generated and check its contents.
			pdfReader = new PdfReader ("src/test/main/resources/unit_test_output.pdf");
			
			//Extracting the PDF text with iText PDF API.
			SimpleTextExtractionStrategy extractionStrategy = new SimpleTextExtractionStrategy();
			StringBuffer textFromPage = new StringBuffer(PdfTextExtractor.getTextFromPage(pdfReader, 1,extractionStrategy));
			
			textFromPage.indexOf(customerReportingList.get(0).getAccBeneficiary());
			
			//1st record parsed into the PDF.
			assertTrue(textFromPage.indexOf(customerReportingList.get(0).getClientName()) > -1);//greater than -1 implies pdf has the text...
			assertTrue(textFromPage.indexOf(customerReportingList.get(0).getCompanyName()) > -1);
			assertTrue(textFromPage.indexOf(customerReportingList.get(0).getNumberOfActiveAcc()) > -1);
			assertTrue(textFromPage.indexOf(customerReportingList.get(0).getAccBeneficiary()) > -1);
			
			//second record parsed into the PDF.
			assertTrue(textFromPage.indexOf(customerReportingList.get(1).getClientName()) > -1);//greater than -1 implies pdf has the text...
			assertTrue(textFromPage.indexOf(customerReportingList.get(1).getCompanyName()) > -1);
			assertTrue(textFromPage.indexOf(customerReportingList.get(1).getNumberOfActiveAcc()) > -1);
			assertTrue(textFromPage.indexOf(customerReportingList.get(1).getAccBeneficiary()) > -1);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(pdfReader != null)
				pdfReader.close();
		}
		
		//input validation
		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
			.isThrownBy(() -> pdfParser.createPDF(null));
		//input validation
		Assertions.assertThatExceptionOfType(IndexOutOfBoundsException.class)
			.isThrownBy(() -> pdfParser.createPDF(new ArrayList<IntelligentReportingCustomerDetails>()));
	}

}
