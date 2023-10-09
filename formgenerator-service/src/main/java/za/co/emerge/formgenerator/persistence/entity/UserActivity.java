package za.co.emerge.formgenerator.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

//Use Lombok
@Entity
@Table(name ="user_activity")

public class UserActivity 
{
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	private Long Id;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "csv_file-input", nullable = false,updatable = false)
	private byte[] csvInputFile;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(name = "pdf_file-output",nullable = false,updatable = false)
	private byte[] pdfOutputFile;
	
	@Column(name = "user_name", nullable = false,updatable = false)
	private String user;
	
	@Column(name = "creation_date",nullable = false,updatable = false)
	private LocalDateTime creationDate;

	@Column(name = "file_destination", nullable = false,updatable = false)
	private String fileDestination;
	
	public UserActivity() {}
	
	public Long getId() {
		return Id;
	}

	public byte[] getCsvInputFile() {
		return csvInputFile;
	}

	public void setCsvInputFile(byte[] csvInputFile) {
		this.csvInputFile = csvInputFile;
	}

	public byte[] getPdfOutputFile() {
		return pdfOutputFile;
	}

	public void setPdfOutputFile(byte[] pdfOutputFile) {
		this.pdfOutputFile = pdfOutputFile;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getFileDestination() {
		return fileDestination;
	}

	public void setFileDestination(String fileDestination) {
		this.fileDestination = fileDestination;
	}

}
