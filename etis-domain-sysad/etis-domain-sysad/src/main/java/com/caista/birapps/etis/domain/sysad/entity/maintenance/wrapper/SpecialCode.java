/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 12:40:35 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;

public class SpecialCode {

  private MaintSpecialCode maintSpecialCode;
  private List<MaintSpecialCodeBranchPerTaxpayerClassification> tpClassPerBranchList;

  public SpecialCode() {
    super();
  }

  public SpecialCode(MaintSpecialCode maintSpecialCode,
      List<MaintSpecialCodeBranchPerTaxpayerClassification> tpClassPerBranchList) {
    super();
    this.maintSpecialCode = maintSpecialCode;
    this.tpClassPerBranchList = tpClassPerBranchList;
  }

  public MaintSpecialCode getMaintSpecialCode() {
    return maintSpecialCode;
  }


  public void setMaintSpecialCode(MaintSpecialCode maintSpecialCode) {
    this.maintSpecialCode = maintSpecialCode;
  }



  public List<MaintSpecialCodeBranchPerTaxpayerClassification> getTpClassPerBranchList() {
    return tpClassPerBranchList;
  }



  public void setTpClassPerBranchList(
      List<MaintSpecialCodeBranchPerTaxpayerClassification> tpClassPerBranchList) {
    this.tpClassPerBranchList = tpClassPerBranchList;
  }



  @Override
  public String toString() {
    return "SpecialCode [maintSpecialCode=" + maintSpecialCode + ", tpClassPerBranchList="
        + tpClassPerBranchList + "]";
  }



  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((maintSpecialCode == null)
        ? 0
        : maintSpecialCode.hashCode());
    result = prime * result + ((tpClassPerBranchList == null)
        ? 0
        : tpClassPerBranchList.hashCode());
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
    SpecialCode other = (SpecialCode) obj;
    if (maintSpecialCode == null) {
      if (other.maintSpecialCode != null)
        return false;
    } else if (!maintSpecialCode.equals(other.maintSpecialCode))
      return false;
    if (tpClassPerBranchList == null) {
      if (other.tpClassPerBranchList != null)
        return false;
    } else if (!tpClassPerBranchList.equals(other.tpClassPerBranchList))
      return false;
    return true;
  }



}
