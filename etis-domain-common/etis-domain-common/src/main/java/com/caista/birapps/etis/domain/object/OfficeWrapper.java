/*
  * Modified by: obregoj
  * Last updated: Oct 29, 2018 10:39:39 AM
  */
package com.caista.birapps.etis.domain.object;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OfficeWrapper implements Serializable {
  private Long id;
  private Long primaryOfficeId;
  private String code;
  private String description;
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPrimaryOfficeId() {
    return primaryOfficeId;
  }

  public void setPrimaryOfficeId(Long primaryOfficeId) {
    this.primaryOfficeId = primaryOfficeId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OfficeWrapper() {
    super();
  }

  public OfficeWrapper(Long id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  @Override
  public String toString() {
    return "OfficeWrapper [id=" + id + ", code=" + code + ", description=" + description + "]";
  }


}
