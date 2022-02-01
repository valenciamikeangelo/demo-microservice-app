/*
 * Modified by: santojo
 * Last updated: Nov 12, 2018 4:14:43 PM
 */
package com.caista.birapps.etis.reference.api;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.domain.sysad.reference.util.*;

/**
 * The Interface ReferenceApi.
 */
public interface ReferenceApi {

  /**
   * Save.
   *
   * @param reference the reference
   * @return the response entity
   */
  public ResponseEntity<Object> save(ReferenceObjectWrapper reference, HttpServletRequest request);

  /**
   * Update.
   *
   * @param reference the reference
   * @return the response entity
   */
  public ResponseEntity<Object> update(ReferenceObjectWrapper reference,
      HttpServletRequest request);


  public ResponseEntity<List<Object>> getAllReferenceByType(ReferenceTypeEnum referenceType);

  public ResponseEntity<List<Object>> getAllValidReference(ReferenceTypeEnum referenceType);

  public ResponseEntity<ServerSidePaginationResponse> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request);

  public ResponseEntity<Object> getReferenceById(ReferenceTypeEnum referenceType, String id);

  public ResponseEntity<List<ReferencePropertyLengthWrapper>> getPropertyLength(
      ReferenceTypeEnum referenceType);

}
