/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 3:17:08 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;

public interface MaintAccreditedPrinterApi {

  public ResponseEntity<List<MaintAccreditedPrinter>> findAll();

  public ResponseEntity<List<MaintAccreditedPrinter>> findAllValid();

  public ResponseEntity<MaintAccreditedPrinter> save(MaintAccreditedPrinter maintAccreditedPrinter);

  public ResponseEntity<MaintAccreditedPrinter> update(
      MaintAccreditedPrinter maintAccreditedPrinter);

  public ResponseEntity<List<MaintAccreditedPrinter>> findByPrinterTinBusinessNameAndPrinterName(
      String printerTin, String businessName, String printerName, String branchCode);

  public ResponseEntity<List<MaintAccreditedPrinter>> findByPrinterTin(String printerTin);
}
