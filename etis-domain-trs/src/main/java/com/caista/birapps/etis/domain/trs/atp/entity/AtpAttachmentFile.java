/*
  * Modified by: bongalr
  * Last updated: Nov 27, 2018 1:12:21 PM
  */
package com.caista.birapps.etis.domain.trs.atp.entity;

import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The Class AtpAttachmentFile.
 */
@Entity
@Table(name = "ATP_ATTACHMENT_FILE")
public class AtpAttachmentFile implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "ATP_ATTACHMENT_FILE_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "ATP_ATTACHMENT_FILE_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_ATP_ATTACHMENT_FILE"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "ATP_ATTACHMENT_FILE_ID", unique = true, nullable = false)
	private Long id;

	/** The file name. */
    @Column(name = "ATP_ATTACHMENT_FILE_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
	private String fileName;

	/** The file byte. */
	@Column(name = "ATP_ATTACHMENT_FILE_BYTE")
	@Lob
	private byte[] fileByte;

	/** The file size. */
	@Column(name = "ATP_ATTACHMENT_FILE_SIZE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String fileSize;

	/** The file type. */
	@Column(name = "ATP_ATTACHMENT_FILE_TYPE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String fileType;

	/**
	 * Instantiates a new atp attachment file.
	 */
	public AtpAttachmentFile() {
		super();
	}

	/**
	 * Instantiates a new atp attachment file.
	 *
	 * @param id
	 *            the id
	 * @param fileName
	 *            the file name
	 * @param fileByte
	 *            the file byte
	 * @param fileSize
	 *            the file size
	 * @param fileType
	 *            the file type
	 */
	public AtpAttachmentFile(Long id, String fileName, byte[] fileByte, String fileSize, String fileType) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.fileByte = fileByte;
		this.fileSize = fileSize;
		this.fileType = fileType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileByte() {
		return fileByte;
	}

	public void setFileByte(byte[] fileByte) {
		this.fileByte = fileByte;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
