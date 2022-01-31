package com.caista.birapps.etis.domain.ds;

import java.util.Map;

public class DsCrrDynamicDTO {

  private Object object;
  private Object oldObject;
  private Map<String, Object> properties;

  public DsCrrDynamicDTO() {
    super();
  }

public DsCrrDynamicDTO(Object object, Object oldObject, Map<String, Object> properties) {
	super();
	this.object = object;
	this.oldObject = oldObject;
	this.properties = properties;
}

public Object getObject() {
	return object;
}

public void setObject(Object object) {
	this.object = object;
}

public Object getOldObject() {
	return oldObject;
}

public void setOldObject(Object oldObject) {
	this.oldObject = oldObject;
}

public Map<String, Object> getProperties() {
	return properties;
}

public void setProperties(Map<String, Object> properties) {
	this.properties = properties;
}

}
