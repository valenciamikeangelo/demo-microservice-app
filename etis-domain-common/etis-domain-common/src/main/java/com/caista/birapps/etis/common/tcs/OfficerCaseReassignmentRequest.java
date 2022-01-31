/*
  * Modified by: romerov
  * Last updated: 06 16, 20 3:06:51 PM
*/
package com.caista.birapps.etis.common.tcs;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class OfficerCaseReassignmentRequest {

  private String userId;
  private String firstName;
  private String middleName;
  private String lastName;
  private String suffix;
  private String userName;
  private String email;
  private String emailOfficeFormat;
  private List<String> caseNumber;

  /**
   * @return the userId
   */
  public String getUserId() {
    return userId;
  }

  /**
   * @param userId the userId to set
   */
  public void setUserId(String userId) {
    this.userId = userId;
  }

  /**
   * @return the firstName
   */
  public String getFirstName() {
    return firstName;
  }

  /**
   * @param firstName the firstName to set
   */
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
   * @return the middleName
   */
  public String getMiddleName() {
    return middleName;
  }

  /**
   * @param middleName the middleName to set
   */
  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  /**
   * @return the lastName
   */
  public String getLastName() {
    return lastName;
  }

  /**
   * @param lastName the lastName to set
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * @return the suffix
   */
  public String getSuffix() {
    return suffix;
  }

  /**
   * @param suffix the suffix to set
   */
  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  /**
   * @return the userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * @param userName the userName to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the caseNumber
   */
  public List<String> getCaseNumber() {
    return caseNumber;
  }

  /**
   * @param caseNumber the caseNumber to set
   */
  public void setCaseNumber(List<String> caseNumber) {
    this.caseNumber = caseNumber;
  }

  public String getEmailOfficeFormat() {
    return emailOfficeFormat;
  }

  public void setEmailOfficeFormat(Boolean isLargeTp, String officeDesc, String officeCode) {
    if (isLargeTp != null && isLargeTp) {
      if (StringUtils.isNotBlank(officeCode)
          && (StringUtils.equals(officeCode, "123") || StringUtils.equals(officeCode, "127"))) {
        this.emailOfficeFormat = "Large Taxpayers District: " + officeDesc;
      } else {
        this.emailOfficeFormat = "Large Taxpayers Service: " + officeDesc;
      }
    } else if (isLargeTp != null && !isLargeTp) {
      this.emailOfficeFormat = "Revenue District Office: " + officeDesc;
    } else {
      this.emailOfficeFormat = "";
    }
  }

  public String getFullName() {
    StringBuilder fullNameBuilder = new StringBuilder();
    fullNameBuilder.append(StringUtils.isNotBlank(getLastName()) ? getLastName() + ", " : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getFirstName()) ? getFirstName() + " " : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getMiddleName()) ? getMiddleName().substring(0, 1) + ". " : "");
    fullNameBuilder.append(StringUtils.isNotBlank(getSuffix()) ? getSuffix() : "");
    return StringUtils.upperCase(fullNameBuilder.toString());
  }

}
