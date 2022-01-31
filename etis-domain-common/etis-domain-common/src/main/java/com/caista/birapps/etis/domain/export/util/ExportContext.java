/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:35 PM
 */
package com.caista.birapps.etis.domain.export.util;

import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportContext.
 */
public class ExportContext {

  /** The export strategy. */
  private ExportStrategy exportStrategy;

  /** The workbook. */
  private Workbook workbook;
  
  /** The model to export. */
  private Map<String, Object> modelToExport = new HashMap<>();
  
  /** The header list. */
  private List<String> headerList = new ArrayList<>();

  /**
   * Instantiates a new export context.
   *
   * @param builder the builder
   */
  public ExportContext(Builder builder) {
    this.exportStrategy = builder.exportStrategy;
    this.modelToExport = builder.modelToExport;

    this.workbook = builder.workbook;

    this.modelToExport.put("sheetName", builder.sheetName);

    this.headerList = builder.headerList;
    modelToExport.put("headers", headerList);
  }

  /**
   * Export to excel.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public void exportToExcel() {

    ExportHelper exportHelper = new ExportHelper();
    exportHelper.setWorkbook(workbook);

    String sheetName = (String) modelToExport.get("sheetName");
    List<String> headers = (List<String>) modelToExport.get("headers");
    List<?> results = (List<?>) modelToExport.get("results");

    Sheet exportSheet = workbook.createSheet(sheetName);
    exportSheet.setDefaultColumnWidth((short) 30);
    exportSheet.autoSizeColumn(3);

    int currentColumn = 0;
    Row headerRow = exportSheet.createRow(0);

    for (String header : headers) {
      HSSFRichTextString text = new HSSFRichTextString(header);
      Cell cellHeader = headerRow.createCell(currentColumn);
      cellHeader.setCellStyle(exportHelper.constHeaderStyle());
      cellHeader.setCellValue(text);
      currentColumn++;
    }

    this.exportStrategy.forExport(modelToExport, workbook, exportSheet, results);
  }

  /**
   * The Class Builder.
   */
  public static class Builder {

    /** The workbook. */
    private Workbook workbook;
    
    /** The model to export. */
    private Map<String, Object> modelToExport = new HashMap<>();

    /** The sheet name. */
    private String sheetName;
    
    /** The header list. */
    private List<String> headerList = new ArrayList<>();

    /** The export strategy. */
    private ExportStrategy exportStrategy;

    /**
     * Instantiates a new builder.
     *
     * @param modelToExport the model to export
     * @param workbook the workbook
     */
    public Builder(Map<String, Object> modelToExport, Workbook workbook) {
      this.modelToExport = modelToExport;
      this.workbook = workbook;
    }

    /**
     * Export strategy.
     *
     * @param exportStrategy the export strategy
     * @return the builder
     */
    public Builder exportStrategy(ExportStrategy exportStrategy) {
      this.exportStrategy = exportStrategy;
      return this;
    }

    /**
     * Sheet.
     *
     * @param sheetName the sheet name
     * @return the builder
     */
    public Builder sheet(String sheetName) {
      this.sheetName = sheetName;
      return this;
    }

    /**
     * Adds the header.
     *
     * @param headerList the header list
     * @return the builder
     */
    public Builder addHeader(String[] headerList) {
      this.headerList = Arrays.asList(headerList);
      return this;
    }

    /**
     * Adds the header.
     *
     * @param headerList the header list
     * @return the builder
     */
    public Builder addHeader(List<String> headerList) {
      this.headerList = headerList;
      return this;
    }

    /**
     * Adds the header.
     *
     * @param headerName the header name
     * @return the builder
     */
    public Builder addHeader(String headerName) {
      this.headerList.add(headerName);
      return this;
    }

    /**
     * Builds the.
     *
     * @return the export context
     */
    public ExportContext build() {
      return new ExportContext(this);
    }

  }
}
