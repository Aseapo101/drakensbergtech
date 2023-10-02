package za.co.emerge.formgenerator.performancematrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@author FRANS MEHLAPE (ASEAPO101)
 *PostgresPerfomanceMatrixLogger - Class logs to console the time taken in milliseconds by AWS S3
 *bucket to upload the generated PDF files.
 */
public class AwsS3PerformanceMatrixLogger implements PerfomanceMatrix
{
	private static Logger log = LoggerFactory.getLogger(AwsS3PerformanceMatrixLogger.class);
	
	private static final String DATA_STORE_NAME = "Amazon-S3-store";
	@Override
	public Long logStartTimeMatric()
	{
		return System.currentTimeMillis();
	}
	
	@Override
	public void logEndTimeMatric(Long startTimingExecution)
	{
		log.info("TIME TAKEN TO UPLOAD THE PDF FILE BY, "+DATA_STORE_NAME+" IN MILLISECONDS, "+Math.subtractExact(System.currentTimeMillis(),startTimingExecution));
	}
}