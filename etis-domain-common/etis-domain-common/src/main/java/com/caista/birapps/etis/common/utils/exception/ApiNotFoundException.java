/*
 * Modified by: santojo
 * Last updated: Jul 3, 2018 2:26:41 PM
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * The Class ResourceNotFoundException.
 */
public class ApiNotFoundException extends BaseApiRuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * + Instantiates a new api not found exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public ApiNotFoundException(EtisModules module, String resource) {
    super(module, resource + " NOT FOUND!");
  }

}