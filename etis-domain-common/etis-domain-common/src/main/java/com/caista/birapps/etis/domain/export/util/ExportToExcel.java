/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:39 PM
 */
package com.caista.birapps.etis.domain.export.util;

import java.util.*;
import javax.servlet.http.*;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportToExcel.
 */
public class ExportToExcel extends AbstractXlsView {

  /** The export strategy. */
  private ExportStrategy exportStrategy;
  
  /** The headers list. */
  private List<String> headersList;
  
  /** The sheet name. */
  private String sheetName;
  
  /** The file name. */
  private String fileName;

  /**
   * Instantiates a new export to excel.
   *
   * @param exportStrategy the export strategy
   * @param sheetName the sheet name
   * @param headersList the headers list
   * @param fileName the file name
   */
  public ExportToExcel(ExportStrategy exportStrategy, String sheetName, List<String> headersList,
      String fileName) {
    this.exportStrategy = exportStrategy;
    this.sheetName = sheetName;
    this.headersList = headersList;
    this.fileName = fileName;
  }

  @Override
  protected void buildExcelDocument(Map<String, Object> modelToExport, Workbook workbook,
      HttpServletRequest request, HttpServletResponse response) throws Exception {

    response.setContentType("text/html");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".xlsx\"");
    response.flushBuffer();

    ExportContext exporter = new ExportContext.Builder(modelToExport, workbook)
        .exportStrategy(exportStrategy).sheet(sheetName).addHeader(headersList).build();

    exporter.exportToExcel();
  }

}
