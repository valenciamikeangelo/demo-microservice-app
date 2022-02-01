/*
  * Modified by: obregoj
  * Last updated: Feb 27, 2019 11:31:33 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;

@Repository
public class MaintReceiptInvoiceRepositoryImpl implements MaintReceiptInvoiceRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintReceiptInvoiceRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintReceiptInvoice> findAll() {
    LOG.info("REPOSITORY: FIND ALL MAINT RECEIPT INVOICE");

    String query = "SELECT a FROM MaintReceiptInvoice a ORDER BY a.createdDate DESC";

    TypedQuery<MaintReceiptInvoice> result = entityManager.createQuery(query,
        MaintReceiptInvoice.class);

    return result.getResultList();
  }

  @Override
  public MaintReceiptInvoice findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintReceiptInvoice a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintReceiptInvoice> result = entityManager.createQuery(query,
        MaintReceiptInvoice.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS: ", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintReceiptInvoice a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintReceiptInvoice save(MaintReceiptInvoice maintReceiptInv) {
    LOG.info("REPOSITORY: SAVE: {}", maintReceiptInv);

    entityManager.persist(maintReceiptInv);
    entityManager.flush();

    return maintReceiptInv;
  }

  @Override
  public MaintReceiptInvoice update(MaintReceiptInvoice maintReceiptInv) {
    LOG.info("REPOSITORY: UPDATE: {}", maintReceiptInv);

    entityManager.merge(maintReceiptInv);

    entityManager.flush();

    return maintReceiptInv;
  }

  @Override
  public MaintReceiptInvoice findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintReceiptInvoice entity = entityManager.find(MaintReceiptInvoice.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintReceiptInvoice> advanceSearch(
      MaintReceiptInvoiceRequest maintReceiptInvoiceRequest) {

    LOG.info("REPOSITORY : ADVANCE SEARCH : {}", maintReceiptInvoiceRequest);

    String query = "SELECT a FROM MaintReceiptInvoice a LEFT JOIN FETCH a.kindReceiptInvoice "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.kindReceiptInvoice.code) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintReceiptInvoice> result = entityManager.createQuery(query,
        MaintReceiptInvoice.class);
    result.setParameter(1, maintReceiptInvoiceRequest.getCode());
    result.setParameter(2, maintReceiptInvoiceRequest.getDescription());
    result.setParameter(3, maintReceiptInvoiceRequest.getCreatedBy());
    result.setParameter(4, maintReceiptInvoiceRequest.getKindReceiptInvoice());

    return result.getResultList();
  }

  @Override
  public List<MaintReceiptInvoice> findByKindReceiptInvoiceCode(String code) {
    LOG.info("REPOSITORY: findByKindReceiptInvoiceCode Code: {}", code);
    String query = "SELECT a FROM MaintReceiptInvoice a WHERE UPPER(a.kindReceiptInvoice.code) = UPPER(?1) "
        + "AND TRUNC(?2) BETWEEN a.effectiveDate AND a.expiryDate ORDER BY a.description ASC";
    TypedQuery<MaintReceiptInvoice> result = entityManager.createQuery(query,
        MaintReceiptInvoice.class);
    result.setParameter(1, code);
    result.setParameter(2, new Date());
    return result.getResultList();
  }

  @Override
  public List<MaintReceiptInvoice> findByKindReceiptInvoiceId(String id) {
    LOG.info("REPOSITORY: findByKindReceiptInvoice ID:  {}", id);
    String query = "SELECT a FROM MaintReceiptInvoice a WHERE UPPER(a.kindReceiptInvoice.id) = UPPER(?1)";
    TypedQuery<MaintReceiptInvoice> result = entityManager.createQuery(query,
        MaintReceiptInvoice.class);
    result.setParameter(1, id);
    return result.getResultList();
  }

}
