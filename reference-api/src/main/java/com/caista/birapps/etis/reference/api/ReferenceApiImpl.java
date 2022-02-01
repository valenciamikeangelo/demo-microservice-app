/*
  * Modified by: obregoj
  * Last updated: Sep 23, 2019 11:06:42 AM
  */
package com.caista.birapps.etis.reference.api;

import java.lang.reflect.Field;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.module.*;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.audit.entity.annotation.ETISLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.domain.sysad.reference.annotations.DedicatedLength;
import com.caista.birapps.etis.domain.sysad.reference.util.*;
import com.caista.birapps.etis.reference.service.*;
import com.caista.birapps.etis.reference.service.factory.*;
import io.swagger.annotations.Api;

/**
 * The Class ReferenceApi.
 */
@RestController
@RequestMapping("/reference")
@Api(value = "Reference API", produces = "application/json")
public class ReferenceApiImpl implements ReferenceApi {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(ReferenceApiImpl.class);

  @Autowired
  private ReferenceQueryServiceFactory queryFactory;

  @Autowired
  private ReferenceCommandServiceFactory commandFactory;

  /**
   * Save.
   *
   * @param reference the reference
   * @return the response entity
   */
  @Override
  @RequestMapping(method = RequestMethod.POST)
  @ETISLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.REFERENCE,
      logEvent = LogEvent.CREATE)
  public ResponseEntity<Object> save(@RequestBody ReferenceObjectWrapper reference,
      HttpServletRequest request) {
    LOG.info("API: Saving = {}", reference);
    ReferenceCommandService<?> commandService = commandFactory
        .getCommandService(reference.getReferenceType());
    return new ResponseEntity<>(commandService.addReferenceWithGeneratedId(reference),
        HttpStatus.OK);
  }

  /**
   * Update.
   *
   * @param reference the reference
   * @return the response entity
   */
  @Override
  @RequestMapping(method = RequestMethod.PUT)
  @ETISLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.REFERENCE,
      logEvent = LogEvent.UPDATE)
  public ResponseEntity<Object> update(@RequestBody ReferenceObjectWrapper reference,
      HttpServletRequest request) {
    LOG.info("API: Updating = {}", reference);
    ReferenceCommandService<?> commandService = commandFactory
        .getCommandService(reference.getReferenceType());
    return new ResponseEntity<>(commandService.updateReference(reference), HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/getReferenceByType", method = RequestMethod.GET)
  public ResponseEntity<List<Object>> getAllReferenceByType(
      @RequestParam("referenceType") ReferenceTypeEnum referenceType) {
    ReferenceQueryService<Object> service = queryFactory.getQueryService(referenceType);
    return new ResponseEntity<List<Object>>(service.getAllReference(), HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/getValidReferenceByType", method = RequestMethod.GET)
  public ResponseEntity<List<Object>> getAllValidReference(
      @RequestParam("referenceType") ReferenceTypeEnum referenceType) {
    ReferenceQueryService<Object> service = queryFactory.getQueryService(referenceType);
    return new ResponseEntity<List<Object>>(service.getAllValidReference(), HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse> serverSidePagination(
      @RequestBody ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    ReferenceQueryService<Object> service = queryFactory
        .getQueryService(request.getClientParam().getReferenceType());
    ServerSidePaginationResponse response = service.serverSidePagination(request);
    return new ResponseEntity<ServerSidePaginationResponse>(response, HttpStatus.OK);
  }

  @SuppressWarnings("unchecked")
  @Override
  @RequestMapping(value = "/getReferenceById", method = RequestMethod.GET)
  public ResponseEntity<Object> getReferenceById(ReferenceTypeEnum referenceType, String id) {
    LOG.info("API : GET REFERENCE BY ID: REFTYPE:{} ID: {}", referenceType, id);
    ReferenceQueryService<Object> service = queryFactory.getQueryService(referenceType);
    return new ResponseEntity<Object>(service.getReferenceById(id), HttpStatus.OK);
  }

  @Override
  @RequestMapping(value = "/getPropLengthByReferenceType", method = RequestMethod.GET)
  public ResponseEntity<List<ReferencePropertyLengthWrapper>> getPropertyLength(
      @RequestParam("referenceType") ReferenceTypeEnum referenceType) {
    LOG.info("API : GET DEDICATED LENGTHS FOR {} ", referenceType);
    List<ReferencePropertyLengthWrapper> propLengths = new ArrayList<>();

    try {
      Class<?> referenceClass = Class.forName(referenceType.getValue());

      for (Field field : referenceClass.getDeclaredFields()) {
        if (field.isAnnotationPresent(DedicatedLength.class)) {
          DedicatedLength propLength = field.getAnnotation(DedicatedLength.class);
          ReferencePropertyLengthWrapper propWrapper = new ReferencePropertyLengthWrapper();
          propWrapper.setName(propLength.name());
          propWrapper.setLength(propLength.value());

          propLengths.add(propWrapper);
        }
      }

    } catch (NullPointerException | SecurityException | ClassNotFoundException e) {
      LOG.error("ERROR: ", e);
    }

    return new ResponseEntity<>(propLengths, HttpStatus.OK);
  }


}
