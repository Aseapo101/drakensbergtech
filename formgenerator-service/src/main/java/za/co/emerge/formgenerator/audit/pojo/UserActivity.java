package za.co.emerge.formgenerator.audit.pojo;

import java.sql.Timestamp;

/**
 * @author @author FRANS MEHLAPE (ASEAPO101)
 *
 */
public class UserActivity 
{

	private String username;
	private String csvFileInput;
	private String pdfFileOutput;
	private String fileDestination;
	private Timestamp activityTime;
	
	public UserActivity(String username, String csvFileInput, String pdfFileOutput, String fileDestination, Timestamp activityTime) 
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

	public String getCsvFileInput() {
		return csvFileInput;
	}

	public void setCsvFileInput(String csvFileInput) {
		this.csvFileInput = csvFileInput;
	}

	public String getPdfFileOutput() {
		return pdfFileOutput;
	}

	public void setPdfFileOutput(String pdfFileOutput) {
		this.pdfFileOutput = pdfFileOutput;
	}

	public String getFileDestination() {
		return fileDestination;
	}

	public void setFileDestination(String fileDestination) {
		this.fileDestination = fileDestination;
	}

	public Timestamp getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Timestamp activityTime) {
		this.activityTime = activityTime;
	}
	
	
}
