package za.co.emerge.aws.service;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.amazonaws.services.s3.AmazonS3Client;

import za.co.emerge.formgenerator.aws.service.impl.AWSSimpleStorageServiceImpl;
import za.co.emerge.formgenerator.aws.service.impl.SimpleStorageServiceCredentialsConfig;
import za.co.emerge.formgenerator.aws.utils.SimpleStorageServiceBucketManager;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@ExtendWith(MockitoExtension.class)
class AWSSimpleStorageServiceTest {

	
	@InjectMocks
	private AWSSimpleStorageServiceImpl awsS3Handle;
	
	@Mock
	SimpleStorageServiceCredentialsConfig awsS3CredentialsHanlde;
	
	@Mock
	SimpleStorageServiceBucketManager awsS3bucketmanagerHandle;
	
	private static PDFform pdfEntity = null;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		pdfEntity = new PDFform();
		pdfEntity.setDocument(new byte [1000]);
		pdfEntity.setDocumentName("test_document");
		
		LocalDateTime dateTime = LocalDateTime.now();
		pdfEntity.setLocalDateTime(dateTime);
	}

	@Test
	void testAwsS3PdfFileUpload() 
	{
		AmazonS3Client s3Client = Mockito.mock(AmazonS3Client.class);
		org.mockito.BDDMockito.given(awsS3CredentialsHanlde.getSimpleStorageServiceClient(awsS3Handle)).willReturn(s3Client);
		org.mockito.BDDMockito.given(awsS3bucketmanagerHandle.getBucket(s3Client)).willReturn("dummy_bucket");
		
		//void method, 
		awsS3Handle.awsS3PdfFileUpload(pdfEntity);

		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
        .isThrownBy(() -> 
        {
        	pdfEntity.setDocument(null);
        	awsS3Handle.awsS3PdfFileUpload(pdfEntity);
        	}
        ).withMessage("Form data to upload to AWS S3 has no content");
		
		//check if null is passed.
		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
        .isThrownBy(() -> awsS3Handle.awsS3PdfFileUpload(null)).withMessage("Form data to upload to AWS S3 is null");
	}

}
