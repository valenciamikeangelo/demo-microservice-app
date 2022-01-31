/*
 * Modified by: tolentf
 * Last updated: Feb 24, 2020 8:25:48 AM
 */
package com.caista.birapps.etis.common.utils.exception;

import org.slf4j.*;
import com.caista.birapps.etis.common.utils.module.*;

public class ApiAuditSelectionException extends BaseApiRuntimeException {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiAuditSelectionException.class);

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new api task manager exception.
   *
   * @param module the module
   * @param resource the resource
   */
  public ApiAuditSelectionException(EtisModules module, String resource) {
    super(module, resource);
  }

  public ApiAuditSelectionException(EtisModules module, String resource, EtisSubmodules submodule,
      Object param) {
    super(module, resource);
    LOGGER.error("Service: {}, Param: {}", submodule, param);
  }

}
