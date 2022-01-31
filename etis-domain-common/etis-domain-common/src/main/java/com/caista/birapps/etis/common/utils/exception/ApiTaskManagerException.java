/*
  * Modified by: romeror
  * Last updated: Jul 18, 2018 1:29:28 PM
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

public class ApiTaskManagerException extends BaseApiRuntimeException {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new api task manager exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public ApiTaskManagerException(EtisModules module, String resource) {
    super(module, resource);
  }

}
