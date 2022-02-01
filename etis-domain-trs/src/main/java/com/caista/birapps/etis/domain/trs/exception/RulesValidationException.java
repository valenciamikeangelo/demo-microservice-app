/*
 * Modified by: fuentem
 * Last updated: Oct 17, 2018 4:32:37 PM
 */
package com.caista.birapps.etis.domain.trs.exception;

import java.util.List;

public class RulesValidationException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private List<String> errorMessages;

  public RulesValidationException(List<String> errorMessages) {
    super();
    this.errorMessages = errorMessages;
  }

  public List<String> getErrorMessages() {
    return errorMessages;
  }

  public void setErrorMessages(List<String> errorMessages) {
    this.errorMessages = errorMessages;
  }



}
