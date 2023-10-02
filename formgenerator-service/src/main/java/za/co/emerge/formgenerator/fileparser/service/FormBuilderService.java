package za.co.emerge.formgenerator.fileparser.service;

import java.io.InputStream;


/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface FormBuilderService 
{
	byte [] buildFile(InputStream fileInputStream) throws RuntimeException;
}
