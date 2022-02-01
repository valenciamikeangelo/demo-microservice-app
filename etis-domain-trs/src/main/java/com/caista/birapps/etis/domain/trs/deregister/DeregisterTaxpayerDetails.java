/*
 * Modified by: santojo
 * Last updated: Aug 16, 2019 3:25:29 PM
 */

package com.caista.birapps.etis.domain.trs.deregister;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import com.caista.birapps.etis.domain.trs.entity.*;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;


/**
 * The Class DeregisterTaxpayerDetails.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeregisterTaxpayerDetails {

  /** The transaction id. */
  private String transactionId;

  /** The taxpayer id. */
  private Long taxpayerId;

  /** The tin. */
  private String tin;

  /** The branch code. */
  private String branchCode;

  /** The registered name. */
  private String registeredName;

  /** The first name. */
  private String firstName;

  /** The last name. */
  private String lastName;

  /** The middle name. */
  private String middleName;

  private String estateName;

  private String trustName;

  private Date submissionDate;

  private String suffixId;

  private String suffixDescription;

  /** The date of registration. */
  private Date dateOfRegistration;

  /** The status. */
  private String status;

  /** The tin status. */
  private TinStatusEnum tinStatus;

  /** The business status. */
  private BusinessStatusEnum businessStatus;

  private Date tinIssuanceDate;

  private boolean taxpayerTypeIsBusiness;

  private String officeCode;

  private Long officeId;

  private String officeDescription;

  private String taxpayerTypeId;

  private String taxpayerTypeDescription;

  private Boolean taxpayerTypeBusiness;

  private String taxpayerClassificationId;

  private String taxpayerClassificationDescription;

  /** The document locator number. */
  private String documentLocatorNumber;

  /** The date of deregistration. */
  private Date dateOfDeregistration;

  private String reasonId;

  private String reasonDescription;

  /** The remarks. */
  private String remarks;

  /** The tp tax types. */
  private List<TaxPayerTaxType> tpTaxTypes;

  /** The dereg tax type. */
  private List<TaxPayerTaxType> deregTaxType;

  /** The dereg form type. */
  private List<TaxPayerFormType> deregFormType;

  /** The is dereg T pand all taxes. */
  private Boolean isDeregTPandAllTaxes;

  /** The type. */
  private String type;

  /** The registration type. */
  private String registrationType;

  /** The business summary details. */
  private List<BusinessSummaryDetail> businessSummaryDetails;

  /** The contact information. */
  private List<ContactInformation> contactInformations;

  private Date deregistrationDate;

  private Date registrationDate;

  /**
   * Instantiates a new deregister taxpayer details.
   */
  public DeregisterTaxpayerDetails() {
    super();
  }

  /**
   * Instantiates a new deregister taxpayer details.
   *
   * @param transactionId the transaction id
   * @param taxpayerId the taxpayer id
   * @param tin the tin
   * @param branchCode the branch code
   * @param registeredName the registered name
   * @param firstName the first name
   * @param lastName the last name
   * @param middleName the middle name
   * @param estateName the estate name
   * @param trustName the trust name
   * @param submissionDate the submission date
   * @param suffixId the suffix id
   * @param suffixDescription the suffix description
   * @param dateOfRegistration the date of registration
   * @param status the status
   * @param tinStatus the tin status
   * @param businessStatus the business status
   * @param tinIssuanceDate the tin issuance date
   * @param taxpayerTypeIsBusiness the taxpayer type is business
   * @param officeCode the office code
   * @param officeId the office id
   * @param officeDescription the office description
   * @param taxpayerTypeId the taxpayer type id
   * @param taxpayerTypeDescription the taxpayer type description
   * @param taxpayerTypeBusiness the taxpayer type business
   * @param taxpayerClassificationId the taxpayer classification id
   * @param taxpayerClassificationDescription the taxpayer classification description
   * @param documentLocatorNumber the document locator number
   * @param dateOfDeregistration the date of deregistration
   * @param reasonId the reason id
   * @param reasonDescription the reason description
   * @param remarks the remarks
   * @param tpTaxTypes the tp tax types
   * @param deregTaxType the dereg tax type
   * @param deregFormType the dereg form type
   * @param isDeregTPandAllTaxes the is dereg T pand all taxes
   * @param type the type
   * @param registrationType the registration type
   * @param tradeNames the trade names
   * @param businessSummaryDetails the business summary details
   * @param contactInformations the contact informations
   */
  public DeregisterTaxpayerDetails(String transactionId, Long taxpayerId, String tin,
      String branchCode, String registeredName, String firstName, String lastName,
      String middleName, String estateName, String trustName, Date submissionDate, String suffixId,
      String suffixDescription, Date dateOfRegistration, String status, TinStatusEnum tinStatus,
      BusinessStatusEnum businessStatus, Date tinIssuanceDate, boolean taxpayerTypeIsBusiness,
      String officeCode, Long officeId, String officeDescription, String taxpayerTypeId,
      String taxpayerTypeDescription, Boolean taxpayerTypeBusiness, String taxpayerClassificationId,
      String taxpayerClassificationDescription, String documentLocatorNumber,
      Date dateOfDeregistration, String reasonId, String reasonDescription, String remarks,
      List<TaxPayerTaxType> tpTaxTypes, List<TaxPayerTaxType> deregTaxType,
      List<TaxPayerFormType> deregFormType, Boolean isDeregTPandAllTaxes, String type,
      String registrationType, String tradeNames,
      List<BusinessSummaryDetail> businessSummaryDetails,
      List<ContactInformation> contactInformations, Date deregistrationDate,
      Date registrationDate) {
    super();
    this.transactionId = transactionId;
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.registeredName = registeredName;
    this.firstName = firstName;
    this.lastName = lastName;
    this.middleName = middleName;
    this.estateName = estateName;
    this.trustName = trustName;
    this.submissionDate = submissionDate;
    this.suffixId = suffixId;
    this.suffixDescription = suffixDescription;
    this.dateOfRegistration = dateOfRegistration;
    this.status = status;
    this.tinStatus = tinStatus;
    this.businessStatus = businessStatus;
    this.tinIssuanceDate = tinIssuanceDate;
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
    this.officeCode = officeCode;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.taxpayerTypeId = taxpayerTypeId;
    this.taxpayerTypeDescription = taxpayerTypeDescription;
    this.taxpayerTypeBusiness = taxpayerTypeBusiness;
    this.taxpayerClassificationId = taxpayerClassificationId;
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
    this.documentLocatorNumber = documentLocatorNumber;
    this.dateOfDeregistration = dateOfDeregistration;
    this.reasonId = reasonId;
    this.reasonDescription = reasonDescription;
    this.remarks = remarks;
    this.tpTaxTypes = tpTaxTypes;
    this.deregTaxType = deregTaxType;
    this.deregFormType = deregFormType;
    this.isDeregTPandAllTaxes = isDeregTPandAllTaxes;
    this.type = type;
    this.registrationType = registrationType;
    this.businessSummaryDetails = businessSummaryDetails;
    this.contactInformations = contactInformations;
    this.deregistrationDate = deregistrationDate;
    this.registrationDate = registrationDate;
  }



  public Boolean getTaxpayerTypeBusiness() {
    return taxpayerTypeBusiness;
  }

  public void setTaxpayerTypeBusiness(Boolean taxpayerTypeBusiness) {
    this.taxpayerTypeBusiness = taxpayerTypeBusiness;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
    this.registrationType = registrationType;
  }

  public String getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(String transactionId) {
    this.transactionId = transactionId;
  }

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
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

  public Date getDateOfRegistration() {
    return dateOfRegistration;
  }

  public void setDateOfRegistration(Date dateOfRegistration) {
    this.dateOfRegistration = dateOfRegistration;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public TinStatusEnum getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(TinStatusEnum tinStatus) {
    this.tinStatus = tinStatus;
  }

  public BusinessStatusEnum getBusinessStatus() {
    return businessStatus;
  }

  public void setBusinessStatus(BusinessStatusEnum businessStatus) {
    this.businessStatus = businessStatus;
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

  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }

  public String getTaxpayerClassificationId() {
    return taxpayerClassificationId;
  }

  public void setTaxpayerClassificationId(String taxpayerClassificationId) {
    this.taxpayerClassificationId = taxpayerClassificationId;
  }

  public String getTaxpayerClassificationDescription() {
    return taxpayerClassificationDescription;
  }

  public void setTaxpayerClassificationDescription(String taxpayerClassificationDescription) {
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
  }

  public Date getDateOfDeregistration() {
    return dateOfDeregistration;
  }

  public void setDateOfDeregistration(Date dateOfDeregistration) {
    this.dateOfDeregistration = dateOfDeregistration;
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

  public List<TaxPayerTaxType> getTpTaxTypes() {
    return tpTaxTypes;
  }

  public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
    this.tpTaxTypes = tpTaxTypes;
  }

  public Boolean getIsDeregTPandAllTaxes() {
    return isDeregTPandAllTaxes;
  }

  public void setIsDeregTPandAllTaxes(Boolean isDeregTPandAllTaxes) {
    this.isDeregTPandAllTaxes = isDeregTPandAllTaxes;
  }

  public List<TaxPayerTaxType> getDeregTaxType() {
    return deregTaxType;
  }

  public void setDeregTaxType(List<TaxPayerTaxType> deregTaxType) {
    this.deregTaxType = deregTaxType;
  }

  public List<TaxPayerFormType> getDeregFormType() {
    return deregFormType;
  }

  public void setDeregFormType(List<TaxPayerFormType> deregFormType) {
    this.deregFormType = deregFormType;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public boolean isTaxpayerTypeIsBusiness() {
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public String getEstateName() {
    return estateName;
  }

  public void setEstateName(String estateName) {
    this.estateName = estateName;
  }

  public String getTrustName() {
    return trustName;
  }

  public void setTrustName(String trustName) {
    this.trustName = trustName;
  }

  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public void setDocumentLocatorNumber(String documentLocatorNumber) {
    this.documentLocatorNumber = documentLocatorNumber;
  }

  public String getTradeNames() {
    List<String> trades = new ArrayList<>();
    if (!CollectionUtils.isEmpty(getBusinessSummaryDetails())) {
      getBusinessSummaryDetails().forEach(business -> {
        if (StringUtils.equalsIgnoreCase(business.getStatus(),
            BusinessStatusEnum.ACTIVE.toString())) {
          trades.add(business.getTradeName());
        }
      });
    }
    return StringUtils.join(trades, ",");
  }

  public List<BusinessSummaryDetail> getBusinessSummaryDetails() {
    return businessSummaryDetails;
  }

  public void setBusinessSummaryDetails(List<BusinessSummaryDetail> businessSummaryDetails) {
    this.businessSummaryDetails = businessSummaryDetails;
  }



  public List<ContactInformation> getContactInformations() {
    return contactInformations;
  }

  public void setContactInformations(List<ContactInformation> contactInformations) {
    this.contactInformations = contactInformations;
  }

  public Date getDeregistrationDate() {
    return deregistrationDate;
  }

  public void setDeregistrationDate(Date deregistrationDate) {
    this.deregistrationDate = deregistrationDate;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  @Override
  public String toString() {
    return new Gson().toJson(this);
  }

}
