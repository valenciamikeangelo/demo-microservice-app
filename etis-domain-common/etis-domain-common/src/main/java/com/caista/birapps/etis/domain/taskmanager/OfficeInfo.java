/*
  * Modified by: tolentf
  * Last updated: Apr 27, 2019 2:03:44 AM
  */
package com.caista.birapps.etis.domain.taskmanager;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class UserInfo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OfficeInfo implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private Long id;

  /** The code. */
  private String code;

  /** The description. */
  private String description;

  /** The email. */
  private String email;

  // String representation of Long Id
  private String idV2;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getIdV2() {
    idV2 = String.valueOf(id);
    return idV2;
  }

}
