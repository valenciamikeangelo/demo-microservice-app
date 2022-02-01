/*
  * Modified by: logronj
  * Last updated: Oct 8, 2018 4:24:52 PM
*/
package com.caista.birapps.etis.reference.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;

public interface ReferenceTypeApi {

  public ResponseEntity<List<ReferenceTypeLookup>> getAllReferenceType();


}
