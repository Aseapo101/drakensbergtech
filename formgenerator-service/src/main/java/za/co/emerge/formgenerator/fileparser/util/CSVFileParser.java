package za.co.emerge.formgenerator.fileparser.util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;

public class CSVFileParser 

{
	
	private static Logger log = LoggerFactory.getLogger(CSVFileParser.class);
	
	public static List<IntelligentReportingCustomerDetails> parseCSVFileInput(InputStream is) 
	{
		InputStreamReader inputStreamReader = null;
    	
    	BufferedReader fileReader = null;
    	
        CSVParser csvParser = null;
    	
	    try {
	    
	    	inputStreamReader = new InputStreamReader(is);
	    	
	    	fileReader = new BufferedReader(inputStreamReader);
	    	
	        csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()); 
	    	List<IntelligentReportingCustomerDetails> developerTutorialList = new ArrayList<>();
	    	Iterable<CSVRecord> csvRecords = csvParser.getRecords();

	    	
	      for (CSVRecord csvRecord : csvRecords) {
	    	  IntelligentReportingCustomerDetails customerDetails = new IntelligentReportingCustomerDetails(
	              csvRecord.get("Client Name"),
	              csvRecord.get("Company Name"),
	              csvRecord.get("Number of Active Bank accounts"),
	              csvRecord.get("Account Beneficiary")
	            );

	    	  developerTutorialList.add(customerDetails);
	      }

	      return developerTutorialList;
	    }
	    catch (IOException e) 
	    {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	    finally {
	    	
	    	closeResource(csvParser,fileReader,inputStreamReader);
	    	log.info(" ###############System resources closed in the CSVparser############ ");
	    }
	  }
	
	//Implement Try with resources statement in the above try block
	private static void closeResource(CSVParser parser, BufferedReader bufferedReader, InputStreamReader inputStreamreader ) 
	{
		try
		{
			if(parser != null) 
				parser.close();
			
			if(bufferedReader != null) 
				bufferedReader.close();

			if(inputStreamreader != null) 
				inputStreamreader.close();

		} 
		catch (IOException e) 
		{
			log.error(" Error while closing system resources in the CSVparser : ",e);
		}
	}
	
}
