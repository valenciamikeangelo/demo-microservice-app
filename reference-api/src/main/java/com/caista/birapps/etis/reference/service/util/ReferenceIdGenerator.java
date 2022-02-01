/*
  * Modified by: logronj
  * Last updated: Nov 26, 2018 3:43:02 PM
*/
package com.caista.birapps.etis.reference.service.util;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.caista.birapps.etis.domain.sysad.idgenerator.context.generators.GenerateTexts;

@Component
public class ReferenceIdGenerator {

  private static final ReferenceIdGenerator INSTANCE = new ReferenceIdGenerator();

  @Autowired
  private GenerateTexts generateText;
  // PRIVATE CONSTRUCTOR FOR UTIL CLASS
  private ReferenceIdGenerator() {}

  public String generateReferenceId(String prefix, String code) {
    String generatedId = "";
    Date generationTime = new Date();
    String generatedText = generateText.generateId(5);
    generatedId = prefix.concat("-" + generationTime.getTime() + "-" + generatedText);
    return generatedId;
  }

  public static ReferenceIdGenerator getInstance() {
    return INSTANCE;
  }

}
