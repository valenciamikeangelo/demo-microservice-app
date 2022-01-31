/*
 * Last modified by: delmund
 * Last updated date: Jun 1, 2018 3:04:37 PM
 */
package com.caista.birapps.etis.domain.export.csv;

import java.io.IOException;
import java.util.*;
import org.supercsv.io.CsvMapWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class ExportCsvContext.
 */
public class ExportCsvContext {

  /** The csv writer. */
  private CsvMapWriter csvWriter;

  /** The csv strategy. */
  private ExportCsvStrategy csvStrategy;
  
  /** The model to export. */
  private Map<String, Object> modelToExport = new HashMap<>();

  /**
   * Instantiates a new export csv context.
   *
   * @param builder the builder
   */
  public ExportCsvContext(Builder builder) {
    this.csvWriter = builder.csvWriter;
    this.csvStrategy = builder.csvStrategy;
    this.modelToExport = builder.modelToExport;
  }

  /**
   * Export csv.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  public void exportCsv() throws IOException {

    String[] headers = (String[]) modelToExport.get("csvHeader");
    csvWriter.writeHeader(headers);

    csvStrategy.forCsvExport(csvWriter, modelToExport);
  }

  /**
   * The Class Builder.
   */
  public static class Builder {

    /** The csv writer. */
    private CsvMapWriter csvWriter;
    
    /** The csv strategy. */
    private ExportCsvStrategy csvStrategy;
    
    /** The model to export. */
    private Map<String, Object> modelToExport = new HashMap<>();

    /**
     * Builds the.
     *
     * @return the export csv context
     */
    public ExportCsvContext build() {
      return new ExportCsvContext(this);
    }

    /**
     * Instantiates a new builder.
     *
     * @param csvWriter the csv writer
     * @param csvStrategy the csv strategy
     * @param modelToExport the model to export
     */
    public Builder(CsvMapWriter csvWriter, ExportCsvStrategy csvStrategy,
        Map<String, Object> modelToExport) {
      this.csvWriter = csvWriter;
      this.csvStrategy = csvStrategy;
      this.modelToExport = modelToExport;
    }

  }
}
