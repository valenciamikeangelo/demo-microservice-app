/*
  * Modified by: bongalr
  * Last updated: Aug 28, 2019 3:37:38 PM
  */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.BatchAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class BatchSender {

  /** The json topic. */
  @Value("${kafka.topic.json.batchinteg}")
  private String jsonTopic;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<BatchAudit>> batchKafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(BatchSender.class);

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<BatchAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    batchKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
