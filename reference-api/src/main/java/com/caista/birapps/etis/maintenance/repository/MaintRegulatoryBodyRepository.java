/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 1:55:09 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;

public interface MaintRegulatoryBodyRepository {

  public List<MaintRegulatoryBody> findAll();

  public MaintRegulatoryBody findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintRegulatoryBody save(MaintRegulatoryBody maintRegulatoryBody);

  public MaintRegulatoryBody update(MaintRegulatoryBody maintRegulatoryBody);

  public MaintRegulatoryBody findById(String id);

  public List<MaintRegulatoryBody> advanceSearch(MaintRegulatoryBodyRequest request);

  public List<MaintRegulatoryBody> findRegulatoryBodyByTaxpayerClassification(
      String taxpayerClassificationId);


}
