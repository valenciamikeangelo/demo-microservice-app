/*
 * Modified by: fuentem
 * Last updated: Oct 17, 2018 3:36:13 PM
 */
package com.caista.birapps.etis.domain.trs.validation;

public class RulesValidationRequest<T> {

  private T form;

  public RulesValidationRequest(T form) {
    super();
    this.form = form;
  }

  public T getForm() {
    return form;
  }

  public void setForm(T form) {
    this.form = form;
  }



}
