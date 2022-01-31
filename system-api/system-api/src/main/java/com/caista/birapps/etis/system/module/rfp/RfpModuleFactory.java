/*
  * Modified by: santosj
  * Last updated: Aug 6, 2018 4:41:20 PM
  */
package com.caista.birapps.etis.system.module.rfp;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating RfpModule objects.
 */
@Component
public class RfpModuleFactory implements ApplicationContextAware {

	/** The context. */
	private ApplicationContext context;

	/**
	 * Creates the.
	 *
	 * @return the rfp module
	 */
	public RfpModule create() {
		RfpModule rfpModule = context.getBean(RfpModule.class);
		return rfpModule;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
}
