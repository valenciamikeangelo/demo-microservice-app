/*
 * Modified by: fuentem
 * Last updated: Aug 21, 2018 6:15:09 PM
 */
package com.caista.birapps.etis.domain.audit.paginated;

import java.util.List;

public class PaginatedSearchResponse<T> {

  private List<T> records;
  private Integer recordsTotal;

  public PaginatedSearchResponse() {
    super();
  }

  public PaginatedSearchResponse(List<T> records, Integer recordsTotal) {
    super();
    this.records = records;
    this.recordsTotal = recordsTotal;
  }

  public List<T> getRecords() {
    return records;
  }

  public void setRecords(List<T> records) {
    this.records = records;
  }

  public Integer getRecordsTotal() {
    return recordsTotal;
  }

  public void setRecordsTotal(Integer recordsTotal) {
    this.recordsTotal = recordsTotal;
  }


}
