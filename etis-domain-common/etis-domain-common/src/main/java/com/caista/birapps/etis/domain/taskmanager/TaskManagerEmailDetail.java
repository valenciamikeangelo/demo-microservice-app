/*
  * Modified by: tolentf
  * Last updated: Feb 26, 2019 10:11:43 AM
  */
package com.caista.birapps.etis.domain.taskmanager;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaskManagerEmailDetail.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskManagerEmailDetail {

  /** The requested by. */
  private UserInfo requestedBy;

  /** The assignee. */
  private UserInfo assignee;

  /** The task number. */
  private String taskNumber;

  /** The task code. */
  private String taskCode;

  /** The task description. */
  private String taskDescription;

  /** The remark. */
  private String remark;

  /** The office. */
  private OfficeInfo office;

  /** The recepients. */
  private List<UserInfo> recepients;

  /**
   * Instantiates a new task manager email detail.
   */
  public TaskManagerEmailDetail() {
    super();
  }

  /**
   * Instantiates a new task manager email detail.
   *
   * @param requestedBy the requested by
   * @param assignee the assignee
   * @param taskNumber the task number
   * @param taskCode the task code
   * @param taskDescription the task description
   * @param remark the remark
   * @param office the office
   * @param recepients the recepients
   */
  public TaskManagerEmailDetail(UserInfo requestedBy, UserInfo assignee, String taskNumber,
      String taskCode, String taskDescription, String remark, OfficeInfo office,
      List<UserInfo> recepients) {
    super();
    this.requestedBy = requestedBy;
    this.assignee = assignee;
    this.taskNumber = taskNumber;
    this.taskCode = taskCode;
    this.taskDescription = taskDescription;
    this.remark = remark;
    this.office = office;
    this.recepients = recepients;
  }

  public UserInfo getRequestedBy() {
    return requestedBy;
  }

  public void setRequestedBy(UserInfo requestedBy) {
    this.requestedBy = requestedBy;
  }

  public UserInfo getAssignee() {
    return assignee;
  }

  public void setAssignee(UserInfo assignee) {
    this.assignee = assignee;
  }

  public String getTaskNumber() {
    return taskNumber;
  }

  public void setTaskNumber(String taskNumber) {
    this.taskNumber = taskNumber;
  }

  public String getTaskCode() {
    return taskCode;
  }

  public void setTaskCode(String taskCode) {
    this.taskCode = taskCode;
  }

  public String getTaskDescription() {
    return taskDescription;
  }

  public void setTaskDescription(String taskDescription) {
    this.taskDescription = taskDescription;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public OfficeInfo getOffice() {
    return office;
  }

  public void setOffice(OfficeInfo office) {
    this.office = office;
  }

  public List<UserInfo> getRecepients() {
    return recepients;
  }

  public void setRecepients(List<UserInfo> recepients) {
    this.recepients = recepients;
  }

  @Override
  public String toString() {
    return "TaskManagerEmailDetail [requestedBy=" + requestedBy + ", assignee=" + assignee
        + ", taskNumber=" + taskNumber + ", taskCode=" + taskCode + ", taskDescription="
        + taskDescription + ", office=" + office + ", recepients=" + recepients + "]";
  }

}
