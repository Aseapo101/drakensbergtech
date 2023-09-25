package za.co.emerge.formgenerator.fileparser.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;

import za.co.emerge.formgenerator.fileparser.service.FormBuilderService;
import za.co.emerge.formgenerator.fileparser.util.CSVFileParser;
import za.co.emerge.formgenerator.fileparser.util.PDFParser;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@Service
public class FormBuilderServiceImpl implements FormBuilderService
{

	private static Logger log = LoggerFactory.getLogger(FormBuilderServiceImpl.class);
	
	@Override
	public byte [] buildFile(InputStream fileInputStream) 
	{
		Document pdfDocument = null;
		try
		{
			List<IntelligentReportingCustomerDetails> parsedCustomerDetails = CSVFileParser.parseCSVFileInput(fileInputStream);
			byte [] pdfByteArrayOutPutStream = PDFParser.createPDF(parsedCustomerDetails);
			
			log.info("Successfully built the pdf..........");
			return pdfByteArrayOutPutStream;
		}
		catch(Exception e)
		{
			log.error("Error while parsing the CSV file : ",e);
			throw new FormGeneratorServiceException(e.getMessage(), e);
		}
		finally
		{
			if(pdfDocument != null)
			pdfDocument.close();
		}
	}
}
