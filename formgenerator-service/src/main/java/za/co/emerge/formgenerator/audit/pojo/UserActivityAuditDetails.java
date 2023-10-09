package za.co.emerge.formgenerator.audit.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 * UserActivityAuditDetails - POJO used to transfer data from the Service and send to Artemis ActiveMQ.
 */
//TODO: USE LOMBOK
public class UserActivityAuditDetails implements Serializable
{

	private String username;
	private byte [] csvFileInput;
	private byte [] pdfFileOutput;
	private String fileDestination;
	private LocalDateTime activityTime;
	
	private static final long serialVersionUID = -3008463479315488279L;
	
	public UserActivityAuditDetails(String username, byte [] csvFileInput, byte [] pdfFileOutput, String fileDestination, LocalDateTime activityTime) 
	{
	
		this.username = username;
		this.csvFileInput = csvFileInput;
		this.pdfFileOutput = pdfFileOutput;
		this.fileDestination = fileDestination;
		this.activityTime = activityTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public byte [] getCsvFileInput() {
		return csvFileInput;
	}

	public void setCsvFileInput(byte [] csvFileInput) {
		this.csvFileInput = csvFileInput;
	}

	public byte [] getPdfFileOutput() {
		return pdfFileOutput;
	}

	public void setPdfFileOutput(byte [] pdfFileOutput) {
		this.pdfFileOutput = pdfFileOutput;
	}

	public String getFileDestination() {
		return fileDestination;
	}

	public void setFileDestination(String fileDestination) {
		this.fileDestination = fileDestination;
	}

	public LocalDateTime getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(LocalDateTime activityTime) {
		this.activityTime = activityTime;
	}

}
