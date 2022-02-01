package com.caista.birapps.etis.integration.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.caista.birapps.etis.domain.audit.entity.TaxpayerAccountingSystemAudit;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;

@Component
public class TasSender {

	@Value("${kafka.topic.json.tas}")
	private String jsonTopic;

	@Autowired
	private KafkaTemplate<String, AuditLog<TaxpayerAccountingSystemAudit>> tasKafkaTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(TasSender.class);

	public void send(AuditLog<TaxpayerAccountingSystemAudit> auditMessage) {
		LOGGER.info("sending <" + auditMessage + ">");
		tasKafkaTemplate.send(jsonTopic, auditMessage);
	}
}
