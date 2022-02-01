/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:51:31 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.caista.birapps.etis.domain.sysad.reference.annotations.DedicatedLength;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "MAIN_PURPOSE_OF_TIN", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "PURPOSE_OF_TIN_CODE" }, name = "UC_MAIN_PURPOSE_OF_TIN_01") })
@JsonInclude(Include.NON_NULL)
public class MaintPurposeOfTin implements Serializable, Auditable {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The code. */
	@Id
	@Column(name = "PURPOSE_OF_TIN_ID", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String id;

	@DedicatedLength(value = 30, name = "code")
	@Column(name = "PURPOSE_OF_TIN_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
	private String code;

	@Cascade(CascadeType.SAVE_UPDATE)
	@ManyToMany
	@JoinTable(name = "MAIN_PURPOSE_OF_TIN_TAXPAYER_TYPE", joinColumns = @JoinColumn(name = "PURPOSE_OF_TIN_ID", referencedColumnName = "PURPOSE_OF_TIN_ID", foreignKey = @ForeignKey(name = "FK_MAIN_PURPOSE_OF_TIN__MAIN_PURPOSE_OF_TIN_TAXPAYER_TYPE_01")), inverseJoinColumns = @JoinColumn(name = "TAXPAYER_TYPE_ID", referencedColumnName = "TAXPAYER_TYPE_ID", foreignKey = @ForeignKey(name = "FK_REF_TAXPAYER_TYPE__MAIN_PURPOSE_OF_TIN_TAXPAYER_TYPE_01")), uniqueConstraints = {
			@UniqueConstraint(columnNames = { "PURPOSE_OF_TIN_ID",
					"TAXPAYER_TYPE_ID" }, name = "UC_MAIN_PURPOSE_OF_TIN_02") })
	private List<MaintTaxpayerType> taxpayerTypes;

	/** The description. */
	@DedicatedLength(value = 140, name = "description")
	@Column(name = "PURPOSE_OF_TIN_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(140)")
	private String description;

	/** The created by. */
	@Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "VARCHAR(50)")
	private String createdBy;

	/** The date created. */
	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
	private Date createdDate;

	/** The updated by. */
	@Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(50)")
	private String updatedBy;

	/** The date updated. */
	@Column(name = "UPDATED_DATE")
	private Date updatedDate;

	/** The effectivity date. */
	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
	private Date effectiveDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
	private Date expiryDate;

	public MaintPurposeOfTin() {
		super();
	}

	public MaintPurposeOfTin(String code) {
		this.code = code;
	}

	public MaintPurposeOfTin(String id, String code, String description, String createdBy, Date createdDate,
			String updatedBy, Date updatedDate, Date effectiveDate, Date expiryDate) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.updatedBy = updatedBy;
		this.updatedDate = updatedDate;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
	}

	public MaintPurposeOfTin(String id, String code, String description) {
		super();
		this.id = id;
		this.code = code;
		this.description = description;
	}

	public List<MaintTaxpayerType> getTaxpayerTypes() {
		return taxpayerTypes;
	}

	public void setTaxpayerTypes(List<MaintTaxpayerType> taxpayerTypes) {
		this.taxpayerTypes = taxpayerTypes;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReferencePurposeOfTin [id=" + id + ", code=" + code + ", description=" + description + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((createdBy == null) ? 0 : createdBy.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((effectiveDate == null) ? 0 : effectiveDate.hashCode());
		result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((taxpayerTypes == null) ? 0 : taxpayerTypes.hashCode());
		result = prime * result + ((updatedBy == null) ? 0 : updatedBy.hashCode());
		result = prime * result + ((updatedDate == null) ? 0 : updatedDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaintPurposeOfTin other = (MaintPurposeOfTin) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (createdBy == null) {
			if (other.createdBy != null)
				return false;
		} else if (!createdBy.equals(other.createdBy))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (effectiveDate == null) {
			if (other.effectiveDate != null)
				return false;
		} else if (!effectiveDate.equals(other.effectiveDate))
			return false;
		if (expiryDate == null) {
			if (other.expiryDate != null)
				return false;
		} else if (!expiryDate.equals(other.expiryDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (taxpayerTypes == null) {
			if (other.taxpayerTypes != null)
				return false;
		} else if (!taxpayerTypes.equals(other.taxpayerTypes))
			return false;
		if (updatedBy == null) {
			if (other.updatedBy != null)
				return false;
		} else if (!updatedBy.equals(other.updatedBy))
			return false;
		if (updatedDate == null) {
			if (other.updatedDate != null)
				return false;
		} else if (!updatedDate.equals(other.updatedDate))
			return false;
		return true;
	}

}
