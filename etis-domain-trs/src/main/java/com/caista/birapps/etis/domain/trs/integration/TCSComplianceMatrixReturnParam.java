/*
  * Modified by: romerov
  * Last updated: 05 7, 20 4:22:10 PM
*/

package com.caista.birapps.etis.domain.trs.integration;

import java.util.Date;

public class TCSComplianceMatrixReturnParam {

	private String createdBy;
	private String taxTypeId;
	private String taxTypeCode;
	private String taxTypeDescription;
	private String formTypeId;
	private String formTypeCode;
	private String formTypeDescription;
	private String status;
	private String filingFrequency;
	private Long complianceMatrixId;
	private Date legislativeDueDate;
	private Date returnPeriod;
	private Date systemRunDate;
	private Date filingDate;
	private String updateReason;
	private Date givenDate;

	public TCSComplianceMatrixReturnParam() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TCSComplianceMatrixReturnParam(String createdBy, String taxTypeId, String taxTypeCode,
			String taxTypeDescription, String formTypeId, String formTypeCode, String formTypeDescription,
			String status, String filingFrequency, Long complianceMatrixId, Date legislativeDueDate, Date returnPeriod,
			Date systemRunDate, Date filingDate, String updateReason, Date givenDate) {
		super();
		this.createdBy = createdBy;
		this.taxTypeId = taxTypeId;
		this.taxTypeCode = taxTypeCode;
		this.taxTypeDescription = taxTypeDescription;
		this.formTypeId = formTypeId;
		this.formTypeCode = formTypeCode;
		this.formTypeDescription = formTypeDescription;
		this.status = status;
		this.filingFrequency = filingFrequency;
		this.complianceMatrixId = complianceMatrixId;
		this.legislativeDueDate = legislativeDueDate;
		this.returnPeriod = returnPeriod;
		this.systemRunDate = systemRunDate;
		this.filingDate = filingDate;
		this.updateReason = updateReason;
		this.givenDate = givenDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(String taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public String getTaxTypeCode() {
		return taxTypeCode;
	}

	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}

	public String getTaxTypeDescription() {
		return taxTypeDescription;
	}

	public void setTaxTypeDescription(String taxTypeDescription) {
		this.taxTypeDescription = taxTypeDescription;
	}

	public String getFormTypeId() {
		return formTypeId;
	}

	public void setFormTypeId(String formTypeId) {
		this.formTypeId = formTypeId;
	}

	public String getFormTypeCode() {
		return formTypeCode;
	}

	public void setFormTypeCode(String formTypeCode) {
		this.formTypeCode = formTypeCode;
	}

	public String getFormTypeDescription() {
		return formTypeDescription;
	}

	public void setFormTypeDescription(String formTypeDescription) {
		this.formTypeDescription = formTypeDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFilingFrequency() {
		return filingFrequency;
	}

	public void setFilingFrequency(String filingFrequency) {
		this.filingFrequency = filingFrequency;
	}

	public Long getComplianceMatrixId() {
		return complianceMatrixId;
	}

	public void setComplianceMatrixId(Long complianceMatrixId) {
		this.complianceMatrixId = complianceMatrixId;
	}

	public Date getLegislativeDueDate() {
		return legislativeDueDate;
	}

	public void setLegislativeDueDate(Date legislativeDueDate) {
		this.legislativeDueDate = legislativeDueDate;
	}

	public Date getReturnPeriod() {
		return returnPeriod;
	}

	public void setReturnPeriod(Date returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	public Date getFilingDate() {
		return filingDate;
	}

	public void setFilingDate(Date filingDate) {
		this.filingDate = filingDate;
	}

	public String getUpdateReason() {
		return updateReason;
	}

	public void setUpdateReason(String updateReason) {
		this.updateReason = updateReason;
	}

	public Date getGivenDate() {
		return givenDate;
	}

	public void setGivenDate(Date givenDate) {
		this.givenDate = givenDate;
	}

	public Date getSystemRunDate() {
		return systemRunDate;
	}

	public void setSystemRunDate(Date systemRunDate) {
		this.systemRunDate = systemRunDate;
	}

}
