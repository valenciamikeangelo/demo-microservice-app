/*
 * Modified by: fuentem
 * Last updated: Nov 8, 2018 6:44:11 PM
 */
package com.caista.birapps.etis.common.report;

import java.util.Date;

public class CertificateOfRegistrationTradeNameParam {

  private Long businessSummaryDtlId;
  private String tradeName;
  private Date bsdRegistrationDate;
  private Long industryDtlId;
  private String industryClassificationCode;
  private String industryClassificationDesc;
  private String lineOfBusiness;
  private Boolean isPrimary;

  public CertificateOfRegistrationTradeNameParam() {
    super();
  }

  public CertificateOfRegistrationTradeNameParam(Long businessSummaryDtlId, String tradeName,
      Date bsdRegistrationDate, Long industryDtlId, String industryClassificationCode, 
      String industryClassificationDesc, String lineOfBusiness, Boolean isPrimary) {
    super();
    this.businessSummaryDtlId = businessSummaryDtlId;
    this.tradeName = tradeName;
    this.bsdRegistrationDate = bsdRegistrationDate;
    this.industryDtlId = industryDtlId;
    this.industryClassificationCode = industryClassificationCode;
    this.industryClassificationDesc = industryClassificationDesc;
    this.lineOfBusiness = lineOfBusiness;
    this.isPrimary = isPrimary;
  }

  public Long getBusinessSummaryDtlId() {
    return businessSummaryDtlId;
  }

  public void setBusinessSummaryDtlId(Long businessSummaryDtlId) {
    this.businessSummaryDtlId = businessSummaryDtlId;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public Date getBsdRegistrationDate() {
    return bsdRegistrationDate;
  }

  public void setBsdRegistrationDate(Date bsdRegistrationDate) {
    this.bsdRegistrationDate = bsdRegistrationDate;
  }

  public Long getIndustryDtlId() {
    return industryDtlId;
  }

  public void setIndustryDtlId(Long industryDtlId) {
    this.industryDtlId = industryDtlId;
  }
  
  public String getIndustryClassificationCode() {
    return industryClassificationCode;
  }

  public void setIndustryClassificationCode(String industryClassificationCode) {
    this.industryClassificationCode = industryClassificationCode;
  }

  public String getIndustryClassificationDesc() {
    return industryClassificationDesc;
  }

  public void setIndustryClassificationDesc(String industryClassificationDesc) {
    this.industryClassificationDesc = industryClassificationDesc;
  }

  public String getLineOfBusiness() {
    return lineOfBusiness;
  }

  public void setLineOfBusiness(String lineOfBusiness) {
    this.lineOfBusiness = lineOfBusiness;
  }

  public Boolean getIsPrimary() {
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
  }



}
