/*
 * Modified by: santojo
 * Last updated: Jul 3, 2018 3:09:11 PM
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * The Class ApiConstrainViolationException.
 */
public class ApiConstraintViolationException extends BaseApiRuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new api constraint violation exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public ApiConstraintViolationException(EtisModules module, String resource) {
    super(module, resource);
  }

}
