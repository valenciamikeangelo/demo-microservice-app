/*
 * Modified by: fuentem
 * Last updated: Oct 19, 2018 6:58:20 PM
 */
package com.caista.birapps.etis.domain.trs;

public class ReportsGenerated {

  private String ocn;
  private String correspondence;

  public ReportsGenerated() {
    super();
  }

  public ReportsGenerated(String ocn, String correspondence) {
    super();
    this.ocn = ocn;
    this.correspondence = correspondence;
  }


  public String getOcn() {
    return ocn;
  }


  public void setOcn(String ocn) {
    this.ocn = ocn;
  }


  public String getCorrespondence() {
    return correspondence;
  }


  public void setCorrespondence(String correspondence) {
    this.correspondence = correspondence;
  }

}
