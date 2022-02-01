/*
  * Modified by: logronj
  * Last updated: Nov 26, 2018 3:43:38 PM
*/
package com.caista.birapps.etis.domain.sysad.idgenerator.context.generators;

import java.util.Random;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.sysad.idgenerator.context.GeneratorStrategy;

@Component
public class GenerateTexts implements GeneratorStrategy {

  private static final String TEXTS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  @Override
  public String generateId(int length) {
    StringBuilder builder = new StringBuilder();
    Random randomTexts = new Random();

    for (int i = 0; i < length; i++) {
      builder.append(TEXTS.charAt(randomTexts.nextInt(TEXTS.length())));
    }

    return builder.toString();
  }


}
