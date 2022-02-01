/*
  * Modified by: tolentf
  * Last updated: Mar 13, 2019 6:35:27 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceSourceAgency;

@Repository
public class ReferenceSourceAgencyRepositoryImpl implements ReferenceSourceAgencyRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceSourceAgencyRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReferenceSourceAgency addReference(ReferenceSourceAgency reference) {
		LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
		entityManager.persist(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public ReferenceSourceAgency updateReference(ReferenceSourceAgency reference) {
		LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
		reference = entityManager.merge(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public List<ReferenceSourceAgency> getAllValidReference() {
		LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
		String hqlString = "SELECT a FROM ReferenceSourceAgency a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<ReferenceSourceAgency> query = entityManager.createQuery(hqlString, ReferenceSourceAgency.class);
		query.setParameter("currentDate", new Date());
		return query.getResultList();
	}

	@Override
	public List<ReferenceSourceAgency> getAllReference() {
		LOGGER.info("{}", "GETTING ALL REFERENCE");
		String hqlString = "SELECT a FROM ReferenceSourceAgency a";
		TypedQuery<ReferenceSourceAgency> query = entityManager.createQuery(hqlString, ReferenceSourceAgency.class);
		return query.getResultList();
	}

	@Override
	public ReferenceSourceAgency getReferenceById(String id) {
		LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
		return entityManager.find(ReferenceSourceAgency.class, id);
	}

	@Override
	public ReferenceSourceAgency getReferenceByCode(String code) {
		LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
		String hqlString = "SELECT a FROM ReferenceSourceAgency a where UPPER(a.code) = UPPER(?1)";
		TypedQuery<ReferenceSourceAgency> query = entityManager.createQuery(hqlString, ReferenceSourceAgency.class);
		query.setParameter(1, code);
		return query.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM ReferenceSourceAgency a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

}
