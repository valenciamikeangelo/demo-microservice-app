/*
  * Modified by: logronj
  * Last updated: 03 5, 20 3:35:23 PM
  */
package com.caista.birapps.etis.domain.sysad.util.commonrepository;

import java.util.List;

public interface CommonRepository {

  public <T> T findById(String id, Class<T> domainClass);

  public <T> T save(T domainClass);

  public <T> T update(T domainClass);

  public <T> boolean isSingleValueExist(String singleValue, Class<T> domainClass, String column);

  public <T> T findBySingleValue(String singleValue, Class<T> domainClass, String column);

  public <T> List<T> findAll(Class<T> domainClass);

  public <T> List<T> findAll(Class<T> domainClass, String constructorString);


  public <T> List<T> findAllValid(Class<T> domainClass, String constructorString);

  public <T> List<T> findAllValid(Class<T> domainClass, String constructorString, String orderBy);

  public <T> List<T> findAllValid(Class<T> domainClass);

  public <T> List<T> findAllValidByModule(Class<T> domainClass, String constructorString,
      String flag);


}
