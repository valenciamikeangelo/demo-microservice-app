/*
 * Modified by: santojo
 * Last updated: Jul 3, 2018 2:01:45 PM
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * The Class BaseApiRuntimeException.
 */
public class BaseApiRuntimeException extends RuntimeException {

  /** The module. */
  private final EtisModules module;

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new base api runtime exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public BaseApiRuntimeException(EtisModules module, String resource) {
    super(resource);
    this.module = module;
  }

  public EtisModules getModule() {
    return module;
  }
}
