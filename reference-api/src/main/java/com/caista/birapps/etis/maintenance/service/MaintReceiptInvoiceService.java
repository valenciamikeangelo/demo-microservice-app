/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:10:27 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;

public interface MaintReceiptInvoiceService
    extends AuditableMaintenanceService<MaintReceiptInvoice> {

  public List<MaintReceiptInvoice> findAll();

  public List<MaintReceiptInvoice> findAllValid();

  public MaintReceiptInvoice findByCode(String code);

  public MaintReceiptInvoice save(MaintReceiptInvoice maintReceiptInv);

  public MaintReceiptInvoice update(MaintReceiptInvoice maintReceiptInv);

  public MaintReceiptInvoice saveFromCsv(MaintReceiptInvoice maintReceiptInv);

  public List<MaintReceiptInvoice> advanceSearch(
      MaintReceiptInvoiceRequest maintReceiptInvoiceRequest);

  public List<MaintReceiptInvoice> findByKindReceiptInvoiceCode(String code);

  public List<MaintReceiptInvoice> findByKindReceiptInvoiceId(String id);

  public ServerSidePaginationResponse<MaintReceiptInvoice> serverSideSearch(
      ServerSidePaginationRequest<MaintReceiptInvoiceRequest> request);

}
