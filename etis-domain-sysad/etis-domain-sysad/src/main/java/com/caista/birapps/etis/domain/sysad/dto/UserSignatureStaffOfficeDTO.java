/*
 * Modified by: santojo
 * Last updated: Nov 12, 2018 3:47:48 PM
 */
package com.caista.birapps.etis.domain.sysad.dto;

import com.caista.birapps.etis.domain.sysad.entity.UploadSignature;

public class UserSignatureStaffOfficeDTO {
  private Long id;
  private UploadSignature uploadSignature;
  private String signatoryFirstName;
  private String signatoryLastName;
  private String signatoryName;

  public UserSignatureStaffOfficeDTO() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UploadSignature getUploadSignature() {
    return uploadSignature;
  }

  public void setUploadSignature(UploadSignature uploadSignature) {
    this.uploadSignature = uploadSignature;
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

  public String getSignatoryName() {
    return signatoryName;
  }

  public void setSignatoryName(String signatoryName) {
    this.signatoryName = signatoryName;
  }

  @Override
  public String toString() {
    return "UserSignatureStaffOfficeDTO [id=" + id + ", uploadSignature=" + uploadSignature
        + ", signatoryFirstName=" + signatoryFirstName + ", signatoryLastName=" + signatoryLastName
        + ", signatoryName=" + signatoryName + "]";
  }
}
