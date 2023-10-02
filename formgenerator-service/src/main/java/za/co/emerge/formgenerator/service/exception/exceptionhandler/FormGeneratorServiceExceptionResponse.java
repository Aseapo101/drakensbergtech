package za.co.emerge.formgenerator.service.exception.exceptionhandler;

import org.springframework.http.HttpStatus;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * FormGeneratorServiceExceptionResponse - Sanitized message sent to client system in case of an Exception occurrence.
 */
public class FormGeneratorServiceExceptionResponse {
	
	private String message;
	private HttpStatus serviceResponseStatus;
	
	public FormGeneratorServiceExceptionResponse(String message,HttpStatus serviceResponseStatus) 
	{
		this.message = message;
		this.serviceResponseStatus = serviceResponseStatus;
	}

	public String getMessage() 
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}

	public HttpStatus getServiceResponseStatus() 
	{
		return serviceResponseStatus;
	}

	public void setServiceResponseStatus(HttpStatus serviceResponseStatus) 
	{
		this.serviceResponseStatus = serviceResponseStatus;
	}

}
