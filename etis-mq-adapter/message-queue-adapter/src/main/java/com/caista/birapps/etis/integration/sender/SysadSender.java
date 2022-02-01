/*
  * Modified by: bacosal
  * Last updated: Jul 6, 2018 11:30:53 AM
  */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.SystemAdministrationAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class SysadSender {

  /** The json topic. */
  @Value("${kafka.topic.json.sysad}")
  private String jsonTopic;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<SystemAdministrationAudit>> sysadKafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(SysadSender.class);

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<SystemAdministrationAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    sysadKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
