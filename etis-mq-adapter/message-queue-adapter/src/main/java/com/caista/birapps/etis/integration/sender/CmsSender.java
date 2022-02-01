/*
 * Modified by: obregoj Last updated: 07 23, 20 11:39:25 AM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.CmsAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

@Component
public class CmsSender {

  /** The json topic. */
  @Value("${kafka.topic.json.cms}")
  private String jsonTopic;

  /** The report kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<CmsAudit>> cmsKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CmsSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(AuditLog<CmsAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    cmsKafkaTemplate.send(jsonTopic, auditMessage);
  }

}
