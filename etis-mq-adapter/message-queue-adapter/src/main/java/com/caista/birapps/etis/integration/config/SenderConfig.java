/*
  * Modified by: obregoj
  * Last updated: 07 23, 20 12:02:43 PM
  */
package com.caista.birapps.etis.integration.config;

import java.util.*;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import com.caista.birapps.etis.domain.audit.entity.*;
import com.caista.birapps.etis.domain.audit.entity.wrapper.AuditLog;
import com.caista.birapps.etis.domain.ds.DataSynchronizationRequest;
import com.caista.birapps.etis.integration.sender.*;
import com.caista.birapps.etis.integration.sender.ds.*;

/**
 * The Class SenderConfig.
 */
@Configuration
@PropertySource(value = "classpath:kafka.properties")
public class SenderConfig {

  /** The bootstrap servers. */
  @Value("${kafka.bootstrap-servers}")
  private List<String> bootstrapServers;

  /**
   * Producer configs.
   *
   * @return the map
   */
  @Bean
  public Map<String, Object> producerConfigs() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    return props;
  }

  // SYSAD AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<SystemAdministrationAudit>> sysadProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<SystemAdministrationAudit>> sysadKafkaTemplate() {
    return new KafkaTemplate<>(sysadProducer());
  }

  // END OF SYSAD AUDIT CONFIGURATION

  // AUDIT SELECTION AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<AuditSelectionAudit>> auditSelectionProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<AuditSelectionAudit>> auditSelectionKafkaTemplate() {
    return new KafkaTemplate<>(auditSelectionProducer());
  }

  @Bean
  public ProducerFactory<String, Object> submitTaxpayerToCmsProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, Object> submitTaxpayerToCmsKafkaTemplate() {
    return new KafkaTemplate<>(submitTaxpayerToCmsProducer());
  }

  // AUDIT SELECTION CONFIGURATION

  // TASK MANAGER CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<TaskManagerAudit>> taskManagerProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<TaskManagerAudit>> taskManagerKafkaTemplate() {
    return new KafkaTemplate<>(taskManagerProducer());
  }

  // END OF TASK MANAGER CONFIGURATION

  // TAXPAYER CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<TaxPayerAudit>> taxPayerProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<TaxPayerAudit>> taxPayerKafkaTemplate() {
    return new KafkaTemplate<>(taxPayerProducer());
  }

  // BATCH
  @Bean
  public ProducerFactory<String, AuditLog<BatchAudit>> batchProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<BatchAudit>> batchKafkaTemplate() {
    return new KafkaTemplate<>(batchProducer());
  }

  @Bean
  public ProducerFactory<String, DataSynchronizationRequest> taxPayerDSProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, DataSynchronizationRequest> taxPayerDSKafkaTemplate() {
    return new KafkaTemplate<>(taxPayerDSProducer());
  }

  // END OF TAXPAYER CONFIGURATION

  // ATP CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<ATPAudit>> atpProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<ATPAudit>> atpKafkaTemplate() {
    return new KafkaTemplate<>(atpProducer());
  }

  // END OF ATP CONFIGURATION

  // REPORT CONFIGURATION

  @Bean
  public ProducerFactory<String, AuditLog<ReportAudit>> reportProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<ReportAudit>> reportKafkaTemplate() {
    return new KafkaTemplate<>(reportProducer());
  }

  // END OF REPORT CONFIGURATION

  // CRR CONFIGURATION

  @Bean
  public ProducerFactory<String, AuditLog<CrrAudit>> crrProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<CrrAudit>> crrKafkaTemplate() {
    return new KafkaTemplate<>(crrProducer());
  }

  // END OF REPORT CONFIGURATION

  // TCR CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<TaxCreditRefundAudit>> tcrProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<TaxCreditRefundAudit>> tcrKafkaTemplate() {
    return new KafkaTemplate<>(tcrProducer());
  }

  // END OF TCR CONFIGURATION

  // TCS CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<TaxComplianceAudit>> tcsProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<TaxComplianceAudit>> tcsKafkaTemplate() {
    return new KafkaTemplate<>(tcsProducer());
  }

  // END OF TCS CONFIGURATION

  // PUDS MONITORING CONFIGURATION

  // RFP AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<RfpAudit>> rfpProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<RfpAudit>> rfpKafkaTemplate() {
    return new KafkaTemplate<>(rfpProducer());
  }

  // END OF RFP AUDIT CONFIGURATION

  @Bean
  public ProducerFactory<String, PudsParameters> pudsMonitoringProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, PudsParameters> pudsMonitoringKafkaTemplate() {
    return new KafkaTemplate<>(pudsMonitoringProducer());
  }

  @Bean
  public ProducerFactory<String, PudsRtuParameters> pudsRtuProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, PudsRtuParameters> pudsRtusKafkaTemplate() {
    return new KafkaTemplate<>(pudsRtuProducer());
  }

  // PUDS MONITORING CONFIGURATION

  // TAS AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<TaxpayerAccountingSystemAudit>> tasProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<TaxpayerAccountingSystemAudit>> tasKafkaTemplate() {
    return new KafkaTemplate<>(tasProducer());
  }
  // END OF TAS AUDIT CONFIGURATION

  // SYSTEM AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<SystemAudit>> systemProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<SystemAudit>> systemKafkaTemplate() {
    return new KafkaTemplate<>(systemProducer());
  }
  // SYSTEM OF TAS AUDIT CONFIGURATION

  // CMS AUDIT CONFIGURATION
  @Bean
  public ProducerFactory<String, AuditLog<CmsAudit>> cmsProducer() {
    return new DefaultKafkaProducerFactory<>(producerConfigs());
  }

  @Bean
  public KafkaTemplate<String, AuditLog<CmsAudit>> cmsKafkaTemplate() {
    return new KafkaTemplate<>(cmsProducer());
  }
  // CMS AUDIT CONFIGURATION

  /**
   * Sender.
   *
   * @return the sender
   */
  @Bean
  public BatchSender batchSender() {
    return new BatchSender();
  }

  @Bean
  public AuditSelectionSender auditSelectionSender() {
    return new AuditSelectionSender();
  }

  @Bean
  public SysadSender sysadSender() {
    return new SysadSender();
  }

  @Bean
  public TaskManagerSender taskManagerSender() {
    return new TaskManagerSender();
  }

  @Bean
  public TaxPayerSender taxPayerSender() {
    return new TaxPayerSender();
  }

  @Bean
  public ATPSender atpSender() {
    return new ATPSender();
  }

  @Bean
  public ReportSender reportSender() {
    return new ReportSender();
  }

  @Bean
  public CrrSender crrSender() {
    return new CrrSender();
  }

  @Bean
  public TcrSender tcrSender() {
    return new TcrSender();
  }

  @Bean
  public TcsSender tcsSender() {
    return new TcsSender();
  }

  @Bean
  public PudsMonitoringSender pudsMonitoringSender() {
    return new PudsMonitoringSender();
  }

  @Bean
  public PudsRealTimeUpdateSender pudsRealTimeUpdateSender() {
    return new PudsRealTimeUpdateSender();
  }

  @Bean
  public RfpSender rfpSender() {
    return new RfpSender();
  }

  @Bean
  public TasSender tasSender() {
    return new TasSender();
  }

  @Bean
  public SystemSender systemSender() {
    return new SystemSender();
  }

  @Bean
  public CmsSender cmsSender() {
    return new CmsSender();
  }

}
