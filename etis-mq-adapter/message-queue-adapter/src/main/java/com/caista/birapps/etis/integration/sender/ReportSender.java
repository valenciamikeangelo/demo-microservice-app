/*
* Modified by: gamboam
* Last updated: Aug 8, 2018 12:06:47 PM
*/
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.ReportAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class ReportSender.
 */
@Component
public class ReportSender {

  /** The json topic. */
  @Value("${kafka.topic.json.report}")
  private String jsonTopic;

  /** The report kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<ReportAudit>> reportKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(ReportSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(AuditLog<ReportAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    reportKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
