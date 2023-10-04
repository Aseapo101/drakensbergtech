package za.co.emerge.formgenerator.service;

import java.security.Principal;
/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface FormGeneratorService 
{
	void process(java.io.InputStream fileInputStream, Principal userPrincipal) throws RuntimeException;
}
