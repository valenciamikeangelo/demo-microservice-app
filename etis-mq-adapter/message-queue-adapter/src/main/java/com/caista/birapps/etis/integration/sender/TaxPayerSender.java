/*
 * Last modified by: delmund
 * Last updated date: Jul 19, 2018 3:09:03 PM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.TaxPayerAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

// TODO: Auto-generated Javadoc
/**
 * The Class TaxPayerSender.
 */
@Component
public class TaxPayerSender {

  /** The json topic. */
  @Value("${kafka.topic.json.taxpayer}")
  private String jsonTopic;

  /** The tax payer kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<TaxPayerAudit>> taxPayerKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(TaxPayerSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(AuditLog<TaxPayerAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    taxPayerKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
