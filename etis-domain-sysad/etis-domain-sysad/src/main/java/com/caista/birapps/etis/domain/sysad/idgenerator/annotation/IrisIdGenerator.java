/*
 * Modified by: santojo
 * Last updated: Oct 2, 2018 12:20:05 PM
 */
package com.caista.birapps.etis.domain.sysad.idgenerator.annotation;

import java.lang.annotation.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IrisIdGenerator {

  public String name();

  public int length() default 5; // init length = 0 to include all

  public String uniqueProperty() default "code";

  public GeneratorTypeEnum type() default GeneratorTypeEnum.NUMBERS_ONLY;

  public boolean generateByUniqueProperty() default false;

}