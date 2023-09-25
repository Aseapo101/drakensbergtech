package za.co.emerge.formgenerator.service;

import org.springframework.web.multipart.MultipartFile;

public interface FormGeneratorService 
{

	void process(MultipartFile file);
}
