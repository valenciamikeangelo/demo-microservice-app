/*
  * Modified by: obregoj
  * Last updated: 05 21, 20 11:59:23 AM
  */
package com.caista.birapps.etis.integration.config;

import java.net.InetAddress;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.ObjectUtils;
import com.caista.birapps.etis.common.utils.module.*;
import com.caista.birapps.etis.domain.audit.entity.*;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;
import com.caista.birapps.etis.integration.sender.SystemSender;

@Configuration
public class SystemStartUpAudit {
  private static final Logger LOGGER = LoggerFactory.getLogger(SystemStartUpAudit.class);
  @Autowired
  private Environment env;

  @Autowired
  private SystemSender systemSender;

  @PostConstruct
  private void logSystemStartup() {
    try {

      final String ipAddress = InetAddress.getLocalHost().getHostAddress();
      final String username = System.getProperty("user.name");
      final String schemaName = env.getProperty("spring.datasource.username");
      final String usedDdl = env.getProperty("spring.jpa.hibernate.ddl-auto");
      String contextPath = env.getProperty("server.servlet.context-path");

      if (ObjectUtils.isEmpty(contextPath)) {
        contextPath = env.getProperty("server.contextPath");
      }

      AuditLog<SystemAudit> auditLog = new AuditLog<>();
      AuditMainDetails auditMainDetails = new AuditMainDetails();
      SystemAudit auditSubDetails = new SystemAudit();

      auditMainDetails.setEtisModule(EtisModules.SYSTEM);
      auditMainDetails.setEtisSubModule(EtisSubmodules.NONE);
      auditMainDetails.setLogEvent(LogEvent.SYSTEM_STARTUP);
      auditMainDetails.setLogTime(new Date());
      auditMainDetails.setSourceId(auditSubDetails.getRefAuditId());
      auditMainDetails.setUsername(username);
      auditMainDetails.setIpAddress(ipAddress);

      final String apiName = contextPath.replace("/", "").toUpperCase();
      auditMainDetails.setDetails(apiName.concat(" HAS BEEN STARTED"));

      auditSubDetails.setDescription("AN API SERVICE HAS BEEN STARTED");
      auditSubDetails.setContextPath(contextPath);
      auditSubDetails.setUsedDdl(usedDdl);
      auditSubDetails.setSchemaName(schemaName);

      auditLog.setAuditMainDetails(auditMainDetails);
      auditLog.setSubDetails(auditSubDetails);
      systemSender.send(auditLog);
    } catch (Exception e) {
      LOGGER.error("IRIS MQ - SYSTEM START UP ERROR: ", e);
    }
  }

}
