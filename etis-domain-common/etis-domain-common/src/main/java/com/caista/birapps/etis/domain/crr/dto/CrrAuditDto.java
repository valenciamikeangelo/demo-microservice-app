/*
  * Modified by: santosj
  * Last updated: Sep 4, 2019 2:26:06 PM
  */
package com.caista.birapps.etis.domain.crr.dto;

public class CrrAuditDto {

	private String refAuditId;

	private String description;

	private String otherDetails;

	private String newValue;

	private String oldValue;

	/**
	 * @return the refAuditId
	 */
	public String getRefAuditId() {
		return refAuditId;
	}

	/**
	 * @param refAuditId
	 *            the refAuditId to set
	 */
	public void setRefAuditId(String refAuditId) {
		this.refAuditId = refAuditId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the otherDetails
	 */
	public String getOtherDetails() {
		return otherDetails;
	}

	/**
	 * @param otherDetails
	 *            the otherDetails to set
	 */
	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	/**
	 * @return the newValue
	 */
	public String getNewValue() {
		return newValue;
	}

	/**
	 * @param newValue
	 *            the newValue to set
	 */
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	/**
	 * @return the oldValue
	 */
	public String getOldValue() {
		return oldValue;
	}

	/**
	 * @param oldValue
	 *            the oldValue to set
	 */
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

}
