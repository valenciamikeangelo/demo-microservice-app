/*
  * Modified by: obregoj
  * Last updated: Jul 3, 2019 4:56:14 PM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceIndustryGroup;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_INDUSTRY_CLASSIFICATION",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"INDUSTRY_CLASSIFICATION_CODE"},
        name = "UC_MAIN_INDUSTRY_CLASSIFICATION_01")})
@JsonInclude(Include.NON_NULL)
public class MaintIndustryClassification implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "INDUSCLASS", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "INDUSTRY_CLASSIFICATION_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "INDUSTRY_GROUP_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_INDUSTRY_GROUP__MAIN_INDUSTRY_CLASSIFICATION_01"))
  private ReferenceIndustryGroup industryGroup;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_INDUSTRY_PSIC_GROUP",
      joinColumns = @JoinColumn(name = "INDUSTRY_CLASSIFICATION_ID",
          referencedColumnName = "INDUSTRY_CLASSIFICATION_ID",
          foreignKey = @ForeignKey(
              name = "FK_MAIN_INDUSTRY_CLASSIFICATION__MAIN_INDUSTRY_PSIC_GROUP_01")),
      inverseJoinColumns = @JoinColumn(name = "PSIC_GROUP_ID",
          referencedColumnName = "PSIC_GROUP_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_PSIC_GROUP__MAIN_INDUSTRY_PSIC_GROUP_01")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"INDUSTRY_CLASSIFICATION_ID", "PSIC_GROUP_ID"},
              name = "UC_MAIN_INDUST_GROUP_01")})
  private List<MaintPsicGroup> psicGroupList;

  @Column(name = "INDUSTRY_CLASSIFICATION_CODE", nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "INDUSTRY_CLASSIFICATION_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(200)")
  private String description;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", nullable = false, updatable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "INDUSTRY_CLASS", columnDefinition = "VARCHAR2(500)")
  private String industryClass;

  @Column(name = "ISIC3_1", columnDefinition = "VARCHAR2(500)")
  private String isic31;

  @Column(name = "MIT_CODE", columnDefinition = "VARCHAR2(500)")
  private String mitCode;

  @Column(name = "INDTYP_CODE", columnDefinition = "VARCHAR2(500)")
  private String indtypCode;

  @Column(name = "IND_BENCHMARK_IT_RATE", columnDefinition = "NUMBER(14,2)")
  private BigDecimal indBenchmarkItRate;

  @Column(name = "IND_BENCHMARK_VAT_RATE", columnDefinition = "NUMBER(14,2)")
  private BigDecimal indBenchmarkVatRate;

  @Column(name = "ORG_BENCHMARK_IT_RATE", columnDefinition = "NUMBER(14,2)")
  private BigDecimal orgBenchmarkItRate;

  @Column(name = "ORG_BENCHMARK_VAT_RATE", columnDefinition = "NUMBER(14,2)")
  private BigDecimal orgBenchmarkVatRate;

  @Column(name = "LTS_IT_BEN_RATE", columnDefinition = "NUMBER(30,2)")
  private BigDecimal ltsItBenRate;

  @Column(name = "LTS_VAT_BEN_RATE", columnDefinition = "NUMBER(30,2)")
  private BigDecimal ltsVatBenRate;

  @Transient
  private String status;

  public MaintIndustryClassification() {
    super();
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public MaintIndustryClassification(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintIndustryClassification(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintIndustryClassification(String id, String code, String description, String createdBy) {
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }

  public MaintIndustryClassification(String id, ReferenceIndustryGroup industryGroup, String code,
      String description) {
    super();
    this.id = id;
    this.industryGroup = new ReferenceIndustryGroup(industryGroup.getId(), industryGroup.getCode(),
        industryGroup.getDescription());
    this.code = code;
    this.description = description;
  }

  public MaintIndustryClassification(String id, String code, String description, Date effectiveDate,
      Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
      String industryClass, ReferenceIndustryGroup industryGroup, String isic31, String mitCode,
      String indtypCode, BigDecimal indBenchmarkItRate, BigDecimal indBenchmarkVatRate,
      BigDecimal orgBenchmarkItRate, BigDecimal orgBenchmarkVatRate, BigDecimal ltsItBenRate,
      BigDecimal ltsVatBenRate) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.industryClass = industryClass;
    this.industryGroup = industryGroup;
    this.isic31 = isic31;
    this.mitCode = mitCode;
    this.indtypCode = indtypCode;
    this.indBenchmarkItRate = indBenchmarkItRate;
    this.indBenchmarkVatRate = indBenchmarkVatRate;
    this.orgBenchmarkItRate = orgBenchmarkItRate;
    this.orgBenchmarkVatRate = orgBenchmarkVatRate;
    this.ltsItBenRate = ltsItBenRate;
    this.ltsVatBenRate = ltsVatBenRate;
  }

  @Override
  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getIndustryClass() {
    return industryClass;
  }

  public void setIndustryClass(String industryClass) {
    this.industryClass = industryClass;
  }

  public ReferenceIndustryGroup getIndustryGroup() {
    return industryGroup;
  }

  public void setIndustryGroup(ReferenceIndustryGroup industryGroup) {
    this.industryGroup = industryGroup;
  }

  public String getIsic31() {
    return isic31;
  }

  public void setIsic31(String isic31) {
    this.isic31 = isic31;
  }

  public String getMitCode() {
    return mitCode;
  }

  public void setMitCode(String mitCode) {
    this.mitCode = mitCode;
  }

  public String getIndtypCode() {
    return indtypCode;
  }

  public void setIndtypCode(String indtypCode) {
    this.indtypCode = indtypCode;
  }

  public BigDecimal getIndBenchmarkItRate() {
    return indBenchmarkItRate;
  }

  public void setIndBenchmarkItRate(BigDecimal indBenchmarkItRate) {
    this.indBenchmarkItRate = indBenchmarkItRate;
  }

  public BigDecimal getIndBenchmarkVatRate() {
    return indBenchmarkVatRate;
  }

  public void setIndBenchmarkVatRate(BigDecimal indBenchmarkVatRate) {
    this.indBenchmarkVatRate = indBenchmarkVatRate;
  }

  public BigDecimal getOrgBenchmarkItRate() {
    return orgBenchmarkItRate;
  }

  public void setOrgBenchmarkItRate(BigDecimal orgBenchmarkItRate) {
    this.orgBenchmarkItRate = orgBenchmarkItRate;
  }

  public BigDecimal getOrgBenchmarkVatRate() {
    return orgBenchmarkVatRate;
  }

  public void setOrgBenchmarkVatRate(BigDecimal orgBenchmarkVatRate) {
    this.orgBenchmarkVatRate = orgBenchmarkVatRate;
  }

  public BigDecimal getLtsItBenRate() {
    return ltsItBenRate;
  }

  public void setLtsItBenRate(BigDecimal ltsItBenRate) {
    this.ltsItBenRate = ltsItBenRate;
  }

  public BigDecimal getLtsVatBenRate() {
    return ltsVatBenRate;
  }

  public void setLtsVatBenRate(BigDecimal ltsVatBenRate) {
    this.ltsVatBenRate = ltsVatBenRate;
  }

  public List<MaintPsicGroup> getPsicGroupList() {
    return psicGroupList;
  }

  public void setPsicGroupList(List<MaintPsicGroup> psicGroupList) {
    this.psicGroupList = psicGroupList;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintIndustryClassification [id=" + id + ", code=" + code + ", description="
        + description + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", industryClass=" + industryClass + ", industryGroup="
        + industryGroup + ", isic31=" + isic31 + ", mitCode=" + mitCode + ", indtypCode="
        + indtypCode + ", indBenchmarkItRate=" + indBenchmarkItRate + ", indBenchmarkVatRate="
        + indBenchmarkVatRate + ", orgBenchmarkItRate=" + orgBenchmarkItRate
        + ", orgBenchmarkVatRate=" + orgBenchmarkVatRate + ", ltsItBenRate=" + ltsItBenRate
        + ", ltsVatBenRate=" + ltsVatBenRate + ", psicGroupList=" + psicGroupList + "]";
  }

}
