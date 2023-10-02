package za.co.emerge.formgenerator.persistence.entity;

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
	private Long ID;
	
	
	@Column(name = "document_name")
	private String documentName;
	
	@Lob
	@Column(name = "document")
	private byte[] document;

	public Long getId() {
		return ID;
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
