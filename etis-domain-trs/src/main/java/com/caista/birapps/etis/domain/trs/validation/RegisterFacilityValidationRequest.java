/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:12 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;
import javax.validation.constraints.NotNull;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;


/**
 * @author fuentem
 *
 */
public class RegisterFacilityValidationRequest extends ValidationRequest<TaxPayer> {

  @NotNull(message = "taxpayer.tin.required")
  private String tin;

  @NotNull(message = "taxpayer.registeredName.required")
  private String registeredName;

  @NotNull(message = "taxpayer.facilityType.required")
  private String facilityTypeId;


  @NotNull(message = "taxpayer.submissionDate.required")
  private Date submissionDate;

  @NotNull(message = "taxpayer.rdo.required")
  private Long officeId;

  public RegisterFacilityValidationRequest(TaxPayer taxPayer) {
    this.tin = taxPayer.getTin();
    this.registeredName = taxPayer.getRegisteredName();
    this.facilityTypeId = taxPayer.getFacilityTypeId();
    this.submissionDate = taxPayer.getSubmissionDate();
    this.officeId = taxPayer.getOfficeId();

  }

}
