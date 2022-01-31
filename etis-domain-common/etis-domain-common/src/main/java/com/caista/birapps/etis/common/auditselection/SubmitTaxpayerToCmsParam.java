/*
 * Modified by: tolentf
 * Last updated: Jun 3, 2020 10:31:52 PM
 */
package com.caista.birapps.etis.common.auditselection;

import java.util.List;
import org.springframework.http.HttpHeaders;

public class SubmitTaxpayerToCmsParam {

  private HttpHeaders headers;
  private List<Long> actualProgramReportIds;
  private Long auditExecutionId;
  private String batchRefId;
  private String submittedBy;

  public SubmitTaxpayerToCmsParam() {
    super();
  }
  public SubmitTaxpayerToCmsParam(HttpHeaders headers, List<Long> actualProgramReportIds,
      Long auditExecutionId, String batchRefId, String submittedBy) {
    super();
    this.headers = headers;
    this.actualProgramReportIds = actualProgramReportIds;
    this.auditExecutionId = auditExecutionId;
    this.batchRefId = batchRefId;
    this.submittedBy = submittedBy;
  }
  public HttpHeaders getHeaders() {
    return headers;
  }
  public void setHeaders(HttpHeaders headers) {
    this.headers = headers;
  }
  public List<Long> getActualProgramReportIds() {
    return actualProgramReportIds;
  }
  public void setActualProgramReportIds(List<Long> actualProgramReportIds) {
    this.actualProgramReportIds = actualProgramReportIds;
  }
  public Long getAuditExecutionId() {
    return auditExecutionId;
  }
  public void setAuditExecutionId(Long auditExecutionId) {
    this.auditExecutionId = auditExecutionId;
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
