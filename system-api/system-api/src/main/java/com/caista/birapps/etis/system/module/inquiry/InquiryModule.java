/*
  * Modified by: santosj
  * Last updated: Aug 6, 2018 4:27:56 PM
  */
package com.caista.birapps.etis.system.module.inquiry;

import java.util.*;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

import com.caista.birapps.etis.system.module.Module;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "inquiry")
@PropertySource("classpath:inquiry-modules.properties")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InquiryModule {

	/** The modules. */
	private List<Module> modules = new ArrayList<Module>();

	public List<Module> getModules() {
		return modules;
	}

}
