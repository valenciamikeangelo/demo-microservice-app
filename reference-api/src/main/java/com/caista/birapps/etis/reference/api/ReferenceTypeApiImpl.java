/*
  * Modified by: logronj
  * Last updated: Oct 8, 2018 4:25:08 PM
*/
package com.caista.birapps.etis.reference.api;

import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;
import com.caista.birapps.etis.reference.service.ReferenceTypeLookupService;
import io.swagger.annotations.*;

/**
 * The Class ReferenceTypeApi.
 */
@RestController
@RequestMapping("/referenceType")
@Api(value = "ReferenceType API", produces = "application/json")
public class ReferenceTypeApiImpl implements ReferenceTypeApi {

  /** The Constant LOG. */
  private static final Logger LOG = LoggerFactory.getLogger(ReferenceTypeApiImpl.class);

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Override
  @ApiOperation(value = "Get All Reference Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "ok"),
      @ApiResponse(code = 404, message = "Unable to Process")})
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<ReferenceTypeLookup>> getAllReferenceType() {
    return new ResponseEntity<List<ReferenceTypeLookup>>(
        referenceTypeLookupService.getAllReferenceType(), HttpStatus.OK);
  }

}
