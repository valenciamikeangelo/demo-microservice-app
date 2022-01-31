/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:38:44 PM
 */
package com.caista.birapps.etis.system.module.auditselection.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface TcrModuleApi.
 */
public interface AuditSelectionModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers);

}