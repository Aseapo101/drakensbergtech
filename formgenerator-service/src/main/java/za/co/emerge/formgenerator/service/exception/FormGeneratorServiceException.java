package za.co.emerge.formgenerator.service.exception;

/**
 *@author FRANS MEHLAPE (ASEAPO101)
 *FormGeneratorServiceException - General system exception wrapper class.
 */
@SuppressWarnings("serial")
public class FormGeneratorServiceException extends RuntimeException
{
	
	public FormGeneratorServiceException(String message) 
	{
		super(message);
	}
	
	public FormGeneratorServiceException(String message, Throwable throwable) 
	{
		super(message,throwable);
	}

}
