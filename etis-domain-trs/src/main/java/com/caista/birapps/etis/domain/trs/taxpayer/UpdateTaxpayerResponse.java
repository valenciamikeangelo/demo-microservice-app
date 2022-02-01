/*
 * Modified by: romerov
 * Last updated: Jan 27, 2019 1:28:48 PM
 */
package com.caista.birapps.etis.domain.trs.taxpayer;

import java.util.List;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

public class UpdateTaxpayerResponse {

  private TaxPayer oldTaxpayer;
  private TaxPayer updatedTaxpayer;
  private String taskNumber;
  private List<String> validationMsgs;

  public UpdateTaxpayerResponse() {
    super();
  }

  public UpdateTaxpayerResponse(TaxPayer oldTaxpayer, TaxPayer updatedTaxpayer, String taskNumber,
      List<String> validationMsgs) {
    super();
    this.oldTaxpayer = oldTaxpayer;
    this.updatedTaxpayer = updatedTaxpayer;
    this.taskNumber = taskNumber;
    this.validationMsgs = validationMsgs;
  }

  public TaxPayer getUpdatedTaxpayer() {
    return updatedTaxpayer;
  }

  public void setUpdatedTaxpayer(TaxPayer updatedTaxpayer) {
    this.updatedTaxpayer = updatedTaxpayer;
  }

  public String getTaskNumber() {
    return taskNumber;
  }

  public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
  }

  public List<String> getValidationMsgs() {
    return validationMsgs;
  }

  public void setValidationMsgs(List<String> validationMsgs) {
    this.validationMsgs = validationMsgs;
  }

  public TaxPayer getOldTaxpayer() {
    return oldTaxpayer;
  }

  public void setOldTaxpayer(TaxPayer oldTaxpayer) {
    this.oldTaxpayer = oldTaxpayer;
  }
  
}
