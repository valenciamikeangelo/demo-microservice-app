/*
  * Modified by: santosj
  * Last updated: Aug 6, 2018 4:42:09 PM
  */
package com.caista.birapps.etis.system.module.inquiry;

import org.springframework.beans.BeansException;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

/**
 * A factory for creating InquiryModule objects.
 */
@Component
public class InquiryModuleFactory implements ApplicationContextAware {

	/** The context. */
	private ApplicationContext context;

	/**
	 * Creates the.
	 *
	 * @return the report module
	 */
	public InquiryModule create() {
		InquiryModule inquiryModule = context.getBean(InquiryModule.class);
		return inquiryModule;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

}
