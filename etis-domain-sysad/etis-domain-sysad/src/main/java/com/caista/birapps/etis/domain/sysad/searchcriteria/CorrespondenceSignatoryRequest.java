/*
  * Modified by: logronj
  * Last updated: Dec 3, 2018 9:36:22 AM
*/
package com.caista.birapps.etis.domain.sysad.searchcriteria;

public class CorrespondenceSignatoryRequest {

  private String office;
  private String correspondenceType;
  private String updatedBy;
  private String signatoryFirstName;
  private String signatoryLastName;

  public CorrespondenceSignatoryRequest() {
    super();
  }

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

  @Override
  public String toString() {
    return "CorrespondenceSignatoryRequest [office=" + office + ", correspondenceType="
        + correspondenceType + ", updatedBy=" + updatedBy + ", signatoryFirstName="
        + signatoryFirstName + ", signatoryLastName=" + signatoryLastName + "]";
  }
}
