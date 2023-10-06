package za.co.emerge.formgenerator.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import za.co.emerge.formgenerator.persistence.UserActivityRepository;
import za.co.emerge.formgenerator.persistence.entity.UserActivity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserActivityRepositoryTest 
{

	@Autowired
	private UserActivityRepository ref;
	
	@Test
	void test() 
	{
		try
		{
			UserActivity userActivityEntity = new UserActivity();
			
			userActivityEntity.setUser("mock_user");
			userActivityEntity.setFileDestination("destination_url");
			userActivityEntity.setCsvInputFile(new byte [100]);
			userActivityEntity.setPdfOutputFile(new byte [100]);
			
			LocalDateTime  dateTime = LocalDateTime.now();
			userActivityEntity.setCreationDate(LocalDateTime.now());
			
			userActivityEntity =  ref.save(userActivityEntity); //persist.
			userActivityEntity = ref.findById(userActivityEntity.getId()).get(); //retrieve from the Mock Database.
			
			assertThat("test_user").isEqualTo(userActivityEntity.getUser());//same inserted user
			assertThat(userActivityEntity.getCsvInputFile().length).isEqualTo(100);//same file size persisted
			assertThat(userActivityEntity.getPdfOutputFile().length).isEqualTo(100);//same file size persisted.
			assertThat(userActivityEntity.getUser()).isEqualTo("mock_user"); // same user from the Databse.
			assertThat(userActivityEntity.getCsvInputFile().length).isEqualTo(dateTime);// same data insertion time and date.
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
