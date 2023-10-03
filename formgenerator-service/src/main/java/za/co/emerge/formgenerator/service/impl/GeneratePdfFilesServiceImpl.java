package za.co.emerge.formgenerator.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.GeneratePdfFilesService;

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
		
		pdfFormBuilderRepository.findAll(Sort.by("localDateTime").descending()).stream().forEach(pdfFormEntityObject -> pdfFileIds.add(pdfFormEntityObject));
		log.info("Historical PDF files retrieved from the Database");
		
		return pdfFileIds;
	}

	@Override
	public PDFform downloadPdfFile(Long Id) throws RuntimeException
	{
		log.info("PDF files downloaded from the Database");
		return pdfFormBuilderRepository.findById(Id).get();
	}
}
