/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:35 PM
 */
package com.caista.birapps.etis.domain.export.csv;

import java.io.IOException;
import java.util.Map;
import org.supercsv.io.CsvMapWriter;

// TODO: Auto-generated Javadoc
/**
 * The Interface ExportCsvStrategy.
 */
public interface ExportCsvStrategy {

  /**
   * For csv export.
   *
   * @param csvWriter the csv writer
   * @param modelToExport the model to export
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void forCsvExport(CsvMapWriter csvWriter, Map<String, Object> modelToExport)
      throws IOException;
}
