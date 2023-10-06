package za.co.emerge.formgenerator.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.TreeSet;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.assertj.core.api.Assertions;
import org.hibernate.engine.jdbc.ReaderInputStream;
import org.junit.jupiter.api.Test;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

class CsvParserTest 
{

	
	@Test
	void testParseCSVFileInput() throws IOException
	{
		try 
		{
			
			TreeSet<IntelligentReportingCustomerDetails> stubTreeSet = new TreeSet<IntelligentReportingCustomerDetails>(List.of(new IntelligentReportingCustomerDetails("clientname","companyname","4","benefiaciary"),new IntelligentReportingCustomerDetails("clientname2","companyname2","2","benefiaciary2")));
			
			CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
		        .setHeader(FormGeneratorConstants.CSV_FILE_COLUMN_HEADERS)
		        .build();
		    
			FileWriter fileWriter = new FileWriter(new File("src/test/main/resources/unit_test_input.csv"),Charset.forName("utf-8"));
		    
		    try (final CSVPrinter printer = new CSVPrinter(fileWriter, csvFormat)) 
		    {
		    	stubTreeSet.forEach((stubTreeSetElement) -> 
		        {
		            try 
		            {
		                printer.printRecord(stubTreeSetElement.getClientName(),stubTreeSetElement.getCompanyName(),stubTreeSetElement.getNumberOfActiveAcc(),stubTreeSetElement.getAccBeneficiary());
		            } 
		            catch (Exception e) 
		            {
		                e.printStackTrace();
		            }
		        });
		        
		        fileWriter.close();
		        
		        Reader fileReader = new FileReader("src/test/main/resources/unit_test_input.csv");
				ReaderInputStream fileReaderInput = new ReaderInputStream(fileReader);
				
				TreeSet<IntelligentReportingCustomerDetails> parsedTestFile = new TreeSet<> (CSVParser.parseCSVFileInput(fileReaderInput));
				
				//First object same should be same as in the stubbed object due to TreeSet
				assertTrue(stubTreeSet.first().getClientName().equalsIgnoreCase(parsedTestFile.first().getClientName()));
				assertTrue(stubTreeSet.first().getCompanyName().equalsIgnoreCase(parsedTestFile.first().getCompanyName()));
				assertTrue(stubTreeSet.first().getNumberOfActiveAcc().equalsIgnoreCase(parsedTestFile.first().getNumberOfActiveAcc()));
				assertTrue(stubTreeSet.first().getAccBeneficiary().equalsIgnoreCase(parsedTestFile.first().getAccBeneficiary()));
				
				//Last object same should be same as in the stubbed object due to TreeSet
				assertTrue(stubTreeSet.last().getClientName().equalsIgnoreCase(parsedTestFile.last().getClientName()));
				assertTrue(stubTreeSet.last().getCompanyName().equalsIgnoreCase(parsedTestFile.last().getCompanyName()));
				assertTrue(stubTreeSet.last().getNumberOfActiveAcc().equalsIgnoreCase(parsedTestFile.last().getNumberOfActiveAcc()));
				assertTrue(stubTreeSet.last().getAccBeneficiary().equalsIgnoreCase(parsedTestFile.last().getAccBeneficiary()));
				
				
		    } catch (IOException e1) 
		    {
				
				e1.printStackTrace();
			}
			
		}
		catch (FileNotFoundException e)
		{
			
			e.printStackTrace();
		}
		
		//method input validation test
			Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
				.isThrownBy(() -> CSVParser.parseCSVFileInput(null));
	}

}
