/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:16:47 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;

@Repository
public class MaintBankRepositoryImpl implements MaintBankRepository {

	public static final Logger LOG = LoggerFactory.getLogger(MaintBankRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintBank(a.id, a.code, a.description, "
			+ "a.governmentDepositBankFlag, a.statusFlag, a.effectiveDate, a.expiryDate, a.updatedDate) ";

	private static final String USER_CONSTRUCTOR = "SELECT new MaintBank(a.id, a.code, a.description, a.governmentDepositBankFlag, a.statusFlag) ";

	@Override
	public List<MaintBank> findAllForModules() {
		LOG.info("REPOSITORY : FIND ALL FOR MODULES CONSUMPTION {}", "MaintBank");
		String query = USER_CONSTRUCTOR
				+ "from MaintBank a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<MaintBank> result = entityManager.createQuery(query, MaintBank.class);
		result.setParameter("currentDate", new Date());
		return result.getResultList();
	}

	@Override
	public MaintBank findByCode(String code) {
		LOG.info("REPOSITORY: FIND BY CODE: {}", code);
		String query = "SELECT a FROM MaintBank a WHERE UPPER(a.code) = UPPER(?1)";
		TypedQuery<MaintBank> result = entityManager.createQuery(query, MaintBank.class);
		result.setParameter(1, code);
		return result.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM MaintBank a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

	@Override
	public MaintBank save(MaintBank maintBank) {
		LOG.info("REPOSITORY: SAVE: {}", maintBank);
		entityManager.persist(maintBank);
		entityManager.flush();
		return maintBank;
	}

	@Override
	public MaintBank update(MaintBank maintBank) {
		LOG.info("REPOSITORY: UPDATE: {}", maintBank);
		maintBank = entityManager.merge(maintBank);
		entityManager.flush();
		return maintBank;
	}

	@Override
	public List<MaintBank> advanceSearch(MaintBankRequest request) {
		LOG.info("REPOSITORY : ADVANCE SEARCH {}", request);

		String query = ADMIN_CONSTRUCTOR + "FROM MaintBank a "
				+ "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
				+ "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
				+ "AND (?3 IS NULL OR UPPER(a.governmentDepositBankFlag) LIKE UPPER(CONCAT(?3, '%'))) "
				+ "AND (?4 IS NULL OR UPPER(a.statusFlag) LIKE UPPER(CONCAT(?4, '%'))) "
				+ "ORDER BY a.createdDate DESC";

		TypedQuery<MaintBank> result = entityManager.createQuery(query, MaintBank.class);
		result.setParameter(1, request.getCode());
		result.setParameter(2, request.getDescription());
		result.setParameter(3, request.getGovernmentDepositBankFlag());
		result.setParameter(4, request.getStatusFlag());

		return result.getResultList();
	}

	@Override
	public MaintBank findById(String id) {
		LOG.info("REPOSITORY: FIND BY ID {}", id);
		return entityManager.find(MaintBank.class, id);
	}

	@Override
	public List<MaintBank> getAllValidByGovtFlag() {
		LOG.info("REPOSITORY : FIND ALL FOR VALID BANK WITH GOVERNMENT DEPOSIT FLAG = {}", "Y");
		String query = USER_CONSTRUCTOR
				+ "from MaintBank a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate "
				+ "AND a.governmentDepositBankFlag = :governmentDepositBankFlag";
		TypedQuery<MaintBank> result = entityManager.createQuery(query, MaintBank.class);
		result.setParameter("currentDate", new Date());
		result.setParameter("governmentDepositBankFlag", "Y");
		return result.getResultList();
	}

	@Override
	public MaintBank findAbbrevByBankName(String bankName) throws NoResultException, NonUniqueResultException {
		LOG.info("REPOSITORY : FIND ABBREV BY BANK NAME {}", bankName);

		String query = "SELECT new MaintBank(a.id, a.code, a.description) FROM MaintBank a WHERE UPPER(a.description) = UPPER(:bankName)";
		TypedQuery<MaintBank> result = entityManager.createQuery(query, MaintBank.class);
		result.setParameter("bankName", bankName);
		return result.getSingleResult();
	}

}
