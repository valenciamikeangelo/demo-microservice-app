/*
  * Modified by: pastolc
  * Last updated: 08 15, 20 1:12:06 PM
  */
package com.caista.birapps.etis.common.utils.serverside;

import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.caista.birapps.etis.domain.sysad.enums.StatusEnum;

public class QueryBuilder<T> {

  private String whereClauseArgs;
  private String orderBy;
  private Map<String, Object> columnValueMap;
  private ServerSidePaginationRequest<T> param;
  private static final String AND = " AND ";
  private static final String UPPER = " UPPER ";

  public QueryBuilder<T> buildInList(QueryBuilderParameter param) {
    if (param.isParamValid()) {
      this.whereClauseArgs = this.whereClauseArgs
          .concat(AND + UPPER + "(" + param.getColumn() + ") IN (" + ":" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), param.getValue());
    }

    return this;
  }

  public QueryBuilder<T> buildOrderByWithAlias(String alias) {

    if (this.param.getColumnParam() != null
        && StringUtils.isNotBlank(this.param.getColumnParam().getColumnName())) {
      StringBuilder orderBuilder = new StringBuilder();
      orderBuilder.append(" ORDER BY ");
      ServerSidePaginationRowParameter rowParam = this.param.getColumnParam();

      String direction = rowParam.getDirection();
      String columnName = rowParam.getColumnName();
      if (direction == null) {
        direction = "desc";
      }

      if (columnName.equalsIgnoreCase("createdBy")) {
        orderBuilder.append(" UPPER(").append(alias + "." + columnName).append(")");
      } else {
        orderBuilder.append(alias + "." + columnName);
      }

      orderBuilder.append(" ").append(direction);
      orderBuilder.append(", " + alias + "." + "id ").append(direction);

      this.orderBy = this.orderBy.concat(orderBuilder.toString());
      this.whereClauseArgs = this.whereClauseArgs.concat(this.orderBy);
    }

    return this;
  }

  public QueryBuilder<T> buildIsNull(QueryBuilderParameter param) {
    this.whereClauseArgs = this.whereClauseArgs
        .concat(AND + UPPER + "(" + param.getColumn() + ") IS NULL");

    return this;
  }

  public QueryBuilder<T> startBuild(ServerSidePaginationRequest<T> param) {
    this.whereClauseArgs = "";
    this.orderBy = "";
    this.columnValueMap = new HashMap<String, Object>();
    this.param = param;
    return this;
  }

  public QueryBuilder<T> buildOrderByWithNoAlias() {

    if (this.param.getColumnParam() != null
        && StringUtils.isNotBlank(this.param.getColumnParam().getColumnName())) {
      StringBuilder orderBuilder = new StringBuilder();
      orderBuilder.append(" ORDER BY ");
      ServerSidePaginationRowParameter rowParam = this.param.getColumnParam();

      String direction = rowParam.getDirection();
      String columnName = rowParam.getColumnName();
      if (direction == null) {
        direction = "desc";
      }

      orderBuilder.append(columnName);
      orderBuilder.append(" ").append(direction);

      this.orderBy = this.orderBy.concat(orderBuilder.toString());
      this.whereClauseArgs = this.whereClauseArgs.concat(this.orderBy);
    }

    return this;
  }

  public QueryBuilder<T> buildOrderByWithBranchCode(String branchCode) {

    if (this.param.getColumnParam() != null
        && StringUtils.isNotBlank(this.param.getColumnParam().getColumnName())) {
      StringBuilder orderBuilder = new StringBuilder();
      orderBuilder.append(" ORDER BY ");
      ServerSidePaginationRowParameter rowParam = this.param.getColumnParam();

      String direction = rowParam.getDirection();
      String columnName = rowParam.getColumnName();
      if (direction == null) {
        direction = "desc";
      }

      orderBuilder.append(columnName);
      orderBuilder.append(" ").append(direction).append(", ");
      orderBuilder.append(branchCode);
      orderBuilder.append(" ").append(direction);

      this.orderBy = this.orderBy.concat(orderBuilder.toString());
      this.whereClauseArgs = this.whereClauseArgs.concat(this.orderBy);
    }

    return this;
  }

  public QueryBuilder<T> buildExactValue(QueryBuilderParameter param) {
    if (param.isParamValid()) {
      this.whereClauseArgs = this.whereClauseArgs.concat(
          AND + UPPER + "(" + param.getColumn() + ") = UPPER(" + ":" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), param.getValue());
    }

    return this;
  }

  public QueryBuilder<T> buildEnumValue(QueryBuilderParameter param) {
    if (!param.getValue().toString().equalsIgnoreCase("NONE")) {
      this.whereClauseArgs = this.whereClauseArgs.concat(
          AND + UPPER + "(" + param.getColumn() + ") = UPPER(" + ":" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), param.getValue());
    }
    return this;
  }

  public QueryBuilder<T> buildDateExactValue(QueryBuilderParameter param) {
    if (param.isParamValid()) {
      this.whereClauseArgs = this.whereClauseArgs
          .concat(AND + "trunc(" + param.getColumn() + ") = trunc(" + ":" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), param.getValue());
    }

    return this;
  }

  public QueryBuilder<T> buildLikeContainsValue(QueryBuilderParameter param) {
    if (param.isParamValid()) {
      this.whereClauseArgs = this.whereClauseArgs.concat(
          AND + UPPER + "(" + param.getColumn() + ")" + "LIKE UPPER(:" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), "%" + param.getValue() + "%");
    }

    return this;
  }

  public QueryBuilder<T> buildLikeStartsWithValue(QueryBuilderParameter param) {
    if (param.isParamValid()) {
      this.whereClauseArgs = this.whereClauseArgs.concat(AND + UPPER + "(" + param.getColumn() + ")"
          + " LIKE UPPER(" + ":" + param.getName() + ") ");
      this.columnValueMap.put(param.getName(), param.getValue() + "%");
    }

    return this;
  }

  public QueryBuilder<T> buildStatusOfRecord(String status) {
    if (!StringUtils.isBlank(status) || !StringUtils.isEmpty(status)) {
      if (StringUtils.equalsIgnoreCase(status, StatusEnum.ACTIVE.toString())) {
        this.whereClauseArgs = this.whereClauseArgs
            .concat(AND + "CURRENT_DATE " + "< a.expiryDate ");
      } else {
        this.whereClauseArgs = this.whereClauseArgs.concat(AND + "CURRENT_DATE >= a.expiryDate ");
      }
    }

    return this;
  }

  public QueryBuilder<T> buildBetweenValues(QueryBuilderParameter param1,
      QueryBuilderParameter param2) {

    if (!param1.isParamValid() && !param2.isParamValid())
      this.whereClauseArgs = whereClauseArgs.concat(AND + param1.getColumn() + " >= 0 ");

    else if (param1.isParamValid() && !param2.isParamValid()) {
      this.whereClauseArgs = whereClauseArgs
          .concat(AND + param1.getColumn() + " >= :" + param1.getName());
      this.columnValueMap.put(param1.getName(), param1.getValue());

    } else if (!param1.isParamValid() && param2.isParamValid()) {
      this.whereClauseArgs = whereClauseArgs
          .concat(AND + param2.getColumn() + " <= :" + param2.getName());
      this.columnValueMap.put(param2.getName(), param2.getValue());

    } else {
      this.whereClauseArgs = whereClauseArgs.concat(AND + param1.getColumn() + " >= :"
          + param1.getName() + AND + param2.getColumn() + " <= :" + param2.getName());
      this.columnValueMap.put(param1.getName(), param1.getValue());
      this.columnValueMap.put(param2.getName(), param2.getValue());
    }
    return this;
  }

  public QueryBuilder<T> buildDatesInBetween(QueryBuilderParameter startDateParam,
      QueryBuilderParameter endDateParam, QueryBuilderParameter dateParam) {
    if (startDateParam.isParamValid() && endDateParam.isParamValid()) {
      this.whereClauseArgs = whereClauseArgs.concat(AND + dateParam.getColumn() + " BETWEEN " + ":"
          + startDateParam.getName() + AND + ":" + endDateParam.getName());
      this.columnValueMap.put(startDateParam.getName(), startDateParam.getValue());
      this.columnValueMap.put(endDateParam.getName(), endDateParam.getValue());
    }
    return this;
  }

  public QueryBuilder<T> buildDatesTruncated(Date dateFrom, Date dateTo,
      QueryBuilderParameter dateParam) {
    if (dateFrom != null && dateTo == null) {
      this.whereClauseArgs = whereClauseArgs.concat(AND + "TO_DATE(TO_CHAR(" + dateParam.getColumn()
          + ", 'MM/DD/YYYY'), 'MM/DD/YYYY') >= TO_DATE('"
          + new SimpleDateFormat("MM/dd/YYYY").format(dateFrom) + "', 'MM/DD/YYYY')");
    } else if (dateFrom == null && dateTo != null) {
      this.whereClauseArgs = whereClauseArgs.concat(AND + "TO_DATE(TO_CHAR(" + dateParam.getColumn()
          + ", 'MM/DD/YYYY'), 'MM/DD/YYYY') <= TO_DATE('"
          + new SimpleDateFormat("MM/dd/YYYY").format(dateTo) + "', 'MM/DD/YYYY')");
    } else if (dateFrom != null && dateTo != null) {
      this.whereClauseArgs = whereClauseArgs.concat(AND + "TO_DATE(TO_CHAR(" + dateParam.getColumn()
          + ", 'MM/DD/YYYY'), 'MM/DD/YYYY') BETWEEN TO_DATE('"
          + new SimpleDateFormat("MM/dd/YYYY").format(dateFrom) + "'"
          + ", 'MM/DD/YYYY') AND TO_DATE('" + new SimpleDateFormat("MM/dd/YYYY").format(dateTo)
          + "', 'MM/DD/YYYY')");
    }
    return this;
  }

  public QueryBuilder<T> buildOrderBy() {

    if (this.param.getColumnParam() != null) {
      StringBuilder orderBuilder = new StringBuilder();
      orderBuilder.append(" ORDER BY ");
      ServerSidePaginationRowParameter rowParam = this.param.getColumnParam();

      String direction = rowParam.getDirection();
      String columnName = rowParam.getColumnName();
      if (direction == null) {
        direction = "desc";
      }

      if (columnName.equalsIgnoreCase("a.createdBy")) {
        orderBuilder.append(" UPPER(").append(columnName).append(")");
      } else {
        orderBuilder.append(columnName);
      }

      orderBuilder.append(" ").append(direction);
      orderBuilder.append(", a.id ").append(direction);

      this.orderBy = this.orderBy.concat(orderBuilder.toString());
      this.whereClauseArgs = this.whereClauseArgs.concat(this.orderBy);
    }

    return this;
  }

  public QueryBuilder<T> buildOrderBy(String columnToSort) {
    if (this.param.getColumnParam() != null) {
      StringBuilder orderBuilder = new StringBuilder();
      orderBuilder.append(" ORDER BY ");
      ServerSidePaginationRowParameter rowParam = this.param.getColumnParam();

      String direction = rowParam.getDirection();
      String columnName = rowParam.getColumnName();

      orderBuilder.append(columnName);
      orderBuilder.append(" ").append(direction);
      orderBuilder.append(", ").append(columnToSort).append(" ").append(direction);

      this.orderBy = this.orderBy.concat(orderBuilder.toString());
      this.whereClauseArgs = this.whereClauseArgs.concat(this.orderBy);
    }
    return this;
  }

  public ServerSidePaginationParameter buildQuery(String hqlString) {
    // Assuming the query string has already a "WHERE" clause included
    hqlString = hqlString.concat(this.whereClauseArgs);
    return new ServerSidePaginationParameter(columnValueMap, hqlString,
        calculateOffset(this.param.getPageIndex(), this.param.getPageSize()), param.getPageSize());
  }

  private int calculateOffset(int pageIndex, int pageSize) {
    return (pageIndex * pageSize) - pageSize;
  }

}
