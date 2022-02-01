/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 11:25:50 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.caista.birapps.etis.domain.trs.entity.RelationshipDetail;
import com.caista.birapps.etis.domain.trs.transfer.entity.TransferHistory;

public class TransferBusinessValidationRequest extends ValidationRequest<Object> {

  @NotNull(message = "transferDetails.taxpayerId.required")
  private Long taxpayerId;

  @NotNull(message = "transferDetails.tin.required")
  private String tin;

  @NotNull(message = "transferDetails.branchCode.required")
  private String branchCode;

  @NotNull(message = "transferDetails.newRdo.required")
  private Long officeId;

  @NotNull(message = "transferDetails.transferHistory.required")
  private TransferHistory transferHistory;

  @Size(min = 1, message = "transferDetails.relationShipDetails.required")
  @NotNull(message = "transferDetails.relationShipDetails.required")
  private List<RelationshipDetail> relationShipDetails;

  public TransferBusinessValidationRequest(Long taxpayerId, String tin, String branchCode,
      Long officeId, TransferHistory transferHistory,
      List<RelationshipDetail> relationShipDetails) {
    super();
    this.taxpayerId = taxpayerId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.officeId = officeId;
    this.transferHistory = transferHistory;
    this.relationShipDetails = relationShipDetails;
  }

}
