/*
  * Modified by: decinam
  * Last updated: Feb 25, 2019 2:07:54 PM
  */
package com.caista.birapps.etis.domain.trs.atp;

import java.util.*;
import com.caista.birapps.etis.domain.trs.atp.entity.AtpAttachment;
import com.caista.birapps.etis.domain.trs.entity.InvoiceDetail;

/**
 * The Class AtpProcessInstanceObj. *
 */
public class AtpCorrespondenceDetails {

	/** The id. */
	private Long id;

	/** The invoice details. */
	private List<InvoiceDetail> invoiceDetails;

	/** The atp documentary requirements. */
	private List<AtpAttachment> atpDocumentaryRequirements;

	/** The application indicator. */
	private String applicationIndicatorId;

	/** The application indicator description. */
	private String applicationIndicatorDescription;

	/** The application indicator code. */
	private String applicationIndicatorCode;

	/** The application type. */
	private String applicationTypeId;

	/** The application type description. */
	private String applicationTypeDescription;

	/** The atp status. */
	private String atpStatus;

	/** The tin status. */
	private String tinStatus;

	/** The authorization type. */
	private String authorizationTypeId;

	/** The authorization type description. */
	private String authorizationTypeDescription;

	/** The head office tin. */
	private String headOfficeTin;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The middle name. */
	private String middleName;

	/** The registered name. */
	private String registeredName;

	/** The trust name. */
	private String trustName;

	/** The estate name. */
	private String estateName;

	/** The head office registered business address. */
	private String headOfficeRegisteredBusinessAddress;

	/** The branch registered business address. */
	private String branchRegisteredBusinessAddress;

	/** The branch code. */
	private String branchCode;

	/** The trade name. */
	private String tradeName;

	/** The bir registration date. */
	private Date birRegistrationDate;

	/** The primary psic. */
	private String primaryPsic;

	/** The vat registered. */
	private Boolean vatRegistered;

	/** The printer tin. */
	private String printerTin;

	/** The printer name. */
	private String printerName;

	/** The printer accreditation number. */
	private String printerAccreditationNo;

	/** The printer registered address. */
	private String printerRegisteredAddress;

	/** The printer accreditation date. */
	private String printerAccreditationDate;

	/** The signatory. */
	private Long correspondenceSignatoryId;

	/** The correspondence signatory name. */
    private String signatoryFullName;

	/** The user signature id. */
	private Long userSignatureId;

	/** The upload signature id. */
	private Long uploadSignatureId;

	/** The staff primary office id. */
	private Long staffPrimaryOfficeId;

	/** The office id. */
	private Long officeId;

	/** The office description. */
	private String officeDescription;

	/** The office code. */
	private String officeCode;

	/** The outbound correspondence no. */
	private String outboundCorrespondenceNo;

	/** The document locator no. */
	private String documentLocatorNo;

	/** The atp date. */
	private Date atpDate;

	/** The expiry date. */
	private Date expiryDate;

	/** The reason. */
	private String reasonId;

	/** The reason description. */
	private String reasonDescription;

	/** The remarks. */
	private String remarks;

	/** The region office id. */
	private Long regionOfficeId;

	/** The region office id. */
	private String regionOfficeDescription;

	/** The is modified. */
	private Boolean isModified;

	/** The task number. */
	private String taskNumber;

	/** The tin branch code. */
	private String tinBranchCode;

    /** The tin created by. */
    private String createdBy;

	/** The tin created date. */
	private Date createdDate;

	/** The tin created by. */
    private String updatedBy;

	/** The tin created date. */
	private Date updatedDate;

    private String suffixId;

    private String suffixDescription;

	/** The Taxpayer Type Description*/
	private String taxpayerTypeDescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<InvoiceDetail> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(List<InvoiceDetail> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}

	public List<AtpAttachment> getAtpDocumentaryRequirements() {
		return atpDocumentaryRequirements;
	}

	public void setAtpDocumentaryRequirements(List<AtpAttachment> atpDocumentaryRequirements) {
		this.atpDocumentaryRequirements = atpDocumentaryRequirements;
	}

	public String getApplicationIndicatorId() {
		return applicationIndicatorId;
	}

	public void setApplicationIndicatorId(String applicationIndicatorId) {
		this.applicationIndicatorId = applicationIndicatorId;
	}

	public String getApplicationIndicatorDescription() {
		return applicationIndicatorDescription;
	}

	public void setApplicationIndicatorDescription(String applicationIndicatorDescription) {
		this.applicationIndicatorDescription = applicationIndicatorDescription;
	}

	public String getApplicationIndicatorCode() {
		return applicationIndicatorCode;
	}

