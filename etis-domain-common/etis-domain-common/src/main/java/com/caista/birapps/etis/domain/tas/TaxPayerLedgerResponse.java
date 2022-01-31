/*
 * Modified by: fuentem
 * Last updated: Nov 7, 2018 5:24:05 PM
 */
package com.caista.birapps.etis.domain.tas;

public class TaxPayerLedgerResponse {

  private Boolean isSuccess;

  public TaxPayerLedgerResponse() {
    super();
  }

  public TaxPayerLedgerResponse(Boolean isSuccess) {
    super();
    this.isSuccess = isSuccess;
  }

  public Boolean getIsSuccess() {
    return isSuccess;
  }

  public void setIsSuccess(Boolean isSuccess) {
    this.isSuccess = isSuccess;
  }
}
