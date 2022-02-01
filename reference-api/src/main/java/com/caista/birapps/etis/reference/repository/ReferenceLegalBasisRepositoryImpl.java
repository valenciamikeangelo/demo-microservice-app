/*
  * Modified by: tolentf
  * Last updated: Mar 13, 2019 6:37:31 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceLegalBasis;

@Repository
public class ReferenceLegalBasisRepositoryImpl implements ReferenceLegalBasisRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceLegalBasisRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReferenceLegalBasis addReference(ReferenceLegalBasis reference) {
		LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
		entityManager.persist(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public ReferenceLegalBasis updateReference(ReferenceLegalBasis reference) {
		LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
		reference = entityManager.merge(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public List<ReferenceLegalBasis> getAllValidReference() {
		LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
		String hqlString = "SELECT a FROM ReferenceLegalBasis a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<ReferenceLegalBasis> query = entityManager.createQuery(hqlString, ReferenceLegalBasis.class);
		query.setParameter("currentDate", new Date());
		return query.getResultList();
	}

	@Override
	public List<ReferenceLegalBasis> getAllReference() {
		LOGGER.info("{}", "GETTING ALL REFERENCE");
		String hqlString = "SELECT a FROM ReferenceLegalBasis a";
		TypedQuery<ReferenceLegalBasis> query = entityManager.createQuery(hqlString, ReferenceLegalBasis.class);
		return query.getResultList();
	}

	@Override
	public ReferenceLegalBasis getReferenceById(String id) {
		LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
		return entityManager.find(ReferenceLegalBasis.class, id);
	}

	@Override
	public ReferenceLegalBasis getReferenceByCode(String code) {
		LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
		String hqlString = "SELECT a FROM ReferenceLegalBasis a where UPPER(a.code) = UPPER(?1)";
		TypedQuery<ReferenceLegalBasis> query = entityManager.createQuery(hqlString, ReferenceLegalBasis.class);
		query.setParameter(1, code);
		return query.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM ReferenceLegalBasis a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

}
