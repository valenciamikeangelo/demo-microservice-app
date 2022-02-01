/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:45:29 PM
 */
package com.caista.birapps.etis.reference.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.reference.util.ReferenceObjectWrapper;

@Transactional(readOnly = true)
public interface ReferenceQueryService<T> {

  public List<T> getAllValidReference();

  public List<T> getAllReference();

  public T getReferenceById(String id);

  public T getReferenceByCode(String code);

  public ServerSidePaginationResponse<T> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request);

}
