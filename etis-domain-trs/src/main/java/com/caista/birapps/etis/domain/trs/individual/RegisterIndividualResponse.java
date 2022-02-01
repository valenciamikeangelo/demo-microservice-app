/*
 * Modified by: fuentem
 * Last updated: Oct 19, 2018 7:18:24 PM
 */
package com.caista.birapps.etis.domain.trs.individual;

import java.io.Serializable;
import java.util.List;
import com.caista.birapps.etis.domain.trs.ReportsGenerated;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

/**
 * The Class RegisterIndividualResponse.
 */
public class RegisterIndividualResponse implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The document locator number. */
  private String documentLocatorNumber;

  /** The transaction number. */
  private String transactionNumber;

  /** The taxpayer. */
  private TaxPayer taxpayer;

  private List<ReportsGenerated> reportsGenerated;


  /**
   * Instantiates a new register individual response.
   */
  public RegisterIndividualResponse() {
    super();
  }

  public RegisterIndividualResponse(TaxPayer taxpayer) {
    super();
    this.taxpayer = taxpayer;
  }

  /**
   * Instantiates a new register individual response.
   *
   * @param taxpayer the taxpayer
   * @param documentLocatorNumber the document locator number
   * @param transactionNumber the transaction number
   */
  public RegisterIndividualResponse(TaxPayer taxpayer, String documentLocatorNumber,
      String transactionNumber) {
    super();
    this.taxpayer = taxpayer;
    this.documentLocatorNumber = documentLocatorNumber;
    this.transactionNumber = transactionNumber;
  }

  public RegisterIndividualResponse(String documentLocatorNumber, String transactionNumber,
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

  @Override
  public String toString() {
    return "RegisterIndividualResponse [documentLocatorNumber=" + documentLocatorNumber
        + ", transactionNumber=" + transactionNumber + ", taxpayer=" + taxpayer
        + ", reportsGenerated=" + reportsGenerated + "]";
  }

}
