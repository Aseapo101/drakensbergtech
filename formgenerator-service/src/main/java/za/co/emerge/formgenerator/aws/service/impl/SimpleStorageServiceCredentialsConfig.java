package za.co.emerge.formgenerator.aws.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import za.co.emerge.formgenerator.aws.service.AWSSimpleStorageService;
import za.co.emerge.formgenerator.aws.utils.SimpleStorageServicePropertiesConfiguration;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *SimpleStorageServiceCredentialsConfig - Class uploads AWS credentials to be used to connect to the AWS cloud.
 */
@Configuration
public class SimpleStorageServiceCredentialsConfig 
{
	
	private Logger log = LoggerFactory.getLogger(SimpleStorageServiceCredentialsConfig.class);
	
	@Autowired
	private SimpleStorageServicePropertiesConfiguration awsS3properties;
	
	//AWS Credentials read from the properties mapping component.
	private  AWSCredentials getCredentials() 
	{
		return new BasicAWSCredentials(awsS3properties.getAccesskey(),awsS3properties.getSecretkey());
	}
	
	//Set Amazon AWS Credentials.
	@Bean
    public AmazonS3 getSimpleStorageServiceClient(AWSSimpleStorageService service) {
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(this.getCredentials()))
                .withRegion(Regions.US_EAST_1)
                .build();
        log.info("Returned the AWS S3 client handle successfully.");
        return s3client;
    }
}
