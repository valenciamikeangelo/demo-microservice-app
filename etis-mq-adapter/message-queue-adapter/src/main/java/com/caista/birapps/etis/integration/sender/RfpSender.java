package com.caista.birapps.etis.integration.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.caista.birapps.etis.domain.audit.entity.RfpAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

@Component
public class RfpSender {
	/** The json topic. */
	@Value("${kafka.topic.json.rfp}")
	private String jsonTopic;

	/** The kafka template. */
	@Autowired
	private KafkaTemplate<String, AuditLog<RfpAudit>> rfpKafkaTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(RfpSender.class);

	/**
	 * Send.
	 *
	 * @param mqMessage
	 *            the mq message
	 */
	public void send(AuditLog<RfpAudit> auditMessage) {
		LOGGER.info("sending <" + auditMessage + ">");
		rfpKafkaTemplate.send(jsonTopic, auditMessage);
	}
}
