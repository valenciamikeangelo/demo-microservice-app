/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 3:59:34 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;

public interface MaintAccreditedPrinterRepository {

  public List<MaintAccreditedPrinter> findAll();

  public List<MaintAccreditedPrinter> findAllForModules();

  public MaintAccreditedPrinter save(MaintAccreditedPrinter maintAccreditedPrinter);

  public MaintAccreditedPrinter update(MaintAccreditedPrinter maintAccreditedPrinter);

  public List<MaintAccreditedPrinter> findByPrinterTinBusinessNameAndPrinterName(String printerTin,
      String businessName, String printerName, String branchCode);

  public List<MaintAccreditedPrinter> findByPrinterTin(String printerTin);

  public MaintAccreditedPrinter findById(String id);
}
