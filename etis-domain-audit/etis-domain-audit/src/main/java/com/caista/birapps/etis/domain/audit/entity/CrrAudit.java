/*
  * Modified by: santosj
  * Last updated: Sep 2, 2019 5:46:54 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

/**
 * The Class CrrAudit.
 */
@Entity
@Table(name = "CRR_AUDIT")
public class CrrAudit implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1216063325739471726L;

	/** The ref audit id. */
	@Id
	@Column(name = "REF_AUD_ID")
	private String refAuditId;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NEW_VALUE", nullable = false)
	@Lob
	private byte[] newValue;

	@Column(name = "OLD_VALUE", nullable = true)
	@Lob
	private byte[] oldValue;

	/** The other details. */
	@Column(name = "OTHER_DETAILS", columnDefinition = "VARCHAR2(4000)")
	private String otherDetails;

	/**
	 * Instantiates a new crr audit.
	 */
	public CrrAudit() {
		super();
		this.refAuditId = UUID.randomUUID().toString();
	}

	public String getRefAuditId() {
		return refAuditId;
	}

	public void setRefAuditId(String refAuditId) {
		this.refAuditId = refAuditId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the newValue
	 */
	public byte[] getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue
	 *            the newValue to set
	 */
	public void setNewValue(byte[] newValue) {
		this.newValue = newValue;
	}

	/**
	 * @return the oldValue
	 */
	public byte[] getOldValue() {
		return oldValue;
	}

	/**
	 * @param oldValue
	 *            the oldValue to set
	 */
	public void setOldValue(byte[] oldValue) {
		this.oldValue = oldValue;
	}

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	@Override
	public String toString() {
		return "SystemAdministrationAudit [refAuditId=" + refAuditId + ", description=" + description + ", newValue="
				+ newValue + ", oldValue=" + oldValue + ", otherDetails=" + otherDetails + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((newValue == null) ? 0 : newValue.hashCode());
		result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
		result = prime * result + ((otherDetails == null) ? 0 : otherDetails.hashCode());
		result = prime * result + ((refAuditId == null) ? 0 : refAuditId.hashCode());
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
		CrrAudit other = (CrrAudit) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (newValue == null) {
			if (other.newValue != null)
				return false;
		} else if (!newValue.equals(other.newValue))
			return false;
		if (oldValue == null) {
			if (other.oldValue != null)
				return false;
		} else if (!oldValue.equals(other.oldValue))
			return false;
		if (otherDetails == null) {
			if (other.otherDetails != null)
				return false;
		} else if (!otherDetails.equals(other.otherDetails))
			return false;
		if (refAuditId == null) {
			if (other.refAuditId != null)
				return false;
		} else if (!refAuditId.equals(other.refAuditId))
			return false;
		return true;
	}

}
