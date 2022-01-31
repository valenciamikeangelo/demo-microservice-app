/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:35 PM
 */
package com.caista.birapps.etis.domain.export.util;

import java.util.*;
import org.apache.poi.ss.usermodel.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExportStrategy.
 */
public interface ExportStrategy {

  /**
   * For export.
   *
   * @param modelToExport the model to export
   * @param workbook the workbook
   * @param exportSheet the export sheet
   * @param results the results
   */
  public void forExport(Map<String, Object> modelToExport, Workbook workbook, Sheet exportSheet,
      List<?> results);
}
