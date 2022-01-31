package com.caista.birapps.etis.system.module.audit.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface AuditModuleApi.
 */
public interface AuditModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers);

}
