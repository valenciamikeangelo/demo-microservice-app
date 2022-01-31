/*
  * Modified by: decinam
  * Last updated: Apr 23, 2019 2:46:55 PM
  */

package com.caista.birapps.etis.common.tcs;

import java.io.Serializable;
import java.util.Date;

public class ReturnPeriodResponse implements Serializable {

  private static final long serialVersionUID = 1L;

  private Date filingStartDate;

  private String filingDueDate;

  public ReturnPeriodResponse() {
    super();
  }

  public ReturnPeriodResponse(Date filingStartDate, String filingDueDate) {
    super();
    this.filingStartDate = filingStartDate;
    this.filingDueDate = filingDueDate;
  }

  public Date getFilingStartDate() {
    return filingStartDate;
  }

  public void setFilingStartDate(Date filingStartDate) {
    this.filingStartDate = filingStartDate;
  }

  public String getFilingDueDate() {
    return filingDueDate;
  }

  public void setFilingDueDate(String filingDueDate) {
    this.filingDueDate = filingDueDate;
  }

  @Override
  public String toString() {
    return "ReturnPeriodResponse [filingStartDate=" + filingStartDate
        + ", filingDueDate=" + filingDueDate + "]";
  }

}
