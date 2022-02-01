/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:35:34 PM
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
@Table(name = "MAIN_AUTHORISATION_TYPE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"AUTHORISATION_TYPE_CODE" }, name = "UC_MAIN_AUTHORISATION_TYPE_01") }, indexes = {
				@Index(columnList = "AUTHORISATION_TYPE_CODE", name = "IDX_MAIN_AUTHRSTION_TYPE_01") })
@JsonInclude(Include.NON_NULL)
public class MaintAuthorisationType implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "AUTHT", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "AUTHORISATION_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "APPLICATION_TYPE_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_APPLICATION_TYPE__MAIN_AUTHORISATION_TYPE_01"))
	private MaintApplicationType appType;

	@Column(name = "AUTHORISATION_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "AUTHORISATION_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintAuthorisationType() {
		super();
	}

	public MaintAuthorisationType(String code) {
		super();
		this.code = code;
	}

	public MaintAuthorisationType(String id, String code, String description, MaintApplicationType appType) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.appType = appType;
	}

	public MaintAuthorisationType(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}

	public MaintAuthorisationType(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintAuthorisationType(String id, MaintApplicationType appType, String code, String description,
			Date effectiveDate, Date expiryDate, String createdBy, Date createdDate, String updatedBy,
			Date updatedDate) {
		super();
		this.id = id;
		this.appType = appType;
		this.code = code;
		this.description = description;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
	}

	@Override
	public String getId() {
		return id;
	}

	public MaintApplicationType getAppType() {
		return appType;
	}

	public void setAppType(MaintApplicationType appType) {
		this.appType = appType;
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

	@Override
	public String toString() {
		return "MaintAuthorisationType [id=" + id + ", appType=" + appType + ", code=" + code + ", description="
				+ description + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + "]";
	}

}
