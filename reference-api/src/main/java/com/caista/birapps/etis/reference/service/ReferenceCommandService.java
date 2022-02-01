/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:45:09 PM
 */
package com.caista.birapps.etis.reference.service;

import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.reference.util.ReferenceObjectWrapper;

@Transactional
public interface ReferenceCommandService<T> {

  public T addReference(ReferenceObjectWrapper wrapper);

  public T updateReference(ReferenceObjectWrapper wrapper);

  public T addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper);

  public T getReferenceByCode(String code);

  public T getReferenceById(String id);

}
