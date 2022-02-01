/*
  * Modified by: Adzuara
  * Last updated: Nov 15, 2018 11:47:28 AM
  */
package com.caista.birapps.etis.domain.trs.atp;

import java.util.*;

import com.caista.birapps.etis.domain.trs.entity.InvoiceDetail;

/**
 * The Class FindAtpDetails.
 */
public class FindAtpDetails {

	/** The id. */
	private Long id;

	/** The head office tin. */
	private String headOfficeTin;

	/** The branch code. */
	private String branchCode;

	/** The atp status. */
	private String atpStatus;

	/** The outbound correspondence no. */
	private String outboundCorrespondenceNo;

	/** The atp date. */
	private Date atpDate;

	/** The is modified. */
	private Boolean isModified;

	private String applicationIndicatorDescription;

	private String authorizationTypeDescription;
	
	private String applicationTypeId;
	  
	private String applicationTypeDescription;

	private String registeredName;

	private String headOfficeRegisteredBusinessAddress;

	private String tradeName;

	private Date birRegistrationDate;

	private String branchRegisteredBusinessAddress;

	private Boolean vatRegistered;

	private String primaryPsic;

	private List<InvoiceDetail> invoiceDetail;

	private String printerTin;

	private String printerName;

	private String printerAccreditationNo;

	private String printerRegisteredAddress;

	private String printerAccreditationDate;

	private Long officeId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHeadOfficeTin() {
		return headOfficeTin;
	}

	public void setHeadOfficeTin(String headOfficeTin) {
		this.headOfficeTin = headOfficeTin;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getAtpStatus() {
		return atpStatus;
	}

	public void setAtpStatus(String atpStatus) {
		this.atpStatus = atpStatus;
	}

	public String getOutboundCorrespondenceNo() {
		return outboundCorrespondenceNo;
	}

	public void setOutboundCorrespondenceNo(String outboundCorrespondenceNo) {
		this.outboundCorrespondenceNo = outboundCorrespondenceNo;
	}

	public Date getAtpDate() {
		return atpDate;
	}

	public void setAtpDate(Date atpDate) {
		this.atpDate = atpDate;
	}

	public Boolean getIsModified() {
		return isModified;
	}

	public void setIsModified(Boolean isModified) {
		this.isModified = isModified;
	}

	public String getApplicationIndicatorDescription() {
		return applicationIndicatorDescription;
	}

	public void setApplicationIndicatorDescription(String applicationIndicatorDescription) {
		this.applicationIndicatorDescription = applicationIndicatorDescription;
	}

	public String getAuthorizationTypeDescription() {
		return authorizationTypeDescription;
	}

	public void setAuthorizationTypeDescription(String authorizationTypeDescription) {
		this.authorizationTypeDescription = authorizationTypeDescription;
	}

	public String getRegisteredName() {
		return registeredName;
	}

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	public String getHeadOfficeRegisteredBusinessAddress() {
		return headOfficeRegisteredBusinessAddress;
	}

	public void setHeadOfficeRegisteredBusinessAddress(String headOfficeRegisteredBusinessAddress) {
		this.headOfficeRegisteredBusinessAddress = headOfficeRegisteredBusinessAddress;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Date getBirRegistrationDate() {
		return birRegistrationDate;
	}

	public void setBirRegistrationDate(Date birRegistrationDate) {
		this.birRegistrationDate = birRegistrationDate;
	}

	public String getBranchRegisteredBusinessAddress() {
		return branchRegisteredBusinessAddress;
	}

	public void setBranchRegisteredBusinessAddress(String branchRegisteredBusinessAddress) {
		this.branchRegisteredBusinessAddress = branchRegisteredBusinessAddress;
	}

	public Boolean getVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(Boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
	}

	public String getPrimaryPsic() {
		return primaryPsic;
	}

	public void setPrimaryPsic(String primaryPsic) {
		this.primaryPsic = primaryPsic;
	}

	public List<InvoiceDetail> getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(List<InvoiceDetail> invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public String getPrinterTin() {
		return printerTin;
	}

	public void setPrinterTin(String printerTin) {
		this.printerTin = printerTin;
	}

	public String getPrinterName() {
		return printerName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public String getPrinterAccreditationNo() {
		return printerAccreditationNo;
	}

	public void setPrinterAccreditationNo(String printerAccreditationNo) {
		this.printerAccreditationNo = printerAccreditationNo;
	}

	public String getPrinterRegisteredAddress() {
		return printerRegisteredAddress;
	}

	public void setPrinterRegisteredAddress(String printerRegisteredAddress) {
		this.printerRegisteredAddress = printerRegisteredAddress;
	}

	public String getPrinterAccreditationDate() {
		return printerAccreditationDate;
	}

	public void setPrinterAccreditationDate(String printerAccreditationDate) {
		this.printerAccreditationDate = printerAccreditationDate;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getApplicationTypeId() {
		return applicationTypeId;
	}

	public void setApplicationTypeId(String applicationTypeId) {
		this.applicationTypeId = applicationTypeId;
	}

	public String getApplicationTypeDescription() {
		return applicationTypeDescription;
	}

	public void setApplicationTypeDescription(String applicationTypeDescription) {
		this.applicationTypeDescription = applicationTypeDescription;
	}
	
	

}
