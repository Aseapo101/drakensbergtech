package za.co.emerge.formgenerator.common;

import java.util.List;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface FormGeneratorConstants 
{

	public static final String PDF_FILE_GENERATED_MESSAGE = "Your pdf file successfully generated from the csv file name :";
	public static final String  PDF_FILE_DOWNLOADED_MESSAGE = "Your file has been successfully downloaded";
	public static final List<String> PDF_FILE_COLUMN_HEADER_NAMES = List.of("Client_Name", "Company_Name", "Number_of_Active_Bank _accounts","Account_Beneficiary");
	public static final int PDF_FILE_TABLE_COLUMN_SIZE = 4;
	public static final int ZERO_CONSTANT_VALUE = 0;
	public static final int ONE_CONSTANT_VALUE = 1;
	public static final int NEGATIVE_ONE_CONSTANT_VALUE = -1;
	//CSV File headers corresponding to the file being used to generate a PDF file.
	public static final String[] CSV_FILE_COLUMN_HEADERS = { "Client Name", "Company Name","Number of Active Bank accounts","Account Beneficiary"};
	
}
