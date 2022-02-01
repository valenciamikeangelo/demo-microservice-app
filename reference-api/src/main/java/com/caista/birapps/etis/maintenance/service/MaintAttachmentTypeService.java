/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:06:17 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;

public interface MaintAttachmentTypeService
    extends AuditableMaintenanceService<MaintAttachmentType> {

  public List<MaintAttachmentType> findAll();

  public List<MaintAttachmentType> findAllValid();

  public MaintAttachmentType findByCode(String code);

  public MaintAttachmentType save(MaintAttachmentType maintAttachmentType);

  public MaintAttachmentType update(MaintAttachmentType maintAttachmentType);

  public List<MaintAttachmentType> advanceSearch(MaintAttachmentTypeRequest request);

  public ServerSidePaginationResponse<MaintAttachmentType> serverSideSearch(
      ServerSidePaginationRequest<MaintAttachmentTypeRequest> request);

  public List<MaintAttachmentType> findAllValidByTaxpayerTypeId(String id);

  public List<MaintAttachmentType> findByCategory(String categoryId);

  public List<MaintAttachmentType> findAllValidByTaxpayerTypeAndCategory(String taxpayerTypeId,
      String categoryId);

}
