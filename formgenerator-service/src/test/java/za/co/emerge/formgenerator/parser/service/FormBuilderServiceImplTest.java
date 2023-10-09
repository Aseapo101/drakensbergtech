package za.co.emerge.formgenerator.parser.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;

import za.co.emerge.formgenerator.fileparser.service.impl.FormBuilderServiceImpl;
import za.co.emerge.formgenerator.parser.CSVParser;
import za.co.emerge.formgenerator.parser.PDFParser;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@ExtendWith(MockitoExtension.class)
class FormBuilderServiceImplTest {

	@InjectMocks
	private FormBuilderServiceImpl formBuilderServiceHandle;
	
	@Mock
	private PDFParser pdfParser;
	
	@Mock
	private CSVParser csvParser;
	
	private static List<IntelligentReportingCustomerDetails> customerReportingList = new ArrayList<>(2);//two elements
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		customerReportingList.add(new IntelligentReportingCustomerDetails("clientname2","companyname2","2","benefiaciary2"));
		customerReportingList.add(new IntelligentReportingCustomerDetails("clientname","companyname","4","benefiaciary"));
	}
	
	@Test
	public void testBuildFile() throws FileNotFoundException
	{
		PdfReader pdfReader = null;
		try(FileInputStream csvFileInputStream = new FileInputStream(new File("src/test/main/resources/unit_test_input.csv"));
				FileOutputStream fileOutputStream = new FileOutputStream("src/test/main/resources/unit_test_output2.pdf");
				FileInputStream referencedPdf = new FileInputStream (new File ("src/test/main/resources/unit_test_output.pdf")))
		{
			
			org.mockito.BDDMockito.given(csvParser.parseCSVFileInput(csvFileInputStream)).willReturn(customerReportingList);
			org.mockito.BDDMockito.given(pdfParser.createPDF(customerReportingList)).willReturn(referencedPdf.readAllBytes());
			
			byte [] pdfByte = formBuilderServiceHandle.buildFile(csvFileInputStream);
			
			fileOutputStream.write(pdfByte);
			fileOutputStream.flush();
			
			
			//Read the PDF generated and check its contents.
			pdfReader = new PdfReader ("src/test/main/resources/unit_test_output2.pdf");//file just created by the FormBuilderService
			
			//Extracting the PDF text with iText PDF API.
			SimpleTextExtractionStrategy extractionStrategy = new SimpleTextExtractionStrategy();
			StringBuffer textFromPdfFile = new StringBuffer(PdfTextExtractor.getTextFromPage(pdfReader, 1,extractionStrategy));
			
			
			//First record of the CSV file contained in the PDF.
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(0).getClientName()) > -1);//greater than -1 implies data contained in the PDF file.
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(0).getCompanyName()) > -1);
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(0).getNumberOfActiveAcc()) > -1);
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(0).getAccBeneficiary()) > -1);
			
			//Second record of the CSV file contained in the PDF.
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(1).getClientName()) > -1);//greater than -1 implies data contained in the PDF file.
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(1).getCompanyName()) > -1);
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(1).getNumberOfActiveAcc()) > -1);
			assertTrue(textFromPdfFile.indexOf(customerReportingList.get(1).getAccBeneficiary()) > -1);
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
		
		//Exception thrown in case of a null passed...
				Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
		        .isThrownBy(() -> formBuilderServiceHandle.buildFile(null)).withMessage("Invalid file input stream passed to the FormBuilderService");
	}
}
