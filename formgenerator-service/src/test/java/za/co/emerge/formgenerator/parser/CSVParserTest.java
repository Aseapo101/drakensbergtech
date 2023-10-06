package za.co.emerge.formgenerator.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

import org.hibernate.engine.jdbc.ReaderInputStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class CsvParserTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
	
	}

	
	@Test
	void testParseCSVFileInput() 
	{
		
		
		try 
		{
			Reader fileReader = new FileReader("formgenerator-service/src/test/main/resources/unit_test_input.csv");//input test file
			ReaderInputStream fileReaderInput = new ReaderInputStream(fileReader);
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

}
