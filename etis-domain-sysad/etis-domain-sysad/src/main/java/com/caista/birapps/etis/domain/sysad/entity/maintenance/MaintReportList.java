/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 4:00:54 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_REPORT_LIST", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "REPORT_LIST_CODE" }, name = "UC_MAIN_REPORT_LIST_01") }, indexes = {
				@Index(columnList = "REPORT_LIST_CODE", name = "IDX_MAIN_REPORT_LIST_01") })
@JsonInclude(Include.NON_NULL)
public class MaintReportList implements Auditable {

	@Id
	@IrisIdGenerator(name = "REPLIST", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "REPORT_LIST_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "MODULE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_MODULE__MAIN_REPORT_LIST_01"))
	private ReferenceModule module;

	@ManyToOne
	@JoinColumn(name = "REPORT_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_REPORT_TYPE__MAIN_REPORT_LIST_01"))
	private ReferenceReportType reportType;

	@Column(name = "REPORT_LIST_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "REPORT_LIST_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
	private String description;

	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	@Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "VARCHAR2(20)")
	private String createdBy;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;

	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	public MaintReportList() {
		super();
	}

	public MaintReportList(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public ReferenceReportType getReportType() {
		return reportType;
	}

	public void setReportType(ReferenceReportType reportType) {
		this.reportType = reportType;
	}

	@Override
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public ReferenceModule getModule() {
		return module;
	}

	public void setModule(ReferenceModule module) {
		this.module = module;
	}

	@Override
	public String toString() {
		return "MaintReportList [id=" + id + ", code=" + code + ", description=" + description + ", effectiveDate="
				+ effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", module=" + module
				+ ", reportType=" + reportType + "]";
	}

}
