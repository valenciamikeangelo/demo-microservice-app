/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:00 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;
import javax.validation.constraints.NotNull;

/**
 * @author fuentem
 *
 */
public class DeregisterTPTaxFormTypesValidationRequest extends ValidationRequest<Object> {

  @NotNull(message = "deregister.taxpayerId.required")
  private Long taxpayerId;

  @NotNull(message = "deregister.dateOfDeregistration.required")
  private Date dateOfDeregistration;

  @NotNull(message = "deregister.reason.required")
  private String reasonId;

  public DeregisterTPTaxFormTypesValidationRequest(Long taxpayerId, Date dateOfDeregistration,
      String reasonId) {
    super();
    this.taxpayerId = taxpayerId;
    this.dateOfDeregistration = dateOfDeregistration;
    this.reasonId = reasonId;
  }

}
