package za.co.emerge.formgenerator.audit;

import java.io.InputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.http.auth.BasicUserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import za.co.emerge.formgenerator.audit.pojo.UserActivityAuditDetails;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 * UserActivityAuditProducer - The Component sends a message to the Artemis ActiveMQ message Broker Queue.
 */

@Component
public class UserActivityAuditMessageProducer
{

	private Logger log = LoggerFactory.getLogger(UserActivityAuditMessageProducer.class);
	
	@Autowired
	private JmsTemplate jmsTemplateHanle;
	
	@Value("${jms.queue.destination}")
	private String destinationQueueName;
	
	public void sendActivityAuditMessage(InputStream fileInputStream, byte [] pdfFileOutput,Principal userPrincipal) 
	{
		try
		{
			log.info(" Producer received the user activity audit log message");
			
			
			String userName = Optional.ofNullable(userPrincipal).orElse(new BasicUserPrincipal("default_user")).getName();
			this.sendMessage (new UserActivityAuditDetails(userName, fileInputStream.readAllBytes() ,pdfFileOutput,"default_destination",LocalDateTime.now()));
		}
		catch(Exception e)
		{
			log.error(" System exception while sending user activity audit log message",e);
		}
	}
	
	private void sendMessage(UserActivityAuditDetails userActivity)
	{
		try
		{
			log.info(" Producer sending the user activity audit log message");
			jmsTemplateHanle.convertAndSend(destinationQueueName, userActivity);
		}
		catch(Exception e)
		{
			log.error(" Exception while sending user activity audit log message",e);
		}
	}
}
