/*
  * Modified by: logronj
  * Last updated: 03 5, 20 3:38:29 PM
  */
package com.caista.birapps.etis.domain.sysad.util.commonrepository;

import java.util.*;
import javax.persistence.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.idgenerator.context.GeneratorContext;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,
    scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CommonRepositoryImpl implements CommonRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public <T> T findById(String id, Class<T> domainClass) {
    String query = "SELECT a FROM " + domainClass.getSimpleName() + " a "
        + "WHERE UPPER(a.id) = UPPER(?1)";

    TypedQuery<T> result = entityManager.createQuery(query, domainClass);
    result.setParameter(1, id);

    return result.getResultList().stream().findFirst().orElse(null);
  }

  @Override
  public <T> T save(T domainClass) {
    entityManager.persist(new GeneratorContext().generateId(domainClass));
    entityManager.flush();

    return domainClass;
  }

  @Override
  public <T> T update(T domainClass) {
    entityManager.merge(domainClass);
    entityManager.flush();

    return domainClass;
  }

  @Override
  public <T> boolean isSingleValueExist(String singleValue, Class<T> domainClass, String column) {

    StringBuilder builder = new StringBuilder();
    builder.append("SELECT case when (count(a) > 0) then true else false end");
    builder.append(" FROM ");
    builder.append(domainClass.getSimpleName()).append(" a ");
    builder.append("WHERE UPPER(a.").append(column).append(") = UPPER(?1)");

    TypedQuery<Boolean> result = entityManager.createQuery(builder.toString(), Boolean.class);
    result.setParameter(1, singleValue);

    return result.getSingleResult();
  }

  @Override
  public <T> T findBySingleValue(String singleValue, Class<T> domainClass, String column) {

    StringBuilder builder = new StringBuilder();
    builder.append("SELECT a FROM ");
    builder.append(domainClass.getSimpleName()).append(" a ");
    builder.append("WHERE UPPER(a.").append(column).append(") = UPPER(?1)");

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter(1, singleValue);

    return result.getResultList().stream().findFirst().orElse(null);
  }

  @Override
  public <T> List<T> findAll(Class<T> domainClass) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT a ");
    builder.append(" FROM ").append(className).append(" a ");
    builder.append("WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate ");

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

  @Override
  public <T> List<T> findAll(Class<T> domainClass, String constructorString) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT new ");
    builder.append(className).append(constructorString);
    builder.append(" FROM ").append(className).append(" a");

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);

    return result.getResultList();
  }

  @Override
  public <T> List<T> findAllValid(Class<T> domainClass, String constructorString) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT new ");
    builder.append(className).append(constructorString);
    builder.append(" FROM ").append(className).append(" a ");
    builder.append("WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate ");
    builder.append("ORDER BY a.description ASC");

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

  @Override
  public <T> List<T> findAllValid(Class<T> domainClass, String constructorString, String orderBy) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT new ");
    builder.append(className).append(constructorString);
    builder.append(" FROM ").append(className).append(" a ");
    builder.append("WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate ORDER BY ");
    builder.append(orderBy);

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

  @Override
  public <T> List<T> findAllValid(Class<T> domainClass) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT a ");
    builder.append(" FROM ").append(className).append(" a ");
    builder.append("WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate ");
    builder.append("ORDER BY a.description ASC");

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

  @Override
  public <T> List<T> findAllValidByModule(Class<T> domainClass, String constructorString,
      String flag) {
    final String className = domainClass.getSimpleName();
    StringBuilder builder = new StringBuilder();
    builder.append("SELECT new ");
    builder.append(className).append(constructorString);
    builder.append(" FROM ").append(className).append(" a ");
    builder.append("WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate AND ").append(flag);

    TypedQuery<T> result = entityManager.createQuery(builder.toString(), domainClass);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

}
