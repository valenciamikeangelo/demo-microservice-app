/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:36 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceSpouseEmploymentStatus;

@Repository
public class ReferenceSpouseEmploymentStatusRepositoryImpl implements ReferenceSpouseEmploymentStatusRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceSpouseEmploymentStatusRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReferenceSpouseEmploymentStatus addReference(ReferenceSpouseEmploymentStatus reference) {
		LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
		entityManager.persist(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public ReferenceSpouseEmploymentStatus updateReference(ReferenceSpouseEmploymentStatus reference) {
		LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
		reference = entityManager.merge(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public List<ReferenceSpouseEmploymentStatus> getAllValidReference() {
		LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
		String hqlString = "SELECT a FROM ReferenceSpouseEmploymentStatus a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<ReferenceSpouseEmploymentStatus> query = entityManager.createQuery(hqlString,
				ReferenceSpouseEmploymentStatus.class);
		query.setParameter("currentDate", new Date());
		return query.getResultList();
	}

	@Override
	public List<ReferenceSpouseEmploymentStatus> getAllReference() {
		LOGGER.info("{}", "GETTING ALL REFERENCE");
		String hqlString = "SELECT a FROM ReferenceSpouseEmploymentStatus a";
		TypedQuery<ReferenceSpouseEmploymentStatus> query = entityManager.createQuery(hqlString,
				ReferenceSpouseEmploymentStatus.class);
		return query.getResultList();
	}

	@Override
	public ReferenceSpouseEmploymentStatus getReferenceById(String id) {
		LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
		return entityManager.find(ReferenceSpouseEmploymentStatus.class, id);
	}

	@Override
	public ReferenceSpouseEmploymentStatus getReferenceByCode(String code) {
		LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
		String hqlString = "SELECT a FROM ReferenceSpouseEmploymentStatus a where UPPER(a.code) = UPPER(?1)";
		TypedQuery<ReferenceSpouseEmploymentStatus> query = entityManager.createQuery(hqlString,
				ReferenceSpouseEmploymentStatus.class);
		query.setParameter(1, code);
		return query.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM ReferenceSpouseEmploymentStatus a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

}
