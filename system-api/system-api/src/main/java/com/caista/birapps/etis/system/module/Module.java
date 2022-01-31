/*
  * Modified by: logronj
  * Last updated: 03 17, 20 5:59:30 PM
  */
package com.caista.birapps.etis.system.module;

import java.io.Serializable;
import java.util.*;

/**
 * The Class Module.
 */
public class Module implements Cloneable, Serializable {



  /** The client. */
  private String client;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The code. */
  private String code;

  /** The menu icon. */
  private String menuIcon;

  /** The redirect uri. */
  private String redirectUri;

  /** The resource name. */
  private String resourceName;

  /** The require permission. */
  private Boolean requirePermission = Boolean.FALSE;

  /** The sub modules. */
  private List<Module> subModules;

  private List<Module> subResource;

  private Set<String> scopes;

  private Boolean navigationLink = Boolean.TRUE;

  public String getClient() {
    return client;
  }

  public void setClient(String client) {
    this.client = client;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMenuIcon() {
    return menuIcon;
  }

  public void setMenuIcon(String menuIcon) {
    this.menuIcon = menuIcon;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

  public List<Module> getSubModules() {
    return subModules;
  }

  public void setSubModules(List<Module> subModules) {
    this.subModules = subModules;
  }

  public String getResourceName() {
    return resourceName;
  }

  public void setResourceName(String resourceName) {
    this.resourceName = resourceName;
  }

  public boolean isRequirePermission() {
    return requirePermission.booleanValue();
  }

  public void setRequirePermission(Boolean requirePermission) {
    this.requirePermission = requirePermission;
  }

  /**
   * @return the scopes
   */
  public Set<String> getScopes() {
    return scopes;
  }

  /**
   * @param scopes the scopes to set
   */
  public void setScopes(Set<String> scopes) {
    this.scopes = scopes;
  }

  public boolean isNavigationLink() {
    return navigationLink.booleanValue();
  }

  public void setNavigationLink(Boolean navigationLink) {
    this.navigationLink = navigationLink;
  }

  public List<Module> getSubResource() {
    return subResource;
  }

  public void setSubResource(List<Module> subResource) {
    this.subResource = subResource;
  }



}
