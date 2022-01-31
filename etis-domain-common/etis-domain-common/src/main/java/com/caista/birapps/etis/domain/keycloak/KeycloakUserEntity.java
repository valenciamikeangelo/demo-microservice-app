/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:00:50 AM
  */
package com.caista.birapps.etis.domain.keycloak;

public class KeycloakUserEntity {

  private String id;
  private String email;
  private String firstName;
  private String middleName;
  private String lastName;
  private String username;

  public KeycloakUserEntity() {
    super();
    // TODO Auto-generated constructor stub
  }

  public KeycloakUserEntity(String id, String email, String firstName, String middleName,
      String lastName, String username) {
    super();
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.username = username;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

}
