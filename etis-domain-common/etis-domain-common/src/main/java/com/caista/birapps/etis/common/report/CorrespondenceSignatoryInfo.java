package com.caista.birapps.etis.common.report;

import java.io.Serializable;

public class CorrespondenceSignatoryInfo implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private byte[] fileImage;
  private String officeId;
  private String officeCode;
  private String correspondenceId;


  public byte[] getFileImage() {
    return fileImage;
  }

  public void setFileImage(byte[] fileImage) {
    this.fileImage = fileImage;
  }

  public String getOfficeId() {
    return officeId;
  }

  public void setOfficeId(String officeId) {
    this.officeId = officeId;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getCorrespondenceId() {
    return correspondenceId;
  }

  public void setCorrespondenceId(String correspondenceId) {
    this.correspondenceId = correspondenceId;
  }

}
