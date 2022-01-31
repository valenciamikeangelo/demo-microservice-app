package com.caista.birapps.etis.domain.ds;

import java.util.Map;

public class DsDynamicDTO {

  private Object object;
  private Map<String, Object> properties;

  public DsDynamicDTO() {
    super();
  }
  
  public DsDynamicDTO(Object object, Map<String, Object> properties) {
    super();
    this.object = object;
    this.properties = properties;
  }

  public Object getObject() {
    return object;
  }

  public void setObject(Object object) {
    this.object = object;
  }

  public Map<String, Object> getProperties() {
    return properties;
  }

  public void setProperties(Map<String, Object> properties) {
    this.properties = properties;
  }

  @Override
  public String toString() {
    return "DsDynamicDTO [object=" + object + ", properties=" + properties + "]";
  }

}
