/*
  * Modified by: mercadk
  * Last updated: Feb 6, 2020 1:23:34 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceForm0500Series;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_0500_FORM_TAX_TYPE", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "FORM0500_SERIES_ID" }, name = "UC_MAIN_0500_FORM_TAX_TYPE_01") }, indexes = {
		@Index(columnList = "FORM0500_SERIES_ID", name = "IDX_MAIN_0500_FORM_TAX_TYPE_01") })
@JsonInclude(Include.NON_NULL)
public class Maint0500FormTaxType implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "FORMTAXTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "MAIN_0500_FORM_TAX_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(31)")
	private String id;

	@OneToOne
	@JoinColumn(name = "FORM0500_SERIES_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_0500_FORM_TAX_TYPE__REF_FORM_0500_SERIES"))
	private ReferenceForm0500Series form0500Series;

	@Cascade(CascadeType.SAVE_UPDATE)
	@ManyToMany
	@JoinTable(name = "MAIN_0500_FORM_SPECIFIC_TAX_TYPE", joinColumns = @JoinColumn(name = "FORM0500_SERIES_ID", referencedColumnName = "FORM0500_SERIES_ID", foreignKey = @ForeignKey(name = "FK_MAIN_0500_FORM_TAX_TYPE__REF_FORM0500_SERIES_01")), inverseJoinColumns = @JoinColumn(name = "TAX_TYPE_ID", referencedColumnName = "TAX_TYPE_ID", foreignKey = @ForeignKey(name = "FK_MAIN_0500_FORM_TAX_TYPE__MAIN_TAX_TYPE_02")), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "TAX_TYPE_ID",
					"FORM0500_SERIES_ID" }, name = "UC_MAIN_0500_FORM_TAX_TYPE_02") })
	private List<MaintTaxType> taxType;

	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	@Column(name = "CREATED_BY", updatable = false, nullable = false, columnDefinition = "VARCHAR2(20)")
	private String createdBy;

	@Column(name = "CREATED_DATE", updatable = false, nullable = false)
	private Date createdDate;

	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
	private String updatedBy;

	@Column(name = "UPDATED_DATE", columnDefinition = "DATE")
	private Date updatedDate;

	public Maint0500FormTaxType() {
		super();
	}

	public Maint0500FormTaxType(ReferenceForm0500Series form, List<MaintTaxType> taxType) {
		super();
		this.form0500Series = form;
		this.taxType = taxType;
	}

	public void setForm0500Series(ReferenceForm0500Series form0500Series) {
		this.form0500Series = form0500Series;
	}

	public ReferenceForm0500Series getForm0500Series() {
		return form0500Series;
	}

	public List<MaintTaxType> getTaxType() {
		return taxType;
	}

	public void setTaxType(List<MaintTaxType> taxType) {
		this.taxType = taxType;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getUpdatedBy() {
		return updatedBy;
	}


	@Override
	public String toString() {
		return "Maint0500FormTaxType = [id =" + id + ", form0500Series=" + form0500Series + ", taxType" + taxType
				+ ", effectiveDate" + effectiveDate + ", expiryDate" + expiryDate + ", createdDate" + createdDate
				+ ", updatedDate" + updatedDate + ", createdBy" + createdBy + ", updatedBy" + updatedBy + "]";
	}

}
