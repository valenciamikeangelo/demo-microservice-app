/*
  * Modified by: logronj
  * Last updated: Sep 10, 2018 4:01:58 PM
*/
package com.caista.birapps.etis.system.module.rfp.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface RfpModuleApi.
 */
public interface RfpModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
