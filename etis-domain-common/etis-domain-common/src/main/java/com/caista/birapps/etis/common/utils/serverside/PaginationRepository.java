/*
 * Modified by: santojo
 * Last updated: Sep 13, 2018 10:47:18 AM
 */
package com.caista.birapps.etis.common.utils.serverside;

import java.util.List;

public interface PaginationRepository<T> {

  public List<T> serverSideSearch(ServerSidePaginationParameter serverParam, Class<T> domainClass);

  public Long serverSidePaginationCount(ServerSidePaginationParameter serverParamCount);
}
