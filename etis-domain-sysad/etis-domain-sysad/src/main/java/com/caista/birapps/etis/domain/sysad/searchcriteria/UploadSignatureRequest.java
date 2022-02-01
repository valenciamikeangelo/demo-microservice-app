/*
  * Modified by: logronj
  * Last updated: Nov 8, 2018 7:08:00 PM
*/
package com.caista.birapps.etis.domain.sysad.searchcriteria;

import java.util.Date;

public class UploadSignatureRequest {

  private String fileName;
  private String signatoryFname;
  private String signatoryLname;
  private String uploaderFname;
  private String uploaderLname;
  private Date uploadedDate;

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getSignatoryLname() {
    return signatoryLname;
  }

  public void setSignatoryLname(String signatoryLname) {
    this.signatoryLname = signatoryLname;
  }

  public String getSignatoryFname() {
    return signatoryFname;
  }

  public void setSignatoryFname(String signatoryFname) {
    this.signatoryFname = signatoryFname;
  }

  public String getUploaderFname() {
    return uploaderFname;
  }

  public void setUploaderFname(String uploaderFname) {
    this.uploaderFname = uploaderFname;
  }

  public String getUploaderLname() {
    return uploaderLname;
  }

  public void setUploaderLname(String uploaderLname) {
    this.uploaderLname = uploaderLname;
  }

  public Date getUploadedDate() {
    return uploadedDate;
  }

  public void setUploadedDate(Date uploadedDate) {
    this.uploadedDate = uploadedDate;
  }

  @Override
  public String toString() {
    return "UploadSignatureRequest [fileName=" + fileName + ", signatoryFname=" + signatoryFname
        + ", signatoryLname=" + signatoryLname + ", uploaderFname=" + uploaderFname
        + ", uploaderLname=" + uploaderLname + ", uploadedDate=" + uploadedDate + "]";
  }

}
