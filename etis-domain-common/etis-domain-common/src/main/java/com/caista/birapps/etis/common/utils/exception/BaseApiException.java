/**
 * 
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * @author valencm
 *
 */
public class BaseApiException extends Exception {

  private EtisModules module;

  private BaseApiException() {
    super();
  }

  public BaseApiException(EtisModules module) {
    super();
    this.module = module;
  }

  /**
   * @return the module
   */
  public String getModule() {
    return module.toString();
  }
}
