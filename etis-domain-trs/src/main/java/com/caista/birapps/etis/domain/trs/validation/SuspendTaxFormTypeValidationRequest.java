/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:22 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.List;
import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;
import com.caista.birapps.etis.domain.trs.entity.TaxPayerStatusHistory;

/**
 * @author fuentem
 *
 */
public class SuspendTaxFormTypeValidationRequest extends ValidationRequest<TaxPayer> {

  @NotNull(message = "taxpayer.taxPayerHistories.required")
  private List<TaxPayerStatusHistory> taxPayerHistories;



  public SuspendTaxFormTypeValidationRequest(TaxPayer taxPayer) {
    this.taxPayerHistories = taxPayer.getTaxPayerHistories();
  }

}
