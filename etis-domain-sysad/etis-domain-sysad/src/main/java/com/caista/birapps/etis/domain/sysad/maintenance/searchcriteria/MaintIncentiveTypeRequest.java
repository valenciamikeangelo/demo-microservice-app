/*
  * Modified by: sarmier
  * Last updated: Jan 24, 2019 4:30:07 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author sarmier
 *
 */
@JsonInclude(Include.NON_NULL)
public class MaintIncentiveTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String classificationCode;
  private String legalBasisCode;

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

  public String getClassificationCode() {
    return classificationCode;
  }

  public void setClassificationCode(String classificationCode) {
    this.classificationCode = classificationCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getLegalBasisCode() {
    return legalBasisCode;
  }

  public void setLegalBasisCode(String legalBasisCode) {
    this.legalBasisCode = legalBasisCode;
  }

  @Override
  public String toString() {
    return "MaintIncentiveTypeRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", classificationCode=" + classificationCode
        + ", legalBasisCode=" + legalBasisCode + "]";
  }



}
