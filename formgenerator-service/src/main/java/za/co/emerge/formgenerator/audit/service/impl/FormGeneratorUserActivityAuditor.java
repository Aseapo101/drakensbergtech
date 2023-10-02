package za.co.emerge.formgenerator.audit.service.impl;

import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.audit.pojo.UserActivity;
import za.co.emerge.formgenerator.audit.service.FormGeneratorUserActivity;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
@Service
public class FormGeneratorUserActivityAuditor implements FormGeneratorUserActivity{

	@Override
	public boolean auditLogUserActivity(UserActivity userActivity) 
	{
		return false;
	}

}
