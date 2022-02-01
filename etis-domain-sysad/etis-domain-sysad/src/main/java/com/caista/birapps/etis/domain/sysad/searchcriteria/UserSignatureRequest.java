/*
  * Modified by: logronj
  * Last updated: Nov 9, 2018 12:01:20 PM
*/
package com.caista.birapps.etis.domain.sysad.searchcriteria;

public class UserSignatureRequest {

  private String username;
  private String signatoryFirstName;
  private String signatoryLastName;

  public UserSignatureRequest() {
    super();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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
    return "UserSignatureRequest [username=" + username + ", signatoryFirstName="
        + signatoryFirstName + ", signatoryLastName=" + signatoryLastName + "]";
  }

}
