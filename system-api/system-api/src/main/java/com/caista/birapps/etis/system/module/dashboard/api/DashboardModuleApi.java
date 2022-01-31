/*
 * Modified by: tolentf Last updated: Jun 1, 2018 4:38:40 PM
 */
package com.caista.birapps.etis.system.module.dashboard.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface DashboardModuleApi.
 */
public interface DashboardModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
