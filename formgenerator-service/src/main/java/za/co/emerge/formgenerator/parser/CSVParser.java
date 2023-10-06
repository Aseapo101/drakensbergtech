package za.co.emerge.formgenerator.parser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 *CSVFileParser - The class parses a CSV file using Apache commons-csv implementation.
 */
public class CSVParser 

{
	private static Logger log = LoggerFactory.getLogger(CSVParser.class);
	
	/**
	 * @paraminputStream - CSV file parameter inputstream.
	 * @return List<IntelligentReportingCustomerDetails> - A list of records/lines as per in the CSV file mapped according to
	 * header columns of the CSV file.
	 */
	public static List<IntelligentReportingCustomerDetails> parseCSVFileInput(InputStream inputStream) 
	{
		InputStreamReader inputStreamReader = null;
    	BufferedReader fileReader = null;
    	
    	try {
	    
	    		inputStreamReader = new InputStreamReader(inputStream);
	    		fileReader = new BufferedReader(inputStreamReader);
	    	
	    		List<IntelligentReportingCustomerDetails> developerTutorialList = new ArrayList<>();
	    		
	    		CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
		    	        .setHeader(FormGeneratorConstants.CSV_FILE_COLUMN_HEADERS )
		    	        .setSkipHeaderRecord(true)
		    	        .build();
		    	
		    	Iterable<CSVRecord> csvRecords = csvFormat.parse(fileReader);
		    	
		    	csvRecords.forEach(csvRecord -> 
		    	{
		    		IntelligentReportingCustomerDetails customerDetails = new IntelligentReportingCustomerDetails
		    				(
		    				csvRecord.get("Client Name"),
		    				csvRecord.get("Company Name"),
		    				csvRecord.get("Number of Active Bank accounts"),
		    				csvRecord.get("Account Beneficiary")
		    				);
	
		    	  developerTutorialList.add(customerDetails);
		    	});
		    	
	    	return developerTutorialList;
	    }
	    catch (IOException e) 
	    {
	      throw new RuntimeException("Failed to parse CSV file: " + e.getMessage());
	    }
	    finally 
	    {
	    	closeResource(fileReader,inputStreamReader);
	    }
	  }
	
	//TODO:Implement Try with resources statement in the above try block
	private static void closeResource(BufferedReader bufferedReader, InputStreamReader inputStreamreader ) 
	{
		try
		{
			if(bufferedReader != null) 
				bufferedReader.close();

			if(inputStreamreader != null) 
				inputStreamreader.close();

		} 
		catch (IOException e) 
		{
			log.error(" Error while closing system resources in the CSVparser : ",e);
			throw new FormGeneratorServiceException("Exception occurred while closing system resources when parsing the CSV file");
		}
	}
}
