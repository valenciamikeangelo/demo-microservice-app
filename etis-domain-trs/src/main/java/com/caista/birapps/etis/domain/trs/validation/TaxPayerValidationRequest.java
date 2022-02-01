/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:28 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

/**
 * @author valencm
 *
 */
public class TaxPayerValidationRequest extends ValidationRequest<TaxPayer> {

  @NotNull(message = "taxpayer.branch.required")
  private String branchCode;

  public TaxPayerValidationRequest(TaxPayer taxPayer) {
    this.branchCode = taxPayer.getBranchCode();
  }

}
