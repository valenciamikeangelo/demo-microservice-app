package com.caista.birapps.etis.domain.tcs.dto;

public class TcsAuditDto {

	private String refAuditId;

	private String description;

	private String otherDetails;

	private String newValue;

	private String oldValue;

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

	public String getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(String otherDetails) {
		this.otherDetails = otherDetails;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	
}
