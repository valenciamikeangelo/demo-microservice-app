/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 3:16:07 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;

public interface MaintAccreditedPrinterService
    extends AuditableMaintenanceService<MaintAccreditedPrinter> {
  public List<MaintAccreditedPrinter> findAll();

  public List<MaintAccreditedPrinter> findAllValid();

  public MaintAccreditedPrinter save(MaintAccreditedPrinter maintAccreditedPrinter);

  public MaintAccreditedPrinter update(MaintAccreditedPrinter maintAccreditedPrinter);

  public List<MaintAccreditedPrinter> findByPrinterTinBusinessNameAndPrinterName(String printerTin,
      String businessName, String printerName, String branchCode);

  public List<MaintAccreditedPrinter> findByPrinterTin(String printerTin);

}
