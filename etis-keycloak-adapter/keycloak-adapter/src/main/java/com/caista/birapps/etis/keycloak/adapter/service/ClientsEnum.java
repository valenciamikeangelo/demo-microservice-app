/*
  * Modified by: tolentf
  * Last updated: Feb 27, 2019 7:59:18 AM
  */
package com.caista.birapps.etis.keycloak.adapter.service;

/**
 * @author valencm
 *
 */
public enum ClientsEnum {

  TRS("etis-trs"), SYSAD("etis-sa"), REPORTS("etis-report"), TASKS("etis-tasks"), TCR(
      "etis-tcr"), RFP("etis-rfp"), INQUIRY("etis-tax-inquiry"), CRR("etis-crr");

  private String value;

  private ClientsEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
