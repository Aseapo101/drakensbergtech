package za.co.emerge.formgenerator.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.ViewHistoricalPdfFilesService;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

@Service
public class ViewHistoricalPdfFilesServiceImpl implements ViewHistoricalPdfFilesService
{

	private static Logger log = LoggerFactory.getLogger(ViewHistoricalPdfFilesServiceImpl.class);
	
	@Autowired
	private PDFformBuilderRepository pdfFormBuilderRepository;
	
	@Override
	public List<byte []> getHistoricalFiles() 
	{
		try
		{
		
			List<PDFform> pdfFiles = pdfFormBuilderRepository.findAll();
			return null;
		}
		catch(Exception e)
		{
			log.error("Exception while retrieving historical PDF files",e);
			throw new FormGeneratorServiceException(e.getMessage(), e);
			
		}
	}

}
