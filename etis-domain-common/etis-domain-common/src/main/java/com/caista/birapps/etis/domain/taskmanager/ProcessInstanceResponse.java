/*
  * Modified by: santosj
  * Last updated: Sep 14, 2018 10:37:12 AM
  */
package com.caista.birapps.etis.domain.taskmanager;

/**
 * The Class ProcessInstanceResponse.
 */
public class ProcessInstanceResponse {

	/** The status. */
	private Integer status;

	/** The is success. */
	private Boolean isSuccess;

	private String taskNumber;
	
	/** The t. */
	private Throwable t;

	/** The error message. */
	private String errorMessage;

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the isSuccess
	 */
	public Boolean getIsSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess
	 *            the isSuccess to set
	 */
	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the t
	 */
	public Throwable getT() {
		return t;
	}

	/**
	 * @param t
	 *            the t to set
	 */
	public void setT(Throwable t) {
		this.t = t;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
