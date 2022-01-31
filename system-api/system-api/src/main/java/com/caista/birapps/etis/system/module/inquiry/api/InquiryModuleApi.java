/*
  * Modified by: santosj
  * Last updated: Aug 3, 2018 2:42:49 PM
  */
package com.caista.birapps.etis.system.module.inquiry.api;

import java.util.List;

import org.springframework.http.*;

import com.caista.birapps.etis.system.module.Module;

/**
 * The Interface InquiryModuleApi.
 */
public interface InquiryModuleApi {

	/**
	 * Gets the permission.
	 *
	 * @param headers
	 *            the headers
	 * @return the permission
	 * @throws Exception
	 *             the exception
	 */
	ResponseEntity<List<Module>> getPermission(HttpHeaders headers) throws Exception;

}
