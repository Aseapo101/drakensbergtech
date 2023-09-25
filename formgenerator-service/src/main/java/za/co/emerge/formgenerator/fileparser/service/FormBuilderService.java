package za.co.emerge.formgenerator.fileparser.service;

import java.io.InputStream;

import com.lowagie.text.Document;


public interface FormBuilderService 
{

	byte [] buildFile(InputStream fileInputStream);
}
