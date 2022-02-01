/*
 * Modified by: tolentf
 * Last updated: Aug 17, 2020 8:22:57 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TAMP_LIST_NO")
@JsonInclude(Include.NON_NULL)
public class MaintTampListNo implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MAIN_TAMP_LIST_NO_SequenceStyleGenerator")
	@GenericGenerator(name = "MAIN_TAMP_LIST_NO_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_MAIN_TAMP_LIST_NO"),
			@Parameter(name = "optimizer", value = "hilo"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "MAIN_TAMP_LIST_NO_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "OFFICE_ID", columnDefinition = "NUMBER(19,0)", nullable = true)
	private Long officeId;

	@Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)", nullable = true)
	private Long officeCode;

	@Column(name = "TAMP_LIST_GEN_NO", columnDefinition = "NUMBER(1)", nullable = true)
	private Integer tampListGenNo;

	@Column(name = "FIXED_NUMBER_IND", columnDefinition = "NUMBER(1)", nullable = true)
	private Integer fixedNumberInd;

	@Column(name = "PERCENT_INCREASE_IND", columnDefinition = "NUMBER(1)", nullable = true)
	private Integer percentIncreaseInd;

	@Column(name = "PERCENT_INCREASE", columnDefinition = "NUMBER(5,2)", nullable = true)
	private Integer percentIncrease;

	@Column(name = "ALL_TP_IND", columnDefinition = "NUMBER(1)", nullable = true)
	private Integer allTpInd;

	@Column(name = "EFFECTIVE_DATE", nullable = true)
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = true)
	private Date expiryDate;

	@Column(name = "CREATED_BY", nullable = false)
	private String createdBy;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public MaintTampListNo() {
		super();
	}

	public MaintTampListNo(Long officeId, Long officeCode, Integer tampListGenNo, Integer fixedNumberInd,
			Integer percentIncreaseInd, Integer percentIncrease, Integer allTpInd, Date effectiveDate, Date expiryDate,
			String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
		super();
		this.officeId = officeId;
		this.officeCode = officeCode;
		this.tampListGenNo = tampListGenNo;
		this.fixedNumberInd = fixedNumberInd;
		this.percentIncreaseInd = percentIncreaseInd;
		this.percentIncrease = percentIncrease;
		this.allTpInd = allTpInd;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Override
	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public String getId() {
		return null;
	}

	@Override
	public String getUpdatedBy() {
		return updatedBy;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public Long getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(Long officeCode) {
		this.officeCode = officeCode;
	}

	public Integer getTampListGenNo() {
		return tampListGenNo;
	}

	public void setTampListGenNo(Integer tampListGenNo) {
		this.tampListGenNo = tampListGenNo;
	}

	public Integer getFixedNumberInd() {
		return fixedNumberInd;
	}

	public void setFixedNumberInd(Integer fixedNumberInd) {
		this.fixedNumberInd = fixedNumberInd;
	}

	public Integer getPercentIncreaseInd() {
		return percentIncreaseInd;
	}

	public void setPercentIncreaseInd(Integer percentIncreaseInd) {
		this.percentIncreaseInd = percentIncreaseInd;
	}

	public Integer getPercentIncrease() {
		return percentIncrease;
	}

	public void setPercentIncrease(Integer percentIncrease) {
		this.percentIncrease = percentIncrease;
	}

	public Integer getAllTpInd() {
		return allTpInd;
	}

	public void setAllTpInd(Integer allTpInd) {
		this.allTpInd = allTpInd;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "MaintTampListNo [officeId=" + officeId + ", officeCode=" + officeCode + ", tampListGenNo="
				+ tampListGenNo + ", fixedNumberInd=" + fixedNumberInd + ", percentIncreaseInd=" + percentIncreaseInd
				+ ", percentIncrease=" + percentIncrease + ", allTpInd=" + allTpInd + ", effectiveDate=" + effectiveDate
				+ ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
