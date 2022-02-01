/*
  * Modified by: decinam
  * Last updated: May 31, 2019 3:01:41 PM
  */
package com.caista.birapps.etis.integration.sender.ds;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.PudsRtuParameters;

/**
 * The Class PudsRealTimeUpdateSender.
 */
@Component
public class PudsRealTimeUpdateSender {

  /** The json topic. */
  @Value("${kafka.topic.json.puds.rtu}")
  private String jsonTopic;

  /** The tax payer DS kafka template. */
  @Autowired
  private KafkaTemplate<String, PudsRtuParameters> pudsRtuParametersKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(PudsRealTimeUpdateSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(PudsRtuParameters pudsRtuParameters) {
    LOGGER.info("Sending : " + pudsRtuParameters);
    pudsRtuParametersKafkaTemplate.send(jsonTopic, pudsRtuParameters);
  }
}
