/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 4:12:08 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TAXPAYER_TYPE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "TAXPAYER_TYPE_CODE" }, name = "UC_MAIN_TAXPAYER_TYPE_01") }, indexes = {
				@Index(columnList = "TAXPAYER_TYPE_CODE", name = "IDX_MAIN_TAXPAYER_TYPE_01") })
@JsonInclude(Include.NON_NULL)
public class MaintTaxpayerType implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "TPTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "TAXPAYER_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "TAXPAYER_CLASSIFICATION_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_REF_TAXPAYER_CLASSIFICATION__MAIN_TAXPAYER_TYPE_01"))
	private ReferenceTaxpayerClassification taxpayerClassification;

	@Column(name = "TAXPAYER_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "TAXPAYER_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(120)")
	private String description;

	@Column(name = "HIERARCHY_LEVEL", nullable = false)
	private Integer hierarchyLevel;

	@Column(name = "IS_BUSINESS", nullable = false)
	private Boolean business;

	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;

	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(20)", updatable = false)
	private String createdBy;

	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	public MaintTaxpayerType() {
		super();
	}

	public MaintTaxpayerType(String code) {
		super();
		this.code = code;
	}

	public MaintTaxpayerType(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintTaxpayerType(String id, String code, String description, Boolean business,
			ReferenceTaxpayerClassification taxpayerClassification) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.business = business;
		this.taxpayerClassification = new ReferenceTaxpayerClassification(taxpayerClassification.getId(),
				taxpayerClassification.getCode(), taxpayerClassification.getDescription());
	}

	@Override
	public String getId() {
		return id;
	}

	public ReferenceTaxpayerClassification getTaxpayerClassification() {
		return taxpayerClassification;
	}

	public void setTaxpayerClassification(ReferenceTaxpayerClassification taxpayerClassification) {
		this.taxpayerClassification = taxpayerClassification;
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

	public Integer getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(Integer hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}

	public Boolean getBusiness() {
		if (null == business) {
			return false;
		}
		return business;
	}

	public void setBusiness(Boolean business) {
		this.business = business;
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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "MaintTaxpayerType [id=" + id + ", taxpayerClassification=" + taxpayerClassification + ", code=" + code
				+ ", description=" + description + ", hierarchyLevel=" + hierarchyLevel + ", business=" + business
				+ ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", updatedDate=" + updatedDate
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + "]";
	}

}
