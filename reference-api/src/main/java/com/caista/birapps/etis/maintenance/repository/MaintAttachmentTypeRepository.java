/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:06:44 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;

public interface MaintAttachmentTypeRepository {

  public List<MaintAttachmentType> findAll();

  public MaintAttachmentType findById(String id);

  public MaintAttachmentType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintAttachmentType save(MaintAttachmentType maintAttachmentType);

  public MaintAttachmentType update(MaintAttachmentType maintAttachmentType);

  public List<MaintAttachmentType> advanceSearch(MaintAttachmentTypeRequest request);

  public List<MaintAttachmentType> findAllValidByTaxpayerTypeId(String id);

  public List<MaintAttachmentType> findByCategory(String categoryId);

  public List<MaintAttachmentType> findAllValidByTaxpayerTypeAndCategory(String taxpayerTypeId,
      String categoryId);

}
