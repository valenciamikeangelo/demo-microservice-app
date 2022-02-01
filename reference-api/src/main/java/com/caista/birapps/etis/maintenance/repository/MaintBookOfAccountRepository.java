/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:12:21 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;

public interface MaintBookOfAccountRepository {

  public List<MaintBookOfAccount> findAll();

  public MaintBookOfAccount findById(String id);

  public MaintBookOfAccount findByCode(String code);

  public List<MaintBookOfAccount> findByCategory(String category);

  public boolean isCodeExists(String code);

  public MaintBookOfAccount save(MaintBookOfAccount maintBookOfAccount);

  public MaintBookOfAccount update(MaintBookOfAccount maintBookOfAccount);

  public List<MaintBookOfAccount> advanceSearch(MaintBookOfAccountRequest request);

}
