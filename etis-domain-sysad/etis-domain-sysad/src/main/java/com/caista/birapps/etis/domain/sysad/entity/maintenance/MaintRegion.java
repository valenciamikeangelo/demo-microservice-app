/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:55:14 PM
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
@Table(name = "MAIN_REGION", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "REGION_CODE" }, name = "UC_MAIN_REGION_01") })
@JsonInclude(Include.NON_NULL)
public class MaintRegion implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "REGION", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "REGION_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "COUNTRY_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_COUNTRY__MAIN_REGION_01"))
	private MaintCountry country;

	@Column(name = "REGION_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "REGION_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintRegion() {
		super();
	}

	public MaintRegion(String code) {
		super();
		this.code = code;
	}

	public MaintRegion(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintRegion(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintRegion(String id, String code, String description, Date effectiveDate, Date expiryDate,
			MaintCountry country, Date updatedDate) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.country = new MaintCountry(country.getId(), country.getCode(), country.getDescription());
		this.updatedDate = updatedDate;
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

	public MaintCountry getCountry() {
		return country;
	}

	public void setCountry(MaintCountry country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "MaintRegion [id=" + id + ", code=" + code + ", description=" + description + ", effectiveDate="
				+ effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", country=" + country
				+ "]";
	}

}
