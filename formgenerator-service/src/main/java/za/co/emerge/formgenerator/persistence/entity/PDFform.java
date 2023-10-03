package za.co.emerge.formgenerator.persistence.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * @author FRANS MEHLAPE (ASEAPO101)
 *	PDFform - ORM entity object mapping pdf-form database table.
 */
@Entity
@Table(name = "pdf_form")
public class PDFform 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	
	@Column(name = "document_name")
	private String documentName;
	
	@Lob
	@Column(name = "document")
	private byte[] document;
	
	@Column(name = "date_time_created")
	private LocalDateTime localDateTime;
	
	
	public LocalDateTime getLocalDateTime() 
	{
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) 
	{
		this.localDateTime = localDateTime;
	}

	public Long getId() {
		return Id;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}
}
