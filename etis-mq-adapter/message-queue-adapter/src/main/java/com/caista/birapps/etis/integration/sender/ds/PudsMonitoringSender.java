/*
  * Modified by: decinam
  * Last updated: May 31, 2019 3:01:37 PM
  */
package com.caista.birapps.etis.integration.sender.ds;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.PudsParameters;

/**
 * The Class PudsMonitoringSender.
 */
@Component
public class PudsMonitoringSender {

  /** The json topic. */
  @Value("${kafka.topic.json.puds.monitoring}")
  private String jsonTopic;

  /** The tax payer DS kafka template. */
  @Autowired
  private KafkaTemplate<String, PudsParameters> pudsParametersKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(PudsMonitoringSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(PudsParameters pudsParameters) {
    LOGGER.info("Sending : " + pudsParameters);
    pudsParametersKafkaTemplate.send(jsonTopic, pudsParameters);
  }
}
