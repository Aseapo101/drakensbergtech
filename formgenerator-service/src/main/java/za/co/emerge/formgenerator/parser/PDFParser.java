package za.co.emerge.formgenerator.parser;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
@Component
public class PDFParser 
{

	public byte[] createPDF(List <IntelligentReportingCustomerDetails> customerDetailsList) throws FormGeneratorServiceException
	{
		Logger log = LoggerFactory.getLogger(PDFParser.class);
		
		Optional.ofNullable(customerDetailsList).orElseThrow(() ->{
			throw new FormGeneratorServiceException ("Customer reporting details are null");
		});//null check.
		
		Optional.ofNullable(customerDetailsList.get(0)).orElseThrow(() -> {
			
			throw new FormGeneratorServiceException ("Customer report details are empty.");
		});//list not empty check.
		
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream())
		{
			
			Document document = new Document();
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
			return baos.toByteArray();
			
		}
		//TODO: Implement try with resources statement for AutoCloseable
		catch (Exception e) 
		{
			log.error("Exception while generating a PDF file : "+e.getMessage());
			throw new FormGeneratorServiceException("Exception while generating a PDF file",e);
		}
	}
}
