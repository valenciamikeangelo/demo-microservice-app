/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:15:13 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;

public interface MaintBankRepository {

	public List<MaintBank> findAllForModules();

	public MaintBank findByCode(String code);

	public boolean isCodeExists(String code);

	public MaintBank save(MaintBank maintCountry);

	public MaintBank update(MaintBank maintCountry);

	public List<MaintBank> advanceSearch(MaintBankRequest request);

	public MaintBank findById(String id);

	public List<MaintBank> getAllValidByGovtFlag();

	public MaintBank findAbbrevByBankName(String bankName) throws NoResultException, NonUniqueResultException ;

}
