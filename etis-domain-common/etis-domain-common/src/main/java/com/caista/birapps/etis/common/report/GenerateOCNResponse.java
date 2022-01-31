/*
 * Modified by: fuentem
 * Last updated: Nov 6, 2018 7:12:36 PM
 */
package com.caista.birapps.etis.common.report;

public class GenerateOCNResponse {

  private String ocn;
  private String correspondence;

  public GenerateOCNResponse() {
    super();
  }

  public GenerateOCNResponse(String ocn, String correspondence) {
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

  @Override
  public String toString() {
    return "UserReportOCNResponse [ocn=" + ocn + ", correspondence=" + correspondence + "]";
  }

}
