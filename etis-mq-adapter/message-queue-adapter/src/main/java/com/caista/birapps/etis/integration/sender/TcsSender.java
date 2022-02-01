/*
 * Modified by: tolentf
 * Last updated: Aug 16, 2020 8:27:09 PM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.TaxComplianceAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class TcsSender {

  /** The json topic. */
  @Value("${kafka.topic.json.tcs}")
  private String jsonTopic;

  @Value("${kafka.topic.json.tcs.batch.request}")
  private String tcsBatchRequest;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<TaxComplianceAudit>> tcsKafkaTemplate;

  @Autowired
  private KafkaTemplate<String, Object> genericRequest;

  private static final Logger LOGGER = LoggerFactory.getLogger(TcsSender.class);

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<TaxComplianceAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    tcsKafkaTemplate.send(jsonTopic, auditMessage);
  }

  public void sendTcsBatchRequest(Object param) {
    LOGGER.info("sending Tcs Batch request <" + param + ">");
    genericRequest.send(tcsBatchRequest, param);
  }
}
