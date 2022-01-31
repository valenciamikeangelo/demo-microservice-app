/*
 * Modified by: tolentf
 * Last updated: Aug 15, 2020 10:01:46 PM
 */
package com.caista.birapps.etis.common.tcs;

import org.springframework.http.HttpHeaders;

public class TampOfficerBatchParam {

  private HttpHeaders headers;
  private String batchRefId;
  private String submittedBy;

  public HttpHeaders getHeaders() {
    return headers;
  }

  public void setHeaders(HttpHeaders headers) {
    this.headers = headers;
  }

  public String getBatchRefId() {
    return batchRefId;
  }

  public void setBatchRefId(String batchRefId) {
    this.batchRefId = batchRefId;
  }

  public String getSubmittedBy() {
    return submittedBy;
  }

  public void setSubmittedBy(String submittedBy) {
    this.submittedBy = submittedBy;
  }

}
