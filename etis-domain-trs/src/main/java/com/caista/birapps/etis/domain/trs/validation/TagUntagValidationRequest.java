/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:25 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;


public class TagUntagValidationRequest extends ValidationRequest<TaxPayer> {


  @NotNull(message = "taxpayer.businessStatus.required")
  private String taxpayerTypeId;

  @NotNull(message = "taxpayer.reason.required")
  private String reason;


  public TagUntagValidationRequest(TaxPayer taxPayer) {

    this.taxpayerTypeId = taxPayer.getTaxpayerTypeId();
    this.reason = taxPayer.getTagUntagTaxPayerHistories().get(0).getReasonId();
  }

}
