package za.co.emerge.formgenerator.aws.service.impl;

import java.io.ByteArrayInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import za.co.emerge.formgenerator.aws.service.AWSSimpleStorageService;
import za.co.emerge.formgenerator.aws.utils.SimpleStorageServiceBucketManager;
import za.co.emerge.formgenerator.perfomancematrix.util.PerfomanceLoggerSubject;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 * AWSSimpleStorageServiceImpl - The service component invokes the AWS cloud to upload PDF file to S3 bucket.
 */
@Service
public class AWSSimpleStorageServiceImpl implements AWSSimpleStorageService
{

	private Logger log = LoggerFactory.getLogger(AWSSimpleStorageServiceImpl.class);
	
	@Autowired
	SimpleStorageServiceCredentialsConfig awsS3Credentials;
	
	@Autowired
	SimpleStorageServiceBucketManager awsS3bucketmanager;
	
	@Override
	public void awsS3PdfFileUpload(PDFform pdfFormEntiyObject) throws RuntimeException
	{
		try
		{
			Long startTimingExecution = PerfomanceLoggerSubject.startAWSTimeLog();
			AmazonS3 awsClient = awsS3Credentials.getSimpleStorageServiceClient();
			ByteArrayInputStream pdfArrayByteOutputStream = new ByteArrayInputStream(pdfFormEntiyObject.getDocument());
			
			//Set file Metadata on S3 Bucket
			ObjectMetadata metaDataObject = new ObjectMetadata();
			metaDataObject.setContentLength(pdfArrayByteOutputStream.available());
			metaDataObject.setContentType("application/pdf");
			
			awsClient.putObject(awsS3bucketmanager.getBucket(awsClient), pdfFormEntiyObject.getDocumentName() , pdfArrayByteOutputStream , metaDataObject);
			
			PerfomanceLoggerSubject.endAWSTimeLog(startTimingExecution);
			
			log.info(" Successfully uploaded PDF to AWS S3 storage");
		}
		catch(Exception e)
		{
			log.error("Exception while uploading the Pdf file to Amazon Webservices store S3 : "+e.getMessage());
			throw new FormGeneratorServiceException(e.getMessage(), e);
		}
	}
	
}
