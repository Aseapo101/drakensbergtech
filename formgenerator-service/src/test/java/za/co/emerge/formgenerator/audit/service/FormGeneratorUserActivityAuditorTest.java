package za.co.emerge.formgenerator.audit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import za.co.emerge.formgenerator.audit.pojo.UserActivityAuditDetails;
import za.co.emerge.formgenerator.audit.service.impl.FormGeneratorUserActivityAuditor;
import za.co.emerge.formgenerator.persistence.UserActivityRepository;
import za.co.emerge.formgenerator.persistence.entity.UserActivity;

@ExtendWith(MockitoExtension.class)
class FormGeneratorUserActivityAuditorTest {

	@Mock
	private  UserActivityRepository userActivityRepositoryHandle;
	
	@InjectMocks
	private FormGeneratorUserActivityAuditor userActivityAuditorHandle;
	

	@Test
	void persistUserActivityAuditLogMessage() 
	{
		
		UserActivityAuditDetails userActivity = new  UserActivityAuditDetails("username", new byte[10], new byte[10], "file_destination", LocalDateTime.now());
		
		UserActivity userActivityEntity = new UserActivity();
		userActivityEntity.setCreationDate(userActivity.getActivityTime());
		userActivityEntity.setCsvInputFile(userActivity.getCsvFileInput());
		userActivityEntity.setPdfOutputFile(userActivity.getPdfFileOutput());
		userActivityEntity.setFileDestination(userActivity.getFileDestination());
		userActivityEntity.setUser(userActivity.getUsername());
		
		given(userActivityRepositoryHandle.findById(1L)).willReturn(Optional.of(userActivityEntity));
		
		//void method, not really asserting its execution..
		userActivityAuditorHandle.persistUserActivityAuditLogMessage(userActivity);
		
		assertThat(userActivityRepositoryHandle.findById(1L).get().getUser()).isEqualTo("username");
	}
}
