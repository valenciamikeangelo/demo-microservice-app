/*
 * Modified by: santojo
 * Last updated: Oct 31, 2018 1:37:34 PM
 */
package com.caista.birapps.etis.domain.sysad.idgenerator.context;

import java.lang.reflect.Field;
import org.slf4j.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;

public class GeneratorContext {

  public static final Logger LOG = LoggerFactory.getLogger(GeneratorContext.class);

  private String randomGenerator(IrisIdGenerator irisIdGenerator, String className,
      String uniqueField) {

    GeneratorTypeEnum genType = irisIdGenerator.type();
    GeneratorStrategy strategy = new GeneratorStrategyValidator()
        .validateStrategy(genType);
    StringBuilder builder = new StringBuilder();

    builder.append(className).append("-");
    builder.append(strategy.generateId(irisIdGenerator.length()));

    if (!uniqueField.equalsIgnoreCase("none")) {
      builder.append("-").append(uniqueField);
    }

    return builder.toString();
  }

  public <T> T generateId(T domainClass) {

    Field idField;
    Field uniqueField;

    try {
      idField = domainClass.getClass().getDeclaredField("id");
      idField.setAccessible(true);
      IrisIdGenerator irisIdGenerator = idField.getAnnotation(IrisIdGenerator.class);

      String validatedUniqueProperty = irisIdGenerator.uniqueProperty();
      String generatedRandom;


      if (irisIdGenerator.generateByUniqueProperty()) {
        uniqueField = domainClass.getClass().getDeclaredField(validatedUniqueProperty);
        uniqueField.setAccessible(true);
        idField.set(domainClass, uniqueField.get(domainClass).toString());

        return domainClass;
      }

      if (!validatedUniqueProperty.equalsIgnoreCase("none")) {
        uniqueField = domainClass.getClass().getDeclaredField(validatedUniqueProperty);
        uniqueField.setAccessible(true);
        generatedRandom = randomGenerator(irisIdGenerator, irisIdGenerator.name(),
            uniqueField.get(domainClass).toString());

      } else {
        generatedRandom = randomGenerator(irisIdGenerator, irisIdGenerator.name(), "none");
      }

      idField.set(domainClass, generatedRandom);

    } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
        | SecurityException e) {
      LOG.error(e.getMessage());
    }

    return domainClass;
  }

}
