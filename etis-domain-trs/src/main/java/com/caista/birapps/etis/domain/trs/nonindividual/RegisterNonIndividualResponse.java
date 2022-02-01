/*
 * Modified by: fuentem
 * Last updated: Nov 10, 2018 10:11:06 AM
 */
package com.caista.birapps.etis.domain.trs.nonindividual;

import java.io.Serializable;
import java.util.List;
import com.caista.birapps.etis.domain.trs.ReportsGenerated;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

public class RegisterNonIndividualResponse implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String documentLocatorNumber;
  private String transactionNumber;
  private TaxPayer taxpayer;
  private List<ReportsGenerated> reportsGenerated;

  public RegisterNonIndividualResponse() {
    super();
  }


  public RegisterNonIndividualResponse(String documentLocatorNumber, String transactionNumber,
      TaxPayer taxpayer, List<ReportsGenerated> reportsGenerated) {
    super();
    this.documentLocatorNumber = documentLocatorNumber;
    this.transactionNumber = transactionNumber;
    this.taxpayer = taxpayer;
    this.reportsGenerated = reportsGenerated;
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
