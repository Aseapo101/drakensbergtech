package za.co.emerge.formgenerator.audit.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.audit.pojo.UserActivityAuditDetails;
import za.co.emerge.formgenerator.audit.service.FormGeneratorUserActivity;
import za.co.emerge.formgenerator.persistence.UserActivityRepository;
import za.co.emerge.formgenerator.persistence.entity.UserActivity;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
@Service
public class FormGeneratorUserActivityAuditor implements FormGeneratorUserActivity{

	private Logger log = LoggerFactory.getLogger(FormGeneratorUserActivityAuditor.class);
	
	@Autowired
	private UserActivityRepository userActivityRepositoryRef;
	
	@Override
	public void persistUserActivityAuditLogMessage(UserActivityAuditDetails userActivity) 
	{
		
		//TODO: INPUT VALIDATION..
		try
		{
			log.info(" Persisting the user activity audit log message");
			UserActivity userActivityEntity = new UserActivity();
			userActivityEntity.setCreationDate(userActivity.getActivityTime());
			userActivityEntity.setCsvInputFile(userActivity.getCsvFileInput());
			userActivityEntity.setPdfOutputFile(userActivity.getPdfFileOutput());
			userActivityEntity.setFileDestination(userActivity.getFileDestination());
			userActivityEntity.setUser(userActivity.getUsername());
			
			userActivityRepositoryRef.saveAndFlush(userActivityEntity);
			
		}
		catch(Exception e)
		{
			log.error(" Exception while persisting the user activity audit log message",e);
		}
	}
	
}