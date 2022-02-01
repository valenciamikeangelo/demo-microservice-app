/*
 * Modified by: santojo
 * Last updated: Nov 12, 2018 4:12:35 PM
 */
package com.caista.birapps.etis.domain.sysad.reference.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(value = PropertyLength.class)
public @interface DedicatedLength {

  public int value();

  public String name();
}
