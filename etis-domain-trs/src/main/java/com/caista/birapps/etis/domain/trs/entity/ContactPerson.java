/*
 * Modified by: romeror
 * Last updated: Dec 9, 2018 12:39:16 AM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.ContactPersonStatusEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.DataSourceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ContactPerson.
 */
@Entity
@Table(name = "TAXPAYER_CONTACT_PERSON")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactPerson implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_CONTACT_PERSON_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_CONTACT_PERSON_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_CONTACT_PERSON"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_CONTACT_PERSON_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "CONTACT_PERSON_ID")
	private Long contactPersonId;

	@Column(name = "CONTACT_PERSON_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
	private String contactPersonName;

	/** The position id. */
	@Column(name = "POSITION_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String positionId;

	/** The position description. */
	@Column(name = "POSITION_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String positionDescription;

	/** The position held since. */
	@Column(name = "POSITION_HELD_SINCE", columnDefinition = "DATE")
	private Date positionHeldSince;

	@Column(name = "START_DATE", columnDefinition = "DATE")
	private Date startDate;

	/** The data source created. */
	@Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceCreated;

	/** The data source updated. */
	@Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
	private String dataSourceUpdated;

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

	/** The cancellation date. */
	@Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
	private Date cancellationDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;
	@Transient
	private String status;

	public ContactPerson() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public ContactPerson(Long taxpayerId, Long contactPersonId, String contactPersonName, String positionId,
			String positionDescription, Date positionHeldSince, Date startDate, String dataSourceCreated,
			String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
			Date expiryDate, Date cancellationDate) {
		super();
		this.taxpayerId = taxpayerId;
		this.contactPersonId = contactPersonId;
		this.contactPersonName = contactPersonName;
		this.positionId = positionId;
		this.positionDescription = positionDescription;
		this.positionHeldSince = positionHeldSince;
		this.dataSourceCreated = dataSourceCreated;
		this.dataSourceUpdated = dataSourceUpdated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.expiryDate = expiryDate;
		this.cancellationDate = cancellationDate;
		this.startDate = startDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public Date getPositionHeldSince() {
		return positionHeldSince;
	}

	public void setPositionHeldSince(Date positionHeldSince) {
		this.positionHeldSince = positionHeldSince;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getDataSourceCreated() {
		return dataSourceCreated;
	}

	public void setDataSourceCreated(String dataSourceCreated) {
		this.dataSourceCreated = dataSourceCreated;
	}

	public String getDataSourceUpdated() {
		return dataSourceUpdated;
	}

	public void setDataSourceUpdated(String dataSourceUpdated) {
		this.dataSourceUpdated = dataSourceUpdated;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public Long getContactPersonId() {
		return contactPersonId;
	}

	public void setContactPersonId(Long contactPersonId) {
		this.contactPersonId = contactPersonId;
	}

	public String getContactPersonName() {
		return contactPersonName;
	}

	public void setContactPersonName(String contactPersonName) {
		this.contactPersonName = contactPersonName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionDescription() {
		return positionDescription;
	}

	public void setPositionDescription(String positionDescription) {
		this.positionDescription = positionDescription;
	}	

	public String getStatus() {		
		if ((this.expiryDate != null) && (new Date().getTime() > this.expiryDate.getTime())) {
			this.status = String.valueOf(ContactPersonStatusEnum.PREVIOUS);
		} else if (this.cancellationDate != null) {
			this.status = String.valueOf(ContactPersonStatusEnum.CANCELLED);
		} else {
			this.status = String.valueOf(ContactPersonStatusEnum.CURRENT);
		}	
		return status;												
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
