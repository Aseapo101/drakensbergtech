package za.co.emerge.formgenerator.perfomancematrix.util;

import java.util.TreeMap;

import org.springframework.stereotype.Component;

import za.co.emerge.formgenerator.performancematrix.AwsS3PerformanceMatrixLogger;
import za.co.emerge.formgenerator.performancematrix.PerfomanceMatrix;
import za.co.emerge.formgenerator.performancematrix.PostgresPerfomanceMatrixLogger;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *PerfomanceLoggerSubject - Utility class to register data stores to be timed when uploading PDF files. Both AWS S3
 *and Postgres DB stores have been registered. It exposes methods to invoke the Datastore time loggers without the
 *client instantiating performance loggers to be invoked. 
 */
@Component
public class PerfomanceLoggerSubject 
{
	private static TreeMap<String,PerfomanceMatrix> listeners;
	
	static
	{
		listeners = new TreeMap<>();
		listeners.put("PostgresDatabaseStore", new PostgresPerfomanceMatrixLogger());
		listeners.put("AmazonS3store", new AwsS3PerformanceMatrixLogger());
	}
	
	public static Long startPostgresDatabaseTimeLog()
	{
		return listeners.get("PostgresDatabaseStore").logStartTimeMatric();
	}
	
	public static void endPostgresDatabaseTimeLog(Long startTimingExecution)
	{
		listeners.get("PostgresDatabaseStore").logEndTimeMatric(startTimingExecution);
	}
	
	public static Long startAWSTimeLog()
	{
		return listeners.get("AmazonS3store").logStartTimeMatric();
	}
	
	public static void endAWSTimeLog(Long startTimingExecution)
	{
		listeners.get("AmazonS3store").logEndTimeMatric(startTimingExecution);
	}
}
