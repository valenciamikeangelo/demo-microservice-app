/*
  * Modified by: obregoj
  * Last updated: Sep 25, 2019 10:54:18 AM
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
import com.caista.birapps.etis.domain.audit.entity.annotation.MAINTLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.caista.birapps.etis.integration.sender.SysadSender;
import com.caista.birapps.etis.maintenance.service.AuditableMaintenanceService;
import com.caista.birapps.etis.maintenance.service.factory.AuditableMaintenanceServiceFactory;
import com.google.gson.*;

@Component("maintenanceAuditAspect")
public class MaintenanceAuditAspect {

  /** The sender. */
  @Autowired
  private SysadSender sysadSender;

  @Autowired
  private HttpServletRequest request;

  @Autowired
  private AuditableMaintenanceServiceFactory auditableMaintenanceServiceFactory;

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceAuditAspect.class);



  @SuppressWarnings("unchecked")
  public Object maintAuditLogAround(ProceedingJoinPoint joinPoint, MAINTLog maintLog)
      throws Throwable {
    LOGGER.info("INTERCEPTING MAINTENANCE SERVICE...");

    // HEADER REQUEST
    String userInfoString = request.getHeader("UserInfo");
    JsonObject userObject = new JsonObject();

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

    JsonObject office = (userObject != null && null != userObject.get("office"))
        ? new Gson().fromJson(userObject.get("office"), JsonObject.class)
        : new JsonObject();
    JsonObject officeType = null != office.get("officeType")
        ? new Gson().fromJson(office.get("officeType"), JsonObject.class)
        : new JsonObject();



    AuditLog<SystemAdministrationAudit> auditLog = new AuditLog<>();
    AuditMainDetails auditMainDetails = new AuditMainDetails();
    SystemAdministrationAudit auditSubDetails = new SystemAdministrationAudit();

    auditMainDetails.setEtisModule(maintLog.etisModule());
    auditMainDetails.setEtisSubModule(maintLog.etisSubmodule());
    auditMainDetails.setLogEvent(maintLog.logEvent());
    auditMainDetails.setLogTime(new Date());
    auditMainDetails.setSourceId(auditSubDetails.getRefAuditId());


    auditMainDetails.setOfficeCode(office.get("code") != null
        ? office.get("code").toString().replace("\"", "")
        : "");
    auditMainDetails.setOffice(office.get("code") != null
        ? office.get("description").toString().replace("\"", "")
        : "");
    auditMainDetails.setOfficeType(officeType.get("code") != null
        ? officeType.get("description").toString().replace("\"", "")
        : "");
    auditMainDetails.setOfficeTypeCode(officeType.get("code") != null
        ? officeType.get("code").toString().replace("\"", "")
        : "");
    auditMainDetails.setFirstName(userObject.get("firstName").toString().replace("\"", ""));
    auditMainDetails.setLastName(userObject.get("lastName").toString().replace("\"", ""));
    auditMainDetails.setIpAddress(request.getRemoteAddr());

    Object interceptedObject = new Object();

    if (LogEvent.CREATE.equals(maintLog.logEvent())) {

      interceptedObject = joinPoint.proceed();

      ResponseEntity<Auditable> httpResponseReference = (ResponseEntity<Auditable>) interceptedObject;

      Auditable referenceData = httpResponseReference.getBody();
      auditMainDetails.setUsername(referenceData.getCreatedBy());
      auditMainDetails.setIpAddress(getClientIp(request));
      auditMainDetails
          .setDetails("MAINTENANCE DATA CREATED IN TABLE " + maintLog.maintenanceTypeEnum());
      auditSubDetails.setDescription("MAINTENANCE DATA CREATED : " + referenceData.getId());
      auditSubDetails.setNewValue(new Gson().toJson(referenceData).getBytes());
      auditSubDetails.setOtherDetails("");
    } else if (LogEvent.UPDATE.equals(maintLog.logEvent())) {

      AuditableMaintenanceService<?> auditableMaintenanceService = auditableMaintenanceServiceFactory
          .getMaintenanceService(maintLog.maintenanceTypeEnum());

      Object hrBeforeOps = joinPoint.getArgs()[0];

      Auditable refBeforeOps = (Auditable) hrBeforeOps;

      Auditable objectBeforeOps = (Auditable) auditableMaintenanceService
          .findById(refBeforeOps.getId());
      auditSubDetails.setOldValue(new Gson().toJson(objectBeforeOps).getBytes());
      interceptedObject = joinPoint.proceed();
      ResponseEntity<Auditable> httpResponseReference = (ResponseEntity<Auditable>) interceptedObject;
      Auditable referenceData = httpResponseReference.getBody();
      auditMainDetails.setUsername(referenceData.getUpdatedBy());
      auditMainDetails.setIpAddress(getClientIp(request));
      auditMainDetails
          .setDetails("MAINTENANCE DATA UPDATED IN TABLE " + maintLog.maintenanceTypeEnum());
      auditSubDetails.setDescription("MAINTENANCE DATA UPDATED");
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
