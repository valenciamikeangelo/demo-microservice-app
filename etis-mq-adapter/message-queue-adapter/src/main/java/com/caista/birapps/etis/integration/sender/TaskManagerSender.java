/*
 * Modified by: tolentf
 * Last updated: Jul 13, 2018 7:07:31 PM
 */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.audit.entity.TaskManagerAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

/**
 * The Class Sender.
 */

@Component
public class TaskManagerSender {

  /** The json topic. */
  @Value("${kafka.topic.json.taskmanager}")
  private String jsonTopic;

  /** The kafka template. */
  @Autowired
  private KafkaTemplate<String, AuditLog<TaskManagerAudit>> taskManagerKafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(TaskManagerSender.class);

  /**
   * Send.
   *
   * @param mqMessage the mq message
   */
  public void send(AuditLog<TaskManagerAudit> auditMessage) {
    LOGGER.info("sending <" + auditMessage + ">");
    taskManagerKafkaTemplate.send(jsonTopic, auditMessage);
  }
}
