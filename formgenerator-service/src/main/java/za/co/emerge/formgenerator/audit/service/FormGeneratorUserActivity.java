package za.co.emerge.formgenerator.audit.service;

import za.co.emerge.formgenerator.audit.pojo.UserActivityAuditDetails;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public interface FormGeneratorUserActivity 
{

	void persistUserActivityAuditLogMessage(UserActivityAuditDetails userActivity);
}
