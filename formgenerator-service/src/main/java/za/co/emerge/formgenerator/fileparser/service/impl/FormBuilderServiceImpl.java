package za.co.emerge.formgenerator.fileparser.service.impl;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;

import za.co.emerge.formgenerator.fileparser.service.FormBuilderService;
import za.co.emerge.formgenerator.fileparser.util.CSVFileParser;
import za.co.emerge.formgenerator.fileparser.util.PDFParser;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * 
 * FormBuilderServiceImpl - Service component to parse the given CSV file and generate a PDF file from it.
 *
 */
@Service
public class FormBuilderServiceImpl implements FormBuilderService
{

	private static Logger log = LoggerFactory.getLogger(FormBuilderServiceImpl.class);
	
	/**
	 * buildFile (InputStream fileInputStream) - The method builds a PDF file from the given parameter argument.
	 *@paramfileInputStream - CSV file Inputstream used to generate the corresponding PDF file.
	 */
	@Override
	public byte [] buildFile(InputStream fileInputStream) throws RuntimeException
	{
		Document pdfDocument = null;
		try
		{
			List<IntelligentReportingCustomerDetails> parsedCustomerDetails = CSVFileParser.parseCSVFileInput(fileInputStream);
			byte [] pdfByteArrayOutPutStream = PDFParser.createPDF(parsedCustomerDetails);
			
			log.info("Successfully generated PDF file type");
			
			return pdfByteArrayOutPutStream;
		}
		catch(Exception e)
		{
			log.error("Error while parsing the CSV file and generating PDF file types  : ",e);
			throw new FormGeneratorServiceException(e.getMessage(), e);
		}
		finally
		{
			if(pdfDocument != null)
				pdfDocument.close();
		}
	}
}
