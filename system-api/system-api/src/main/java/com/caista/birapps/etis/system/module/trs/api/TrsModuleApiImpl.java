/*
  * Modified by: obregoj
  * Last updated: Oct 24, 2018 11:22:58 AM
  */
package com.caista.birapps.etis.system.module.trs.api;

import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.trs.*;
import io.swagger.annotations.*;

/**
 * The Class TrsModuleApiImpl.
 */
@RestController
@RequestMapping("/trsmodules")
@Api(description = "API for TRS Module", produces = "application/json")
public class TrsModuleApiImpl implements TrsModuleApi {

	/** The trs module factory. */
	@Autowired
	private TrsModuleFactory trsModuleFactory;

	/** The trs config. */
	@Autowired
	private TrsConfig trsConfig;

	/** The keycloak etis authorization service. */
	@Autowired
	private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(TrsModuleApiImpl.class);

	@Override
	@ApiOperation(value = "Get all Trs modules based on authentication token", response = TrsModule.class)
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
		LOGGER.info("Retrieving Permissions for Client " + trsConfig.getClientid());
		try {
			String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token, trsConfig.getClientid());
			Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
					trsConfig.getClientid());
			LOGGER.info("Retrieved Permissions :" + permissions.toString());
			if (CollectionUtils.isEmpty(permissions)) {
				throw new NoDefinedPermissionException(EtisModules.TRS);
			}
			modules = trsModuleFactory.create().getModules();
			traverse(token, modules, permissions);
			return modules;
		} catch (AuthorizationDeniedException ex) {
			throw new NoDefinedPermissionException(EtisModules.TRS);
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
			Map<String, Permission>  modulePermission = (StringUtils.isNotBlank(module.getClient())) ? getClientPermission(token, module)
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
