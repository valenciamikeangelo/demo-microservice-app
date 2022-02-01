/*
  * Modified by: obregoj
  * Last updated: Dec 4, 2019 10:39:48 AM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceForm0500Series;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_ADJUSTMENT_CLASSIFICATION", uniqueConstraints = { @UniqueConstraint(columnNames = {
		"ADJUSTMENT_CLASSIFICATION_CODE",
		"FORM0500_SERIES_ID" }, name = "UC_MAIN_ADJUSTMENT_CLASSIFICATION_01") }, indexes = {
				@Index(columnList = "ADJUSTMENT_CLASSIFICATION_CODE", name = "IDX_MAIN_ADJUSTMENT_CLASSIFICATION_01") })
@JsonInclude(Include.NON_NULL)
public class MaintAdjustmentClassification implements Serializable, Auditable {

	private static final long serialVersionUID = 1L;

	@Id
	@IrisIdGenerator(name = "MAC", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS, uniqueProperty = "none")
	@Column(name = "ADJUSTMENT_CLASSIFICATION_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@Column(name = "ADJUSTMENT_CLASSIFICATION_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Column(name = "ADJUSTMENT_CLASSIFICATION_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(500)")
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

	@ManyToOne
	@JoinColumn(name = "FORM0500_SERIES_ID", foreignKey = @ForeignKey(name = "FK_REF_FORM_0500_SERIES__MAIN_ADJUSTMENT_CLASSIFICATION_01"))
	private ReferenceForm0500Series form0500Series;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ADJUSTMENT_CLASSIFICATION_ID", nullable = false, foreignKey = @ForeignKey(name = "FK_MAIN_ADJUSTMENT_CLASSIFICATION__MAIN_ADJUSTMENT_CLASSIFICATION_TYPE_01"))
	private List<MaintAdjustmentClassificationType> adjustmentClassification;

	@Column(name = "SCHEDULE", nullable = false, columnDefinition = "NUMBER(2,0)")
	private int schedule;

	public MaintAdjustmentClassification() {
		super();
	}

	public MaintAdjustmentClassification(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public MaintAdjustmentClassification(String id, String code, String description, String createdBy) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
	}

	public MaintAdjustmentClassification(String id, String code, String description, Date effectiveDate,
			Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
			ReferenceForm0500Series form0500Series, List<MaintAdjustmentClassificationType> adjustmentClassification) {
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
		this.form0500Series = form0500Series;
		this.adjustmentClassification = adjustmentClassification;
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

	public List<MaintAdjustmentClassificationType> getAdjustmentClassification() {
		return adjustmentClassification;
	}

	public void setAdjustmentClassification(List<MaintAdjustmentClassificationType> adjustmentClassification) {
		this.adjustmentClassification = adjustmentClassification;
	}

	public ReferenceForm0500Series getForm0500Series() {
		return form0500Series;
	}

	public void setForm0500Series(ReferenceForm0500Series form0500Series) {
		this.form0500Series = form0500Series;
	}

	public int getSchedule() {
		return schedule;
	}

	public void setSchedule(int schedule) {
		this.schedule = schedule;
	}

	@Override
	public String toString() {
		return "MaintAdjustmentClassification [id=" + id + ", code=" + code + ", description=" + description
				+ ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", form0500Series=" + form0500Series + ", adjustmentClassification=" + adjustmentClassification + "]";
	}

}
