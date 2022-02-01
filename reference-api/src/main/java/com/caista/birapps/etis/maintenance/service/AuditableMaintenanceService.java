/*
  * Modified by: logronj
  * Last updated: Oct 2, 2018 1:38:21 PM
*/
package com.caista.birapps.etis.maintenance.service;

public interface AuditableMaintenanceService<T> {

  public T findById(String id);

}
