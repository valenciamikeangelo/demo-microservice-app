/*
  * Modified by: obregoj
  * Last updated: Sep 10, 2019 1:40:14 PM
  */
package com.caista.birapps.etis.common.utils.module;

import java.util.Arrays;
import java.util.List;

/**
 * @author valencm
 *
 */
public enum EtisModules {

	NONE("NONE"),

	AUDITSELECTION("AUDIT SELECTION SYSTEM", EtisSubmodules.CREATE_SELECTION_CRITERIA,
			EtisSubmodules.UPDATE_SELECTION_CRITERIA, EtisSubmodules.SAVE_SELECTION_CRITERIA,
			EtisSubmodules.FETCH_SELECTION_CRITERIAS, EtisSubmodules.CREATE_AUDIT_PROGRAM,
			EtisSubmodules.SAVE_AUDIT_PROGRAM, EtisSubmodules.UPDATE_AUDIT_PROGRAM, EtisSubmodules.FETCH_AUDIT_PROGRAMS,
			EtisSubmodules.SIMULATE_AUDIT_PROGRAM, EtisSubmodules.EXECUTE_AUDIT_PROGRAM),

	KEYCLOAK("KEYCLOAK IDM", EtisSubmodules.KEYCLOAK_USER_MANAGEMENT, EtisSubmodules.KEYCLOAK_USER_ACTIVITY),

	TRS("TAXPAYER REGISTRATION SYSTEM", EtisSubmodules.REGISTER_INDIVIDUAL, EtisSubmodules.REGISTER_NON_INDIVIDUAL,
			EtisSubmodules.REGISTER_FACILITY, EtisSubmodules.REGISTER_BRANCH),

	SYSAD("SYSTEM ADMINISTRATION", EtisSubmodules.REFERENCE, EtisSubmodules.MAINTENANCE, EtisSubmodules.MANAGE_OFFICE,
			EtisSubmodules.MANAGE_CASE_OUTLINE, EtisSubmodules.MANAGE_USER_ASSIGNMENT,
			EtisSubmodules.UPLOAD_USER_SIGNATURE, EtisSubmodules.MANAGE_USER_SIGNATURE, EtisSubmodules.MANAGE_SIGNATORY,
			EtisSubmodules.SUPPORTING_ATTACHMENTS, EtisSubmodules.ACTIVITY_LOG),

	BATCH("BATCH ARCHITECTURE MODULE", EtisSubmodules.UPDATE_SCHEDULE),

	DASHBOARD("DASHBOARD"),

	REPORT("REPORT MANAGEMENT", EtisSubmodules.CERTIFICATE_OF_REGISTRATION, EtisSubmodules.AUTHORITY_TO_PRINT,
			EtisSubmodules.CERTIFICATE_OF_REGISTRATION_OF_FACILITY, EtisSubmodules.TAX_CLEARANCE_CERTIFICATE),

	TASKMANAGER("TASK MANAGER", EtisSubmodules.CREATE_TASK, EtisSubmodules.ACQUIRE_TASK, EtisSubmodules.APPROVE_TASK,
			EtisSubmodules.REJECT_TASK, EtisSubmodules.RELEASE_TASK),

	TAXPAYER("TAXPAYER", EtisSubmodules.MANAGE_FINANCIAL_DETAIL, EtisSubmodules.TRANSFER_TAXPAYER_BUSINESS,
			EtisSubmodules.TRANSFER_TAXPAYER_NON_BUSINESS, EtisSubmodules.TAG_UNTAG_TAXPAYER,
			EtisSubmodules.REACTIVATE_TAX_FORM_TYPES, EtisSubmodules.SUSPEND_TAX_FORM_TYPES,
			EtisSubmodules.REREGISTER_TAX_FORM_TYPES, EtisSubmodules.DEREGISTER_TAXPAYER_TAX_FORM_TYPES),

	ATP("ATP", EtisSubmodules.ATP_CREATE, EtisSubmodules.ATP_UPDATE, EtisSubmodules.ATP_CANCEL),

	INQUIRY("INQUIRY"),

	RFP("RETURNS FILING AND PAYMENT", EtisSubmodules.RETURN_INQUIRY, EtisSubmodules.RETURN_REVERSAL,
			EtisSubmodules.RETURN_ADJUSTMENT, EtisSubmodules.RESOLVE_SUSPENDED_RETURNS, EtisSubmodules.REPORTS),

	CRR("COLLECTION, REMITTANCE AND RECONCILIATION", EtisSubmodules.RECEIVE_PAYMENT, EtisSubmodules.MANAGE_PAYMENT,
			EtisSubmodules.MANAGE_DISHONORED_CHECK, EtisSubmodules.MANAGE_CRDC, EtisSubmodules.MANAGE_BSP_LOR,
			EtisSubmodules.MANAGE_BTR, EtisSubmodules.MANAGE_DEPOSIT_SLIP, EtisSubmodules.MANAGE_IBCL_RATE,
			EtisSubmodules.COLLECTION_ACCOUNTABILITY, EtisSubmodules.MANAGE_RCO, EtisSubmodules.MANAGE_AAB),

	SYSTEM("SYSTEM"),

	TAS("TAXPAYER ACCOUNTING SYSTEM"),

	TCR("TAX CREDIT AND REFUND", EtisSubmodules.MANAGE_APPLICATION, EtisSubmodules.PROCESS_CLAIM,
			EtisSubmodules.MANAGE_CASH_REFUND, EtisSubmodules.MANAGE_TCC, EtisSubmodules.MANAGE_TDM),

	TCS("TAX COMPLIANCE SYSTEM", EtisSubmodules.ADD_PENALTY_CONFIGURATION, EtisSubmodules.UPDATE_PENALTY_CONFIGURATION,
			EtisSubmodules.VIEW_PENALTY_CONFIGURATION, EtisSubmodules.ADD_COMPLIANCE_CONFIGURATION,
			EtisSubmodules.UPDATE_COMPLIANCE_CONFIGURATION, EtisSubmodules.VIEW_COMPLIANCE_CONFIGURATION),

	CMS("CASE MANAGEMENT SYSTEM");

	private String description;
	private List<EtisSubmodules> etisSubmodules;

	private EtisModules(String description, EtisSubmodules... etisSubmodules) {
		this.description = description;
		this.etisSubmodules = Arrays.asList(etisSubmodules);
	}

	public String getDescription() {
		return description;
	}

	public List<EtisSubmodules> getEtisSubmodules() {
		return etisSubmodules;
	}

}
