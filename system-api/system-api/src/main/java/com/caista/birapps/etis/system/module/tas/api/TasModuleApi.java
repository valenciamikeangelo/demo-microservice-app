/*
 * Modified by: pastolc
 * Last updated: Feb 27, 2019 9:22:48 AM
 */
package com.caista.birapps.etis.system.module.tas.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface TasModuleApi.
 */
public interface TasModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
