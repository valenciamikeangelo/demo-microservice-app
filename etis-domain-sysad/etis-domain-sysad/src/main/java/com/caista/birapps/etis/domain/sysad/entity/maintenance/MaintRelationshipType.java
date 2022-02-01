/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:59:55 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.DynamicUpdate;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceRelationshipCategory;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_RELATIONSHIP_TYPE", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"RELATIONSHIP_TYPE_CODE" }, name = "UC_MAIN_RELATIONSHIP_TYPE_01") }, indexes = {
				@Index(columnList = "RELATIONSHIP_TYPE_CODE", name = "IDX_MAIN_RELATIONSHIP_TYPE_01") })
@JsonInclude(Include.NON_NULL)
public class MaintRelationshipType implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "RELTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "RELATIONSHIP_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "RELATIONSHIP_CATEGORY_ID", foreignKey = @ForeignKey(name = "FK_REF_RELATIONSHIP_CATEGORY__MAIN_RELATIONSHIP_TYPE_01"), nullable = false)
	private ReferenceRelationshipCategory category;

	@Column(name = "RELATIONSHIP_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "RELATIONSHIP_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintRelationshipType() {
		super();
	}

	public MaintRelationshipType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public MaintRelationshipType(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintRelationshipType(String id, String code, String description, ReferenceRelationshipCategory category) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.category = new ReferenceRelationshipCategory(category.getId(), category.getCode(),
				category.getDescription());
	}

	public MaintRelationshipType(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintRelationshipType(String id, String code, String description, Date effectiveDate, Date expiryDate,
			String createdBy, Date createdDate, String updatedBy, Date updatedDate,
			ReferenceRelationshipCategory category) {
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
		this.category = category;
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

	public ReferenceRelationshipCategory getCategory() {
		return category;
	}

	public void setCategory(ReferenceRelationshipCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "MaintRelationshipType [id=" + id + ", code=" + code + ", description=" + description
				+ ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", category=" + category + "]";
	}

}
