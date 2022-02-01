/*
  * Modified by: obregoj
  * Last updated: Sep 24, 2019 7:21:00 PM
  */
package com.caista.birapps.etis.reference.audit.aspect;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.*;
import com.caista.birapps.etis.domain.audit.entity.annotation.ETISLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.caista.birapps.etis.domain.sysad.reference.util.ReferenceObjectWrapper;
import com.caista.birapps.etis.integration.sender.SysadSender;
import com.caista.birapps.etis.reference.service.ReferenceCommandService;
import com.caista.birapps.etis.reference.service.factory.ReferenceCommandServiceFactory;
import com.google.gson.*;

@Component("referenceAuditAspect")
public class ReferenceAuditAspect {

  @Autowired
  private SysadSender sysadSender;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private ReferenceCommandServiceFactory commandServiceFactory;

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceAuditAspect.class);



  @SuppressWarnings("unchecked")
  public Object refAuditLogAround(ProceedingJoinPoint joinPoint, ETISLog etisLog) throws Throwable {

    LOGGER.info("INTERCEPTING REFERENCE SERVICE...");
    // HEADER REQUEST
    String userInfoString = request.getHeader("UserInfo");

    JsonObject userObject = new JsonObject();
    ReferenceObjectWrapper hrBeforeOps = (ReferenceObjectWrapper) joinPoint.getArgs()[0];
    try {
      userObject = StringUtils.isNotBlank(userInfoString)
          ? new Gson().fromJson(userInfoString, JsonObject.class)
          : new JsonObject();
    } catch (Exception e) {
      String decodeUserInfo = new String(Base64.decodeBase64(userInfoString.getBytes()));
      userObject = StringUtils.isNotBlank(decodeUserInfo)
          ? new Gson().fromJson(decodeUserInfo, JsonObject.class)
          : new JsonObject();
    }

    JsonObject office = null != userObject.get("office")
        ? new Gson().fromJson(userObject.get("office"), JsonObject.class)
        : new JsonObject();

    JsonObject officeType = null != office.get("officeType")
        ? new Gson().fromJson(office.get("officeType"), JsonObject.class)
        : new JsonObject();

    AuditLog<SystemAdministrationAudit> auditLog = new AuditLog<>();
    AuditMainDetails auditMainDetails = new AuditMainDetails();
    SystemAdministrationAudit auditSubDetails = new SystemAdministrationAudit();

    auditMainDetails.setEtisModule(etisLog.etisModule());
    auditMainDetails.setEtisSubModule(etisLog.etisSubmodule());
    auditMainDetails.setLogEvent(etisLog.logEvent());
    auditMainDetails.setLogTime(new Date());
    auditMainDetails.setSourceId(auditSubDetails.getRefAuditId());


    auditMainDetails.setOfficeCode(office.get("code").toString().replace("\"", ""));
    auditMainDetails.setOffice(office.get("description").toString().replace("\"", ""));
    auditMainDetails.setOfficeType(officeType.get("description").toString().replace("\"", ""));
    auditMainDetails.setOfficeTypeCode(officeType.get("code").toString().replace("\"", ""));
    auditMainDetails.setFirstName(userObject.get("firstName").toString().replace("\"", ""));
    auditMainDetails.setLastName(userObject.get("lastName").toString().replace("\"", ""));

    Object interceptedObject = new Object();

    if (LogEvent.CREATE.equals(etisLog.logEvent())) {

      interceptedObject = joinPoint.proceed();

      ResponseEntity<Auditable> httpResponseReference = (ResponseEntity<Auditable>) interceptedObject;

      Auditable referenceData = httpResponseReference.getBody();
      auditMainDetails.setUsername(referenceData.getCreatedBy());
      auditMainDetails.setIpAddress(getClientIp(request));
      auditMainDetails
          .setDetails("REFERENCE DATA CREATED IN TABLE " + hrBeforeOps.getReferenceType());
      auditSubDetails.setDescription("REFERENCE DATA CREATED : " + referenceData.getId());
      auditSubDetails.setNewValue(new Gson().toJson(referenceData).getBytes());
      auditSubDetails.setOtherDetails("");
    } else if (LogEvent.UPDATE.equals(etisLog.logEvent())) {

      ReferenceObjectWrapper referenceObjectWrapper = (ReferenceObjectWrapper) joinPoint
          .getArgs()[0];

      ReferenceCommandService<?> commandService = commandServiceFactory
          .getCommandService(referenceObjectWrapper.getReferenceType());

      Auditable objectBeforeOps = (Auditable) commandService.getReferenceById(hrBeforeOps.getId());
      LOGGER.info("REFERENCE OBJECT TO BE UPDATED; OLD VALUE : {}", objectBeforeOps);
      auditSubDetails.setOldValue(new Gson().toJson(objectBeforeOps).getBytes());
      interceptedObject = joinPoint.proceed();
      ResponseEntity<Auditable> httpResponseReference = (ResponseEntity<Auditable>) interceptedObject;
      Auditable referenceData = httpResponseReference.getBody();
      LOGGER.info("REFERENCE OBJECT UPDATED; NEW VALUE : {}", interceptedObject);
      auditMainDetails.setUsername(referenceData.getUpdatedBy());
      auditMainDetails.setIpAddress(getClientIp(request));
      auditMainDetails
          .setDetails("REFERENCE DATA UPDATED IN TABLE " + hrBeforeOps.getReferenceType());
      auditSubDetails.setDescription("REFERENCE DATA UPDATED");
      auditSubDetails.setNewValue(new Gson().toJson(referenceData).getBytes());

    }
    auditLog.setAuditMainDetails(auditMainDetails);
    auditLog.setSubDetails(auditSubDetails);
    sysadSender.send(auditLog);
    LOGGER.info("{}", "AUDIT MESSAGE HAS BEEN SENT.");

    return interceptedObject;

  }

  private String getClientIp(HttpServletRequest request) {

    String remoteAddr = "";


    if (request != null) {
      remoteAddr = request.getHeader("X-FORWARDED-FOR");
      if (remoteAddr == null || "".equals(remoteAddr)) {
        remoteAddr = request.getRemoteAddr();
      }
    }

    remoteAddr = remoteAddr != null && remoteAddr.contains(",")
        ? remoteAddr.split(",")[0]
        : remoteAddr;

    return remoteAddr;
  }
}
