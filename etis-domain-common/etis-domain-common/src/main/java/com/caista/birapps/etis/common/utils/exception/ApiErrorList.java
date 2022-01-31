/*
 * Modified by: tans
 * Last updated: Feb 15, 2019 7:12:41 PM
 */
package com.caista.birapps.etis.common.utils.exception;

import java.util.List;

import com.caista.birapps.etis.common.utils.module.EtisModules;

/**
 * The Class ApiErrorList.
 */
public class ApiErrorList extends BaseApiRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * + Instantiates a new api error list.
	 *
	 * @param module
	 *            the module
	 * @param resource
	 *            the resource
	 */
	public <T> ApiErrorList(EtisModules module, List<T> errors) {
		super(module, errors + "");
	}

}