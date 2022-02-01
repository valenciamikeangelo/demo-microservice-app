/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 9:46:01 AM
 */
package com.caista.birapps.etis.domain.trs;

import java.math.BigDecimal;
import com.caista.birapps.etis.domain.trs.tin.entity.PreGeneratedTIN;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class TaxPayerPreGenTIN {

  private Boolean exists;
  private PreGeneratedTIN preGeneratedTIN;
  private BigDecimal checkDigit;

  public Boolean getExists() {
    return exists;
  }

  public void setExists(Boolean exists) {
    this.exists = exists;
  }

  public PreGeneratedTIN getPreGeneratedTIN() {
    return preGeneratedTIN;
  }

  public void setPreGeneratedTIN(PreGeneratedTIN preGeneratedTIN) {
    this.preGeneratedTIN = preGeneratedTIN;
  }

  public TaxPayerPreGenTIN() {
    super();
  }

  public BigDecimal getCheckDigit() {
    return checkDigit;
  }

  public void setCheckDigit(BigDecimal checkDigit) {
    this.checkDigit = checkDigit;
  }

}
