package za.co.emerge.formgenerator.service.exception.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
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

	private static Logger log = LoggerFactory.getLogger(FormGeneratorExceptionHandler.class);
	/**
	 * handleBankserviceException - Handles system exception that have been wrapped using, FormGeneratorServiceException throwable.
	 * @paramfgse - System exception wrapper.
	 * @return ModelAndView - sanitized exception response to the user.
	 */
	@ExceptionHandler(FormGeneratorServiceException.class)
	ModelAndView handleBankserviceException(FormGeneratorServiceException fgse,ModelMap modelMap)
	{
		
		log.error("System error : "+fgse.getMessage(),fgse);
		return new ModelAndView("/error", modelMap);
	}
}
