/*
  * Modified by: obregoj
  * Last updated: Oct 5, 2018 11:17:33 AM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.List;

public interface ReferenceMainRepository<T> {

  public T addReference(T reference);

  public T updateReference(T reference);

  public List<T> getAllValidReference();

  public List<T> getAllReference();

  public T getReferenceById(String id);

  public T getReferenceByCode(String code);

  public boolean isCodeExists(String code);

}
