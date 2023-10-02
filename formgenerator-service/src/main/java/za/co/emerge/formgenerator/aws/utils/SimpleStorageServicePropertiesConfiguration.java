package za.co.emerge.formgenerator.aws.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 * SimpleStorageServicePropertiesConfiguration - Maps AWS S3 name and credentials from the properties file.
 * 
 */
@Component
@ConfigurationProperties(prefix = "aws.s3.props")
@PropertySource({"classpath:application.properties"})
public class SimpleStorageServicePropertiesConfiguration 
{
	public SimpleStorageServicePropertiesConfiguration() {}

	private String accesskey;
	private String secretkey;
	private String bucketname;
	
	public String getAccesskey() 
	{
		return accesskey;
	}
	
	public void setAccesskey(String accesskey) 
	{
		this.accesskey = accesskey;
	}
	
	public String getSecretkey() {
		return secretkey;
	}
	
	public void setSecretkey(String secretkey) 
	{
		this.secretkey = secretkey;
	}

	public String getBucketName() 
	{
		return bucketname;
	}

	public void setBucketName(String bucketname) 
	{
		this.bucketname = bucketname;
	}
}
