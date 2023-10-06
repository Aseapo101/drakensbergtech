package za.co.emerge.formgenerator.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * GeneratedPdfFilesServiceImpl - The service component relays request to retrieve a PDF file or list thereof.
 */
@Service
public class GeneratePdfFilesServiceImpl implements GeneratePdfFilesService
{
	private static Logger log = LoggerFactory.getLogger(GeneratePdfFilesServiceImpl.class);
	
	@Autowired
	private PDFformBuilderRepository pdfFormBuilderRepository;
	
	@Override
	public Set<PDFform> getHistoricalPdfFiles() throws RuntimeException
	{
		Set<PDFform> pdfFileIds = new HashSet<PDFform>();
		
		pdfFileIds.addAll(pdfFormBuilderRepository.findAll());
		log.info("Historical PDF files retrieved from the Database");
		
		return pdfFileIds;
	}

	@Override
	public PDFform downloadPdfFile(Long Id) throws RuntimeException
	{
		Optional.ofNullable(Id).orElseThrow(() -> 
			{ 
				return new FormGeneratorServiceException("Document download validation, invalid parameter value");
			});
		
		log.info("PDF file downloaded from the Database successfully.");
		return pdfFormBuilderRepository.findById(Id).get();
	}
}
