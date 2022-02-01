/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:30:32 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceApplicationIndicator;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_APPLICATION_TYPE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"APPLICATION_TYPE_CODE" }, name = "UC_MAIN_APPLICATION_TYPE_01") }, indexes = {
				@Index(columnList = "APPLICATION_TYPE_CODE", name = "IDX_MAIN_APPLICATION_TYPE_01") })
@JsonInclude(Include.NON_NULL)
public class MaintApplicationType implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "APPT", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "APPLICATION_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "APPLICATION_INDICATOR_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_APPLICATION_INDICATOR__MAIN_APPLICATION_TYPE_01"))
	private ReferenceApplicationIndicator appIndicator;

	@Column(name = "APPLICATION_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "APPLICATION_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintApplicationType() {
		super();
	}

	public MaintApplicationType(String code) {
		super();
		this.code = code;
	}

	public MaintApplicationType(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintApplicationType(String id, String code, String description,
			ReferenceApplicationIndicator appIndicator) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.appIndicator = appIndicator;
	}

	public MaintApplicationType(String id, String code, String description, String createdBy, Date createdDate) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	public MaintApplicationType(String id, String code, String description, Date effectiveDate, Date expiryDate,
			String createdBy, Date createdDate, String updatedBy, Date updatedDate,
			ReferenceApplicationIndicator appIndicator) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.appIndicator = appIndicator;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ReferenceApplicationIndicator getAppIndicator() {
		return appIndicator;
	}

	public void setAppIndicator(ReferenceApplicationIndicator appIndicator) {
		this.appIndicator = appIndicator;
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
		return "MaintApplicationType [id=" + id + ", code=" + code + ", description=" + description + ", effectiveDate="
				+ effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", appIndicator="
				+ appIndicator + "]";
	}
}
