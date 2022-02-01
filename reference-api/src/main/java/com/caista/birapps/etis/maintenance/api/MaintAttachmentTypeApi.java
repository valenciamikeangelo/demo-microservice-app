/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:29:20 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;

public interface MaintAttachmentTypeApi {

  public ResponseEntity<List<MaintAttachmentType>> findAll();

  public ResponseEntity<List<MaintAttachmentType>> findAllValid();

  public ResponseEntity<MaintAttachmentType> findByCode(String code);

  public ResponseEntity<MaintAttachmentType> save(MaintAttachmentType maintAttachmentType);

  public ResponseEntity<MaintAttachmentType> update(MaintAttachmentType maintAttachmentType);

  public ResponseEntity<ServerSidePaginationResponse<MaintAttachmentType>> serverSideSearch(
      ServerSidePaginationRequest<MaintAttachmentTypeRequest> request);

  public ResponseEntity<List<MaintAttachmentType>> findByCategory(String categoryId);

  public ResponseEntity<List<MaintAttachmentType>> findAllValidByTaxpayerTypeId(String id);

  public ResponseEntity<List<MaintAttachmentType>> findAllValidByTaxpayerTypeAndCategory(
      String taxpayerTypeId,
      String categoryId);
}
