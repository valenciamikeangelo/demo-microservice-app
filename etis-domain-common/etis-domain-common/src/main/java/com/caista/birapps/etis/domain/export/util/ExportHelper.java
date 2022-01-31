/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:36 PM
 */
package com.caista.birapps.etis.domain.export.util;


import org.apache.poi.ss.usermodel.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportHelper.
 *
 * @param <T> the generic type
 */
public class ExportHelper<T> {

  /** The values style. */
  private CellStyle valuesStyle;
  
  /** The header style. */
  private CellStyle headerStyle;

  public void setWorkbook(Workbook workbook) {

    headerStyle = workbook.createCellStyle();
    Font headerFont = workbook.createFont();
    headerFont.setBold(true);
    headerStyle.setFont(headerFont);
    headerStyle.setAlignment(HorizontalAlignment.CENTER);

    valuesStyle = workbook.createCellStyle();
    valuesStyle.setAlignment(HorizontalAlignment.CENTER);
    valuesStyle.setWrapText(true);

  }

  /**
   * Make cell.
   *
   * @param index the index
   * @param row the row
   * @return the cell
   */
  public Cell makeCell(int index, Row row) {

    Cell cell = row.createCell(index);
    cell.setCellStyle(constValuesStyle());

    return cell;
  }

  /**
   * Const header style.
   *
   * @return the cell style
   */
  public CellStyle constHeaderStyle() {
    return headerStyle;
  }

  /**
   * Const values style.
   *
   * @return the cell style
   */
  public CellStyle constValuesStyle() {
    return valuesStyle;
  }

  /**
   * Row value validator.
   *
   * @param rowValue the row value
   * @return the object
   */
  public Object rowValueValidator(T rowValue) {
    return rowValue == null ? "" : String.valueOf(rowValue);
  }

}
