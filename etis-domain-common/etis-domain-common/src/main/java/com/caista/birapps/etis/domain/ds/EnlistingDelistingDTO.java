/*
  * Modified by: bongalr
  * Last updated: Feb 12, 2020 2:59:29 PM
  */
package com.caista.birapps.etis.domain.ds;

import java.util.*;

public class EnlistingDelistingDTO {

  private String tin;
  private String branchCode;
  private String oldOfficeCode;
  private String newOfficeCode;
  private String taxpayerGroupId;
  private Date transferDate;
  private List<String> employeeTinList;


  public EnlistingDelistingDTO() {
    super();
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


  public String getOldOfficeCode() {
    return oldOfficeCode;
  }


  public void setOldOfficeCode(String oldOfficeCode) {
    this.oldOfficeCode = oldOfficeCode;
  }


  public String getNewOfficeCode() {
    return newOfficeCode;
  }


  public void setNewOfficeCode(String newOfficeCode) {
    this.newOfficeCode = newOfficeCode;
  }


  public String getTaxpayerGroupId() {
    return taxpayerGroupId;
  }


  public void setTaxpayerGroupId(String taxpayerGroupId) {
    this.taxpayerGroupId = taxpayerGroupId;
  }


  public Date getTransferDate() {
    return transferDate;
  }


  public void setTransferDate(Date transferDate) {
    this.transferDate = transferDate;
  }


  public List<String> getEmployeeTinList() {
    return employeeTinList;
  }


  public void setEmployeeTinList(List<String> employeeTinList) {
    this.employeeTinList = employeeTinList;
  }


  @Override
  public String toString() {
    return "EnlistingDelistingDTO [tin=" + tin + ", branchCode=" + branchCode + ", oldOfficeCode="
        + oldOfficeCode + ", newOfficeCode=" + newOfficeCode + ", taxpayerGroupId="
        + taxpayerGroupId + ", transferDate=" + transferDate + ", employeeTinList="
        + employeeTinList + "]";
  }



}
