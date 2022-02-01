/*
  * Modified by: tolentf
  * Last updated: Mar 28, 2019 1:44:45 PM
  */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.TaxCreditRefundAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class TcrSender {

  /** The json topic. */
  @Value("${kafka.topic.json.tcr}")
  private String jsonTopic;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<TaxCreditRefundAudit>> tcrKafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(TcrSender.class);

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<TaxCreditRefundAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    tcrKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
