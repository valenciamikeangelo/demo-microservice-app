/*
 * Modified by: santojo
 * Last updated: Dec 17, 2019 10:34:34 AM
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
 * The Class TaxPayerSpecialCode.
 */
@Entity
@Table(name = "TAXPAYER_SPECIAL_CODE", uniqueConstraints = @UniqueConstraint(columnNames = { "TAXPAYER_ID",
		"TAXPAYER_SPECIAL_CODE_ID" }, name = "UC_TP_SPECIAL_CODE_01"))
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerSpecialCode implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_SPECIAL_CODE_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_SPECIAL_CODE_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_SPECIAL_CODE"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_SPECIAL_CODE_ID", unique = true, nullable = false)
	private Long id;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID")
	private Long taxpayerId;

	@Column(name = "SPECIAL_CODE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String specialCodeId;

	@Column(name = "SPECIAL_CODE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String specialCodeDescription;

	/** The accreditation no. */
	@Column(name = "ACCREDITATION_NUMBER", columnDefinition = "VARCHAR2(50 BYTE)")
	private String accreditationNo;

	/** The accreditation date. */
	@Column(name = "ACCREDITATION_DATE", columnDefinition = "DATE")
	private Date accreditationDate;

	/** The accreditation expiry date. */
	@Column(name = "ACCREDITATION_EXPIRY_DATE", columnDefinition = "DATE")
	private Date accreditationExpiryDate;

	/** The start date. */
	@Column(name = "START_DATE", columnDefinition = "DATE")
	private Date startDate;

	/** The end date. */
	@Column(name = "END_DATE", columnDefinition = "DATE")
	private Date endDate;

    @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
    private Date cancellationDate;

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

	@Transient
	private String status;

	public TaxPayerSpecialCode() {
		super();
		this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
	}

	public TaxPayerSpecialCode(Long id, Long taxpayerId, String specialCodeId, String specialCodeDescription,
			String description, String accreditationNo, Date accreditationDate, Date accreditationExpiryDate,
			Date startDate, Date endDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, String status, Date cancellationDate) {
		super();
		this.id = id;
		this.taxpayerId = taxpayerId;
		this.specialCodeId = specialCodeId;
		this.specialCodeDescription = specialCodeDescription;
		this.accreditationNo = accreditationNo;
		this.accreditationDate = accreditationDate;
		this.accreditationExpiryDate = accreditationExpiryDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.dataSourceCreated = dataSourceCreated;
		this.dataSourceUpdated = dataSourceUpdated;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.status = status;
    this.cancellationDate = cancellationDate;
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

	public String getSpecialCodeId() {
		return specialCodeId;
	}

	public void setSpecialCodeId(String specialCodeId) {
		this.specialCodeId = specialCodeId;
	}

	public String getSpecialCodeDescription() {
		return specialCodeDescription;
	}

	public void setSpecialCodeDescription(String specialCodeDescription) {
		this.specialCodeDescription = specialCodeDescription;
	}

	public String getAccreditationNo() {
		return accreditationNo;
	}

	public void setAccreditationNo(String accreditationNo) {
		this.accreditationNo = accreditationNo;
	}

	public Date getAccreditationDate() {
		return accreditationDate;
	}

	public void setAccreditationDate(Date accreditationDate) {
		this.accreditationDate = accreditationDate;
	}

	public Date getAccreditationExpiryDate() {
		return accreditationExpiryDate;
	}

	public void setAccreditationExpiryDate(Date accreditationExpiryDate) {
		this.accreditationExpiryDate = accreditationExpiryDate;
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

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
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

	public String getStatus() {
    if (getCancellationDate() == null) {
      if (getEndDate() == null && getAccreditationExpiryDate() == null) {
        this.status = String.valueOf(TaxpayerSpecialCodeStatusEnum.TAGGED);
      } else {
        if ((getEndDate() != null && new Date().getTime() > getEndDate().getTime())
            || (getAccreditationExpiryDate() != null && new Date().getTime() > getAccreditationExpiryDate().getTime())) {
          this.status = String.valueOf(TaxpayerSpecialCodeStatusEnum.UNTAGGED);
        } else {
          this.status = String.valueOf(TaxpayerSpecialCodeStatusEnum.TAGGED);
        }
      }
    } else {
      this.status = String.valueOf(TaxpayerSpecialCodeStatusEnum.CANCELLED);
    }
		return status;
	}

  @Override
  public String toString() {
    return "TaxPayerSpecialCode [id=" + id + ", taxpayerId=" + taxpayerId + ", specialCodeId="
        + specialCodeId + ", specialCodeDescription=" + specialCodeDescription
        + ", accreditationNo=" + accreditationNo + ", accreditationDate=" + accreditationDate
        + ", accreditationExpiryDate=" + accreditationExpiryDate + ", startDate=" + startDate
        + ", endDate=" + endDate + ", cancellationDate=" + cancellationDate + ", dataSourceCreated="
        + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", status=" + status + "]";
  }

}
