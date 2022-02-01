/*
 * Modified by: fuentem
 * Last updated: Oct 16, 2018 10:21:15 AM
 */
package com.caista.birapps.etis.domain.trs.validation;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.entity.ContactInformation;
import com.caista.birapps.etis.domain.trs.entity.Identification;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;


public class RegisterIndividualValidationRequest extends ValidationRequest<TaxPayer> {

  @NotNull(message = "taxpayer.legalStatusType.required")
  private String taxpayerTypeId;

  @NotNull(message = "taxpayer.taxpayerGroup.required")
  private String taxpayerGroupId;

  @NotNull(message = "taxpayer.firstName.required")
  private String firstName;

  @NotNull(message = "taxpayer.lastName.required ")
  private String lastName;

  @NotNull(message = "taxpayer.placeOfBirth.required")
  private String placeOfBirth;

  @Past(message = "taxpayer.birthDate.past")
  private Date birthDate;

  @Past(message = "taxpayer.submissionDate.past")
  private Date submissionDate;

  @NotNull(message = "taxpayer.mothersMaidenName.required")
  private String mothersMaidenName;

  @NotNull(message = "taxpayer.fathersName.required")
  private String fathersName;

  @NotNull(message = "taxpayer.rdo.required")
  private Long officeId;

  @NotNull(message = "taxpayer.gender.required")
  private String genderId;

  @NotNull(message = "taxpayer.purposeOfTin.required")
  private String purposeOfTinId;

  @NotNull(message = "taxpayer.citizenship.required")
  private String citizenshipId;

  @Size(min = 1, message = "taxpayer.contactInformation.required")
  @NotNull(message = "taxpayer.contactInformation.required")
  private List<ContactInformation> contactInformation;

  @Size(min = 1, message = "taxpayer.identifications.required")
  @NotNull(message = "taxpayer.identifications.required")
  private List<Identification> identifications;

  @Size(min = 1, message = "taxpayer.addresses.required")
  @NotNull(message = "taxpayer.addresses.required")
  private List<Address> addresses;


  public RegisterIndividualValidationRequest(TaxPayer taxPayer) {

    this.taxpayerTypeId = taxPayer.getTaxpayerTypeId();
    this.taxpayerGroupId = taxPayer.getTaxpayerGroupId();
    this.firstName = taxPayer.getFirstName();
    this.lastName = taxPayer.getLastName();
    this.birthDate = taxPayer.getBirthDate();
    this.submissionDate = taxPayer.getSubmissionDate();
    this.mothersMaidenName = taxPayer.getMotherMaidenName();
    this.fathersName = taxPayer.getFatherName();
    this.officeId = taxPayer.getOfficeId();
    this.genderId = taxPayer.getGenderId();
    this.purposeOfTinId = taxPayer.getPurposeOfTinId();
    this.citizenshipId = taxPayer.getCitizenshipId();
    this.placeOfBirth = taxPayer.getPlaceOfBirth();
    this.contactInformation = taxPayer.getContactInformation();
    this.identifications = taxPayer.getIdentifications();
    this.addresses = taxPayer.getAddresses();
  }

}

