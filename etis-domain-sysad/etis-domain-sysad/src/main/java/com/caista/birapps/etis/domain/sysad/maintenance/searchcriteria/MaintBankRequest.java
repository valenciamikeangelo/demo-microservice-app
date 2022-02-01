/*
 * Last modified by: tans
 * Last updated date: Sep 26, 2018 7:38:08 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintBankRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String governmentDepositBankFlag;
  private String statusFlag;

  public MaintBankRequest() {
    super();
  }

  public MaintBankRequest(String code, String description, String governmentDepositBankFlag,
      String statusFlag) {
    super();
    this.code = code;
    this.description = description;
    this.governmentDepositBankFlag = governmentDepositBankFlag;
    this.statusFlag = statusFlag;
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

  public String getGovernmentDepositBankFlag() {
    return governmentDepositBankFlag;
  }

  public void setGovernmentDepositBankFlag(String governmentDepositBankFlag) {
    this.governmentDepositBankFlag = governmentDepositBankFlag;
  }

  public String getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(String statusFlag) {
    this.statusFlag = statusFlag;
  }

  @Override
  public String toString() {
    return "MaintBankRequest [code=" + code + ", description=" + description
        + ", governmentDepositBankFlag=" + governmentDepositBankFlag + ", statusFlag=" + statusFlag
        + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0 : code.hashCode());
    result = prime * result + ((description == null)
        ? 0 : description.hashCode());
    result = prime * result + ((governmentDepositBankFlag == null)
        ? 0 : governmentDepositBankFlag.hashCode());
    result = prime * result + ((statusFlag == null)
        ? 0 : statusFlag.hashCode());
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
    MaintBankRequest other = (MaintBankRequest) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (governmentDepositBankFlag == null) {
      if (other.governmentDepositBankFlag != null)
        return false;
    } else if (!governmentDepositBankFlag.equals(other.governmentDepositBankFlag))
      return false;
    if (statusFlag == null) {
      if (other.statusFlag != null)
        return false;
    } else if (!statusFlag.equals(other.statusFlag))
      return false;
    return true;
  }

}
