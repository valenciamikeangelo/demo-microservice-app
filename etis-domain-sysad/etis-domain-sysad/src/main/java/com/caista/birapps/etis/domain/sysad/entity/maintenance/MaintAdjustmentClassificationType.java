/*
  * Modified by: obregoj
  * Last updated: Dec 4, 2019 10:32:03 AM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_ADJUSTMENT_CLASSIFICATION_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"ADJUSTMENT_CLASSIFICATION_TYPE_ID"},
        name = "UC_MAIN_ADJUSTMENT_CLASSIFICATION_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintAdjustmentClassificationType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "MACT", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "ADJUSTMENT_CLASSIFICATION_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "ADJUSTMENT_CLASSIFICATION_TYPE_CODE", nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "ADJUSTMENT_CLASSIFICATION_TYPE_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(500)")
  private String description;


  public MaintAdjustmentClassificationType() {
    super();
  }


  public MaintAdjustmentClassificationType(String id, String code, String description,
      int sequence) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }


  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
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

  @Override
  public String toString() {
    return "MaintAdjustmentClassificationType [id=" + id + ", code=" + code + ", description="
        + description + "]";
  }


}
