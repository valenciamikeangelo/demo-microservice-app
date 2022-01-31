/**
 * 
 */
package com.caista.birapps.etis.system.exception;

import com.caista.birapps.etis.common.utils.exception.BaseApiException;
import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * @author valencm
 *
 */
public class UnAuthorizedAccessException extends BaseApiException {

  /**
   * @param module
   */
  public UnAuthorizedAccessException(EtisModules module) {
    super(module);
    // TODO Auto-generated constructor stub
  }

}
