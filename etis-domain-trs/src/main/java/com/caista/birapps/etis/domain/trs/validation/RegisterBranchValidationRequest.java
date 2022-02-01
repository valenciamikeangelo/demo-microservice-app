/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:09 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;

/**
 * @author fuentem
 *
 */
public class RegisterBranchValidationRequest extends ValidationRequest<TaxPayer> {

  @NotNull(message = "taxpayer.legalStatusType.required")
  private String legalStatusTypeId;

  @NotNull(message = "taxpayer.registeredName.required")
  private String registeredName;

  public RegisterBranchValidationRequest(TaxPayer taxPayer) {
    this.legalStatusTypeId = taxPayer.getTaxpayerTypeId();
    this.registeredName = taxPayer.getRegisteredName();
  }

}
