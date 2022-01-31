/*
  * Modified by: bongalr
  * Last updated: Jan 30, 2020 7:19:44 PM
  */
package com.caista.birapps.etis.domain.ds;

// Use this here: DataSynchronizationRequest
public enum DataSynchronizationTypeEnum {

  // TRS
  REGISTER_INDIVIDUAL("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  REGISTER_NONINDIVIDUAL("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  REGISTER_BRANCH("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  UPDATE_INDIVIDUAL("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  UPDATE_NONINDIVIDUAL("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  UPDATE_BRANCH("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  TRANSFER_TAXPAYER("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  DEREGISTER_TAXPAYER_TAX_FORM("com.caista.birapps.etis.domain.trs.deregister.DeregisterTaxpayerDetails"),
  REREGISTER_TAX_FORM(
      "com.caista.birapps.etis.domain.trs.reregister.ReregisterTaxFormTypeParams"),
  MANAGE_ACCOUNTING_PERIOD("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  TAG_UNTAG_INACTIVE_BUSINESS_TAXPAYER("com.caista.birapps.etis.domain.trs.entity.TaxPayer"),
  BATCH_MAIN_ACCREDITED_PRINTER("String"),
  BATCH_ENLISTING_DELISTING("com.caista.birapps.etis.domain.ds.EnlistingDelistingDTO"),
  REACTIVATE_TAXPAYER("com.caista.birapps.etis.domain.trs.reactivate.ReactivateDeregisteredTPDetails"),

  // CRR
  ADD_AAB("com.caista.birapps.etis.domain.crr.entity.AuthorizedAgentBank"),
  UPDATE_AAB("com.caista.birapps.etis.domain.crr.entity.AuthorizedAgentBank"),
  UPDATE_BATCH_AAB("com.caista.birapps.etis.domain.crr.entity.AuthorizedAgentBank"),
  DEACTIVATE_AAB("com.caista.birapps.etis.domain.crr.entity.AuthorizedAgentBank"),
  REACTIVATE_AAB("com.caista.birapps.etis.domain.crr.entity.AuthorizedAgentBank"),

  ADD_RCO("com.caista.birapps.etis.domain.crr.entity.RevenueCollectionOfficer"),
  UPDATE_RCO("com.caista.birapps.etis.domain.crr.entity.RevenueCollectionOfficer"),
  UPDATE_BATCH_RCO("com.caista.birapps.etis.domain.crr.entity.RevenueCollectionOfficer"),
  DEACTIVATE_RCO("com.caista.birapps.etis.domain.crr.entity.RevenueCollectionOfficer"),
  REACTIVATE_RCO("com.caista.birapps.etis.domain.crr.entity.RevenueCollectionOfficer"),

  BCS_UPLOAD("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  RECEIVE_PAYMENT("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  RESOLVE_SUSPENDED_PAYMENT("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  CANCEL_BCS("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"), // Cancellation of Payment included.
  CANCELLATION_OF_SUSPENDED_PAYMENTS("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"), // Expired TDM. Merge in Cancel BCS.
  CANCEL_TDM("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  DCHECK_UPLOAD("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  TAGGING_OF_DISHONORED_CHECKS("com.caista.birapps.etis.domain.crr.entity.BatchControlSheet"),
  ;

  private final String value;

  private DataSynchronizationTypeEnum(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }

}
