/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 5:12:05 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintAccreditedPrinterRepository;

@Transactional
@Service
public class MaintAccreditedPrinterServiceImpl implements MaintAccreditedPrinterService {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAccreditedPrinterServiceImpl.class);

  @Autowired
  private MaintAccreditedPrinterRepository maintAccreditedPrinterRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintAccreditedPrinter> findAll() {
    LOG.info("SERVICE : FIND ALL");
    final String constructor = "(a.id, a.printerTin, a.printerName, a.businessName, a.address, "
        + "a.accreditationNumber, a.accreditationDate)";
    return commonRepository.findAll(MaintAccreditedPrinter.class, constructor);
  }

  @Transactional
  @Override
  public MaintAccreditedPrinter save(MaintAccreditedPrinter maintAccreditedPrinter) {
    Date today = new Date();
    maintAccreditedPrinter.setAccreditationDate(today);
    maintAccreditedPrinter.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintAccreditedPrinter);
    return commonRepository.save(maintAccreditedPrinter);
  }

  @Transactional
  @Override
  public MaintAccreditedPrinter update(MaintAccreditedPrinter maintAccreditedPrinter) {
    maintAccreditedPrinter.setUpdatedDate(new Date());
    LOG.info("SERVICE : UPDATE: {}", maintAccreditedPrinter);
    return commonRepository.update(maintAccreditedPrinter);
  }


  @Transactional(readOnly = true)
  @Override
  public List<MaintAccreditedPrinter> findAllValid() {
    LOG.info("SERVICE: Find all for modules consumption");
    final String constructor = "(a.id, a.printerTin, a.printerName, a.businessName, a.address, "
        + "a.accreditationNumber, a.accreditationDate)";
    final String orderBy = "a.printerName ASC";
    return commonRepository.findAllValid(MaintAccreditedPrinter.class, constructor, orderBy);
  }

  @Override
  public List<MaintAccreditedPrinter> findByPrinterTinBusinessNameAndPrinterName(String printerTin,
      String businessName, String printerName, String branchCode) {
    LOG.info("SERVICE: FIND BY <printerTin = {}, businessName = {}, printerName = {}>", printerTin,
        businessName, printerName);
    return maintAccreditedPrinterRepository.findByPrinterTinBusinessNameAndPrinterName(printerTin,
        businessName, printerName, branchCode);
  }

  @Override
  public List<MaintAccreditedPrinter> findByPrinterTin(String printerTin) {
    LOG.info("SERVICE: FIND BY <printerTin = {}>", printerTin);
    return maintAccreditedPrinterRepository.findByPrinterTin(printerTin);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintAccreditedPrinter findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintAccreditedPrinterRepository.findById(id);
  }

}
