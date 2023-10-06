package za.co.emerge.formgenerator.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;

import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;
import za.co.emerge.formgenerator.pojo.IntelligentReportingCustomerDetails;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 *PDFParser - The class creates a PDF file using PDF iText API implementation.
 */
public class PDFParser 
{

	public static byte[] createPDF(List <IntelligentReportingCustomerDetails> customerDetailsList) 
	{
		Logger log = LoggerFactory.getLogger(PDFParser.class);
		ByteArrayOutputStream baos = null;
		try
		{
			Optional.ofNullable(customerDetailsList).orElseThrow();//null check.
			Optional.ofNullable(customerDetailsList.get(0)).orElseThrow();//list not empty check.
			Document document = new Document();
			baos = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, baos);	
			
			document.open();
			PdfPTable pdfTable = new PdfPTable(FormGeneratorConstants.PDF_FILE_TABLE_COLUMN_SIZE);
			
			//Add headers as per the CSV file headers
			for(String columnHeader : FormGeneratorConstants.PDF_FILE_COLUMN_HEADER_NAMES)
			{
				PdfPCell column = new PdfPCell();
				column.setBackgroundColor(BaseColor.CYAN);
				column.setBorderWidth(2);
				column.setPhrase(new Phrase(columnHeader));
				pdfTable.addCell(column);
			}
		
			//Adding each CSV file record values
			customerDetailsList.stream().forEach(customer -> 
			{
				pdfTable.addCell(customer.getClientName());
				pdfTable.addCell(customer.getCompanyName());
				pdfTable.addCell(customer.getNumberOfActiveAcc());
				pdfTable.addCell(customer.getAccBeneficiary());
			});
				
			document.add(pdfTable);
			document.close();
			
		}
		//TODO: Implement try with resources statement for AutoCloseable
		catch (Exception e) 
		{
			log.error("Exception while generating a PDF file : "+e.getMessage());
			throw new FormGeneratorServiceException("Exception thrown while creating PDF file",e);
		}
		finally 
		{
			try {
					if(baos !=null)
					baos.close();
				}
					catch (IOException e) 
				{
						log.error("Exception thrown while closing the baos system resource : "+e.getMessage());
						throw new FormGeneratorServiceException("Exception thrown while closing the baos system resource : ",e);
				}
		}
		
		return baos.toByteArray();
	}
}
