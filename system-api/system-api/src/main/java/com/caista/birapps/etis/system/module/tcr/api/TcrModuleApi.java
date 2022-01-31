/*
  * Modified by: logronj
  * Last updated: 03 17, 20 5:33:01 PM
  */
package com.caista.birapps.etis.system.module.tcr.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface TcrModuleApi.
 */
public interface TcrModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
