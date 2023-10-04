package za.co.emerge.formgenerator.audit.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


/**
 * @author FRANS MEHLAPE (ASEAOPO101)
 * FormGeneratorUserActivityMessageListener - Component as an Event-Handler for the generation of a PDF file by the user.
 */
@Component
public class FormGeneratorUserActivityMessageListener 
{

	private Logger log = LoggerFactory.getLogger(FormGeneratorUserActivityMessageListener.class);
	
	@Autowired
	private FormGeneratorUserActivity formGeneratorUserActivityHandle;
	
	@JmsListener(destination = "${jms.queue.destination}")
	public void auditLogUserActivity(za.co.emerge.formgenerator.audit.pojo.UserActivityAuditDetails userActivity)
	{
		try
		{
			log.info(" Consuming the user activity audit log message");
			formGeneratorUserActivityHandle.persistUserActivityAuditLogMessage(userActivity);
		}
		catch(Exception e)
		{
			log.error(" Exception while consuming the user activity audit log message",e);
		}
	}
}
