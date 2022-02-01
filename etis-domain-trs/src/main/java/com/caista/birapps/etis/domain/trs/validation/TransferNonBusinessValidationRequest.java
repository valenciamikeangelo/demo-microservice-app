/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 11:25:44 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.transfer.entity.TransferHistory;

public class TransferNonBusinessValidationRequest extends ValidationRequest<Object> {

  @NotNull(message = "transferDetails.taxpayerId.required")
  private Long taxpayerId;

  @NotNull(message = "transferDetails.tin.required")
  private String tin;

  @NotNull(message = "transferDetails.newRdo.required")
  private Long officeId;

  @NotNull(message = "transferDetails.transferHistory.required")
  private TransferHistory transferHistory;

  public TransferNonBusinessValidationRequest(Long taxpayerId, String tin, Long officeId,
      TransferHistory transferHistory) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.officeId = officeId;
    this.transferHistory = transferHistory;
  }

}
