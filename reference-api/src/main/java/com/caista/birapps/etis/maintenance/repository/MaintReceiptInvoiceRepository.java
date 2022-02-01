/*
  * Modified by: logronj
  * Last updated: Oct 15, 2018 3:41:34 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;

public interface MaintReceiptInvoiceRepository {
  public List<MaintReceiptInvoice> findAll();

  public MaintReceiptInvoice findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintReceiptInvoice save(MaintReceiptInvoice maintReceiptInv);

  public MaintReceiptInvoice update(MaintReceiptInvoice maintReceiptInv);

  public MaintReceiptInvoice findById(String id);

  public List<MaintReceiptInvoice> advanceSearch(
      MaintReceiptInvoiceRequest maintReceiptInvoiceRequest);

  public List<MaintReceiptInvoice> findByKindReceiptInvoiceCode(String code);

  public List<MaintReceiptInvoice> findByKindReceiptInvoiceId(String id);

}
