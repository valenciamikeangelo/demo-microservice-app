/*
  * Modified by: obregoj
  * Last updated: 01 16, 20 1:24:33 PM
  */
package com.caista.birapps.etis.integration.sender;

import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.SystemAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class SystemSender {

  /** The json topic. */
  @Value("${kafka.topic.json.system}")
  private String jsonTopic;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<SystemAudit>> sysadKafkaTemplate;

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<SystemAudit> auditMessage) {
    sysadKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
