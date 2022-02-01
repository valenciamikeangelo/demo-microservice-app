/*
 * Last modified by: fuentem
 * Last updated date: Jul 19, 2018 3:09:03 PM
 */
package com.caista.birapps.etis.integration.sender.ds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.ds.DataSynchronizationRequest;

/**
 * The Class TRSDataSynchronizationSender.
 */
@Component
public class TRSDataSynchronizationSender {

  /** The json topic. */
  @Value("${kafka.topic.json.ds.trs}")
  private String jsonTopic;

  /** The tax payer DS kafka template. */
  @Autowired
  private KafkaTemplate<String, DataSynchronizationRequest> taxPayerDSKafkaTemplate;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(TRSDataSynchronizationSender.class);

  /**
   * Send.
   *
   * @param auditMessage the audit message
   */
  public void send(DataSynchronizationRequest dsRequest) {
    LOGGER.info("SENDING TRS (DATA TIN: {} BRANCHCODE: {})", dsRequest.getTin(), dsRequest.getBranchCode());
    taxPayerDSKafkaTemplate.send(jsonTopic, dsRequest);
  }
}
