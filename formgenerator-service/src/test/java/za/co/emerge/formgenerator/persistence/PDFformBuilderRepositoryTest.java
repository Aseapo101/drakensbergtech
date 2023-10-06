package za.co.emerge.formgenerator.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import za.co.emerge.formgenerator.persistence.entity.PDFform;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  AutoConfigureTestDatabase.Replace.NONE)

public class PDFformBuilderRepositoryTest 
{

	@Autowired
	private PDFformBuilderRepository pdfFormBuilderRepositoryHandle;
	
	@Test
	public void test()
	{
		PDFform pdfEntity = new PDFform();
			
			pdfEntity.setDocument(new byte [1000]);
			pdfEntity.setDocumentName("test_document");
			
			LocalDateTime dateTime = LocalDateTime.now();
			pdfEntity.setLocalDateTime(dateTime);
			
			pdfEntity = pdfFormBuilderRepositoryHandle.save(pdfEntity);//persist into the mock DB
			pdfEntity = pdfFormBuilderRepositoryHandle.findById(pdfEntity.getId()).get(); //retrieved from the Database
			
			assertThat("test_document").isEqualTo(pdfEntity.getDocumentName());//same user from the Database.
			assertThat(1000).isEqualTo(pdfEntity.getDocument().length);////same file size froom the Database. 
			assertThat(dateTime).isEqualTo(pdfEntity.getLocalDateTime());//same time value from Database.
	}
}