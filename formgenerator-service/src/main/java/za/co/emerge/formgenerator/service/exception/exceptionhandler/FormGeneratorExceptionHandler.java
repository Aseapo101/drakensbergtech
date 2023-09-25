package za.co.emerge.formgenerator.service.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@ControllerAdvice
public class FormGeneratorExceptionHandler extends ResponseEntityExceptionHandler
{

	@ExceptionHandler(FormGeneratorServiceException.class)
	ResponseEntity<FormGeneratorServiceExceptionResponse> handleBankserviceException(FormGeneratorServiceException fgse)
	{
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new FormGeneratorServiceExceptionResponse(fgse.getMessage(), HttpStatus.ACCEPTED));
	}
}
