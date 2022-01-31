/*
  * Modified by: romerov
  * Last updated: 06 10, 20 7:02:01 PM
*/
package com.caista.birapps.etis.common.cms;

import java.io.Serializable;
import java.util.Date;

public class TransferTaxpayerParam implements Serializable {

  private static final long serialVersionUID = 1L;

  private String tin;

  private String branchCode;

  private Long officeId;

  private String officeCode;

  private String officeDescription;

  private Boolean officeIsLargeTaxpayer;

  private Long oldOfficeId;

  private String oldOfficeCode;

  private String oldOfficeDescription;

  private Boolean oldOfficeIsLargeTaxpayer;

  private Date transferDate;

  public TransferTaxpayerParam() {
    super();
  }

  public TransferTaxpayerParam(String tin, String branchCode, Long officeId, String officeCode,
      String officeDescription, Boolean officeIsLargeTaxpayer, Long oldOfficeId,
      String oldOfficeCode, String oldOfficeDescription, Boolean oldOfficeIsLargeTaxpayer,
      Date transferDate) {
    super();
    this.tin = tin;
    this.branchCode = branchCode;
    this.officeId = officeId;
    this.officeCode = officeCode;
    this.officeDescription = officeDescription;
    this.officeIsLargeTaxpayer = officeIsLargeTaxpayer;
    this.oldOfficeId = oldOfficeId;
    this.oldOfficeCode = oldOfficeCode;
    this.oldOfficeDescription = oldOfficeDescription;
    this.oldOfficeIsLargeTaxpayer = oldOfficeIsLargeTaxpayer;
    this.transferDate = transferDate;
  }

  /**
   * @return the tin
   */
  public String getTin() {
    return tin;
  }

  /**
   * @param tin the tin to set
   */
  public void setTin(String tin) {
    this.tin = tin;
  }

  /**
   * @return the branchCode
   */
  public String getBranchCode() {
    return branchCode;
  }

  /**
   * @param branchCode the branchCode to set
   */
  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  /**
   * @return the officeId
   */
  public Long getOfficeId() {
    return officeId;
  }

  /**
   * @param officeId the officeId to set
   */
  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  /**
   * @return the officeCode
   */
  public String getOfficeCode() {
    return officeCode;
  }

  /**
   * @param officeCode the officeCode to set
   */
  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  /**
   * @return the officeDescription
   */
  public String getOfficeDescription() {
    return officeDescription;
  }

  /**
   * @param officeDescription the officeDescription to set
   */
  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  /**
   * @return the officeIsLargeTaxpayer
   */
  public Boolean getOfficeIsLargeTaxpayer() {
    return officeIsLargeTaxpayer;
  }

  /**
   * @param officeIsLargeTaxpayer the officeIsLargeTaxpayer to set
   */
  public void setOfficeIsLargeTaxpayer(Boolean officeIsLargeTaxpayer) {
    this.officeIsLargeTaxpayer = officeIsLargeTaxpayer;
  }

  /**
   * @return the oldOfficeId
   */
  public Long getOldOfficeId() {
    return oldOfficeId;
  }

  /**
   * @param oldOfficeId the oldOfficeId to set
   */
  public void setOldOfficeId(Long oldOfficeId) {
    this.oldOfficeId = oldOfficeId;
  }

  /**
   * @return the oldOfficeCode
   */
  public String getOldOfficeCode() {
    return oldOfficeCode;
  }

  /**
   * @param oldOfficeCode the oldOfficeCode to set
   */
  public void setOldOfficeCode(String oldOfficeCode) {
    this.oldOfficeCode = oldOfficeCode;
  }

  /**
   * @return the oldOfficeDescription
   */
  public String getOldOfficeDescription() {
    return oldOfficeDescription;
  }

  /**
   * @param oldOfficeDescription the oldOfficeDescription to set
   */
  public void setOldOfficeDescription(String oldOfficeDescription) {
    this.oldOfficeDescription = oldOfficeDescription;
  }

  /**
   * @return the oldOfficeIsLargeTaxpayer
   */
  public Boolean getOldOfficeIsLargeTaxpayer() {
    return oldOfficeIsLargeTaxpayer;
  }

  /**
   * @param oldOfficeIsLargeTaxpayer the oldOfficeIsLargeTaxpayer to set
   */
  public void setOldOfficeIsLargeTaxpayer(Boolean oldOfficeIsLargeTaxpayer) {
    this.oldOfficeIsLargeTaxpayer = oldOfficeIsLargeTaxpayer;
  }

  /**
   * @return the transferDate
   */
  public Date getTransferDate() {
    return transferDate;
  }

  /**
   * @param transferDate the transferDate to set
   */
  public void setTransferDate(Date transferDate) {
    this.transferDate = transferDate;
  }

}
