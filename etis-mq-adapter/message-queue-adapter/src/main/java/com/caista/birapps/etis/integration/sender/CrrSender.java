/*
  * Modified by: santosj
  * Last updated: Sep 26, 2018 11:41:08 AM
  */
package com.caista.birapps.etis.integration.sender;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.caista.birapps.etis.domain.audit.entity.CrrAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

@Component
public class CrrSender {

	/** The json topic. */
	@Value("${kafka.topic.json.crr}")
	private String jsonTopic;

	/** The report kafka template. */
	@Autowired
	private KafkaTemplate<String, AuditLog<CrrAudit>> crrKafkaTemplate;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(CrrSender.class);

	/**
	 * Send.
	 *
	 * @param auditMessage
	 *            the audit message
	 */
	public void send(AuditLog<CrrAudit> auditMessage) {
		LOGGER.info("sending <" + auditMessage + ">");
		crrKafkaTemplate.send(jsonTopic, auditMessage);
	}

}
