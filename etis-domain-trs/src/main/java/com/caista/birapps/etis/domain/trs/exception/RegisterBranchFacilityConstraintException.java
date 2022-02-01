package com.caista.birapps.etis.domain.trs.exception;

import java.sql.SQLIntegrityConstraintViolationException;

public class RegisterBranchFacilityConstraintException extends SQLIntegrityConstraintViolationException {

  /**
   * 
   */
  private static final long serialVersionUID = -4115084575845387162L;


  public RegisterBranchFacilityConstraintException() {

  }
  
  public RegisterBranchFacilityConstraintException(Throwable cause) {
    super(cause);
  }

}
