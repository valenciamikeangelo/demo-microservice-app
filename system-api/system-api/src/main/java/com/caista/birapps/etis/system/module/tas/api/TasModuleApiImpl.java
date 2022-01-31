/*
 * Modified by: pastolc
 * Last updated: Mar 18, 2019 8:24:29 AM
 */
package com.caista.birapps.etis.system.module.tas.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.caista.birapps.etis.system.module.tas.TasConfig;
import com.caista.birapps.etis.system.module.tas.TasModule;
import com.caista.birapps.etis.system.module.tas.TasModuleFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class TasModuleApiImpl.
 */
@RestController
@RequestMapping("/tasmodules")
@Api(description = "API for TAS Module", produces = "application/json")
public class TasModuleApiImpl implements TasModuleApi {

	/** The tas module factory. */
	@Autowired
	private TasModuleFactory tasModuleFactory;

	/** The tas config. */
	@Autowired
	private TasConfig tasConfig;

	/** The keycloak etis authorization service. */
	@Autowired
	private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TasModuleApiImpl.class);

	@Override
	@ApiOperation(value = "Get all Tas modules based on authentication token", response = TasModule.class)
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
		LOGGER.info("Retrieving Permissions for Client " + tasConfig.getClientid());
		try {
			String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token, tasConfig.getClientid());
			Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
					tasConfig.getClientid());
			LOGGER.info("Retrieved Permissions :" + permissions.toString());
			if (CollectionUtils.isEmpty(permissions)) {
				throw new NoDefinedPermissionException(EtisModules.TAS);
			}
			modules = tasModuleFactory.create().getModules();
			traverse(token, modules, permissions);
			return modules;
		} catch (AuthorizationDeniedException ex) {
			throw new NoDefinedPermissionException(EtisModules.TAS);
		}

	}

	/**
	 * Traverse.
	 *
	 * @param modules     the modules
	 * @param permissions the permissions
	 */
	private void traverse(String token, List<Module> modules, Map<String, Permission> permissions) {
		for (Iterator<Module> it = modules.iterator(); it.hasNext();) {
			Module module = it.next();
			if (module.getSubModules() != null && !module.getSubModules().isEmpty()) {
				traverse(token, module.getSubModules(), permissions);
			} else {
				if (!hasPermission(token, module, permissions)) {
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
	private boolean hasPermission(String token, Module module, Map<String, Permission> permissions) {
		if (module.isRequirePermission()) {

			 Map<String, Permission> modulePermission = (StringUtils.isNotBlank(module.getClient())) ? getClientPermission(token, module)
					: permissions;

			Permission granted = modulePermission.get(module.getResourceName());
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

	/**
	 * Checks for client permission.
	 *
	 * @param module the module
	 * @param token  the token
	 * @return permission, if successful
	 */
	private Map<String, Permission> getClientPermission(String token, Module module) {
		Map<String, Permission> permissions = new HashMap<String, Permission>();
		LOGGER.info("Retrieving Permissions for Client {}", module.getClient());
		try {
			String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token, module.getClient());
			permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt, module.getClient());
			LOGGER.info("Retrieved Permissions :" + permissions.toString());
		} catch (Exception ex) {
			LOGGER.warn("Exception retrieving permissions of client: {}, for resource:", module.getClient(),
					module.getResourceName());
		}
		return permissions;
	}

}
