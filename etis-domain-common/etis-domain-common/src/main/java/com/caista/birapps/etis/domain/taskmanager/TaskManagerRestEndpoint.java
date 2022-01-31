/*
  * Modified by: romeror
  * Last updated: Nov 9, 2018 3:23:31 PM
 */
package com.caista.birapps.etis.domain.taskmanager;

import retrofit2.Call;
import retrofit2.http.*;

public interface TaskManagerRestEndpoint {

  @SuppressWarnings("rawtypes")
  @POST("processInstances/")
  Call<Object> sendToTask(@Header("Authorization") String token,
      @Body ProcessInstanceRequest taskInstance);
}
