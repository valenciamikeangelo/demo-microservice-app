/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:07 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

public class ManageFinancialValidationRequest extends ValidationRequest<TaxPayer> {


  @NotNull(message = "taxpayer.accountingYearStartMM.required")
  private String accountingYearStartMonthId;

  @Future(message = "taxpayer.accountingEffectiveDate.required")
  private Date accountingEffectiveDate;

  public ManageFinancialValidationRequest(TaxPayer taxPayer) {
    this.accountingYearStartMonthId = taxPayer.getAccountingYearStartMonthId();
    this.accountingEffectiveDate = taxPayer.getAccountingEffectiveDate();
  }



}
