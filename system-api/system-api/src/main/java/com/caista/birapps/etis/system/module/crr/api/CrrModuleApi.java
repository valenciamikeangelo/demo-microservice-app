/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:01 PM
 */
package com.caista.birapps.etis.system.module.crr.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface CrrModuleApi.
 */
public interface CrrModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers);

}
