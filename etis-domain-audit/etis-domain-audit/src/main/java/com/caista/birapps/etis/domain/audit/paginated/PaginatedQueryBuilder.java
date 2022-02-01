/*
 * Modified by: romeror
 * Last updated: Dec 17, 2018 12:35:20 PM
 */
package com.caista.birapps.etis.domain.audit.paginated;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * The Class PaginatedQueryBuilder.
 */
public class PaginatedQueryBuilder {

  /** The table column map. */
  private Map<Integer, String> tableColumnMap;

  /** The query string. */
  private String queryString;

  /** The offset string. */
  private String offsetString;

  /** The limit string. */
  private String limitString;

  /** The order by string. */
  private String orderByString;

  /** The sql where. */
  private final String SQL_WHERE = " WHERE ";

  /** The sql and. */
  private final String SQL_AND = " AND ";

  /** The sql or. */
  private final String SQL_OR = " OR ";

  /** The sql blank. */
  private final String SQL_BLANK = "";

  /**
   * Instantiates a new paginated query builder.
   *
   * @param tableColumnMap the table column map
   */
  public PaginatedQueryBuilder(Map<Integer, String> tableColumnMap) {
    super();
    this.tableColumnMap = tableColumnMap;
  }

  /**
   * Instantiates a new paginated query builder.
   */
  public PaginatedQueryBuilder() {
    super();
  }

  /**
   * Where.
   *
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder where() {
    this.queryString = SQL_WHERE;
    return this;
  }

  /**
   * And.
   *
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder and() {
    if (!StringUtils.equals(queryString, SQL_WHERE)) {
      this.queryString = StringUtils.stripEnd(queryString, SQL_AND);
      this.queryString = queryString.concat(SQL_AND);
    }
    return this;
  }

  /**
   * And conditional.
   *
   * @param <T> the generic type
   * @param value the value
   * @return the paginated query builder
   */
  public <T> PaginatedQueryBuilder andConditional(T value) {
    if (value != null) {
      if(StringUtils.isNoneEmpty(queryString)) {
        if (!StringUtils.equals(queryString, SQL_WHERE)) {
          this.queryString = StringUtils.stripEnd(queryString, SQL_AND);
          this.queryString = queryString.concat(SQL_AND);
        }
      } else {
        this.queryString = Objects.toString(queryString, " ");
      }
    } else {
      this.queryString = queryString.concat(SQL_BLANK);
    }
    return this;
  }

