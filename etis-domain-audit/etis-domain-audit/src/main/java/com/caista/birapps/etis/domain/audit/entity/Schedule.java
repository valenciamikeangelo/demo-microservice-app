/*
  * Modified by: decinam
  * Last updated: Jun 13, 2019 10:37:56 AM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;

public class Schedule implements Serializable {
  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String periodFrom;
  private String periodTo;

  public Schedule() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Schedule(String periodFrom, String periodTo) {
    super();
    this.periodFrom = periodFrom;
    this.periodTo = periodTo;
  }

  public String getPeriodFrom() {
    return periodFrom;
  }

  public void setPeriodFrom(String periodFrom) {
    this.periodFrom = periodFrom;
  }

  public String getPeriodTo() {
    return periodTo;
  }

  public void setPeriodTo(String periodTo) {
    this.periodTo = periodTo;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "Schedule [periodFrom=" + periodFrom + ", periodTo=" + periodTo + "]";
  }
}
