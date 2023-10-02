package za.co.emerge.formgenerator.service;

import java.util.Set;

import za.co.emerge.formgenerator.persistence.entity.PDFform;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface GeneratePdfFilesService 
{
	Set<PDFform> getHistoricalPdfFiles() throws RuntimeException;
	PDFform downloadPdfFile(Long ID) throws RuntimeException;
}
