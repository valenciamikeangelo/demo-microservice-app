/*
  * Modified by: tolentf
  * Last updated: Apr 30, 2019 9:37:20 AM
  */
package com.caista.birapps.etis.domain.taskmanager;

import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaxpayerInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxpayerInfo implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The tin. */
  private String tin;

  /** The branch code. */
  private String branchCode;

  /** The registered name. */
  private String registeredName;

  /** The first name. */
  private String firstName;

  /** The middle name. */
  private String middleName;

  /** The last name. */
  private String lastName;

  /** The suffix. */
  private String suffix;


  /**
   * Instantiates a new taxpayer info.
   */
  public TaxpayerInfo() {
    super();
  }

  /**
   * Instantiates a new taxpayer info.
   *
   * @param tin the tin
   * @param branchCode the branch code
   * @param registeredName the registered name
   * @param firstName the first name
   * @param middleName the middle name
   * @param lastName the last name
   * @param suffix the suffix
   */
  public TaxpayerInfo(String tin, String branchCode, String registeredName, String firstName,
      String middleName, String lastName, String suffix) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.registeredName = registeredName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.suffix = suffix;
  }



  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
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

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public String getTaxpayerBusinessName() {
    if (StringUtils.isNotBlank(this.registeredName)) {
      return this.registeredName;
    } else {
      return this.firstName + " " + this.middleName + " " + this.lastName + " " + this.suffix;
    }
  }

  @Override
  public String toString() {
    return "TaxpayerInfo [tin=" + tin + ", branchCode=" + branchCode + ", registeredName="
        + registeredName + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName="
        + lastName + ", suffix=" + suffix + "]";
  }

}
