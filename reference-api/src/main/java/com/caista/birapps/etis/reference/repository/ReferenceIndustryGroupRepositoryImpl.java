/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:48:35 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceIndustryGroup;

@Repository
public class ReferenceIndustryGroupRepositoryImpl implements ReferenceIndustryGroupRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceIndustryGroupRepositoryImpl.class);
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ReferenceIndustryGroup addReference(ReferenceIndustryGroup reference) {
		LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
		entityManager.persist(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public ReferenceIndustryGroup updateReference(ReferenceIndustryGroup reference) {
		LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
		reference = entityManager.merge(reference);
		entityManager.flush();
		return reference;
	}

	@Override
	public List<ReferenceIndustryGroup> getAllValidReference() {
		LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
		String hqlString = "SELECT a FROM ReferenceIndustryGroup a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
		TypedQuery<ReferenceIndustryGroup> query = entityManager.createQuery(hqlString, ReferenceIndustryGroup.class);
		query.setParameter("currentDate", new Date());
		return query.getResultList();
	}

	@Override
	public List<ReferenceIndustryGroup> getAllReference() {
		LOGGER.info("{}", "GETTING ALL REFERENCE");
		String hqlString = "SELECT a FROM ReferenceIndustryGroup a";
		TypedQuery<ReferenceIndustryGroup> query = entityManager.createQuery(hqlString, ReferenceIndustryGroup.class);
		return query.getResultList();
	}

	@Override
	public ReferenceIndustryGroup getReferenceById(String id) {
		LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
		return entityManager.find(ReferenceIndustryGroup.class, id);
	}

	@Override
	public ReferenceIndustryGroup getReferenceByCode(String code) {
		LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
		String hqlString = "SELECT a FROM ReferenceIndustryGroup a where UPPER(a.code) = UPPER(?1)";
		TypedQuery<ReferenceIndustryGroup> query = entityManager.createQuery(hqlString, ReferenceIndustryGroup.class);
		query.setParameter(1, code);
		return query.getSingleResult();
	}

	@Override
	public boolean isCodeExists(String code) {
		LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

		String query = "SELECT case when (count(a) > 0) then true else false end "
				+ "FROM ReferenceIndustryGroup a WHERE UPPER(a.code) = UPPER(?1)";

		TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
		result.setParameter(1, code);

		return result.getSingleResult();
	}

}
