/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:16:39 PM
 */
package com.caista.birapps.etis.domain.trs.taguntag.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TagUntagTaxPayerHistory.
 */
@Entity
@Table(name = "TAXPAYER_TAG_UNTAG_HISTORY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TagUntagTaxPayerHistory {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "TAXPAYER_TAG_UNTAG_HISTORY_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "TAXPAYER_TAG_UNTAG_HISTORY_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_TAG_UNTAG_HISTORY"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "TAXPAYER_TAG_UNTAG_HISTORY_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "BUSINESS_STATUS", nullable = false, columnDefinition = "VARCHAR2(100 BYTE)")
	private String businessStatus;

	/** The taxpayer id. */
	@Column(name = "TAXPAYER_ID", nullable = false)
	private Long taxpayerId;

	@Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String reasonId;

	@Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
	private String reasonDescription;

	/** The effectivity date. */
	@Column(name = "EFFECTIVITY_DATE")
	private Date effectivityDate;

	@Column(name = "remarks", columnDefinition = "VARCHAR2(500 BYTE)")
	private String remarks;

	public TagUntagTaxPayerHistory() {
		super();

	}

	public TagUntagTaxPayerHistory(Long id, String businessStatus, Long taxpayerId, String reasonId,
			String reasonDescription, Date effectivityDate, String remarks) {
		super();
		this.id = id;
		this.businessStatus = businessStatus;
		this.taxpayerId = taxpayerId;
		this.reasonId = reasonId;
		this.reasonDescription = reasonDescription;
		this.effectivityDate = effectivityDate;
		this.remarks = remarks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getBusinessStatus() {
		return businessStatus;
	}

	public void setBusinessStatus(String businessStatus) {
		this.businessStatus = businessStatus;
	}

	public Date getEffectivityDate() {
		return effectivityDate;
	}

	public void setEffectivityDate(Date effectivityDate) {
		this.effectivityDate = effectivityDate;
	}

  @Override
  public String toString() {
    return "TagUntagTaxPayerHistory [id=" + id + ", businessStatus=" + businessStatus
        + ", taxpayerId=" + taxpayerId + ", reasonId=" + reasonId + ", reasonDescription="
        + reasonDescription + ", effectivityDate=" + effectivityDate + ", remarks=" + remarks + "]";
  }

}
