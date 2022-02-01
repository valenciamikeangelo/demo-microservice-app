/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:52:36 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTitle;

@Repository
public class ReferenceTitleRepositoryImpl implements ReferenceTitleRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceTitleRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReferenceTitle addReference(ReferenceTitle reference) {
		LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
		entityManager.persist(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public ReferenceTitle updateReference(ReferenceTitle reference) {
		LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
		reference = entityManager.merge(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public List<ReferenceTitle> getAllValidReference() {
		LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
		String hqlString = "SELECT a FROM ReferenceTitle a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<ReferenceTitle> query = entityManager.createQuery(hqlString, ReferenceTitle.class);
		query.setParameter("currentDate", new Date());
		return query.getResultList();
	}

	@Override
	public List<ReferenceTitle> getAllReference() {
		LOGGER.info("{}", "GETTING ALL REFERENCE");
		String hqlString = "SELECT a FROM ReferenceTitle a";
		TypedQuery<ReferenceTitle> query = entityManager.createQuery(hqlString, ReferenceTitle.class);
		return query.getResultList();
	}

	@Override
	public ReferenceTitle getReferenceById(String id) {
		LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
		return entityManager.find(ReferenceTitle.class, id);
	}

	@Override
	public ReferenceTitle getReferenceByCode(String code) {
		LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
		String hqlString = "SELECT a FROM ReferenceTitle a where UPPER(a.code) = UPPER(?1)";
		TypedQuery<ReferenceTitle> query = entityManager.createQuery(hqlString, ReferenceTitle.class);
		query.setParameter(1, code);
		return query.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM ReferenceTitle a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

}
