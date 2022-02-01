package com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper;

import org.apache.commons.lang3.StringUtils;

public class CorrespondenceSignatoryDetails {

  private String firstName;
  private String middleName;
  private String lastName;
  private String position;
  private String suffix;

  
  private String fullName;
  
  public CorrespondenceSignatoryDetails() {
    super();
  }

  public CorrespondenceSignatoryDetails(String firstName, String middleName, String lastName,
      String position) {
    super();
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.position = position;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }
  
  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getFullName() {
    StringBuilder fullNameBuilder = new StringBuilder();
    fullNameBuilder.append(StringUtils.isNotBlank(getFirstName()) ? getFirstName() + " " : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getMiddleName()) ? getMiddleName().substring(0, 1) + ". " : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getLastName()) ? getLastName() : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getSuffix()) ? ", " + getSuffix() : "");
    this.fullName = StringUtils.upperCase(fullNameBuilder.toString());
    return fullName;
  }
  
}
