/*
  * Modified by: romerov
  * Last updated: 02 28, 20 6:53:32 PM
*/
package com.caista.birapps.etis.domain.trs.taxpayer;

import java.io.Serializable;
import java.util.List;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UpdateTaxpayerRequest implements Serializable{

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private TaxPayer taxpayer;
  private List<UpdateTaxpayerSection> updatedSections;


  public TaxPayer getTaxpayer() {
    return taxpayer;
  }

  public void setTaxpayer(TaxPayer taxpayer) {
    this.taxpayer = taxpayer;
  }

  public List<UpdateTaxpayerSection> getUpdatedSections() {
    return updatedSections;
  }

  public void setUpdatedSections(List<UpdateTaxpayerSection> updatedSections) {
    this.updatedSections = updatedSections;
  }

}
