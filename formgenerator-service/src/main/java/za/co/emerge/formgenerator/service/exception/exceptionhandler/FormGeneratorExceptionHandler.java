package za.co.emerge.formgenerator.service.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * FormGeneratorExceptionHandler - System exception listener which sanitizes system exceptions returned to the user.
 * Status 202 is returned to the user to indicate that system received the request but did not successfully execute it.
 */
@ControllerAdvice
public class FormGeneratorExceptionHandler extends ResponseEntityExceptionHandler
{

	/**
	 * handleBankserviceException - Handles system exception that have been wrapped using, FormGeneratorServiceException throwable.
	 * @paramfgse - System exception wrapper.
	 * @return ResponseEntity<FormGeneratorServiceExceptionResponse> - sanitized exception response to the user.
	 */
	@ExceptionHandler(FormGeneratorServiceException.class)
	ResponseEntity<FormGeneratorServiceExceptionResponse> handleBankserviceException(FormGeneratorServiceException fgse)
	{
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new FormGeneratorServiceExceptionResponse(fgse.getMessage(), HttpStatus.ACCEPTED));
	}
}
