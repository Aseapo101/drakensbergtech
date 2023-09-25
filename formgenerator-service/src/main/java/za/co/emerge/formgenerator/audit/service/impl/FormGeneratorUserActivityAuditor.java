package za.co.emerge.formgenerator.audit.service.impl;

import org.springframework.stereotype.Service;

import za.co.emerge.formgenerator.audit.pojo.UserActivity;
import za.co.emerge.formgenerator.audit.service.FormGeneratorUserActivity;

@Service
public class FormGeneratorUserActivityAuditor implements FormGeneratorUserActivity{

	@Override
	public boolean auditLogUserActivity(UserActivity userActivity) 
	{
		// TODO Auto-generated method stub
		return false;
	}

}
