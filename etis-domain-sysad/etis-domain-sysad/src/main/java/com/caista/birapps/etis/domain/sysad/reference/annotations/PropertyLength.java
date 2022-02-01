/*
 * Modified by: santojo
 * Last updated: Nov 12, 2018 4:13:01 PM
 */
package com.caista.birapps.etis.domain.sysad.reference.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PropertyLength {

  public DedicatedLength[] value();
}

