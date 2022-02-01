/*
  * Modified by: santosj
  * Last updated: Nov 26, 2018 1:12:23 PM
  */
package com.caista.birapps.etis.domain.trs.atp.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AtpAttachment.
 */
@Entity
@Table(name = "ATP_ATTACHMENT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtpAttachment implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "ATP_ATTACHMENT_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "ATP_ATTACHMENT_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_ATP_ATTACHMENT"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "ATP_ATTACHMENT_ID", unique = true, nullable = false)
	private Long id;

	/** The date received. */
	@Column(name = "ATP_ATTACHMENT_DATE_RECEIVED", columnDefinition = "DATE")
	private Date dateReceived;

	@Column(name = "ATTACHMENT_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String documentTypeId;

	@Column(name = "ATTACHMENT_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(260 BYTE)")
	private String documentTypeDescription;

	/** The others. */
	@Column(name = "ATP_ATTACHMENT_OTHERS", columnDefinition = "VARCHAR2(100 BYTE)")
	private String others;

	/** The reference. */
	@Column(name = "ATP_ATTACHMENT_REFERENCE", columnDefinition = "VARCHAR2(100 BYTE)")
	private String reference;

	/** The attachment files. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ATP_ATTACHMENT_ID", foreignKey = @ForeignKey(name = "FK_ATP_ATTACHMENT_FILE__ATP_ATTACHMENT_01"))
	private List<AtpAttachmentFile> attachmentFiles;

	/** The remarks. */
	@Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
	private String remarks;

	/** The effectivity date. */
	@Column(name = "START_DATE", columnDefinition = "DATE")
	private Date startDate;

	/** The expiry date. */
	@Column(name = "END_DATE", columnDefinition = "DATE")
	private Date endDate;
	
	/** The created by. */
    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
    private String createdBy;

    /** The created date. */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
    private Date createdDate;

    /** The updated by. */
    @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
    private String updatedBy;

    /** The updated date. */
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;

	public AtpAttachment() {
		super();
	}

	public AtpAttachment(Long id, Date dateReceived, String documentTypeId,
			String documentTypeDescription, String others, String reference, List<AtpAttachmentFile> attachmentFiles,
			String remarks, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.dateReceived = dateReceived;
		this.documentTypeId = documentTypeId;
		this.documentTypeDescription = documentTypeDescription;
		this.others = others;
		this.reference = reference;
		this.attachmentFiles = attachmentFiles;
		this.remarks = remarks;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public String getDocumentTypeId() {
		return documentTypeId;
	}

	public void setDocumentTypeId(String documentTypeId) {
		this.documentTypeId = documentTypeId;
	}

	public String getDocumentTypeDescription() {
		return documentTypeDescription;
	}

	public void setDocumentTypeDescription(String documentTypeDescription) {
		this.documentTypeDescription = documentTypeDescription;
	}

	public String getOthers() {
		return others;
	}

	public void setOthers(String others) {
		this.others = others;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<AtpAttachmentFile> getAttachmentFiles() {
		return attachmentFiles;
	}

	public void setAttachmentFiles(List<AtpAttachmentFile> attachmentFiles) {
		this.attachmentFiles = attachmentFiles;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

    public String getCreatedBy() {
      return createdBy;
    }
  
    public void setCreatedBy(String createdBy) {
      this.createdBy = createdBy;
    }
  
    public Date getCreatedDate() {
      return createdDate;
    }
  
    public void setCreatedDate(Date createdDate) {
      this.createdDate = createdDate;
    }
  
    public String getUpdatedBy() {
      return updatedBy;
    }
  
    public void setUpdatedBy(String updatedBy) {
      this.updatedBy = updatedBy;
    }
  
    public Date getUpdatedDate() {
      return updatedDate;
    }
  
    public void setUpdatedDate(Date updatedDate) {
      this.updatedDate = updatedDate;
    }

}
