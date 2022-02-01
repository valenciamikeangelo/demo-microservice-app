/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 5:49:26 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;

@Entity
@Table(name = "MAIN_SPECIAL_CODE_PER_BTPC",
    uniqueConstraints = {@UniqueConstraint(
        columnNames = {"MAIN_SPECIAL_CODE_PER_BTPC_ID", "SPECIAL_CODE_ID", "IS_BRANCH"},
        name = "UC_MAIN_SPECIAL_CODE_PER_BTPC_01")},
    indexes = {
        @Index(columnList = "MAIN_SPECIAL_CODE_PER_BTPC_ID,SPECIAL_CODE_ID,IS_BRANCH",
            name = "IDX_MAIN_SPECIAL_CODE_PER_BTPC_ID_01")})
public class MaintSpecialCodeBranchPerTaxpayerClassification implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "MAIN_SPECIAL_CODE_PER_BTPC_ID", nullable = false, updatable = false)
  private String id;

  @ManyToOne
  @JoinColumn(name = "SPECIAL_CODE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_SPECIAL_CODE__MAINT_SPECIAL_CODE_PER_BTPC_01"))
  private MaintSpecialCode specialCode;

  @Column(name = "IS_BRANCH")
  private boolean isBranch;

  @ManyToOne
  @JoinColumn(name = "TAXPAYER_CLASSIFICATION_ID", nullable = false, foreignKey = @ForeignKey(
      name = "FK_REF_TAXPAYER_CLASSIFICATION__MAINT_SPECIAL_CODE_PER_BTPC_01"))
  private ReferenceTaxpayerClassification taxpayerClassification;

  public MaintSpecialCodeBranchPerTaxpayerClassification() {
    super();
    this.id = UUID.randomUUID().toString();
  }

  public MaintSpecialCodeBranchPerTaxpayerClassification(String id, MaintSpecialCode specialCode,
      boolean isBranch, ReferenceTaxpayerClassification taxpayerClassification) {
    super();
    this.id = id;
    this.specialCode = specialCode;
    this.isBranch = isBranch;
    this.taxpayerClassification = taxpayerClassification;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MaintSpecialCode getSpecialCode() {
    return specialCode;
  }

  public void setSpecialCode(MaintSpecialCode specialCode) {
    this.specialCode = specialCode;
  }

  public boolean isBranch() {
    return isBranch;
  }

  public void setBranch(boolean isBranch) {
    this.isBranch = isBranch;
  }

  public ReferenceTaxpayerClassification getTaxpayerClassification() {
    return taxpayerClassification;
  }

  public void setTaxpayerClassification(ReferenceTaxpayerClassification taxpayerClassification) {
    this.taxpayerClassification = taxpayerClassification;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintSpecialCodeBranchPerTaxpayerClassification [id=" + id + ", specialCode="
        + specialCode + ", isBranch=" + isBranch + ", taxpayerClassification="
        + taxpayerClassification + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null)
        ? 0
        : id.hashCode());
    result = prime * result + (isBranch
        ? 1231
        : 1237);
    result = prime * result + ((specialCode == null)
        ? 0
        : specialCode.hashCode());
    result = prime * result + ((taxpayerClassification == null)
        ? 0
        : taxpayerClassification.hashCode());
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
    MaintSpecialCodeBranchPerTaxpayerClassification other = (MaintSpecialCodeBranchPerTaxpayerClassification) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (isBranch != other.isBranch)
      return false;
    if (specialCode == null) {
      if (other.specialCode != null)
        return false;
    } else if (!specialCode.equals(other.specialCode))
      return false;
    if (taxpayerClassification == null) {
      if (other.taxpayerClassification != null)
        return false;
    } else if (!taxpayerClassification.equals(other.taxpayerClassification))
      return false;
    return true;
  }



}
