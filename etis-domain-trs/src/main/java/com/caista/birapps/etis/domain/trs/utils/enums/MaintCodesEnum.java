/*
 * Modified by: tolentf
 * Last updated: Nov 20, 2018 11:50:52 AM
 */
package com.caista.birapps.etis.domain.trs.utils.enums;

public enum MaintCodesEnum {

  MAINTTASK_CANCELATP("CANCEL_ATP"),

  MAINTTASK_DEREGISTER("DEREGISTER_TAXPAYER"),

  MAINTTASK_DEREGISTER_TAX_FORM("DEREGISTER_TAX_FORM"),

  MAINTTASK_REACTIVATE("REACTIVATE_TAXPAYER"),

  MAINTTASK_SUSPEND_TAX_FORM("SUSPEND_TAX_FORM"),

  MAINTTASK_UPDATE_TAXPAYER("UPDATE_TAXPAYER"),

  MAINTTASK_TRANSFER("TRANSFER_TAXPAYER");

  private String code;

  private MaintCodesEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
