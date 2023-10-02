package za.co.emerge.formgenerator.service.impl;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.aws.service.impl.AWSSimpleStorageServiceImpl;
import za.co.emerge.formgenerator.fileparser.service.FormBuilderService;
import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.FormGeneratorService;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * FormGeneratorServiceImpl - The service component is a conductor between request to generate a PDF file and 
 * uploading it to AWS S3 bucket and Database store.
 */
@Service
public class FormGeneratorServiceImpl implements FormGeneratorService
{
	private static Logger log = LoggerFactory.getLogger(FormGeneratorServiceImpl.class);
	
	@Autowired
	private FormBuilderService formBuilderService;
	
	@Autowired
	private PDFformBuilderRepository pdfFormBuilderRepository;
	
	@Autowired
	private AWSSimpleStorageServiceImpl awsS3;
	

	@Override
	public void process(InputStream fileInputStream) throws RuntimeException
	{
		byte[] pdfFileByteArray = formBuilderService.buildFile(fileInputStream);
		this.saveToAmazonWebserviceS3(this.saveToDatabaseFileStore(pdfFileByteArray));
		log.info("PDF file successfully generated");	
	}

	/**
	 * @parampdfFileByteArray - PDF byte array generated from parsing the CSV file.
	 * @return PDFform - Database entity of the saved PDF file.
	 */
	private PDFform saveToDatabaseFileStore(byte[] pdfFileByteArray)
	{
		PDFform pdfFormEntiyObject = new PDFform();
		pdfFormEntiyObject.setDocument(pdfFileByteArray);
		pdfFormEntiyObject.setDocumentName(new StringBuffer ("clientdetailsform").append(System.currentTimeMillis()).append(".pdf").toString());
		
		return pdfFormBuilderRepository.saveEntityInstance(pdfFormEntiyObject);
		
	}
	
	/**
	 * saveToAmazonWebserviceS3 - uploads the PDF file to AWS S3.
	 * @parampdfFormEntiyObject - corresponding entity object as saved in the database store.
	 */
	private void saveToAmazonWebserviceS3(PDFform pdfFormEntiyObject)
	{
		awsS3.awsS3PdfFileUpload(pdfFormEntiyObject);
	}
	
}
