/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:12 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;

public interface MaintReceiptInvoiceApi {
  public ResponseEntity<List<MaintReceiptInvoice>> findAll();

  public ResponseEntity<List<MaintReceiptInvoice>> findAllValid();

  public ResponseEntity<MaintReceiptInvoice> findByCode(String code);

  public ResponseEntity<MaintReceiptInvoice> save(MaintReceiptInvoice maintReceiptInv);

  public ResponseEntity<MaintReceiptInvoice> update(MaintReceiptInvoice maintReceiptInv);

  public ResponseEntity<List<MaintReceiptInvoice>> findByKindReceiptInvoiceCode(String code);

  public ResponseEntity<List<MaintReceiptInvoice>> findByKindReceiptInvoiceId(String id);

  public ResponseEntity<ServerSidePaginationResponse<MaintReceiptInvoice>> serverSideSearch(
      ServerSidePaginationRequest<MaintReceiptInvoiceRequest> request);

}
