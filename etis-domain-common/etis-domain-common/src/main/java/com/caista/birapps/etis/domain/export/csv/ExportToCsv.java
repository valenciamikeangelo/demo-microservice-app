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
 * The Class ExportToCsv.
 */
public class ExportToCsv extends AbstractCsvView {

  /** The csv strategy. */
  private ExportCsvStrategy csvStrategy;

  /**
   * Instantiates a new export to csv.
   *
   * @param fileName the file name
   * @param csvStrategy the csv strategy
   */
  public ExportToCsv(String fileName, ExportCsvStrategy csvStrategy) {
    super(fileName);
    this.csvStrategy = csvStrategy;
  }

  @Override
  protected void buildCsvDocument(CsvMapWriter csvWriter, Map<String, Object> model)
      throws IOException {

    ExportCsvContext exporter = new ExportCsvContext.Builder(csvWriter, csvStrategy, model).build();

    exporter.exportCsv();

  }

}
