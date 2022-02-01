/*
 * Modified by: tolentf
 * Last updated: Aug 9, 2019 10:26:23 AM
 */
package com.caista.birapps.etis.maintenance.api;

import java.util.*;
import org.springframework.http.*;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.object.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.*;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.*;

public interface MaintTaskEmailGroupApi {
  public ResponseEntity<List<MaintTaskEmailGroup>> findAll();

  public ResponseEntity<List<MaintTaskEmailGroup>> findAllValid();

  public ResponseEntity<MaintTaskEmailGroup> findByCode(String code);

  public ResponseEntity<MaintTaskEmailGroup> findByTaskIdAndOfficeId(String maintTaskId,
      String officeId);

  public ResponseEntity<MaintTaskEmailGroup> save(MaintTaskEmailGroup maintTaskEmailGroup);

  public ResponseEntity<MaintTaskEmailGroup> update(MaintTaskEmailGroup maintTaskEmailGroup);

  public ResponseEntity<TaskEmailGroup> save(TaskEmailGroup maintTaskEmailGroup);

  public ResponseEntity<TaskEmailGroup> update(TaskEmailGroup maintTaskEmailGroup);

  public ResponseEntity<List<MaintTaskEmailGroupMember>> getEmailGroupMemberById(
      String emailGroupId);

  public ResponseEntity<ServerSidePaginationResponse<MaintTaskEmailGroup>> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskEmailGroupRequest> request);

  public ResponseEntity<List<User>> getUpdatedEmailList(HttpHeaders header, String officeCode,
      String taskId, String resourceName);

  public ResponseEntity<List<MaintTaskEmailGroupMember>> getEmailGroupMemberByTaskIdAndOfficeId(
      String taskId, String officeIdStr);
}
