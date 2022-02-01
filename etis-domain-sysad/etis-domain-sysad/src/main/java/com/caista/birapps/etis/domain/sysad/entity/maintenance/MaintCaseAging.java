/*
  * Modified by: mercadk
  * Last updated: Mar 23, 2020 10:35:14 AM
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
@Table(name = "MAIN_CASE_AGING",
    uniqueConstraints = {
				@UniqueConstraint(columnNames = { "OFFICE_TYPE",
						"CASE_TYPE_ID", "QUARTER" }, name = "UC_MAIN_CASE_AGING_01") },
    indexes = {@Index(columnList = "CASE_AGING_ID", name = "IDX_MAIN_CASE_AGING_01")})
@JsonInclude(Include.NON_NULL)
public class MaintCaseAging implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "CASEAGING", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
	@Column(name = "CASE_AGING_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
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

	@Column(name = "OFFICE_TYPE", columnDefinition = "VARCHAR2(20)", nullable = false)
	private String tpOffice;

	@Column(name = "QUARTER", columnDefinition = "VARCHAR2(20)")
	private String quarter;

	@Column(name = "YELLOW_WARNING", columnDefinition = "NUMBER(19,0)", nullable = false)
	private Long yellowWarning;

	@Column(name = "RED_WARNING", columnDefinition = "NUMBER(19,0)", nullable = false)
	private Long redWarning;

	@OneToOne
	@JoinColumn(name = "CASE_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_CASE_TYPE__MAIN_CASE_AGING_01"))
	private MaintCaseType caseType;

	public MaintCaseAging() {
		super();
	}

	public MaintCaseAging(String tpOffice, Long yellowWarning, Long redWarning, String quarter,
			MaintCaseType caseType) {
		super();
		this.tpOffice = tpOffice;
		this.yellowWarning = yellowWarning;
		this.redWarning = redWarning;
		this.quarter = quarter;
		this.caseType = new MaintCaseType(caseType.getId(), caseType.getCode(), caseType.getDescription());
	}

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String getId() {
    return id;
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

	public MaintCaseType getCaseType() {
		return caseType;
  }

	public void setCaseType(MaintCaseType caseType) {
		this.caseType = caseType;
	}

	public String getTpOffice() {
		return tpOffice;
	}

	public void setTpOffice(String tpOffice) {
		this.tpOffice = tpOffice;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public Long getYellowWarning() {
		return yellowWarning;
	}

	public void setYellowWarning(Long yellowWarning) {
		this.yellowWarning = yellowWarning;
	}

	public Long getRedWarning() {
		return redWarning;
	}

	public void setRedWarning(Long redWarning) {
		this.redWarning = redWarning;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
    return "MaintCaseAging [id=" + id + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", caseType=" + caseType
				+ ", tpOffice=" + tpOffice + ", quarter=" + quarter + ", yellowWarning=" + yellowWarning
				+ ", redWarning=" + redWarning + "]";
	}
}
