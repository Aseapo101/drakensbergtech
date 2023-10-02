package za.co.emerge.formgenerator.fileparser;


/**
 * @author FRANS MEHLAPE (ASEAPO101)
 * 
 * GeneratedPdfFormFile - POJO to return the Generated PDF details required for downloading the file.
 */
public class GeneratedPdfFormFile 
{

	private String fileName;
	private String downloadUri;
	private int fileSize;
	private String fileType;
	
	public GeneratedPdfFormFile(String fileName, String downloadUri,int fileSize, String fileType)
	{
		this.fileName = fileName;
		this.downloadUri = downloadUri;
		this.fileSize = fileSize;
		this.fileType = fileType;
		
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

	public int getFileSize() {
		return fileSize;
	}

	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	
}
