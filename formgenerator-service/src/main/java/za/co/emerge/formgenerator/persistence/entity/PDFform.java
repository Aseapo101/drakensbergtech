package za.co.emerge.formgenerator.persistence.entity;

import java.sql.Blob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "pdf_form")
public class PDFform 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ID;
	
	@Lob
	@Column(name = "document")
	private byte[] document;

	public Long getID() {
		return ID;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}
	
	
}
