/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 9:40:07 AM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintPurposeOfTinRepository;

@Service
public class MaintPurposeOfTinServiceImpl implements MaintPurposeOfTinService {

	private static final Logger LOG = LoggerFactory.getLogger(MaintPurposeOfTinServiceImpl.class);

	@Autowired
	private MaintPurposeOfTinRepository maintPurposeOfTinRepository;

	@Autowired
	private PaginationRepository<MaintPurposeOfTin> paginationRepository;

	@Autowired
	private CommonRepository commonRepository;

	@Transactional(readOnly = true)
	@Override
	public List<MaintPurposeOfTin> findAll() {
		LOG.info("SERVICE : FIND ALL {}", "MaintPurposeOfTin");
		return commonRepository.findAll(MaintPurposeOfTin.class, CommonConstructor.ID_CODE_DESCRIPTION);
	}

	@Transactional(readOnly = true)
	@Override
	public MaintPurposeOfTin findByCode(String code) {
		LOG.info("SERVICE : FIND BY CODE: {}", code);

		try {
			return maintPurposeOfTinRepository.findByCode(code);
		} catch (EmptyResultDataAccessException ex) {
			throw new ApiNotFoundException(EtisModules.SYSAD, "MaintPurposeOfTin findByCode: " + code);
		}
	}

	@Transactional
	@Override
	public MaintPurposeOfTin save(MaintPurposeOfTin MaintPurposeOfTin) {
		Date today = new Date();
		MaintPurposeOfTin.setCreatedDate(today);
		MaintPurposeOfTin.setUpdatedDate(today);
		LOG.info("SERVICE : SAVE: {}", MaintPurposeOfTin);

		if (maintPurposeOfTinRepository.isCodeExists(MaintPurposeOfTin.getCode()))
			throw new ApiConstraintViolationException(EtisModules.SYSAD,
					"MaintPurposeOfTin Save: " + MaintPurposeOfTin);

		try {

			return commonRepository.save(MaintPurposeOfTin);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiConstraintViolationException(EtisModules.SYSAD, ex.getMostSpecificCause().toString());
		}
	}

	@Transactional
	@Override
	public MaintPurposeOfTin update(MaintPurposeOfTin MaintPurposeOfTin) {

		String id = MaintPurposeOfTin.getId();
		MaintPurposeOfTin result = commonRepository.findById(id, MaintPurposeOfTin.class);
		LOG.info("SERVICE : UPDATE: findById result: {}", result);
		if (result == null)
			throw new ApiNotFoundException(EtisModules.SYSAD, "MaintPurposeOfTin Update findById: " + id);

		try {

			MaintPurposeOfTin.setUpdatedDate(new Date());
			LOG.info("SERVICE : UPDATE: {}", MaintPurposeOfTin);

			return commonRepository.update(MaintPurposeOfTin);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiConstraintViolationException(EtisModules.SYSAD, ex.getMostSpecificCause().toString());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<MaintPurposeOfTin> advanceSearch(MaintPurposeOfTinRequest request) {
		LOG.info("SERVICE : ADVANCE SEARCH {}", request);

		return maintPurposeOfTinRepository.advanceSearch(request);
	}

	@Transactional(readOnly = true)
	@Override
	public ServerSidePaginationResponse<MaintPurposeOfTin> serverSideSearch(
			ServerSidePaginationRequest<MaintPurposeOfTinRequest> request) {
		LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

		String hqlString = "SELECT a FROM MaintPurposeOfTin a WHERE a.id is not null";
		QueryBuilder<MaintPurposeOfTinRequest> builder = new QueryBuilder<>();
		builder.startBuild(request)
				.buildLikeStartsWithValue(
						new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
				.buildLikeContainsValue(new QueryBuilderParameter("a.description",
						request.getClientParam().getDescription(), "description"))
				.buildOrderBy();
		ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

		String hqlCountString = "SELECT count(a) FROM MaintPurposeOfTin a WHERE a.id is not null";
		ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

		ServerSidePaginationResponse<MaintPurposeOfTin> response = new ServerSidePaginationResponse<>();
		response.setResult(paginationRepository.serverSideSearch(serverParam, MaintPurposeOfTin.class));
		response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

		return response;
	}

	@Transactional(readOnly = true)
	@Override
	public MaintPurposeOfTin findById(String id) {

		LOG.info("SERVICE: Find By Id {}", id);

		return maintPurposeOfTinRepository.findById(id);
	}

	@Override
	public List<MaintPurposeOfTin> findAllValid() {
		final String newConstructor = "(a.id, a.code, a.description)";
		return commonRepository.findAllValid(MaintPurposeOfTin.class, newConstructor);
	}

	@Override
	public List<MaintPurposeOfTin> findPurposeOfTinByTaxpayerType(String taxpayerClassificationId) {
		return maintPurposeOfTinRepository.findPurposeOfTinByTaxpayerType(taxpayerClassificationId);
	}

}
