/*
 * Modified by: fuentem
 * Last updated: Sep 5, 2018 2:19:06 PM
 */
package com.caista.birapps.etis.system.module.report.api;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.UserReportModule;


/**
 * The Interface ReportModuleApi.
 */
public interface ReportModuleApi {

  /**
   * Gets the permission.
   *
   * @param headers the headers
   * @return the permission
   * @throws Exception the exception
   */
  ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

  /**
   * Gets the authorized modules for user report.
   *
   * @param headers the headers
   * @return the permission
   * @throws Exception the exception
   */
  ResponseEntity<List<UserReportModule>> getUserReportModules(HttpHeaders headers) throws Exception;

  ResponseEntity<List<Module>> getBatchReportModules(HttpHeaders headers)
      throws Exception;

}
