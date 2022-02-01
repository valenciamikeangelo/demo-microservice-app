/*
  * Modified by: decinam
  * Last updated: May 7, 2019 5:11:22 PM
  */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaxPayerStatusHistory.
 */
@Entity
@Table(name = "TAXPAYER_STATUS_HISTORY")
@Check(constraints = "TIN_STATUS_CODE IN ('ACTIVE', 'CANCELLED', 'PENDING')")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerStatusHistory implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TP_TAX_TYPE_HISTORY_SequenceStyleGenerator")
	@GenericGenerator(name = "TP_TAX_TYPE_HISTORY_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_STATUS_HISTORY"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_HISTORY_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "TIN_STATUS_CODE", columnDefinition = "VARCHAR2(10 BYTE)", nullable = false)
	private String tinStatus;

	@Column(name = "OLD_TIN_STATUS_CODE", columnDefinition = "VARCHAR2(10 BYTE)")
	private String oldTinStatus;

	@Column(name = "REGISTRATION_DATE", columnDefinition = "DATE")
	private Date registrationDate;

	@Column(name = "DEREGISTRATION_DATE")
	private Date deregistrationDate;

	@Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String reasonId;

	@Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
	private String reasonDescription;

	@Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)", nullable = true)
	private String remarks;

	/** The created by. */
	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
	private String createdBy;

	/** The created date. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

	/** The taxpayer. */
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TAXPAYER_ID", foreignKey = @ForeignKey(name = "FK_TAXPAYER_STATUS_HISTORY__TAXPAYER_01"))
    private TaxPayer taxpayer;

	public TaxPayerStatusHistory() {
		super();
	}

	public TaxPayerStatusHistory(Long id, String tinStatus, String oldTinStatus, Date registrationDate, Date deregistrationDate,
      String reasonId, String reasonDescription, String remarks, String createdBy,
      Date createdDate) {
		super();
		this.id = id;
		this.tinStatus = tinStatus;
		this.oldTinStatus = oldTinStatus;
		this.registrationDate = registrationDate;
		this.deregistrationDate = deregistrationDate;
		this.reasonId = reasonId;
		this.reasonDescription = reasonDescription;
		this.remarks = remarks;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public TaxPayerStatusHistory(TaxPayer taxPayer) {
      super();
      this.tinStatus = taxPayer.getTinStatus();
      this.registrationDate = taxPayer.getCreatedDate();
      this.createdBy = taxPayer.getCreatedBy();
      this.createdDate = taxPayer.getCreatedDate();
      this.taxpayer = taxPayer;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTinStatus() {
		return tinStatus;
	}

	public void setTinStatus(String tinStatus) {
		this.tinStatus = tinStatus;
	}

	public String getOldTinStatus() {
		return oldTinStatus;
	}

	public void setOldTinStatus(String oldTinStatus) {
		this.oldTinStatus = oldTinStatus;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getDeregistrationDate() {
		return deregistrationDate;
	}

	public void setDeregistrationDate(Date deregistrationDate) {
		this.deregistrationDate = deregistrationDate;
	}

	public String getReasonId() {
		return reasonId;
	}

	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public TaxPayer getTaxpayer() {
      return taxpayer;
    }

    public void setTaxpayer(TaxPayer taxpayer) {
      this.taxpayer = taxpayer;
    }

  @Override
  public String toString() {
    return "{id=" + id + ", tinStatus=" + tinStatus + ", oldTinStatus="
        + oldTinStatus + ", registrationDate=" + registrationDate + ", deregistrationDate="
        + deregistrationDate + ", reasonId=" + reasonId + ", reasonDescription=" + reasonDescription
        + ", remarks=" + remarks + ", createdBy=" + createdBy + ", createdDate=" + createdDate + "}";
  }


}