	public void setApplicationIndicatorCode(String applicationIndicatorCode) {
		this.applicationIndicatorCode = applicationIndicatorCode;
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

	public String getAtpStatus() {
		return atpStatus;
	}

	public void setAtpStatus(String atpStatus) {
		this.atpStatus = atpStatus;
	}

	public String getTinStatus() {
		return tinStatus;
	}

	public void setTinStatus(String tinStatus) {
		this.tinStatus = tinStatus;
	}

	public String getAuthorizationTypeId() {
		return authorizationTypeId;
	}

	public void setAuthorizationTypeId(String authorizationTypeId) {
		this.authorizationTypeId = authorizationTypeId;
	}

	public String getAuthorizationTypeDescription() {
		return authorizationTypeDescription;
	}

	public void setAuthorizationTypeDescription(String authorizationTypeDescription) {
		this.authorizationTypeDescription = authorizationTypeDescription;
	}

	public String getHeadOfficeTin() {
		return headOfficeTin;
	}

	public void setHeadOfficeTin(String headOfficeTin) {
		this.headOfficeTin = headOfficeTin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

  public String getRegisteredName() {
    return registeredName;
  }

	public void setRegisteredName(String registeredName) {
		this.registeredName = registeredName;
	}

	public String getTrustName() {
		return trustName;
	}

	public void setTrustName(String trustName) {
		this.trustName = trustName;
	}

	public String getEstateName() {
		return estateName;
	}

	public void setEstateName(String estateName) {
		this.estateName = estateName;
	}

	public String getHeadOfficeRegisteredBusinessAddress() {
		return headOfficeRegisteredBusinessAddress;
	}

	public void setHeadOfficeRegisteredBusinessAddress(String headOfficeRegisteredBusinessAddress) {
		this.headOfficeRegisteredBusinessAddress = headOfficeRegisteredBusinessAddress;
	}

	public String getBranchRegisteredBusinessAddress() {
		return branchRegisteredBusinessAddress;
	}

	public void setBranchRegisteredBusinessAddress(String branchRegisteredBusinessAddress) {
		this.branchRegisteredBusinessAddress = branchRegisteredBusinessAddress;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
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

	public String getPrimaryPsic() {
		return primaryPsic;
	}

	public void setPrimaryPsic(String primaryPsic) {
		this.primaryPsic = primaryPsic;
	}

	public Boolean getVatRegistered() {
		return vatRegistered;
	}

	public void setVatRegistered(Boolean vatRegistered) {
		this.vatRegistered = vatRegistered;
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

	public Long getCorrespondenceSignatoryId() {
		return correspondenceSignatoryId;
	}

	public void setCorrespondenceSignatoryId(Long correspondenceSignatoryId) {
		this.correspondenceSignatoryId = correspondenceSignatoryId;
	}

	public String getSignatoryFullName() {
		return signatoryFullName;
	}

	public void setSignatoryFullName(String signatoryFullName) {
		this.signatoryFullName = signatoryFullName;
	}

	public Long getUserSignatureId() {
		return userSignatureId;
	}

	public void setUserSignatureId(Long userSignatureId) {
		this.userSignatureId = userSignatureId;
	}

	public Long getUploadSignatureId() {
		return uploadSignatureId;
	}

	public void setUploadSignatureId(Long uploadSignatureId) {
		this.uploadSignatureId = uploadSignatureId;
	}

	public Long getStaffPrimaryOfficeId() {
		return staffPrimaryOfficeId;
	}

	public void setStaffPrimaryOfficeId(Long staffPrimaryOfficeId) {
		this.staffPrimaryOfficeId = staffPrimaryOfficeId;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public String getOfficeDescription() {
		return officeDescription;
	}

	public void setOfficeDescription(String officeDescription) {
		this.officeDescription = officeDescription;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOutboundCorrespondenceNo() {
		return outboundCorrespondenceNo;
	}

	public void setOutboundCorrespondenceNo(String outboundCorrespondenceNo) {
		this.outboundCorrespondenceNo = outboundCorrespondenceNo;
	}

	public String getDocumentLocatorNo() {
		return documentLocatorNo;
	}

	public void setDocumentLocatorNo(String documentLocatorNo) {
		this.documentLocatorNo = documentLocatorNo;
	}

	public Date getAtpDate() {
		return atpDate;
	}

	public void setAtpDate(Date atpDate) {
		this.atpDate = atpDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getReasonId() {
		return reasonId;
	}

	public void setReasonId(String reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonDescription() {
		return reasonDescription;
	}

	public void setReasonDescription(String reasonDescription) {
		this.reasonDescription = reasonDescription;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Long getRegionOfficeId() {
		return regionOfficeId;
	}

	public void setRegionOfficeId(Long regionOfficeId) {
		this.regionOfficeId = regionOfficeId;
	}

	public String getRegionOfficeDescription() {
		return regionOfficeDescription;
	}

	public void setRegionOfficeDescription(String regionOfficeDescription) {
		this.regionOfficeDescription = regionOfficeDescription;
	}

	public Boolean getIsModified() {
		return isModified;
	}

	public void setIsModified(Boolean isModified) {
		this.isModified = isModified;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public String getTinBranchCode() {
		return headOfficeTin + branchCode;
	}

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

	public String getSuffixId() {
		return suffixId;
	}

	public void setSuffixId(String suffixId) {
		this.suffixId = suffixId;
	}

	public String getSuffixDescription() {
		return suffixDescription;
	}

	public void setSuffixDescription(String suffixDescription) {
		this.suffixDescription = suffixDescription;
	}

	public String getTaxpayerTypeDescription() {
		return taxpayerTypeDescription;
	}

	public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
		this.taxpayerTypeDescription = taxpayerTypeDescription;
	}
}
