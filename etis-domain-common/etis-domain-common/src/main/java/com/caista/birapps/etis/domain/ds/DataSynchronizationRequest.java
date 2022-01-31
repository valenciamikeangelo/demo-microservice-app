/*
 * Modified by: santojo Last updated: Dec 6, 2019 6:13:30 PM
 */
package com.caista.birapps.etis.domain.ds;

public class DataSynchronizationRequest {

  private DataSynchronizationTypeEnum type;
  private String tin;
  private String branchCode;
  private Object obj;
  private Object oldObj;

  public DataSynchronizationRequest() {
    super();
  }

  public DataSynchronizationRequest(DataSynchronizationTypeEnum type, String tin, String branchCode,
      Object obj) {
    super();
    this.type = type;
    this.tin = tin;
    this.branchCode = branchCode;
    this.obj = obj;
  }

  public DataSynchronizationRequest(DataSynchronizationTypeEnum type, String tin, String branchCode,
      Object obj, Object oldObj) {
    super();
    this.type = type;
    this.tin = tin;
    this.branchCode = branchCode;
    this.obj = obj;
    this.oldObj = oldObj;
  }

  public DataSynchronizationTypeEnum getType() {
    return type;
  }

  public void setType(DataSynchronizationTypeEnum type) {
    this.type = type;
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

  public Object getObj() {
    return obj;
  }

  public void setObj(Object obj) {
    this.obj = obj;
  }

  public Object getOldObj() {
    return oldObj;
  }

  public void setOldObj(Object oldObj) {
    this.oldObj = oldObj;
  }

//  @Override
//  public String toString() {
//    return "DataSynchronizationRequest [type=" + type + ", tin=" + tin + ", branchCode="
//        + branchCode + ", obj=" + Boolean.valueOf(obj == null) + ", oldObj="
//        + Boolean.valueOf(oldObj == null) + "]";
//  }

}
