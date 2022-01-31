/*
 * Modified by: santojo
 * Last updated: Jun 19, 2018 12:26:56 PM
 */
package com.caista.birapps.etis.domain.export.csv;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.prefs.CsvPreference;


/**
 * The Class AbstractCsvView.
 */
public abstract class AbstractCsvView extends AbstractView {

  /** The file name. */
  private String fileName;

  /**
   * Instantiates a new abstract csv view.
   *
   * @param fileName the file name
   */
  public AbstractCsvView(String fileName) {
    this.fileName = fileName;
  }

  @Override
  protected void prepareResponse(HttpServletRequest request, HttpServletResponse response) {

    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s.csv\"", fileName + "");

    response.setContentType("text/csv;charset=UTF-8");
    response.setHeader(headerKey, headerValue);
  }

  @Override
  protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
      HttpServletResponse response) throws Exception {

    CsvMapWriter mapWriter =
        new CsvMapWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

    buildCsvDocument(mapWriter, model);
    mapWriter.close();
    response.flushBuffer();
  }

  /**
   * Builds the csv document.
   *
   * @param csvWriter the csv writer
   * @param model the model
   * @throws IOException Signals that an I/O exception has occurred.
   */
  protected abstract void buildCsvDocument(CsvMapWriter csvWriter, Map<String, Object> model)
      throws IOException;

}
