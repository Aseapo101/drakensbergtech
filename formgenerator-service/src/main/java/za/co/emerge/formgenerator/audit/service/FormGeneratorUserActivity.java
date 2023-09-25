package za.co.emerge.formgenerator.audit.service;

import za.co.emerge.formgenerator.audit.pojo.UserActivity;

public interface FormGeneratorUserActivity 
{

	boolean auditLogUserActivity(UserActivity userActivity );
}
