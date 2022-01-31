/*
  * Modified by: obregoj
  * Last updated: 06 2, 20 12:35:47 PM
  */
package com.caista.birapps.etis.system.module.cms.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface CmsModuleApi.
 */
public interface CmsModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
