/**
 * 
 */
package com.caista.birapps.etis.common.utils.exception;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * @author valencm
 *
 */
public class NoDefinedPermissionException extends BaseApiException {

  /**
   * @param module
   */
  public NoDefinedPermissionException(EtisModules module) {
    super(module);
  }



}
