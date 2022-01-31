/*
  * Modified by: tolentf
  * Last updated: Apr 4, 2019 7:41:26 AM
  */
package com.caista.birapps.etis.domain.taskmanager;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class ProcessRequest.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessInstanceRequest<T> {

  /** The process definition key. */
  private String processDefinitionKey;

  /** The username. */
  private String username;

  /** The office code. */
  private String officeCode;

  /** The process description. */
  private String processDescription;

  /** The taxpayer info. */
  private TaxpayerInfo taxpayerInfo;

  /** The form. */
  private T form;

  /**
   * Instantiates a new process request.
   */
  public ProcessInstanceRequest() {
    super();
  }

  /**
   * Instantiates a new process instance request.
   *
   * @param processDefinitionKey the process definition key
   * @param username the username
   * @param officeCode the office code
   * @param processDescription the process description
   * @param taxpayerInfo the taxpayer info
   * @param form the form
   */
  public ProcessInstanceRequest(String processDefinitionKey, String username, String officeCode,
      String processDescription, TaxpayerInfo taxpayerInfo, T form) {
    super();
    this.processDefinitionKey = processDefinitionKey;
    this.username = username;
    this.officeCode = officeCode;
    this.processDescription = processDescription;
    this.taxpayerInfo = taxpayerInfo;
    this.form = form;
  }

  public String getProcessDefinitionKey() {
    return processDefinitionKey;
  }

  public void setProcessDefinitionKey(String processDefinitionKey) {
    this.processDefinitionKey = processDefinitionKey;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getProcessDescription() {
    return processDescription;
  }

  public void setProcessDescription(String processDescription) {
    this.processDescription = processDescription;
  }

  public T getForm() {
    return form;
  }

  public void setForm(T form) {
    this.form = form;
  }

  public TaxpayerInfo getTaxpayerInfo() {
    return taxpayerInfo;
  }

  public void setTaxpayerInfo(TaxpayerInfo taxpayerInfo) {
    this.taxpayerInfo = taxpayerInfo;
  }
}
