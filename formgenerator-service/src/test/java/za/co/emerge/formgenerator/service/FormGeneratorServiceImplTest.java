package za.co.emerge.formgenerator.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;
import za.co.emerge.formgenerator.service.impl.FormGeneratorServiceImpl;

@ExtendWith(MockitoExtension.class)
public class FormGeneratorServiceImplTest 
{

@InjectMocks
	private FormGeneratorServiceImpl formGenerationServiceHandle;
	
	@Test
	void test()
	{
		try
		{
			Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
				.isThrownBy(() -> formGenerationServiceHandle.process(null,null)).withMessage("System cannot process a null File input.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
