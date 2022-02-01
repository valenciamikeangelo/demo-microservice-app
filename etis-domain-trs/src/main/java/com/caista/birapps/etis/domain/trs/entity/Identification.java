/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:16:06 PM
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
 * The Class Identification.
 */
@Entity
@Table(name = "TAXPAYER_IDENTIFICATION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Identification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_IDENTIFICATION_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_IDENTIFICATION_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_IDENTIFICATION"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_IDENTIFICATION_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "IDENTIFIER_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String identificationTypeId;

	@Column(name = "IDENTIFIER_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String identificationTypeDescription;

	/** The id number. */
	@Column(name = "IDENTIFICATION_NUMBER", columnDefinition = "VARCHAR2(20 BYTE)")
	private String identificationNumber;

	/** The issuer. */
	@Column(name = "IDENTIFICATION_ISSUER", columnDefinition = "VARCHAR2(50 BYTE)")
	private String identificationIssuer;

	@Column(name = "COUNTRY_OF_ISSUE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String countryOfIssueId;

	@Column(name = "COUNTRY_OF_ISSUE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String countryOfIssueDescription;

	/** The place. */
	@Column(name = "PLACE_OF_ISSUE", columnDefinition = "VARCHAR2(150 BYTE)")
	private String placeOfIssue;

	/** The effectivity date. */
	@Column(name = "IDENTIFICATION_EFFECTIVE_DATE", columnDefinition = "DATE")
	private Date identificationEffectiveDate;

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

	@Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
	private Date cancellationDate;

	@Transient
	private String status;

	public Identification() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public Identification(Long id, Long taxpayerId, String identificationTypeId,
			String identificationTypeDescription, String identificationNumber, String identificationIssuer,
			String countryOfIssueId, String countryOfIssueDescription, String placeOfIssue,
			Date identificationEffectiveDate, Date expiryDate, String dataSourceCreated, String dataSourceUpdated,
			String createdBy, Date createdDate, String updatedBy, Date updatedDate, Date cancellationDate,
			String status) {
		super();
		this.id = id;
		this.taxpayerId = taxpayerId;
		this.identificationTypeId = identificationTypeId;
		this.identificationTypeDescription = identificationTypeDescription;
		this.identificationNumber = identificationNumber;
		this.identificationIssuer = identificationIssuer;
		this.countryOfIssueId = countryOfIssueId;
		this.countryOfIssueDescription = countryOfIssueDescription;
		this.placeOfIssue = placeOfIssue;
		this.identificationEffectiveDate = identificationEffectiveDate;
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

	public String getIdentificationTypeId() {
		return identificationTypeId;
	}

	public void setIdentificationTypeId(String identificationTypeId) {
		this.identificationTypeId = identificationTypeId;
	}

	public String getIdentificationTypeDescription() {
		return identificationTypeDescription;
	}

	public void setIdentificationTypeDescription(String identificationTypeDescription) {
		this.identificationTypeDescription = identificationTypeDescription;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getIdentificationIssuer() {
		return identificationIssuer;
	}

	public void setIdentificationIssuer(String identificationIssuer) {
		this.identificationIssuer = identificationIssuer;
	}

	public String getCountryOfIssueId() {
		return countryOfIssueId;
	}

	public void setCountryOfIssueId(String countryOfIssueId) {
		this.countryOfIssueId = countryOfIssueId;
	}

	public String getCountryOfIssueDescription() {
		return countryOfIssueDescription;
	}

	public void setCountryOfIssueDescription(String countryOfIssueDescription) {
		this.countryOfIssueDescription = countryOfIssueDescription;
	}

	public String getPlaceOfIssue() {
		return placeOfIssue;
	}

	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}

	public Date getIdentificationEffectiveDate() {
		return identificationEffectiveDate;
	}

	public void setIdentificationEffectiveDate(Date identificationEffectiveDate) {
		this.identificationEffectiveDate = identificationEffectiveDate;
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

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCancellationDate() {
		return cancellationDate;
	}

	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}

	public String getStatus() {
		Date currentDate = new Date();
		if (getCancellationDate() != null) {
			status = String.valueOf(IdentificationStatusEnum.CANCELLED);
		} else if(getExpiryDate() != null) {
			if (currentDate.after(getExpiryDate())) {
				status = String.valueOf(IdentificationStatusEnum.CANCELLED);
			} else {
				status = String.valueOf(IdentificationStatusEnum.SUBMITTED);
			}
		} else {
			status = String.valueOf(IdentificationStatusEnum.SUBMITTED);
		}
		return status;
	}

  @Override
  public String toString() {
    return "Identification [id=" + id + ", taxpayerId=" + taxpayerId + ", identificationTypeId="
        + identificationTypeId + ", identificationTypeDescription=" + identificationTypeDescription
        + ", identificationNumber=" + identificationNumber + ", identificationIssuer="
        + identificationIssuer + ", countryOfIssueId=" + countryOfIssueId
        + ", countryOfIssueDescription=" + countryOfIssueDescription + ", placeOfIssue="
        + placeOfIssue + ", identificationEffectiveDate=" + identificationEffectiveDate
        + ", expiryDate=" + expiryDate + ", dataSourceCreated=" + dataSourceCreated
        + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy=" + createdBy + ", createdDate="
        + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
        + ", cancellationDate=" + cancellationDate + ", status=" + status + "]";
  }

}
