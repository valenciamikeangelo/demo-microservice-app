/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:17:44 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintBankRepository;

@Service
public class MaintBankServiceImpl implements MaintBankService {

	private static final Logger LOG = LoggerFactory.getLogger(MaintBankServiceImpl.class);

	@Autowired
	private MaintBankRepository maintBankRepository;

	@Autowired
	private PaginationRepository<MaintBank> paginationRepository;

	@Autowired
	private CommonRepository commonRepository;

	@Transactional(readOnly = true)
	@Override
	public List<MaintBank> findAll() {
		LOG.info("SERVICE: FIND ALL FOR MODULES CONSUMPTION {}", "MaintBank");
		return commonRepository.findAll(MaintBank.class, CommonConstructor.ID_CODE_DESCRIPTION);
	}

	@Transactional(readOnly = true)
	@Override
	public MaintBank findById(String id) {
		return maintBankRepository.findById(id);
	}

	@Transactional(readOnly = true)
	@Override
	public MaintBank findByCode(String code) {
		LOG.info("SERVICE : FIND BY CODE: {}", code);

		try {
			return maintBankRepository.findByCode(code);
		} catch (EmptyResultDataAccessException ex) {
			throw new ApiNotFoundException(EtisModules.SYSAD, "MaintBank findByCode: " + code);
		}
	}

	@Transactional
	@Override
	public MaintBank save(MaintBank maintBank) {
		Date today = new Date();
		maintBank.setCreatedDate(today);
		maintBank.setUpdatedDate(today);
		LOG.info("SERVICE : SAVE: {}", maintBank);

		if (maintBankRepository.isCodeExists(maintBank.getCode()))
			throw new ApiConstraintViolationException(EtisModules.SYSAD, "MaintBank Save: " + maintBank);

		try {
			return commonRepository.save(maintBank);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiConstraintViolationException(EtisModules.SYSAD, ex.getMostSpecificCause().toString());
		}
	}

	@Transactional
	@Override
	public MaintBank update(MaintBank maintBank) {

		String id = maintBank.getId();
		MaintBank result = commonRepository.findById(id, MaintBank.class);
		LOG.info("SERVICE : UPDATE: findById result: {}", result);
		if (result == null)
			throw new ApiNotFoundException(EtisModules.SYSAD, "MaintBank Update findById: " + id);

		try {
			maintBank.setUpdatedDate(new Date());
			LOG.info("SERVICE : UPDATE: {}", maintBank);
			return commonRepository.update(maintBank);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiConstraintViolationException(EtisModules.SYSAD, ex.getMostSpecificCause().toString());
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<MaintBank> advanceSearch(MaintBankRequest request) {
		LOG.info("SERVICE: ADVANCE SEARCH {}", request);
		return maintBankRepository.advanceSearch(request);
	}

	@Transactional(readOnly = true)
	@Override
	public ServerSidePaginationResponse<MaintBank> serverSideSearch(
			ServerSidePaginationRequest<MaintBankRequest> request) {
		LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

		String hqlString = "SELECT a FROM MaintBank a WHERE a.id is not null";
		QueryBuilder<MaintBankRequest> builder = new QueryBuilder<>();
		builder.startBuild(request)
				.buildLikeStartsWithValue(
						new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
				.buildLikeContainsValue(new QueryBuilderParameter("a.description",
						request.getClientParam().getDescription(), "description"))
				.buildLikeContainsValue(new QueryBuilderParameter("a.governmentDepositBankFlag",
						request.getClientParam().getGovernmentDepositBankFlag(), "governmentDepositBankFlag"))
				.buildLikeContainsValue(new QueryBuilderParameter("a.statusFlag",
						request.getClientParam().getStatusFlag(), "statusFlag"))
				.buildOrderBy();
		ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

		String hqlCountString = "SELECT count(a) FROM MaintBank a WHERE a.id is not null";
		ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

		ServerSidePaginationResponse<MaintBank> response = new ServerSidePaginationResponse<>();
		response.setResult(paginationRepository.serverSideSearch(serverParam, MaintBank.class));
		response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

		return response;
	}

	@Override
	public List<MaintBank> findAllValid() {
		return commonRepository.findAllValid(MaintBank.class, CommonConstructor.ID_CODE_DESCRIPTION);
	}

	@Override
	public List<MaintBank> getAllValidByGovtFlag() {
		LOG.info("SERVICE : FIND ALL FOR VALID BANK WITH GOVERNMENT DEPOSIT FLAG = {}", "Y");
		return maintBankRepository.getAllValidByGovtFlag();
	}

	@Override
	public MaintBank findAbbrevByBankName(String bankName) {
		LOG.info("SERVICE : FIND ABBREV BY BANK NAME {}", bankName);
		try {
			return maintBankRepository.findAbbrevByBankName(bankName);
		} catch (NoResultException e) {
			throw new ApiNoResultException(EtisModules.SYSAD, "findAbbrevByBankName");
		} catch (NonUniqueResultException e) {
			throw new ApiNotFoundException(EtisModules.SYSAD, "findAbbrevByBankName");
		}
	}

}
