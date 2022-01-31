/*
 * Modified by: santojo
 * Last updated: Aug 6, 2018 11:22:22 AM
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;


/**
 * The Class ApiNoResultException.
 */
public class ApiNoResultException extends BaseApiRuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new api no result exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public ApiNoResultException(EtisModules module, String resource) {
    super(module, resource + " EMPTY RESULT");
  }

}
