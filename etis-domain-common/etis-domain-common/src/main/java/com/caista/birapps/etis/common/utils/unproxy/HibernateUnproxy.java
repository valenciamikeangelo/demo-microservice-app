/*
  * Modified by: obregoj
  * Last updated: Apr 17, 2019 10:03:20 AM
  */
package com.caista.birapps.etis.common.utils.unproxy;

import org.hibernate.proxy.*;

public class HibernateUnproxy {

  @SuppressWarnings("unchecked")
  public static <T> T unproxy(T proxyObject) {

    T unproxiedObject = null;

    if (proxyObject instanceof HibernateProxy) {
      HibernateProxy hibernateProxy = (HibernateProxy) proxyObject;
      LazyInitializer initializer = hibernateProxy.getHibernateLazyInitializer();
      unproxiedObject = (T) initializer.getImplementation();
    }

    return unproxiedObject;

  }

}
