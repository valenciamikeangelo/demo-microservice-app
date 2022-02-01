/*
 * Modified by: santojo
 * Last updated: Oct 5, 2018 5:35:27 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintIndustryClassficationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String industryClass;
  private String industryGroup;
  private String isic31;
  private String mitCode;
  private String indtypCode;
  private BigDecimal indBenchmarkItRate;
  private BigDecimal indBenchmarkVatRate;
  private BigDecimal orgBenchmarkItRate;
  private BigDecimal orgBenchmarkVatRate;
  private BigDecimal ltsItBenRate;
  private BigDecimal ltsVatBenRate;

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

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getIndustryClass() {
    return industryClass;
  }

  public void setIndustryClass(String industryClass) {
    this.industryClass = industryClass;
  }

  public String getIndustryGroup() {
    return industryGroup;
  }

  public void setIndustryGroup(String industryGroup) {
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  @Override
  public String toString() {
    return "MaintIndustryClassficationRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", industryClass=" + industryClass + ", industryGroup="
        + industryGroup + ", isic31=" + isic31 + ", mitCode=" + mitCode + ", indtypCode="
        + indtypCode + ", indBenchmarkItRate=" + indBenchmarkItRate + ", indBenchmarkVatRate="
        + indBenchmarkVatRate + ", orgBenchmarkItRate=" + orgBenchmarkItRate
        + ", orgBenchmarkVatRate=" + orgBenchmarkVatRate + ", ltsItBenRate=" + ltsItBenRate
        + ", ltsVatBenRate=" + ltsVatBenRate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((indBenchmarkItRate == null)
        ? 0
        : indBenchmarkItRate.hashCode());
    result = prime * result + ((indBenchmarkVatRate == null)
        ? 0
        : indBenchmarkVatRate.hashCode());
    result = prime * result + ((indtypCode == null)
        ? 0
        : indtypCode.hashCode());
    result = prime * result + ((industryClass == null)
        ? 0
        : industryClass.hashCode());
    result = prime * result + ((industryGroup == null)
        ? 0
        : industryGroup.hashCode());
    result = prime * result + ((isic31 == null)
        ? 0
        : isic31.hashCode());
    result = prime * result + ((ltsItBenRate == null)
        ? 0
        : ltsItBenRate.hashCode());
    result = prime * result + ((ltsVatBenRate == null)
        ? 0
        : ltsVatBenRate.hashCode());
    result = prime * result + ((mitCode == null)
        ? 0
        : mitCode.hashCode());
    result = prime * result + ((orgBenchmarkItRate == null)
        ? 0
        : orgBenchmarkItRate.hashCode());
    result = prime * result + ((orgBenchmarkVatRate == null)
        ? 0
        : orgBenchmarkVatRate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MaintIndustryClassficationRequest other = (MaintIndustryClassficationRequest) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (indBenchmarkItRate == null) {
      if (other.indBenchmarkItRate != null)
        return false;
    } else if (!indBenchmarkItRate.equals(other.indBenchmarkItRate))
      return false;
    if (indBenchmarkVatRate == null) {
      if (other.indBenchmarkVatRate != null)
        return false;
    } else if (!indBenchmarkVatRate.equals(other.indBenchmarkVatRate))
      return false;
    if (indtypCode == null) {
      if (other.indtypCode != null)
        return false;
    } else if (!indtypCode.equals(other.indtypCode))
      return false;
    if (industryClass == null) {
      if (other.industryClass != null)
        return false;
    } else if (!industryClass.equals(other.industryClass))
      return false;
    if (industryGroup == null) {
      if (other.industryGroup != null)
        return false;
    } else if (!industryGroup.equals(other.industryGroup))
      return false;
    if (isic31 == null) {
      if (other.isic31 != null)
        return false;
    } else if (!isic31.equals(other.isic31))
      return false;
    if (ltsItBenRate == null) {
      if (other.ltsItBenRate != null)
        return false;
    } else if (!ltsItBenRate.equals(other.ltsItBenRate))
      return false;
    if (ltsVatBenRate == null) {
      if (other.ltsVatBenRate != null)
        return false;
    } else if (!ltsVatBenRate.equals(other.ltsVatBenRate))
      return false;
    if (mitCode == null) {
      if (other.mitCode != null)
        return false;
    } else if (!mitCode.equals(other.mitCode))
      return false;
    if (orgBenchmarkItRate == null) {
      if (other.orgBenchmarkItRate != null)
        return false;
    } else if (!orgBenchmarkItRate.equals(other.orgBenchmarkItRate))
      return false;
    if (orgBenchmarkVatRate == null) {
      if (other.orgBenchmarkVatRate != null)
        return false;
    } else if (!orgBenchmarkVatRate.equals(other.orgBenchmarkVatRate))
      return false;
    return true;
  }

}
