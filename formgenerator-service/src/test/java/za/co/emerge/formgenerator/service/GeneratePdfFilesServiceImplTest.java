package za.co.emerge.formgenerator.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import za.co.emerge.formgenerator.persistence.PDFformBuilderRepository;
import za.co.emerge.formgenerator.persistence.entity.PDFform;
import za.co.emerge.formgenerator.service.exception.FormGeneratorServiceException;
import za.co.emerge.formgenerator.service.impl.GeneratePdfFilesServiceImpl;

@ExtendWith(MockitoExtension.class)
class GeneratePdfFilesServiceImplTest {

	
	
	@Mock
	private PDFformBuilderRepository repository;
	
	@InjectMocks
	private GeneratePdfFilesServiceImpl serviceHandle;
	
	private static PDFform pdfEntity = null;
	
	@BeforeAll
	 public static void setUpBeforeClass() throws Exception 
	{
		pdfEntity = new PDFform();
		
		pdfEntity.setDocument(new byte [1000]);
		pdfEntity.setDocumentName("test_document");
		
		LocalDateTime dateTime = LocalDateTime.now();
		pdfEntity.setLocalDateTime(dateTime);
	}

	@Test
	public void testGetHistoricalPdfFiles() 
	{
		PDFform pdfEntity1 = new PDFform();
		
		pdfEntity1.setDocument(new byte [2000]);
		pdfEntity1.setDocumentName("test_document_1");
		
		LocalDateTime dateTime = LocalDateTime.now();
		pdfEntity.setLocalDateTime(dateTime);
		
		List<PDFform> formList = List.of(pdfEntity,pdfEntity1);
		org.mockito.BDDMockito.given(repository.findAll()).willReturn(formList);
		
		java.util.Set<PDFform> servicePdfFormList = serviceHandle.getHistoricalPdfFiles();
		
		assertThat(servicePdfFormList.size()).isEqualTo(2);// same number of entities as per the repository mock.
		assertThat(servicePdfFormList.contains(pdfEntity1)).isEqualTo(true); //same document name from the Database.
		assertThat(servicePdfFormList.contains(pdfEntity)).isEqualTo(true); // if true, implies all historical PDF Documents are returned.
		
	}

	@Test
	public void testDownloadPdfFile() 
	{
		org.mockito.BDDMockito.given(repository.findById(1L)).willReturn(Optional.of(pdfEntity));
		
		pdfEntity = serviceHandle.downloadPdfFile(1L);
		
		assertThat(pdfEntity.getDocumentName()).isEqualTo("test_document"); //same document name from the Database.
		assertThat(pdfEntity.getDocument().length).isEqualTo(1000);//same document size
		
		//Exception thrown in case of a null passed...
		Assertions.assertThatExceptionOfType(FormGeneratorServiceException.class)
        .isThrownBy(() -> serviceHandle.downloadPdfFile(null));
	}
}
