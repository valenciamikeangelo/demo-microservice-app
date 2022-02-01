/*
 * Last modified by: delmund
 * Last updated date: Nov 9, 2018 4:53:16 PM
 */
package com.caista.birapps.etis.domain.trs.managefinancialdetail;

import java.io.Serializable;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

public class ManageAccoutingPeriodResponse implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The document locator number. */
  private String documentLocatorNumber;

  /** The transaction number. */
  private String transactionNumber;

  /** The taxpayer. */
  private TaxPayer taxpayer;

  public ManageAccoutingPeriodResponse() {
    super();
  }

  public ManageAccoutingPeriodResponse(String documentLocatorNumber, String transactionNumber,
      TaxPayer taxpayer) {
    super();
    this.documentLocatorNumber = documentLocatorNumber;
    this.transactionNumber = transactionNumber;
    this.taxpayer = taxpayer;
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


}
