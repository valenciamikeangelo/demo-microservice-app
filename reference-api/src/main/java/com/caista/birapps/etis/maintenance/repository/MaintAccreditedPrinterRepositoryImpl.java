/*
  * Modified by: obregoj
  * Last updated: Feb 14, 2019 12:50:59 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;

@Repository
public class MaintAccreditedPrinterRepositoryImpl implements MaintAccreditedPrinterRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAccreditedPrinterRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;


  @Override
  public List<MaintAccreditedPrinter> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintAccreditedPrinter");
    String query = "SELECT a FROM MaintAccreditedPrinter a";
    TypedQuery<MaintAccreditedPrinter> typedQuery = entityManager.createQuery(query,
        MaintAccreditedPrinter.class);
    return typedQuery.getResultList();
  }


  @Override
  public MaintAccreditedPrinter save(MaintAccreditedPrinter maintAccreditedPrinter) {
    LOG.info("REPOSITORY: SAVE: {}", maintAccreditedPrinter);
    entityManager.persist(maintAccreditedPrinter);
    entityManager.flush();
    return maintAccreditedPrinter;
  }

  @Override
  public MaintAccreditedPrinter update(MaintAccreditedPrinter maintAccreditedPrinter) {
    LOG.info("REPOSITORY: UPDATE: {}", maintAccreditedPrinter);
    maintAccreditedPrinter = entityManager.merge(maintAccreditedPrinter);
    entityManager.flush();
    return maintAccreditedPrinter;
  }

  @Override
  public List<MaintAccreditedPrinter> findAllForModules() {
    LOG.info("REPOSITORY : Find all {} for modules consumption", "MaintAccreditedPrinter");
    String query = "SELECT a from MaintAccreditedPrinter a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintAccreditedPrinter> typedQuery = entityManager.createQuery(query,
        MaintAccreditedPrinter.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }


  @Override
  public List<MaintAccreditedPrinter> findByPrinterTinBusinessNameAndPrinterName(String printerTin,
      String businessName, String printerName, String branchCode) {
    LOG.info("REPOSITORY : FIND BY <printerTin = {}, businessName = {}, printerName = {}>",
        printerTin, businessName, printerName);

    String query = "SELECT new MaintAccreditedPrinter(id, printerTin, printerName, businessName, "
        + "address, accreditationNumber, accreditationDate) FROM MaintAccreditedPrinter a "
        + "WHERE (?1 IS NULL OR UPPER(a.printerTin) LIKE UPPER(CONCAT(?1,'-_____'))) "
        + "AND (?2 IS NULL OR UPPER(a.businessName) LIKE UPPER(?2)) "
        + "AND (?3 IS NULL OR UPPER(a.printerName) LIKE UPPER(?3)) "
        + "AND (?4 IS NULL OR a.printerTin LIKE CONCAT('___-___-___-',?4))";

    TypedQuery<MaintAccreditedPrinter> result = entityManager.createQuery(query,
        MaintAccreditedPrinter.class);
    result.setParameter(1, printerTin);
    result.setParameter(2, businessName);
    result.setParameter(3, printerName);
    result.setParameter(4, branchCode);

    return result.getResultList();
  }

  @Override
  public List<MaintAccreditedPrinter> findByPrinterTin(String printerTin) {
    LOG.info("REPOSITORY : FIND BY <printerTin = {}>", printerTin);

    String query = "SELECT a FROM MaintAccreditedPrinter a WHERE UPPER(a.printerTin) = UPPER(?1)";

    TypedQuery<MaintAccreditedPrinter> result = entityManager.createQuery(query,
        MaintAccreditedPrinter.class);
    result.setParameter(1, printerTin);


    return result.getResultList();
  }

  @Override
  public MaintAccreditedPrinter findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintAccreditedPrinter.class, id);
  }


}
