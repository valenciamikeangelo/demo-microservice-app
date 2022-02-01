/*
 * Modified by: santojo
 * Last updated: Oct 31, 2018 1:45:47 PM
 */
package com.caista.birapps.etis.domain.sysad.idgenerator.context.generators;

import com.caista.birapps.etis.domain.sysad.idgenerator.context.GeneratorStrategy;

public class GenerateTextsAndNumbers implements GeneratorStrategy {

  @Override
  public String generateId(int length) {
    StringBuilder builder = new StringBuilder();

    builder.append(new GenerateNumbers().generateId(length));
    builder.append("-");
    builder.append(new GenerateTexts().generateId(length));

    return builder.toString();
  }

}
