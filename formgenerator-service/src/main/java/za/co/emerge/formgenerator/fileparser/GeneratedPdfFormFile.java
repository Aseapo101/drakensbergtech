package za.co.emerge.formgenerator.fileparser;

import java.lang.Comparable;
import java.time.LocalDateTime;
/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * 
 * GeneratedPdfFormFile - POJO to return the Generated PDF details required for downloading the file.
 */
public class GeneratedPdfFormFile implements Comparable<Object>
{

	private String fileName;
	private String downloadUri;
	private int fileId;
	private String fileType;
	private LocalDateTime dateTimeCreated;
	
	public GeneratedPdfFormFile(String fileName, String downloadUri,int fileId, String fileType,LocalDateTime dateTimeCreated)
	{
		this.fileName = fileName;
		this.downloadUri = downloadUri;
		this.fileId = fileId;
		this.fileType = fileType;
		this.dateTimeCreated = dateTimeCreated;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDownloadUri() {
		return downloadUri;
	}

	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public LocalDateTime getDateTimeCreated() {
		return dateTimeCreated;
	}

	public void setDateTimeCreated(LocalDateTime dateTimeCreated) {
		this.dateTimeCreated = dateTimeCreated;
	}

	@Override
	public int compareTo(Object o) 
	{
		if (this.getClass().cast(o).getDateTimeCreated().equals(this.dateTimeCreated))
		{
			return 0;
		}
		else
		{
			return (this.dateTimeCreated.isAfter(this.getClass().cast(o).getDateTimeCreated()))?-1:1; 
		}
	}
}