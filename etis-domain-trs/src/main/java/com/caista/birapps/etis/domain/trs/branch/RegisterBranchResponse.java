/*
  * Modified by: albertv
  * Last updated: Nov 7, 2018 11:03:21 AM
  */
package com.caista.birapps.etis.domain.trs.branch;

import java.util.List;
import com.caista.birapps.etis.domain.trs.ReportsGenerated;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

public class RegisterBranchResponse {

  private static final long serialVersionUID = 1L;

  private String outboundCorrespondenceNumber;
  private String documentLocatorNumber;
  private String transactionNumber;
  private TaxPayer taxpayer;
  private List<ReportsGenerated> reportsGenerated;

  public RegisterBranchResponse() {
    super();
  }

  public RegisterBranchResponse(String documentLocatorNumber,
      String transactionNumber, TaxPayer taxpayer, List<ReportsGenerated> reportsGenerated) {
    super();
    this.documentLocatorNumber = documentLocatorNumber;
    this.transactionNumber = transactionNumber;
    this.taxpayer = taxpayer;
    this.reportsGenerated = reportsGenerated;
  }

  public String getOutboundCorrespondenceNumber() {
    return outboundCorrespondenceNumber;
  }

  public void setOutboundCorrespondenceNumber(String outboundCorrespondenceNumber) {
    this.outboundCorrespondenceNumber = outboundCorrespondenceNumber;
  }

  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public void setDocumentLocatorNumber(String documentLocatorNumber) {
    this.documentLocatorNumber = documentLocatorNumber;
  }

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public TaxPayer getTaxpayer() {
    return taxpayer;
  }

  public void setTaxpayer(TaxPayer taxpayer) {
    this.taxpayer = taxpayer;
  }

  public List<ReportsGenerated> getReportsGenerated() {
    return reportsGenerated;
  }

  public void setReportsGenerated(List<ReportsGenerated> reportsGenerated) {
    this.reportsGenerated = reportsGenerated;
  }

}
