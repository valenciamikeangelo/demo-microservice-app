/*
  * Modified by: decinam
  * Last updated: Aug 8, 2018 1:30:03 PM
  */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.validation.ValidationResponse;

/**
 * @author valencm
 *
 */
public class ValidationException extends BaseApiException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private ValidationResponse validationResponse;

  /**
   * @param module
   */
  public ValidationException(EtisModules module) {
    super(module);
  }

  public ValidationException(EtisModules module, ValidationResponse validationResponse) {
    super(module);
    this.validationResponse = validationResponse;
  }


  /**
   * @return the validationResponse
   */
  public ValidationResponse getValidationResponse() {
    return validationResponse;
  }



}
