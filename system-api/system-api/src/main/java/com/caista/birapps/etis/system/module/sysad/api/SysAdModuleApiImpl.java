/*
  * Modified by: obregoj
  * Last updated: Oct 24, 2018 11:22:48 AM
  */
package com.caista.birapps.etis.system.module.sysad.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.sysad.SysAdConfig;
import com.caista.birapps.etis.system.module.sysad.SysAdModule;
import com.caista.birapps.etis.system.module.sysad.SysAdModuleFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class SysAdModuleApiImpl.
 */
@RestController
@RequestMapping("/sysadmodules")
@Api(description = "API for System Administration Module", produces = "application/json")
public class SysAdModuleApiImpl implements SysAdModuleApi {

	/** The sys ad module factory. */
	@Autowired
	private SysAdModuleFactory sysAdModuleFactory;

	/** The sysad config. */
	@Autowired
	private SysAdConfig sysadConfig;

	/** The keycloak etis authorization service. */
	@Autowired
	private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(SysAdModuleApiImpl.class);

	@Override
	@ApiOperation(value = "Get all Sysad modules based on authentication token", response = SysAdModule.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Unauthorized Access") })
	@RequestMapping(value = "/getPermission", method = RequestMethod.GET)
	public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers) throws Exception {
		String token = (headers.get("Authorization") != null) ? headers.get("Authorization").get(0) : "";

		LOGGER.info("Processing Request with token :" + token);

		List<Module> modules = getUserModulesByPermission(token);

		return new ResponseEntity<>(modules, HttpStatus.OK);
	}

	/**
	 * Gets the user modules by permission.
	 *
	 * @param token the token
	 * @return the user modules by permission
	 * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized
	 *                                           exception
	 */
	private List<Module> getUserModulesByPermission(String token) throws Exception {
		List<Module> modules = new ArrayList<>();
		LOGGER.info("Retrieving Permissions for Client " + sysadConfig.getClientid());
		try {
			String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token, sysadConfig.getClientid());
			Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
					sysadConfig.getClientid());
			LOGGER.info("Retrieved Permissions :" + permissions.toString());
			if (CollectionUtils.isEmpty(permissions)) {
				throw new NoDefinedPermissionException(EtisModules.SYSAD);
			}
			modules = sysAdModuleFactory.create().getModules();
			traverse(modules, permissions);
			LOGGER.info("MODULES : {} ", modules);
			return modules;

		} catch (AuthorizationDeniedException ex) {
			throw new NoDefinedPermissionException(EtisModules.SYSAD);
		}

	}

	/**
	 * Traverse.
	 *
	 * @param modules     the modules
	 * @param permissions the permissions
	 */
	private void traverse(List<Module> modules, Map<String, Permission> permissions) {
		for (Iterator<Module> it = modules.iterator(); it.hasNext();) {
			Module module = it.next();
			if (module.getSubModules() != null && !module.getSubModules().isEmpty()) {
				traverse(module.getSubModules(), permissions);
			} else {
				if (!hasPermission(module, permissions)) {
					it.remove();
				}
			}
		}
	}

	/**
	 * Checks for permission.
	 *
	 * @param module      the module
	 * @param permissions the permissions
	 * @return true, if successful
	 */
	private boolean hasPermission(Module module, Map<String, Permission> permissions) {
		if (module.isRequirePermission()) {
			Permission granted = permissions.get(module.getResourceName());
			if (granted != null) {
				LOGGER.info("Module " + module.getResourceName() + "is allowed to be accessed");

				module.setScopes(granted.getScopes());
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

}
