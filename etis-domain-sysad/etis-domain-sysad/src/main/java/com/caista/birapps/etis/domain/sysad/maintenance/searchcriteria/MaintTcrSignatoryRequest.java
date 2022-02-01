/*
  * Modified by: logronj
  * Last updated: 03 27, 20 5:50:20 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTcrSignatoryRequest implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  private String office;
  private String correspondenceType;
  private String updatedBy;
  private String signatoryFirstName;
  private String signatoryLastName;

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }



  public String getCorrespondenceType() {
    return correspondenceType;
  }

  public void setCorrespondenceType(String correspondenceType) {
    this.correspondenceType = correspondenceType;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public String getSignatoryFirstName() {
    return signatoryFirstName;
  }

  public void setSignatoryFirstName(String signatoryFirstName) {
    this.signatoryFirstName = signatoryFirstName;
  }

  public String getSignatoryLastName() {
    return signatoryLastName;
  }

  public void setSignatoryLastName(String signatoryLastName) {
    this.signatoryLastName = signatoryLastName;
  }



}
