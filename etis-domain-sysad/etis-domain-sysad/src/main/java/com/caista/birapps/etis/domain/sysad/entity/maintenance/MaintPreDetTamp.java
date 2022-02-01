/*
 * Modified by: tolentf
 * Last updated: Aug 17, 2020 8:24:20 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_PRE_DET_TAMP")
@JsonInclude(Include.NON_NULL)
public class MaintPreDetTamp implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "MAIN_PRE_DET_TAMP_SequenceStyleGenerator")
	@GenericGenerator(name = "MAIN_PRE_DET_TAMP_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_MAIN_PRE_DET_TAMP"),
			@Parameter(name = "optimizer", value = "hilo"), @Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "MAIN_PRE_DET_TAMP_ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "OFFICE_ID", columnDefinition = "NUMBER(19,0)", nullable = true)
	private Long officeId;

	@Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)", nullable = true)
	private String officeCode;

	@Column(name = "TAXPAYER_ID", columnDefinition = "NUMBER(19,0)", nullable = true)
	private Long taxpayerId;

	@Column(name = "TAXPAYER_TIN", columnDefinition = "VARCHAR2(15)", nullable = true)
	private String tin;

	@Column(name = "TAXPAYER_BRANCH_CODE", columnDefinition = "VARCHAR2(5)", nullable = true)
	private String branchCode;

	@Column(name = "TAMP_YEAR", columnDefinition = "NUMBER(4)", nullable = true)
	private Integer tampYear;

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

	public MaintPreDetTamp() {
		super();
	}

	public MaintPreDetTamp(Long officeId, String officeCode, Long taxpayerId, String tin, String branchCode,
			Integer tampYear, String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
		super();
		this.officeId = officeId;
		this.officeCode = officeCode;
		this.taxpayerId = taxpayerId;
		this.tin = tin;
		this.branchCode = branchCode;
		this.tampYear = tampYear;
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

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public Long getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(Long taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public Integer getTampYear() {
		return tampYear;
	}

	public void setTampYear(Integer tampYear) {
		this.tampYear = tampYear;
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
		return "MaintPreDetTamp [officeId=" + officeId + ", officeCode=" + officeCode + ", taxpayerId=" + taxpayerId
				+ ", tin=" + tin + ", branchCode=" + branchCode + ", tampYear=" + tampYear + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
