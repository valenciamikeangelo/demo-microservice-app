/*
 * Modified by: tolentf
 * Last updated: Oct 9, 2018 5:27:57 PM
 */
package com.caista.birapps.etis.domain.taskmanager;

import com.caista.birapps.etis.domain.tas.TASTaxPayer;

import retrofit2.Call;
import retrofit2.http.*;

public interface TaxAccountingRestEndpoint {

  @POST("/createTaxpayerLedger")
  Call<TASTaxPayer> createTaxpayerLedger(@Header("Authorizaion") String token,
		  @Body TASTaxPayer tasTaxPayer);
}
