/*
 * Modified by: santojo
 * Last updated: Oct 31, 2018 1:46:19 PM
 */
package com.caista.birapps.etis.domain.sysad.idgenerator.context.generators;

import com.caista.birapps.etis.domain.sysad.idgenerator.context.GeneratorStrategy;

public class GenerateNumbers implements GeneratorStrategy {

  @Override
  public String generateId(int length) {
    StringBuilder builder = new StringBuilder();

    builder.append(String.valueOf(System.currentTimeMillis()));

    return builder.toString();
  }

}
