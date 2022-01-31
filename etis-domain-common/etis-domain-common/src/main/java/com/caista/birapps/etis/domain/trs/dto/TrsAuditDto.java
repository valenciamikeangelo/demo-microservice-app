/*
 * Modified by: romerov
 * Last updated: Sep 13, 2019 2:23:33 PM
 */
package com.caista.birapps.etis.domain.trs.dto;

public class TrsAuditDto {

  private String refAuditId;

  /** The tin. */
  private String tin;

  /** The branch code. */
  private String branchCode;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The new value. */
  private String newValue;

  /** The old value. */
  private String oldValue;

  public String getRefAuditId() {
    return refAuditId;
  }

  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }


}
