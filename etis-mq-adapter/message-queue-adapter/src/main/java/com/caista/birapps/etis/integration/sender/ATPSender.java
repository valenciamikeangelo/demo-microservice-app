/*
 * Last modified by: delmund
 * Last updated date: Jul 19, 2018 3:09:00 PM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.ATPAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

// TODO: Auto-generated Javadoc
/**
 * The Class ATPSender.
 */
@Component
public class ATPSender {

  /** The json topic. */
  @Value("${kafka.topic.json.atp}")
  private String jsonTopic;

  /** The atp kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<ATPAudit>> atpKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ATPSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(AuditLog<ATPAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    atpKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
