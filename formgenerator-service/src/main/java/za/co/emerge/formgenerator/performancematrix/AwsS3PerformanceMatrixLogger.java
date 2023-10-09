package za.co.emerge.formgenerator.performancematrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import za.co.emerge.formgenerator.common.FormGeneratorConstants;

/**
 *@author FRANS MEHLAPE (ASEAPO101)
 *PostgresPerfomanceMatrixLogger - Class logs to console the time taken in milliseconds by AWS S3
 *bucket to upload the generated PDF files.
 */
public class AwsS3PerformanceMatrixLogger implements PerfomanceMatrix
{
	private static Logger log = LoggerFactory.getLogger(AwsS3PerformanceMatrixLogger.class);
	
	@Override
	public Long logStartTimeMatric()
	{
		return System.currentTimeMillis();
	}
	
	@Override
	public void logEndTimeMatric(Long startTimingExecution)
	{
		log.info(new StringBuffer(FormGeneratorConstants.TIME_MATRIX_SENTENCE).append(FormGeneratorConstants.S3_DATA_STORE_NAME).append(FormGeneratorConstants.MILLISECONDS).append(Math.subtractExact(System.currentTimeMillis(),startTimingExecution)).toString());
	}
}