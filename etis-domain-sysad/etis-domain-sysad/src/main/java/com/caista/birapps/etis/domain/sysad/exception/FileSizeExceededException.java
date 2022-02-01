/*
 * Modified by: santojo
 * Last updated: Jul 10, 2018 12:29:42 PM
 */
package com.caista.birapps.etis.domain.sysad.exception;

import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * The Class FileSizeExceededException.
 */
public class FileSizeExceededException extends BaseApiRuntimeException {


  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new file size exceeded exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public FileSizeExceededException(EtisModules module, String resource) {
    super(module, resource + " attachment size limit exceeded.");
  }

}
