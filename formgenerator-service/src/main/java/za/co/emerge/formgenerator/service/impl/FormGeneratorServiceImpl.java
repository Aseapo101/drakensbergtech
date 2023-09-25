package za.co.emerge.formgenerator.service.impl;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lowagie.text.Document;

import za.co.emerge.formgenerator.fileparser.service.FormBuilderService;
import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.FormGeneratorService;

@Service
public class FormGeneratorServiceImpl implements FormGeneratorService
{

	private static Logger log = LoggerFactory.getLogger(FormGeneratorServiceImpl.class);
	
	@Autowired
	private FormBuilderService formBuilder;
	
	@Autowired
	private PDFformBuilderRepository pdfFormBuilderRepository;
	

	@Override
	public void process(MultipartFile file)
	{
		
		try
		{
			byte[] pdfFile = formBuilder.buildFile(file.getInputStream());
			//persistParallel(pdfFile);
			
			PDFform p = new PDFform();
			p.setDocument(pdfFile);
			
			pdfFormBuilderRepository.save(p);
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	//Reference a service to persist to AWS S3
	private void persistParallel(Document pdfFile) 
	{
		
		ExecutorService e = Executors.newCachedThreadPool();
		
		
	}
	

}
