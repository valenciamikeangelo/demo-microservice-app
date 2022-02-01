/*
  * Modified by: decinam
  * Last updated: Feb 25, 2019 1:42:14 PM
  */
package com.caista.birapps.etis.domain.trs.atp.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class AtpCorrespondence.
 */
@Entity
@Table(name = "ATP_CORRESPONDENCE", uniqueConstraints = @UniqueConstraint(columnNames = { "TRADE_NAME",
		"OCN" }, name = "UC_ATP_CORRESPONDENCE_01"))
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtpCorrespondence implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "ATP_CORRESPONDENCE_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "ATP_CORRESPONDENCE_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_ATP_CORRESPONDENCE"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "ATP_CORRESPONDENCE_ID", unique = true, nullable = false)
	private Long id;

	/** The invoice details. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ATP_CORRESPONDENCE_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_ATP_INVOICE_DETAIL__ATP_CORRESPONDENCE_01"))
	private List<InvoiceDetail> invoiceDetails;

	/** The atp documentary requirements. */
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "ATP_CORRESPONDENCE_ID", nullable = true, foreignKey = @ForeignKey(name = "FK_ATP_ATTACHMENT__ATP_CORRESPONDENCE_01"))
	private List<AtpAttachment> atpDocumentaryRequirements;

	/** The application indicator id. */
	@Column(name = "APPLICATION_INDICATOR_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String applicationIndicatorId;

	/** The application indicator description. */
	@Column(name = "APPLICATION_INDICATOR_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String applicationIndicatorDescription;

	/** The application type id. */
	@Column(name = "APPLICATION_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String applicationTypeId;

	/** The application type description. */
	@Column(name = "APPLICATION_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String applicationTypeDescription;

	/** The atp status. */
	@Column(name = "ATP_STATUS", columnDefinition = "VARCHAR2(10 BYTE)")
	private String atpStatus;

	/** The authorization type id. */
	@Column(name = "AUTHORIZATION_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
	private String authorizationTypeId;

	/** The authorization type description. */
	@Column(name = "AUTHORIZATION_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String authorizationTypeDescription;

	/** The head office tin. */
	@Column(name = "HEAD_OFFICE_TIN", columnDefinition = "VARCHAR2(9 BYTE)")
	private String headOfficeTin;

	/** The first name. */
	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
	private String firstName;

	/** The last name. */
	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
	private String lastName;

	/** The middle name. */
	@Column(name = "MIDDLE_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
	private String middleName;

	/** The registered name. */
	@Column(name = "REGISTERED_NAME", columnDefinition = "VARCHAR2(150 BYTE)")
	private String registeredName;

	/** The head office registered business address. */
	@Column(name = "HEAD_OFFICE_REGISTERED_BUSINESS_ADDRESS", columnDefinition = "VARCHAR2(200 BYTE)")
	private String headOfficeRegisteredBusinessAddress;

	/** The branch registered business address. */
	@Column(name = "BRANCH_REGISTERED_BUSINESS_ADDRESS", columnDefinition = "VARCHAR2(200 BYTE)")
	private String branchRegisteredBusinessAddress;

	/** The branch code. */
	@Column(name = "BRANCH_CODE", columnDefinition = "VARCHAR2(5 BYTE)")
	private String branchCode;

	/** The trade business name. */
	@Column(name = "TRADE_NAME", columnDefinition = "VARCHAR2(255 BYTE)")
	private String tradeName;

	/** The bir registration date (Registration date of the Trade Name). */
	@Column(name = "BIR_REGISTRATION_DATE", columnDefinition = "DATE")
	private Date birRegistrationDate;

	/** The primary psic. */
	@Column(name = "PRIMARY_PSIC", columnDefinition = "VARCHAR2(200 BYTE)")
	private String primaryPsic;

	/** The vat registered. */
	@Column(name = "VAT_REGISTERED", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean vatRegistered;

	/** The printer tin. */
	@Column(name = "PRINTER_TIN", columnDefinition = "VARCHAR2(20 BYTE)")
	private String printerTin;

	/** The printer name. */
	@Column(name = "PRINTER_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
	private String printerName;

	/** The printer accreditation number. */
	@Column(name = "PRINTER_ACCREDITATION_NUMBER", columnDefinition = "VARCHAR2(50 BYTE)")
	private String printerAccreditationNo;

	/** The printer business address. */
	@Column(name = "PRINTER_REGISTERED_ADDRESS", columnDefinition = "VARCHAR2(200 BYTE)")
	private String printerRegisteredAddress;

	/** The printer accreditation date. */
	@Column(name = "PRINTER_ACCREDITATION_DATE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String printerAccreditationDate;

	/** The signatory id. */
	@Column(name = "CORRESPONDENCE_SIGNATORY_ID", length = 100)
	private Long signatoryId;

	/** The signatory description. */
	@Column(name = "CORRESPONDENCE_SIGNATORY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String signatoryDescription;

	/** The ocn. */
	@Column(name = "OCN", columnDefinition = "VARCHAR2(50 BYTE)")
	private String outboundCorrespondenceNo;

	/** The dln. */
	@Column(name = "DLN", columnDefinition = "VARCHAR2(50 BYTE)")
	private String documentLocatorNo;

	/** The atp date. */
	@Column(name = "ATP_DATE")
	private Date atpDate;

	/** The expiry date. */
	@Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
	private Date expiryDate;

	/** The reason id. */
	@Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String reasonId;

	/** The reason description. */
	@Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
	private String reasonDescription;

	/** The remark. */
	@Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)")
	private String remarks;

	/** The isModified. */
	@Column(name = "IS_MODIFIED", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean isModified;

	/** The signatory full name. */
	@Column(name = "CORRESPONDENCE_SIGNATORY_FULLNAME", columnDefinition = "VARCHAR2(150 BYTE)")
	private String signatoryFullName;
	
	@Column(name = "POSITION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String signatoryPosition;

	/** The office id. */
	@Column(name = "OFFICE_ID", columnDefinition = "NUMBER(19,0)")
	private Long officeId;

	/** The office description. */
	@Column(name = "OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
	private String officeDescription;

	/** The office code. */
	@Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
	private String officeCode;

	/** The region office id. */
	@Column(name = "REGION_OFFICE_ID", columnDefinition = "NUMBER(19,0)")
	private Long regionOfficeId;

	/** The region office description. */
	@Column(name = "REGION_OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
	private String regionOfficeDescription;

	/** The created by. */
	@Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(50 BYTE)", nullable = false)
	private String createdBy;

	/** The trust name. */
	@Column(name = "TRUST_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
	private String trustName;

	/** The estate name. */
	@Column(name = "ESTATE_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
	private String estateName;

    @Column(name = "SUFFIX_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String suffixId;

	@Column(name = "SUFFIX_DESCRIPTION", columnDefinition = "VARCHAR2(50 BYTE)")
	private String suffixDescription;

	/** The created date. */
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
	private Date createdDate;

    /** The updated by. */
    @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
    private String updatedBy;

    /** The updated date. */
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;
    
    @Column(name = "FILE_IMAGE")
    @Lob
    private byte[] fileImage;

	@Transient
	private String transactionNumber;

	@Transient
	private List<ContactInformation> contactInformations;

	@Transient
	private DocumentLocatorNumberHistory documentLocatorNumberHistory;

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

	public Long getSignatoryId() {
		return signatoryId;
	}

	public void setSignatoryId(Long signatoryId) {
		this.signatoryId = signatoryId;
	}

	public String getSignatoryDescription() {
		return signatoryDescription;
	}

	public void setSignatoryDescription(String signatoryDescription) {
		this.signatoryDescription = signatoryDescription;
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

	public Boolean getIsModified() {
		return isModified;
	}

	public void setIsModified(Boolean isModified) {
		this.isModified = isModified;
	}

	public String getSignatoryFullName() {
		return signatoryFullName;
	}

	public void setSignatoryFullName(String signatoryFullName) {
		this.signatoryFullName = signatoryFullName;
	}
	
	public String getSignatoryPosition() {
      return signatoryPosition;
    }
  
    public void setSignatoryPosition(String signatoryPosition) {
      this.signatoryPosition = signatoryPosition;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
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

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public List<ContactInformation> getContactInformations() {
		return contactInformations;
	}

	public void setContactInformations(List<ContactInformation> contactInformations) {
		this.contactInformations = contactInformations;
	}

	public DocumentLocatorNumberHistory getDocumentLocatorNumberHistory() {
		return documentLocatorNumberHistory;
	}

	public void setDocumentLocatorNumberHistory(DocumentLocatorNumberHistory documentLocatorNumberHistory) {
		this.documentLocatorNumberHistory = documentLocatorNumberHistory;
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
    
    public byte[] getFileImage() {
      return fileImage;
    }

    public void setFileImage(byte[] fileImage) {
      this.fileImage = fileImage;
    }

    @Override
    public String toString() {
      return "AtpCorrespondence [id=" + id + ", invoiceDetails=" + invoiceDetails
          + ", atpDocumentaryRequirements=" + atpDocumentaryRequirements
          + ", applicationIndicatorId=" + applicationIndicatorId
          + ", applicationIndicatorDescription=" + applicationIndicatorDescription
          + ", applicationTypeId=" + applicationTypeId + ", applicationTypeDescription="
          + applicationTypeDescription + ", atpStatus=" + atpStatus + ", authorizationTypeId="
          + authorizationTypeId + ", authorizationTypeDescription=" + authorizationTypeDescription
          + ", headOfficeTin=" + headOfficeTin + ", firstName=" + firstName + ", lastName="
          + lastName + ", middleName=" + middleName + ", registeredName=" + registeredName
          + ", headOfficeRegisteredBusinessAddress=" + headOfficeRegisteredBusinessAddress
          + ", branchRegisteredBusinessAddress=" + branchRegisteredBusinessAddress + ", branchCode="
          + branchCode + ", tradeName=" + tradeName + ", birRegistrationDate=" + birRegistrationDate
          + ", primaryPsic=" + primaryPsic + ", vatRegistered=" + vatRegistered + ", printerTin="
          + printerTin + ", printerName=" + printerName + ", printerAccreditationNo="
          + printerAccreditationNo + ", printerRegisteredAddress=" + printerRegisteredAddress
          + ", printerAccreditationDate=" + printerAccreditationDate + ", signatoryId="
          + signatoryId + ", signatoryDescription=" + signatoryDescription
          + ", outboundCorrespondenceNo=" + outboundCorrespondenceNo + ", documentLocatorNo="
          + documentLocatorNo + ", atpDate=" + atpDate + ", expiryDate=" + expiryDate
          + ", reasonId=" + reasonId + ", reasonDescription=" + reasonDescription + ", remarks="
          + remarks + ", isModified=" + isModified + ", signatoryFullName=" + signatoryFullName
          + ", signatoryPosition=" + signatoryPosition + ", officeId=" + officeId
          + ", officeDescription=" + officeDescription + ", officeCode=" + officeCode
          + ", regionOfficeId=" + regionOfficeId + ", regionOfficeDescription="
          + regionOfficeDescription + ", createdBy=" + createdBy + ", trustName=" + trustName
          + ", estateName=" + estateName + ", suffixId=" + suffixId + ", suffixDescription="
          + suffixDescription + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
          + ", updatedDate=" + updatedDate + ", transactionNumber=" + transactionNumber
          + ", contactInformations=" + contactInformations + ", documentLocatorNumberHistory="
          + documentLocatorNumberHistory + "]";
    }
}