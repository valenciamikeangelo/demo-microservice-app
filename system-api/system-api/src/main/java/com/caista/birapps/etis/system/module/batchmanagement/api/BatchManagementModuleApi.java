/*
  * Modified by: resontf
  * Last updated: Oct 4, 2019 2:47:49 PM
  */
package com.caista.birapps.etis.system.module.batchmanagement.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;


/**
 * The Interface BatchModuleApi.
 */
public interface BatchManagementModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   * @throws Exception
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
