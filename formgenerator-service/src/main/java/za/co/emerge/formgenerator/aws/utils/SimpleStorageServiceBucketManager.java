package za.co.emerge.formgenerator.aws.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;

import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
@Component
public class SimpleStorageServiceBucketManager 
{

	private static Logger log = LoggerFactory.getLogger(SimpleStorageServiceBucketManager.class);
	
	@Autowired
	private SimpleStorageServicePropertiesConfiguration awsS3properties;
	
	//Retrieval of the AWS S3 bucket after having created it using the AWS I-AM console.
	public String getBucket(AmazonS3 awsClient) 
	{
		if( awsClient.doesBucketExistV2(awsS3properties.getBucketName())) {
		 
			return awsS3properties.getBucketName();
		}
		else
		{
			log.error("Exception thrown due to AWS s3 bucket non-existence : "+awsS3properties.getBucketName());
			throw new FormGeneratorServiceException(" AWS simple storage service bucket name does not exist "+ awsS3properties.getBucketName());
		}
	}
}
