package za.co.emerge.formgenerator.parser;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public static List<IntelligentReportingCustomerDetails> parseCSVFileInput(InputStream inputStream) throws FormGeneratorServiceException
	{
		
		try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream);BufferedReader fileReader = new BufferedReader(inputStreamReader)){
    			
			Optional.ofNullable(inputStream).orElseThrow();
	    		
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
		catch (Exception e) 
	    {	
			log.error("Exception while parsing the CSV file",e);
			throw new FormGeneratorServiceException("Failed to parse CSV file: " + e.getMessage());
	    }
	    
	}
}
