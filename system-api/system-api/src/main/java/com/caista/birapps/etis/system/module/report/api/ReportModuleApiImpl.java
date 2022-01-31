/*
 * Modified by: fuentem
 * Last updated: Nov 20, 2018 6:50:22 PM
 */
package com.caista.birapps.etis.system.module.report.api;

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
import com.caista.birapps.etis.system.module.UserReportModule;
import com.caista.birapps.etis.system.module.handlers.DefaultModuleAuthorizationHandler;
import com.caista.birapps.etis.system.module.report.BatchReportModuleFactory;
import com.caista.birapps.etis.system.module.report.ReportConfig;
import com.caista.birapps.etis.system.module.report.ReportModule;
import com.caista.birapps.etis.system.module.report.ReportModuleFactory;
import com.caista.birapps.etis.system.module.report.UserReportModuleFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class ReportModuleApiImpl.
 */
@RestController
@RequestMapping("/reportmodules")
@Api(description = "API for Report Module", produces = "application/json")
public class ReportModuleApiImpl implements ReportModuleApi {

	/** The report module factory. */
	@Autowired
	private ReportModuleFactory reportModuleFactory;

	/** The user report module factory. */
	@Autowired
	private UserReportModuleFactory userReportModuleFactory;

	/** The batch report module factory. */
	@Autowired
	private BatchReportModuleFactory batchReportModuleFactory;

	/** The default module authorization handler. */
	@Autowired
	private DefaultModuleAuthorizationHandler defaultModuleAuthorizationHandler;

	/** The report config. */
	@Autowired
	private ReportConfig reportConfig;

	/** The keycloak etis authorization service. */
	@Autowired
	private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportModuleApiImpl.class);

	@Override
	@ApiOperation(value = "Get all report modules based on authentication token", response = ReportModule.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Unauthorized Access") })
	@RequestMapping(value = "/getPermission", method = RequestMethod.GET)
	public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers) throws Exception {
		String token = (headers.get("Authorization") != null) ? headers.get("Authorization").get(0) : "";

		LOGGER.info("Processing Request with token :" + token);

		List<Module> modules = reportModuleFactory.create().getModules();
		getModulesByPermission(token, modules);
		return new ResponseEntity<>(modules, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get all user report modules based on authentication token", response = UserReportModule.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Unauthorized Access") })
	@RequestMapping(value = "/getUserReportModules", method = RequestMethod.GET)
	public ResponseEntity<List<UserReportModule>> getUserReportModules(@RequestHeader HttpHeaders headers)
			throws Exception {
		String token = (headers.get("Authorization") != null) ? headers.get("Authorization").get(0) : "";

		List<UserReportModule> userReportModules = getUserReportModulesByPermission(token);

		userReportModules.forEach(userRep -> {
			try {
				getModulesByPermission(token, userRep.getSubModules());
			} catch (Exception e) {
				LOGGER.error(e.getLocalizedMessage());
			}
		});

		return new ResponseEntity<List<UserReportModule>>(userReportModules, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get all batch report modules based on authentication token", response = UserReportModule.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 403, message = "Unauthorized Access") })
	@RequestMapping(value = "/getBatchReportModules", method = RequestMethod.GET)
	public ResponseEntity<List<Module>> getBatchReportModules(@RequestHeader HttpHeaders headers) throws Exception {
		String token = (headers.get("Authorization") != null) ? headers.get("Authorization").get(0) : "";

		List<Module> batchReportModules = getBatchReportModulesByPermission(token);

		batchReportModules.forEach(repModule -> {
			try {
				getModulesByPermission(token, repModule.getSubModules());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
		});

		return new ResponseEntity<List<Module>>(batchReportModules, HttpStatus.OK);
	}

	/**
	 * Gets the modules by permission.
	 *
	 * @param token the token
	 * @return the user modules by permission
	 * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized
	 *                                           exception
	 */
	private List<Module> getModulesByPermission(String token, List<Module> modules) throws Exception {
		LOGGER.info("Retrieving Permissions for Client " + reportConfig.getClientid());
		try {
			String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token, reportConfig.getClientid());
			Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
					reportConfig.getClientid());
			LOGGER.info("Retrieved Permissions :" + permissions.toString());
			if (CollectionUtils.isEmpty(permissions)) {
				throw new NoDefinedPermissionException(EtisModules.REPORT);
			}
			traverse(modules, permissions);
			LOGGER.info("MODULES : {} ", modules);
			return modules;

		} catch (AuthorizationDeniedException ex) {
			throw new NoDefinedPermissionException(EtisModules.REPORT);
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

	/**
	 * Gets the user report modules by permission.
	 *
	 * @param token the token
	 * @return the user report modules by permission
	 * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized
	 *                                           exception
	 */
	private List<UserReportModule> getUserReportModulesByPermission(String token) throws Exception {
		List<UserReportModule> modules = new ArrayList<UserReportModule>();

		userReportModuleFactory.create().getModules().forEach(module -> {
			try {

				if (!module.isRequirePermission()) {
					modules.add(module);
				} else if (defaultModuleAuthorizationHandler.isAuthorized(token, module.getClient())) {
					LOGGER.info("User with token :" + token + " has access to " + module.getClient());
					modules.add(module);
				}

			} catch (Exception e) {
				LOGGER.info(e.getMessage());
				LOGGER.info("User with token :" + token + " has no access to " + module.getClient());
			}
		});

		if (CollectionUtils.isEmpty(modules)) {
			throw new NoDefinedPermissionException(EtisModules.REPORT);
		}

		return modules;
	}

	/**
	 * Gets the batch report modules by permission.
	 *
	 * @param token the token
	 * @return the batch report modules by permission
	 * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized
	 *                                           exception
	 */
	private List<Module> getBatchReportModulesByPermission(String token) throws Exception {
		List<Module> modules = new ArrayList<Module>();

		batchReportModuleFactory.create().getModules().forEach(module -> {
			try {

				if (!module.isRequirePermission()) {
					modules.add(module);
				} else if (defaultModuleAuthorizationHandler.isAuthorized(token, module.getClient())) {
					LOGGER.info("User with token :" + token + " has access to " + module.getClient());
					modules.add(module);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage());
				LOGGER.error("User with token :" + token + " has no access to " + module.getClient());
			}
		});

		if (CollectionUtils.isEmpty(modules)) {
			throw new NoDefinedPermissionException(EtisModules.REPORT);
		}

		return modules;
	}

}