  /**
   * Or.
   *
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder or() {
    if (!StringUtils.equals(queryString, SQL_WHERE)) {
      this.queryString = StringUtils.stripEnd(queryString, SQL_OR);
      this.queryString = queryString.concat(SQL_OR);
    }
    return this;
  }

  /**
   * Start group.
   *
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder startGroup() {
    this.queryString = queryString.concat("(");
    return this;
  }

  /**
   * End group.
   *
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder endGroup() {
    this.queryString = queryString.concat(")");
    return this;
  }


  /**
   * Builds the offset.
   *
   * @param offset the offset
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder buildOffset(Integer offset) {
    this.offsetString = "OFFSET " + offset + " ROWS";
    return this;
  }

  /**
   * Builds the limit.
   *
   * @param limit the limit
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder buildLimit(Integer limit) {
    this.limitString = "FETCH NEXT " + limit + " ROWS ONLY";
    return this;
  }

  /**
   * Compare equal.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareEqual(Integer tableColumnIndex, String value) {
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString
          .concat(tableColumnMap.get(tableColumnIndex) + " = '" + value + "'");
    }
    return this;
  }

  /**
   * Compare Not equal.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareNotEqual(Integer tableColumnIndex, String value) {
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString
          .concat(tableColumnMap.get(tableColumnIndex) + " != '" + value + "'");
    }
    return this;
  }

  /**
   * Compare equal.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareEqual(Integer tableColumnIndex, Long value) {
    if (value != null) {
      this.queryString = queryString.concat(tableColumnMap.get(tableColumnIndex) + " = " + value);
    }
    return this;
  }

  /**
   * Compare like.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareLike(Integer tableColumnIndex, String value) {
    value = escapeSingleQuoteInParam(value);
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString.concat(
          "UPPER(" + tableColumnMap.get(tableColumnIndex) + ") LIKE UPPER('" + value + "')");
    }
    return this;
  }

  /**
   * Compare full wildcard like.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareFullWildcardLike(Integer tableColumnIndex, String value) {
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString.concat(
          "UPPER(" + tableColumnMap.get(tableColumnIndex) + ") LIKE UPPER('%" + value + "%')");
    }
    return this;
  }

  /**
   * Compare soundex.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareSoundex(Integer tableColumnIndex, String value) {
    value = escapeSingleQuoteInParam(value);
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString.concat("SOUNDEX(UPPER(" + tableColumnMap.get(tableColumnIndex)
          + ")) = SOUNDEX(UPPER('" + value + "'))");
    }
    return this;
  }

  /**
   * Compare soundex nvl middle name.
   *
   * @param tableColumnLastNameIndex the table column last name index
   * @param tableColumnMiddleNameIndex the table column middle name index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareSoundexNvlMiddleName(Integer tableColumnLastNameIndex,
      Integer tableColumnMiddleNameIndex, String value) {
    value = escapeSingleQuoteInParam(value);
    if (StringUtils.isNotBlank(value)) {
      this.queryString = queryString.concat(" (UPPER("
          + tableColumnMap.get(tableColumnMiddleNameIndex) + ") IS NULL " + "OR SOUNDEX(NVL(UPPER('"
          + value + "') , " + tableColumnMap.get(tableColumnLastNameIndex) + ")) = SOUNDEX(UPPER('"
          + value + "'))" + "OR SOUNDEX(NVL(UPPER('" + value + "') , "
          + tableColumnMap.get(tableColumnMiddleNameIndex) + ")) = SOUNDEX(UPPER('" + value
          + "')))");
    }
    return this;
  }

  /**
   * Compare date MMDDYYYY.
   *
   * @param tableColumnIndex the table column index
   * @param value the value
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder compareDateMMDDYYYY(Integer tableColumnIndex, Date value) {
    if (value != null) {
      this.queryString = queryString.concat("TO_CHAR(" + tableColumnMap.get(tableColumnIndex)
          + ", 'MM/DD/YYYY') = '" + new SimpleDateFormat("MM/dd/YYYY").format(value) + "'");
    }
    return this;
  }

  /**
   * Order by.
   *
   * @param columnSort the column sort
   * @return the paginated query builder
   */
  public PaginatedQueryBuilder orderBy(List<PaginatedSearchColumnSort> columnSort) {
    if (!CollectionUtils.isEmpty(columnSort)) {
      Collections.sort(columnSort);
      for (PaginatedSearchColumnSort sort : columnSort) {

        orderByString = (StringUtils.isNotBlank(orderByString))
            ? orderByString + ","
            : "ORDER BY";

        if (StringUtils.equalsIgnoreCase(sort.getDirection(), "ASC")) {
          this.orderByString = orderByString
              .concat(" " + tableColumnMap.get(sort.getColumnIndex()) + " ASC");
        } else {
          this.orderByString = orderByString
              .concat(" " + tableColumnMap.get(sort.getColumnIndex()) + " DESC");
        }
      }
    }
    return this;
  }

  /**
   * Builds the paginated query.
   *
   * @return the paginated query
   */
  public PaginatedQuery buildPaginatedQuery() {
    this.queryString = StringUtils.stripEnd(queryString, SQL_OR);
    this.queryString = StringUtils.stripEnd(queryString, SQL_AND);
    return new PaginatedQuery(queryString, orderByString, offsetString, limitString);
  }

  /** The Constant SINGLE_QUOTE_TOKENNIZER. */
  public static final String SINGLE_QUOTE_TOKENNIZER = "'|| '''' || '";

  /** The Constant SINGLE_QUOTE. */
  public static final String SINGLE_QUOTE = "'";

  /**
   * Escape single quote in param.
   *
   * @param param the param
   * @return the string
   */
  public static String escapeSingleQuoteInParam(String param) {
    if (!StringUtils.isBlank(param) && param.contains(SINGLE_QUOTE)) {
      param = param.replaceAll(SINGLE_QUOTE, SINGLE_QUOTE_TOKENNIZER);
    }
    return param;
  }

}
