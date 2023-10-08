package za.co.emerge.formgenerator.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import za.co.emerge.formgenerator.persistence.entity.UserActivity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserActivityRepositoryTest 
{

	@Autowired
	private UserActivityRepository userActivityRepositoryHandle;
	
	@Test
	void test() 
	{	
			UserActivity userActivityEntity = new UserActivity();
			
			userActivityEntity.setUser("test_user");
			userActivityEntity.setFileDestination("destination_url");
			userActivityEntity.setCsvInputFile(new byte [100]);
			userActivityEntity.setPdfOutputFile(new byte [100]);
			
			LocalDateTime  dateTime = LocalDateTime.now();
			userActivityEntity.setCreationDate(dateTime);
			
			userActivityEntity =  userActivityRepositoryHandle.save(userActivityEntity); //persist.
			userActivityEntity = userActivityRepositoryHandle.findById(userActivityEntity.getId()).get(); //retrieve from the Mock Database.
			
			assertThat(userActivityEntity.getCsvInputFile().length).isEqualTo(100);//same file size persisted
			assertThat(userActivityEntity.getPdfOutputFile().length).isEqualTo(100);//same file size persisted.
			assertThat(userActivityEntity.getUser()).isEqualTo("test_user"); // same user from the Databse.
			assertThat(userActivityEntity.getCreationDate()).isEqualTo(dateTime);// same data insertion time and date.
	}
}
