package za.co.emerge.formgenerator.aws.service;

import za.co.emerge.formgenerator.persistence.entity.PDFform;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface AWSSimpleStorageService 
{

	void awsS3PdfFileUpload(PDFform formEntityObect) throws RuntimeException;
}
