/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:36:23 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_BARANGAY", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "BARANGAY_CODE" }, name = "UC_MAIN_BARANGAY_01") }, indexes = {
				@Index(columnList = "BARANGAY_CODE", name = "IDX_MAIN_BARANGAY_01") })
@JsonInclude(Include.NON_NULL)
public class MaintBarangay implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "BRGY", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "BARANGAY_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@ManyToOne
	@JoinColumn(name = "CITY_MUNICIPALITY_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_CITY_MUNICIPALITY__MAIN_BARANGAY_01"))
	private MaintCityMunicipality municipality;

	@Cascade(CascadeType.SAVE_UPDATE)
	@ManyToMany
	@JoinTable(name = "MAIN_BARANGAY_ZIP_CODE", joinColumns = @JoinColumn(name = "BARANGAY_ID", referencedColumnName = "BARANGAY_ID", foreignKey = @ForeignKey(name = "FK_MAIN_BARANGAY__MAIN_BARANGAY_ZIP_CODE_01")), inverseJoinColumns = @JoinColumn(name = "ZIP_CODE_ID", referencedColumnName = "ZIP_CODE_ID", foreignKey = @ForeignKey(name = "FK_MAIN_ZIP_CODE__MAIN_BARANGAY_ZIP_CODE_01")), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "BARANGAY_ID", "ZIP_CODE_ID" }, name = "UC_MAIN_BARANGAY_ZIP_CODE_01") })
	private List<MaintZipCode> zipCodes;

	@Column(name = "BARANGAY_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "BARANGAY_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
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

	public MaintBarangay() {
		super();
	}

	public MaintBarangay(String code) {
		super();
		this.code = code;
	}

	public MaintBarangay(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintBarangay(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintBarangay(String id, MaintCityMunicipality municipality, String code, String description,
			Date updatedDate) {
		super();
		this.id = id;
		this.municipality = new MaintCityMunicipality(municipality.getId(), municipality.getCode(),
				municipality.getDescription(), municipality.getProvince());
		this.code = code;
		this.description = description;
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

	public MaintCityMunicipality getMunicipality() {
		return municipality;
	}

	public void setMunicipality(MaintCityMunicipality municipality) {
		this.municipality = municipality;
	}

	public List<MaintZipCode> getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(List<MaintZipCode> zipCodes) {
		this.zipCodes = zipCodes;
	}

	@Override
	public String toString() {
		return "MaintBarangay [id=" + id + ", code=" + code + ", description=" + description + ", effectiveDate="
				+ effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", municipality="
				+ municipality + "]";
	}

}
