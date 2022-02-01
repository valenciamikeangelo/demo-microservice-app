/*
 * Modified by: santojo
 * Last updated: Jul 2, 2019 12:20:33 PM
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
@Table(name = "MAIN_PSIC_CLASSIFICATION", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PSIC_CLASSIFICATION_CODE" }, name = "UC_MAIN_PSIC_CLASSIFICATION_01") })
@JsonInclude(Include.NON_NULL)
public class MaintPsicClassification implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "PSICCLASS", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "PSIC_CLASSIFICATION_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@Column(name = "PSIC_CLASSIFICATION_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "PSIC_CLASSIFICATION_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintPsicClassification() {
		super();
	}

	public MaintPsicClassification(String code) {
		super();
		this.code = code;
	}

	public MaintPsicClassification(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MaintPsicClassification [id=" + id + ", code=" + code + ", description=" + description
				+ ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
