/*
 * Last modified by: feliped
 * Last updated date: Sep 19, 2019 4:06:43 PM
 */
package com.caista.birapps.etis.domain.sysad.enums;

public enum ReferenceTypeEnum {

  GENDER("ReferenceGender"),
  OFFICE_TYPE("ReferenceOfficeType"),
  TAXPAYER_CLASSIFICATION("ReferenceTaxpayerClassification"),
  ADDRESS_TYPE("ReferenceAddressType"),
  BOOK_OF_ACCOUNT_TYPE("ReferenceBookOfAccountType"),
  INDUSTRY_GROUP("ReferenceIndustryGroup"),
  COVERAGE_TYPE("ReferenceCoverageType"),
  FACILITY_TYPE("ReferenceFacilityType"),
  MARITAL_STATUS("ReferenceMaritalStatus"),
  SUFFIX("ReferenceSuffix"),
  TAX_REGIME_TYPE("ReferenceTaxRegimeType"),
  REPORT_TYPE("ReferenceReportType"),
  TIN_STATUS("ReferenceTinStatus"),
  BUSINESS_STATUS("ReferenceBusinessStatus"),
  TAXPAYER_GROUP("ReferenceTaxpayerGroup"),
  MODULE("ReferenceModule"),
  REPORT_OUTPUT_TYPE("ReferenceReportOutputType"),
  CONTACT_TYPE("ReferenceContactType"),
  CONTACT_PURPOSE("ReferenceContactPurpose"),
  REASON_CATEGORY("ReferenceReasonCategory"),
  TITLE("ReferenceTitle"),
  PRINTER("ReferencePrinter"),
  RELATIONSHIP_CATEGORY("ReferenceRelationshipCategory"),
  SPOUSE_EMPLOYMENT_STATUS("ReferenceSpouseEmploymentStatus"),
  CORRESPONDENCE("ReferenceCorrespondence"),
  SITE_TYPE("ReferenceSiteType"),
  APPLICATION_INDICATOR("ReferenceApplicationIndicator"),
  AAB_TYPE("ReferenceAabType"),
  AAB_OFFICE("ReferenceAabOffice"),
  RECEIPT_INVOICE_KIND("ReferenceReceiptInvoiceKind"),
  ACCOUNT_YEAR_START_MM("ReferenceAccountYearStartMonth"),
  DLN_MODULE("ReferenceDlnModule"),
  CLAIM_ADDITIONAL_DEDUCTION("ReferenceClaimAdditionalDeduction"),
  PREGEN_TIN_SOURCE("ReferencePreGenTinSource"),
  ACCOUNTING_PERIOD("ReferenceAccountingPeriod"),
  ACCOUNTING_YEAR_START_MM("ReferenceAccountYearStartMonth"),
  PAYMENT_MODE("ReferencePaymentMode"),
  PAYMENT_MANNER("ReferencePaymentManner"),
  RETURN_PROCEDURAL_ERROR("ReferenceReturnProceduralError"),
  BCS_PROCEDURAL_ERROR("ReferenceBcsProceduralError"),
  INCIDENT_REPORT_VIOLATION("ReferenceIncidentReportViolation"),
  DOCUMENT_CATEGORY("ReferenceDocumentCategory"),
  LEGAL_BASIS("ReferenceLegalBasis"),
  SOURCE_AGENCY("ReferenceSourceAgency"),
  MANNER_OF_ISSUANCE("ReferenceMannerOfIssuance"),
  TCC_RESTRICTION("ReferenceTccRestriction"),
  PAYMENT_REASON("ReferencePaymentReason"),
  ADJUSTMENT_TYPE("ReferenceAdjustmentType"),
  OCCUPATION_CLASSIFICATION("ReferenceOccupationClassification"),
  CASE_CLASSIFICATION("ReferenceCaseClassification"),
  ATTACHMENT("ReferenceAttachment"),
  CASE_STATUS("ReferenceCaseStatus"),
  BASIS_TYPE_AUDIT("ReferenceBasisTypeAudit"),
  CASE_EVENT_STATUS("ReferenceCaseEventStatus"),
  DESIGNATION("ReferenceDesignation"),
  LA_STATUS("ReferenceLaStatus"),
  NOTICE_TYPE("ReferenceNoticeType"),
  AUDIT_STATUS("ReferenceAuditStatus"),
  PAYMENT_CHANNEL("ReferencePaymentChannel"),
  OFFICE_SECTION("ReferenceOfficeSection"),
  CASE_EVENT_GROUP("ReferenceCaseEventGroup"),
  ADJUSTMENT_CLASSIFICATION("ReferenceAdjustmentClassification"),

  FORM0500_SERIES("ReferenceForm0500Series"),

  VIOLATION_CODE("ReferenceViolationCode"), RETURN_TYPE("ReferenceReturnType"),

  TAS_ADJUSTMENT_TYPE("ReferenceTasAdjustmentType"), FREQUENCY("ReferenceFrequency"),

  INFORMATION_RETURN_REPORTORIAL_REQUIREMENT("ReferenceInformationReturnReportorialRequirement"),

  FORM_0500_AUDIT_CATEGORY("ReferenceForm0500AuditCategory"),

  FORM_0500_SERIES_AUDIT_BASIS("ReferenceForm0500SeriesAuditBasis"),

  CASE_TYPE_CATEGORY("ReferenceCaseTypeCategoryServiceImpl"), DUE_DATE_TYPE("ReferenceDueDateType"),

  OFFICE_GROUP("ReferenceOfficeGroup"), OFFICE_LTS_GROUP("ReferenceOfficeLtsGroup"),

  CASE_EVENT_ACTION("ReferenceCaseEventAction"), DUE_DATE_CONDITION("ReferenceDueDateCondition"),

  TABLE_OF_CONTENTS("ReferenceTableOfContents"), 
	
  FIL_SUB_COMPLIANCE_CASE_TYPE("ReferenceFilSubComplianceCaseType");

  private final String value;

  private ReferenceTypeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return "com.caista.birapps.etis.domain.sysad.entity.reference." + this.value;
  }
}
