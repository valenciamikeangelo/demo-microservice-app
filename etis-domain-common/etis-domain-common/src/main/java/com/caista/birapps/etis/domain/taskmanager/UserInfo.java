/*
 * Modified by: santojo
 * Last updated: Jul 19, 2019 5:16:00 PM
 */
package com.caista.birapps.etis.domain.taskmanager;

import java.io.Serializable;

/**
 * The Class UserInfo.
 */
public class UserInfo implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  private String id;

  /** The user name. */
  private String username;

  /** The first name. */
  private String firstName;

  /** The last name. */
  private String lastName;

  /** The display name. */
  private String displayName;

  /** The email. */
  private String email;

  /** The primary office. */
  private OfficeInfo office;

  /**
   * Instantiates a new user info.
   */
  public UserInfo() {
    super();
  }

  public UserInfo(String username, String email) {
    super();
    this.username = username;
    this.email = email;
  }

  /**
   * Instantiates a new user info.
   *
   * @param id the id
   * @param username the username
   * @param firstName the first name
   * @param lastName the last name
   * @param displayName the display name
   * @param email the email
   * @param primaryOffice the primary office
   */
  public UserInfo(String id, String username, String firstName, String lastName, String displayName,
      String email, OfficeInfo office) {
    super();
    this.id = id;
    this.username = username;
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.email = email;
    this.office = office;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username.toUpperCase();
  }

  public void setUsername(String username) {
    this.username = username.toUpperCase();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public OfficeInfo getOffice() {
    return office;
  }

  public void setOffice(OfficeInfo office) {
    this.office = office;
  }

  @Override
  public String toString() {
    return "UserInfo [id=" + id + ", username=" + username + ", firstName=" + firstName
        + ", lastName=" + lastName + ", displayName=" + displayName + ", email=" + email
        + ", primaryOffice=" + office + "]";
  }

}
