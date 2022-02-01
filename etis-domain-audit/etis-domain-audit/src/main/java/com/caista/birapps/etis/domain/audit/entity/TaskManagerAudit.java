/*
  * Modified by: tolentf
  * Last updated: Apr 29, 2019 9:44:46 AM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaskManagerAudit.
 *
 * @author tolentf
 */
@Entity
@Table(name = "TASKMANAGER_AUDIT")
@JsonIgnoreProperties
public class TaskManagerAudit implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3241647787574460278L;

  /** The ref audit id. */
  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  /** The tin. */
  @Column(name = "TIN", columnDefinition = "VARCHAR2(10)")
  private String tin;

  /** The branch code. */
  @Column(name = "BRANCH_CODE", columnDefinition = "VARCHAR2(6)")
  private String branchCode;

  /** The taxpayer business name. */
  @Column(name = "TAXPAYER_BUSINESS_NAME", columnDefinition = "VARCHAR2(150)")
  private String taxpayerBusinessName;

  @Column(name = "ASSIGNED_USERS", columnDefinition = "VARCHAR2(1000)")
  private String assignedUsers;

  /** The process instance id. */
  @Column(name = "PROCESS_INSTANCE_ID")
  private String processInstanceId;

  /** The task status. */
  @Column(name = "TASK_STATUS")
  private String taskStatus;

  /** The task code. */
  @Column(name = "TASK_CODE")
  private String taskCode;

  /** The task description. */
  @Column(name = "TASK_DESCRIPTION")
  private String taskDescription;

  /** The process instance. */
  @Column(name = "PROCESS_INSTANCE", columnDefinition = "VARCHAR2(4000)")
  private String processInstance;

  /** The assignee. */
  @Column(name = "ASSIGNEE")
  private String assignee;

  /** The requested by. */
  @Column(name = "REQUESTED_BY")
  private String requestedBy;

  /** The office id. */
  @Column(name = "OFFICE_ID", columnDefinition = "VARCHAR2(20)")
  private String officeId;

  /** The office code. */
  @Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)")
  private String officeCode;

  /** The module. */
  @Column(name = "MODULE", columnDefinition = "VARCHAR2(10)")
  private String module;

  /** The priority. */
  @Column(name = "PRIORITY", columnDefinition = "NUMBER(2)")
  private Integer priority;

  /** The start time. */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "START_TIME", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE")
  private Date startTime;

  /** The transaction date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "TRANSACTION_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE")
  private Date transactionDate;

  /**
   * Instantiates a new task manager audit.
   */
  public TaskManagerAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  public String getRefAuditId() {
    return refAuditId;
  }

  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getTaxpayerBusinessName() {
    return taxpayerBusinessName;
  }

  public void setTaxpayerBusinessName(String taxpayerBusinessName) {
    this.taxpayerBusinessName = taxpayerBusinessName;
  }

  public String getAssignedUsers() {
    return assignedUsers;
  }

  public void setAssignedUsers(String assignedUsers) {
    this.assignedUsers = assignedUsers;
  }

  public String getProcessInstanceId() {
    return processInstanceId;
  }

  public void setProcessInstanceId(String processInstanceId) {
    this.processInstanceId = processInstanceId;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
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

  public String getProcessInstance() {
    return processInstance;
  }

  public void setProcessInstance(String processInstance) {
    this.processInstance = processInstance;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public String getRequestedBy() {
    return requestedBy;
  }

  public void setRequestedBy(String requestedBy) {
    this.requestedBy = requestedBy;
  }

  public String getOfficeId() {
    return officeId;
  }

  public void setOfficeId(String officeId) {
    this.officeId = officeId;
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getModule() {
    return module;
  }

  public void setModule(String module) {
    this.module = module;
  }

  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Date getTransactionDate() {
    return transactionDate;
  }

  public void setTransactionDate(Date transactionDate) {
    this.transactionDate = transactionDate;
  }

}
