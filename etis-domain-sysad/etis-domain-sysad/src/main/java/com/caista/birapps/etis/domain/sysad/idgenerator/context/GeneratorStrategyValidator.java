/*
 * Modified by: santojo
 * Last updated: Oct 31, 2018 1:37:27 PM
 */
package com.caista.birapps.etis.domain.sysad.idgenerator.context;

import com.caista.birapps.etis.domain.sysad.idgenerator.context.generators.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;

public class GeneratorStrategyValidator {

  public GeneratorStrategy validateStrategy(GeneratorTypeEnum genType) {

    GeneratorStrategy strategy;

    if (genType == GeneratorTypeEnum.TEXTS_ONLY) {
      strategy = new GenerateTexts();
    } else if (genType == GeneratorTypeEnum.TEXTS_AND_NUMBERS) {
      strategy = new GenerateTextsAndNumbers();
    } else {
      strategy = new GenerateNumbers();
    }

    return strategy;
  }
}
