package com.caista.birapps.etis.domain.auds.dto;

public class AudsAuditDto {
	
	private String refAuditID;
	
	private String description;
	
	private String otherDetails;
	
	private String newValue;
	
	private String oldValue;

	public String getRefAuditID() {
		return refAuditID;
	}

	public void setRefAuditID(String refAuditID) {
		this.refAuditID = refAuditID;
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
