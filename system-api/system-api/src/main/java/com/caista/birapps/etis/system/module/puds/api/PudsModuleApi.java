/*
 * Modified by: santojo
 * Last updated: May 2, 2019 10:20:16 AM
 */
package com.caista.birapps.etis.system.module.puds.api;

import java.util.List;
import org.springframework.http.*;
import com.caista.birapps.etis.system.module.Module;

public interface PudsModuleApi {

  ResponseEntity<List<Module>> getPermission(HttpHeaders headers);

}
