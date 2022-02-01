/*
 * Modified by: tolentf
 * Last updated: May 18, 2020 3:51:49 PM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.AuditSelectionAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

@Component
public class AuditSelectionSender {

  @Value("${kafka.topic.json.auditselection}")
  private String jsonTopic;

  @Autowired
  private KafkaTemplate<String, AuditLog<AuditSelectionAudit>> auditSelectionKafkaTemplate;

  @Value("${kafka.topic.json.auditselection.submit.taxpayer.to.cms}")
  private String submitTaxpayerToCmsTopic;

  @Autowired
  private KafkaTemplate<String, Object> submitTaxpayerToCmsKafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(AuditSelectionSender.class);

  public void send(AuditLog<AuditSelectionAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    auditSelectionKafkaTemplate.send(jsonTopic, auditMessage);
  }

  public void submitTaxpayerToCms(Object param) {
    LOGGER.info("sending <" + param + ">");
    submitTaxpayerToCmsKafkaTemplate.send(submitTaxpayerToCmsTopic, param);
  }

}
