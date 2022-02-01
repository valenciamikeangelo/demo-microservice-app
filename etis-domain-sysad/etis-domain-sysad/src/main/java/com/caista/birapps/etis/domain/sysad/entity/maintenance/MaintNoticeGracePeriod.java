/*
  * Modified by: mercadk
  * Last updated: Mar 16, 2020 1:02:56 PM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_NOTICE_GRACE_PERIOD",
    uniqueConstraints = {
				@UniqueConstraint(columnNames = { "NOTICE",
						"CASE_TYPE_ID" }, name = "UC_MAIN_NOTICE_GRACE_PERIOD_01") }, indexes = {
								@Index(columnList = "NOTICE_GRACE_PERIOD_ID", name = "IDX_MAIN_NOTICE_GRACE_PERIOD_01") })
@JsonInclude(Include.NON_NULL)
public class MaintNoticeGracePeriod implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "NOTICEPERIOD", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
	@Column(name = "NOTICE_GRACE_PERIOD_ID", unique = true, nullable = false,
			columnDefinition = "VARCHAR2(40)")
	private String id;

	@Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
	private Date effectiveDate;
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
	private Date expiryDate;
	@Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)")
	private String createdBy;
	@Column(name = "CREATED_DATE", updatable = false)
	private Date createdDate;
	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "NOTICE", columnDefinition = "VARCHAR2(20)", nullable = false)
	private String notice;

	@Column(name = "NUMBER_OF_DAYS", columnDefinition = "NUMBER(10,0)", nullable = false)
	private Long noOfDays;

	@Column(name = "GRACE_PERIOD", columnDefinition = "NUMBER(10,0)", nullable = false)
	private Long gracePeriod;

	@OneToOne
	@JoinColumn(name = "CASE_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_CASE_TYPE__MAIN_NOTICE_GRACE_PERIOD_01"))
	private MaintCaseType caseType;

	public MaintNoticeGracePeriod() {
		super();
	}

	public MaintNoticeGracePeriod(String notice, Long noOfDays, Long gracePeriod, MaintCaseType caseType) {
		super();
		this.notice = notice;
		this.noOfDays = noOfDays;
		this.gracePeriod = gracePeriod;
		this.caseType = new MaintCaseType(caseType.getId(), caseType.getCode(), caseType.getDescription());
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
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

	@Override
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

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Long getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(Long noOfDays) {
		this.noOfDays = noOfDays;
	}

	public MaintCaseType getCaseType() {
		return caseType;
	}

	public void setCaseType(MaintCaseType caseType) {
		this.caseType = caseType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(Long gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	@Override
	public String toString() {
		return "MaintNoticeGracePeriod [id=" + id + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", caseType=" + caseType
				+ notice + ", notice=" + noOfDays + ", noOfDays=" + gracePeriod + ", gracePeriod=" + "]";
	}
}
