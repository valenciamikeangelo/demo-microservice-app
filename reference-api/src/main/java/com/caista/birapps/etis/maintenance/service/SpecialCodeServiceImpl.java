/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 1:18:59 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.SpecialCode;
import com.caista.birapps.etis.maintenance.repository.MaintSpecialCodeBranchPerTaxpayerClassificationRepository;

@Service
public class SpecialCodeServiceImpl implements SpecialCodeService {

  @Autowired
  private MaintSpecialCodeService maintSpecialCodeService;

  @Autowired
  private MaintSpecialCodeBranchPerTaxpayerClassificationRepository spBranchPerTpClassRepository;

  @Override
  @Transactional
  public SpecialCode save(SpecialCode specialCode) {
    maintSpecialCodeService.save(specialCode.getMaintSpecialCode());
    Iterator<MaintSpecialCodeBranchPerTaxpayerClassification> scPerTpBranchIterator = specialCode
        .getTpClassPerBranchList().iterator();
    while (scPerTpBranchIterator.hasNext()) {
      scPerTpBranchIterator.next().setSpecialCode(specialCode.getMaintSpecialCode());
    }
    spBranchPerTpClassRepository.save(specialCode.getTpClassPerBranchList());
    return specialCode;
  }

  @Override
  @Transactional
  public SpecialCode update(SpecialCode specialCode) {
    maintSpecialCodeService.update(specialCode.getMaintSpecialCode());
    spBranchPerTpClassRepository.deleteBySpecialCodeId(specialCode.getMaintSpecialCode().getId());
    Iterator<MaintSpecialCodeBranchPerTaxpayerClassification> scPerTpBranchIterator = specialCode
        .getTpClassPerBranchList().iterator();
    while (scPerTpBranchIterator.hasNext()) {
      scPerTpBranchIterator.next().setSpecialCode(specialCode.getMaintSpecialCode());
    }
    spBranchPerTpClassRepository.save(specialCode.getTpClassPerBranchList());
    return specialCode;
  }

  @Override
  public SpecialCode findById(String id) {
    SpecialCode specialCode = new SpecialCode();
    specialCode.setMaintSpecialCode(maintSpecialCodeService.findById(id));
    specialCode.setTpClassPerBranchList(spBranchPerTpClassRepository.findBySpecialCodeId(id));
    return specialCode;
  }

  @Override
  public List<SpecialCode> findByTaxpayerClassification(String taxClassId) {

    List<SpecialCode> specialCodeList = new ArrayList<>();

    // MAINTSPECIALCODE ID == KEY
    Map<String, MaintSpecialCode> specialCodeMap = new HashMap<>();

    // MAINTSPECIALCODE ID == KEY
    Map<String, List<MaintSpecialCodeBranchPerTaxpayerClassification>> tpPerBranchMap = new HashMap<>();

    List<MaintSpecialCodeBranchPerTaxpayerClassification> tpPerBranchList = spBranchPerTpClassRepository
        .findByTaxClassification(taxClassId);
    for (MaintSpecialCodeBranchPerTaxpayerClassification tpPerBranch : tpPerBranchList) {
      if (specialCodeMap.get(tpPerBranch.getSpecialCode().getId()) == null) {
        List<MaintSpecialCodeBranchPerTaxpayerClassification> newTpPerBranchList = new ArrayList<>();
        newTpPerBranchList.add(tpPerBranch);
        specialCodeMap.put(tpPerBranch.getSpecialCode().getId(), tpPerBranch.getSpecialCode());
        tpPerBranchMap.put(tpPerBranch.getSpecialCode().getId(), newTpPerBranchList);
      } else {
        tpPerBranchMap.get(tpPerBranch.getSpecialCode().getId()).add(tpPerBranch);
      }
    }

    for (Map.Entry<String, MaintSpecialCode> entry : specialCodeMap.entrySet()) {
      String entryKey = entry.getKey();
      SpecialCode specialCode = new SpecialCode();
      specialCode.setMaintSpecialCode(entry.getValue());
      specialCode.setTpClassPerBranchList(tpPerBranchMap.get(entryKey));
      specialCodeList.add(specialCode);
    }

    return specialCodeList;
  }

  @Override
  public List<SpecialCode> findAllValidSpecialCode() {
    List<SpecialCode> specialCodeList = new ArrayList<>();
    List<MaintSpecialCode> maintSpecialCodeList = maintSpecialCodeService.findAllValid();

    for (MaintSpecialCode maintSpecialCode : maintSpecialCodeList) {
      List<MaintSpecialCodeBranchPerTaxpayerClassification> tpClassPerBranchList = spBranchPerTpClassRepository
          .findBySpecialCodeId(maintSpecialCode.getId());
      SpecialCode specialCode = new SpecialCode();
      specialCode.setMaintSpecialCode(maintSpecialCode);
      specialCode.setTpClassPerBranchList(tpClassPerBranchList);
      specialCodeList.add(specialCode);
    }

    return specialCodeList;
  }



}
