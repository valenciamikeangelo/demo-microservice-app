/*
 * Modified by: tolentf
 * Last updated: Apr 26, 2020 12:40:15 PM
 */
package com.caista.birapps.etis.common.auditselection;

import java.util.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditSelectionEmailDetail {

  private String createdBy;

  private List<String> recepients;

  private List<String> reportNames;

  private List<String> auditCriterias;

  private String auditProgramName;

  private String region;

  private String rdo;

  private Date runDate;

  private String irisUrl;

  private Boolean simulationFlag = false;

  private String errorDetails;

  public AuditSelectionEmailDetail() {
    super();
  }

  public AuditSelectionEmailDetail(String createdBy, List<String> recepients,
      List<String> reportNames, List<String> auditCriterias, String auditProgramName, String region,
      String rdo, Date runDate, String irisUrl, Boolean simulationFlag, String errorDetails) {
    super();
    this.createdBy = createdBy;
    this.recepients = recepients;
    this.reportNames = reportNames;
    this.auditCriterias = auditCriterias;
    this.auditProgramName = auditProgramName;
    this.region = region;
    this.rdo = rdo;
    this.runDate = runDate;
    this.irisUrl = irisUrl;
    this.simulationFlag = simulationFlag;
    this.errorDetails = errorDetails;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public List<String> getRecepients() {
    return recepients;
  }

  public void setRecepients(List<String> recepients) {
    this.recepients = recepients;
  }

  public List<String> getReportNames() {
    return reportNames;
  }

  public void setReportNames(List<String> reportNames) {
    this.reportNames = reportNames;
  }

  public List<String> getAuditCriterias() {
    return auditCriterias;
  }

  public void setAuditCriterias(List<String> auditCriterias) {
    this.auditCriterias = auditCriterias;
  }

  public String getAuditProgramName() {
    return auditProgramName;
  }

  public void setAuditProgramName(String auditProgramName) {
    this.auditProgramName = auditProgramName;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getRdo() {
    return rdo;
  }

  public void setRdo(String rdo) {
    this.rdo = rdo;
  }

  public Date getRunDate() {
    return runDate;
  }

  public void setRunDate(Date runDate) {
    this.runDate = runDate;
  }

  public String getIrisUrl() {
    return irisUrl;
  }

  public void setIrisUrl(String irisUrl) {
    this.irisUrl = irisUrl;
  }

  public Boolean getSimulationFlag() {
    return simulationFlag;
  }

  public void setSimulationFlag(Boolean simulationFlag) {
    this.simulationFlag = simulationFlag;
  }

  public String getErrorDetails() {
    return errorDetails;
  }

  public void setErrorDetails(String errorDetails) {
    this.errorDetails = errorDetails;
  }

}
