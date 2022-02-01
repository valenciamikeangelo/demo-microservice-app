/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:15:57 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ContactInformation.
 */
@Entity
@Table(name = "TAXPAYER_CONTACT_INFORMATION")
@Check(constraints = "IS_PREFERRED IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactInformation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_CONTACT_INFORMATION_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_CONTACT_INFORMATION_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_CONTACT_INFORMATION"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_CONTACT_INFORMATION_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "CONTACT_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String contactTypeId;

	@Column(name = "CONTACT_TYPE_CODE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String contactTypeCode;

	@Column(name = "CONTACT_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String contactTypeDescription;

	/** The contact details. */
	@Column(name = "CONTACT_DETAILS", columnDefinition = "VARCHAR2(255 BYTE)")
	private String contactDetails;

	/** The is preferred. */
	@Column(name = "IS_PREFERRED", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean isPreferred;

	@Column(name = "CONTACT_PURPOSE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String contactPurposeId;

	@Column(name = "CONTACT_PURPOSE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String contactPurposeDescription;

	/** The effectivity date. */
	@Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
	private Date effectivityDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;

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

	@Transient
	private String status;

	public String getStatus() {
    if (getCancellationDate() != null || getExpiryDate() != null) {
			if (getCancellationDate() != null) {
				status = (String.valueOf(ContactInformationStatusEnum.CANCELLED));
			} else {
				Date currDate = new Date();
				if (currDate.before(getExpiryDate()) || currDate.equals(getExpiryDate())) {
					status = (String.valueOf(ContactInformationStatusEnum.ACTIVE));
				} else if (currDate.after(getExpiryDate())) {
					status = (String.valueOf(ContactInformationStatusEnum.INACTIVE));
				}
			}
		} else {
			status = (String.valueOf(ContactInformationStatusEnum.ACTIVE));
		}
		return status;
	}

	public ContactInformation() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public ContactInformation(Long id, Long taxpayerId, String contactTypeId, String contactTypeDescription,
			String contactDetails, Boolean isPreferred, String contactPurposeId, String contactPurposeDescription,
			Date effectivityDate, Date expiryDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
			Date createdDate, String updatedBy, Date updatedDate, Date cancellationDate, String status) {
		super();
		this.id = id;
		this.taxpayerId = taxpayerId;
		this.contactTypeId = contactTypeId;
		this.contactTypeDescription = contactTypeDescription;
		this.contactDetails = contactDetails;
		this.isPreferred = isPreferred;
		this.contactPurposeId = contactPurposeId;
		this.contactPurposeDescription = contactPurposeDescription;
		this.effectivityDate = effectivityDate;
		this.expiryDate = expiryDate;
		this.dataSourceCreated = dataSourceCreated;
		this.dataSourceUpdated = dataSourceUpdated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.cancellationDate = cancellationDate;
		this.status = status;
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

	public String getContactTypeId() {
		return contactTypeId;
	}

	public void setContactTypeId(String contactTypeId) {
		this.contactTypeId = contactTypeId;
	}

	public String getContactTypeDescription() {
		return contactTypeDescription;
	}

	public void setContactTypeDescription(String contactTypeDescription) {
		this.contactTypeDescription = contactTypeDescription;
	}

	public String getContactDetails() {
		return contactDetails;
	}

	public void setContactDetails(String contactDetails) {
		this.contactDetails = contactDetails;
	}

	public Boolean getIsPreferred() {
		if (null == this.isPreferred) {
			isPreferred = false;
		    }
		return isPreferred;
	}

	public void setIsPreferred(Boolean isPreferred) {
		this.isPreferred = isPreferred;
		if (null == this.isPreferred) {
			this.isPreferred = false;
		    }
	}

	public String getContactPurposeId() {
		return contactPurposeId;
	}

	public void setContactPurposeId(String contactPurposeId) {
		this.contactPurposeId = contactPurposeId;
	}

	public String getContactPurposeDescription() {
		return contactPurposeDescription;
	}

	public void setContactPurposeDescription(String contactPurposeDescription) {
		this.contactPurposeDescription = contactPurposeDescription;
	}

	public Date getEffectivityDate() {
		return effectivityDate;
	}

	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

  @Override
  public String toString() {
    return "ContactInformation [id=" + id + ", taxpayerId=" + taxpayerId + ", contactTypeId="
        + contactTypeId + ", contactTypeCode=" + contactTypeCode + ", contactTypeDescription="
        + contactTypeDescription + ", contactDetails=" + contactDetails + ", isPreferred="
        + isPreferred + ", contactPurposeId=" + contactPurposeId + ", contactPurposeDescription="
        + contactPurposeDescription + ", effectivityDate=" + effectivityDate + ", expiryDate="
        + expiryDate + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated="
        + dataSourceUpdated + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", cancellationDate="
        + cancellationDate + ", status=" + status + "]";
  }

}
