/*
  * Modified by: romerov
  * Last updated: 05 26, 20 1:28:39 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.taguntag.entity.TagUntagTaxPayerHistory;
import com.caista.birapps.etis.domain.trs.transfer.entity.TransferHistory;
import com.caista.birapps.etis.domain.trs.utils.enums.DataSourceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaxPayer.
 */
@Entity
@Table(name = "TAXPAYER",
    uniqueConstraints = @UniqueConstraint(columnNames = {"TIN", "BRANCH_CODE"},
        name = "UC_TAXPAYER_01"),
    indexes = {@Index(columnList = "TAXPAYER_ID", name = "IDX_TAXPAYER_01"),
        @Index(columnList = "TIN", name = "IDX_TAXPAYER_02"),
        @Index(columnList = "BRANCH_CODE", name = "IDX_TAXPAYER_03")})
@Check(
    constraints = "IS_VIP IN (1, 0) AND IS_TAMP IN (1, 0) AND IS_BIR_INITIATED IN (1, 0) AND IS_CBL IN (1, 0)"
        + " AND IS_EXEMPTED_FROM_REGISTRATION_FEE IN (1, 0)"
        + " AND TIN_SOURCE IN ('PREGEN', 'SYSGEN', 'MIGRATED')"
        + " AND TIN_STATUS_CODE IN ('ACTIVE', 'CANCELLED', 'PENDING')"
        + " AND TAXPAYER_REGISTRATION_TYPE IN ('NONINDIVIDUAL', 'INDIVIDUAL', 'BRANCH', 'FACILITY')")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayer implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TP_SequenceStyleGenerator")
  @GenericGenerator(name = "TP_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_ID", unique = true, nullable = false)
  private Long id;

  /** The tin. */
  @Column(name = "TIN", nullable = false, columnDefinition = "VARCHAR2(9 BYTE)")
  private String tin;

  /** The branch code. */
  @Column(name = "BRANCH_CODE", nullable = false, columnDefinition = "VARCHAR2(6 BYTE)")
  private String branchCode;

  @Column(name = "TAXPAYER_CLASSIFICATION_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxpayerClassificationId;

  @Column(name = "TAXPAYER_CLASSIFICATION_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String taxpayerClassificationDescription;

  @Column(name = "TAXPAYER_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxpayerTypeId;

  @Column(name = "TAXPAYER_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(120 BYTE)")
  private String taxpayerTypeDescription;

  @Column(name = "TAXPAYER_TYPE_IS_BUSINESS")
  private Boolean taxpayerTypeIsBusiness;

  @Column(name = "TAXPAYER_GROUP_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String taxpayerGroupId;

  @Column(name = "TAXPAYER_GROUP_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String taxpayerGroupDescription;

  /** The tin issuance date. */
  @Column(name = "TIN_ISSUANCE_DATE", columnDefinition = "DATE")
  private Date tinIssuanceDate;

  /** The is VIP. */
  @Column(name = "IS_VIP", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isVIP;

  /** The is tamp. */
  @Column(name = "IS_TAMP", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isTamp;

  /** The is CBL. */
  @Column(name = "IS_CBL", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isCBL;

  /** The is CBL source. */
  @Column(name = "CBL_SOURCE", columnDefinition = "VARCHAR2(50 BYTE)")
  private String cblSource;

  /** The is CBL period covered. */
  @Column(name = "CBL_PERIOD_COVERED", columnDefinition = "VARCHAR2(50 BYTE)")
  private String cblPeriodCovered;

  /** The is CBL published date. */
  @Column(name = "CBL_PUBLISHED_DATE", columnDefinition = "DATE")
  private Date cblPublishedDate;

  /** The submission date. */
  @Column(name = "SUBMISSION_DATE", columnDefinition = "DATE")
  private Date submissionDate;

  @Column(name = "OFFICE_ID")
  private Long officeId;

  @Column(name = "OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String officeDescription;

  @Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
  private String officeCode;

  @Column(name = "OFFICE_LARGE_TAXPAYER_OFFICE", nullable = false,
      columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean officeLargeTaxpayerOfficeFlag = false;

  @Column(name = "REGISTERING_OFFICE_ID", columnDefinition = "VARCHAR2(20 BYTE)")
  private String registeringOfficeId;

  @Column(name = "REGISTERING_OFFICE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String registeringOfficeDescription;

  /** The TIN status. */
  @Column(name = "TIN_STATUS_CODE", columnDefinition = "VARCHAR2(10 BYTE)", nullable = false)
  private String tinStatus;

  @Column(name = "OFFICE_COVERAGE_ID", nullable = false)
  private Long officeCoverageId;

  @Column(name = "OFFICE_COVERAGE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String officeCoverageDescription;

  @Column(name = "FACILITY_TYPE_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String facilityTypeId;

  @Column(name = "FACILITY_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String facilityTypeDescription;

  @Column(name = "ACCOUNTING_PERIOD_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String accountingPeriodId;

  @Column(name = "ACCOUNTING_PERIOD_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String accountingPeriodDescription;

  @Column(name = "ACCOUNTING_YEAR_START_MONTH_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String accountingYearStartMonthId;

  @Column(name = "ACCOUNTING_YEAR_START_MONTH_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String accountingYearStartMonthDescription;

  /** The accounting effective date. */
  @Column(name = "ACCOUNTING_EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date accountingEffectiveDate;

  /** The cancellation date. */
  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

  /** ** FOR NONINDIVIDUAL ****. */

  /** The registered name. */
  @Column(name = "REGISTERED_NAME", columnDefinition = "VARCHAR2(150 BYTE)")
  private String registeredName;


  /** The incorporation date. */
  @Column(name = "INCORPORATION_DATE", columnDefinition = "DATE")
  private Date incorporationDate;

  @Column(name = "REGULATORY_BODY_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String regulatoryBodyId;

  @Column(name = "REGULATORY_BODY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String regulatoryBodyDescription;

  /** The registration number. */
  @Column(name = "REGISTRATION_NUMBER", columnDefinition = "VARCHAR2(50 BYTE)")
  private String registrationNumber;

  /** The is BIR initiated registration. */
  @Column(name = "IS_BIR_INITIATED", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isBIRInitiated;

  /** The contact persons. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_CONTACT_PERSON__TAXPAYER_01"))
  private List<ContactPerson> contactPersons;

  /** The incorporator. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_INCORPORATOR__TAXPAYER_01"))
  private List<IncorporatorInformation> incorporator;

  /** ** END - FOR NONINDIVIDUAL ****. */

  /**** FOR INDIVIDUAL *****/

  @Column(name = "TITLE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String titleId;

  @Column(name = "TITLE_DESCRIPTION", columnDefinition = "VARCHAR2(50 BYTE)")
  private String titleDescription;

  /** The last name. */
  @Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String lastName;

  /** The first name. */
  @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String firstName;

  /** The middle name. */
  @Column(name = "MIDDLE_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String middleName;

  /** The trust name. */
  @Column(name = "TRUST_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String trustName;

  /** The estate name. */
  @Column(name = "ESTATE_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String estateName;

  @Column(name = "OTHER_CITIZENSHIP_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String otherCitizenshipId;

  @Column(name = "OTHER_CITIZENSHIP_DESCRIPTION", columnDefinition = "VARCHAR2(120 BYTE)")
  private String otherCitizenshipDescription;

  @Column(name = "CITIZENSHIP_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String citizenshipId;

  @Column(name = "CITIZENSHIP_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String citizenshipDescription;

  @Column(name = "SUFFIX_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String suffixId;

  @Column(name = "SUFFIX_DESCRIPTION", columnDefinition = "VARCHAR2(50 BYTE)")
  private String suffixDescription;

  @Column(name = "GENDER_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String genderId;

  @Column(name = "GENDER_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String genderDescription;

  @Column(name = "MARITAL_STATUS_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String maritalStatusId;

  @Column(name = "MARITAL_STATUS_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String maritalStatusDescription;

  /** The birth date. */
  @Column(name = "BIRTH_DATE", columnDefinition = "DATE")
  private Date birthDate;

  /** The organization date. */
  @Column(name = "ORGANIZATION_DATE", columnDefinition = "DATE")
  private Date organizationDate;

  /** The place of birth. */
  @Column(name = "PLACE_OF_BIRTH", columnDefinition = "VARCHAR2(150 BYTE)")
  private String placeOfBirth;

  /** The mothers maiden name. */
  @Column(name = "MOTHER_MAIDEN_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String motherMaidenName;

  /** The fathers name. */
  @Column(name = "FATHER_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String fatherName;

  @Column(name = "PURPOSE_OF_TIN_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String purposeOfTinId;

  @Column(name = "PURPOSE_OF_TIN_DESCRIPTION", columnDefinition = "VARCHAR2(140 BYTE)")
  private String purposeOfTinDescription;

  /** The pcrn. */
  @Column(name = "PHILSYS_NUMBER", columnDefinition = "VARCHAR2(50 BYTE)")
  private String philSysNumber;

  /** ** END - FOR INDIVIDUAL ****. */

  /**** SPOUSE INFORMATION *****/

  @Column(name = "SPOUSE_EMPLOYEMENT_STATUS_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String spouseEmploymentStatusId;

  @Column(name = "SPOUSE_EMPLOYEMENT_STATUS_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String spouseEmploymentStatusDescription;

  /** The spouse TIN. */
  @Column(name = "SPOUSE_TIN", columnDefinition = "VARCHAR2(9 BYTE)")
  private String spouseTIN;

  /** The spouse TIN branch code. */
  @Column(name = "SPOUSE_TIN_BRANCH_CODE", columnDefinition = "VARCHAR2(5 BYTE)")
  private String spouseTINBranchCode;

  /** The spouse last name. */
  @Column(name = "SPOUSE_LAST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String spouseLastName;

  /** The spouse first name. */
  @Column(name = "SPOUSE_FIRST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String spouseFirstName;

  /** The spouse middle name. */
  @Column(name = "SPOUSE_MIDDLE_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String spouseMiddleName;

  @Column(name = "SPOUSE_SUFFIX_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String spouseSuffixId;

  @Column(name = "SPOUSE_SUFFIX_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String spouseSuffixDescription;

  /** The spouse employers TIN. */
  @Column(name = "SPOUSE_EMPLOYER_TIN", columnDefinition = "VARCHAR2(9 BYTE)")
  private String spouseEmployerTIN;

  /** The spouse employer TIN branch code. */
  @Column(name = "SPOUSE_EMPLOYER_BRANCH_CODE", columnDefinition = "VARCHAR2(5 BYTE)")
  private String spouseEmployerBranchCode;

  /** The spouse employers name. */
  @Column(name = "SPOUSE_EMPLOYER_NAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String spouseEmployerName;

  /** ** END - SPOUSE INFORMATION ****. */

  /**** TRUSTEE DETAILS *****/

  /** The trustee full name. */
  @Column(name = "TRUSTEE_FULLNAME", columnDefinition = "VARCHAR2(100 BYTE)")
  private String trusteeFullName;

  @Column(name = "TRUSTEE_ADDRESS_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeAddressTypeId;

  @Column(name = "TRUSTEE_ADDRESS_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String trusteeAddressTypeDescription;

  /** The trustee town/district. */
  @Column(name = "TRUSTEE_TOWN_DISTRICT", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeTownDistrict;

  /** The trustee subdivision/village. */
  @Column(name = "TRUSTEE_SUBDIVISION_VILLAGE", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeSubdivisionVillage;

  /** The trustee street. */
  @Column(name = "TRUSTEE_STREET", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeStreet;

  /** The trustee lot/block/phase no. */
  @Column(name = "TRUSTEE_LOT_BLOCK_PHASE_NO", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeLotBlockPhaseNo;

  /** The trustee building/tower. */
  @Column(name = "TRUSTEE_BUILDING_TOWER", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeBuildingTower;

  /** The trustee unit room/floor no. */
  @Column(name = "TRUSTEE_UNIT_ROOM_FLOOR_NO", columnDefinition = "VARCHAR2(150 BYTE)")
  private String trusteeUnitRoomFloorNo;

  @Column(name = "TRUSTEE_PROVINCE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeProvinceId;

  @Column(name = "TRUSTEE_PROVINCE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String trusteeProvinceDescription;

  @Column(name = "TRUSTEE_BARANGAY_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeBarangayId;

  @Column(name = "TRUSTEE_BARANGAY_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String trusteeBarangayDescription;

  @Column(name = "TRUSTEE_ZIP_CODE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeZipCodeId;

  @Column(name = "TRUSTEE_ZIP_CODE_DESCRIPTION", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeZipCodeDescription;

  @Column(name = "TRUSTEE_MUNICIPALITY_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeMunicipalityId;

  @Column(name = "TRUSTEE_MUNICIPALITY_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String trusteeMunicipalityDescription;

  @Column(name = "TRUSTEE_COUNTRY_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String trusteeCountryId;

  @Column(name = "TRUSTEE_COUNTRY_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String trusteeCountryDescription;

  /** ** END - TRUSTEE DETAILS ****. */

  /** The children. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_CHILDREN__TAXPAYER_01"))
  private List<Children> children;

  /** The tp tax types. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TAX_TYPE__TAXPAYER_01"))
  private List<TaxPayerTaxType> tpTaxTypes;

  /** The professional informations. */
  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_PSOC__TAXPAYER_01"))
  private List<ProfessionalInformation> professionalInformations;

  /** The business summary details. */
  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_BUSINESS_SUMMARY_DETAIL__TAXPAYER_01"))
  private List<BusinessSummaryDetail> businessSummaryDetails;

  /** The incentive details. */
  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_INCENTIVE_DETAIL__TAXPAYER_01"))
  private List<IncentiveDetail> incentiveDetails;

  /** The documentary requirements. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
  foreignKey = @ForeignKey(name = "FK_TAXPAYER_ATTACHMENT__TAXPAYER_01"))
  private List<TaxpayerAttachment> documentaryRequirements;

  /** The tp special codes. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_SPECIAL_CODE__TAXPAYER_01"))
  private List<TaxPayerSpecialCode> tpSpecialCodes;

  /** The relationship details. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_RELATIONSHIP_DETAIL__TAXPAYER_01"))
  private List<RelationshipDetail> relationshipDetails;

  /** The addresses. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_ADDRESS__TAXPAYER_01"))
  private List<Address> addresses;

  /** The contact information. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_CONTACT_INFORMATION__TAXPAYER_01"))
  private List<ContactInformation> contactInformation;

  /** The identifications. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_IDENTIFICATION__TAXPYER_01"))
  private List<Identification> identifications;

  /** The book of accounts. */
  @OneToMany(cascade = {CascadeType.MERGE, CascadeType.ALL}, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_BOOK_OF_ACCOUNT__TAXPAYER_01"))
  private List<BookOfAccount> bookOfAccounts;

  /** The accounting period histories. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_ACCOUNTING_PERIOD__TAXPAYER_01"))
  private List<AccountingPeriodHistory> accountingPeriodHistories;

  /** The transfer histories. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TRANSFER_HISTORY__TAXPAYER_01"))
  private List<TransferHistory> transferHistories;

  /** The tag untag tax payer histories. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TAG_UNTAG_HISTORY__TAXPAYER_01"))
  private List<TagUntagTaxPayerHistory> tagUntagTaxPayerHistories;

  /** The taxpayer histories. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_STATUS_HISTORY__TAXPAYER_01"), insertable = true,
      updatable = false)
  private List<TaxPayerStatusHistory> taxPayerHistories;

  /** The taxpayer histories. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_ID", nullable = true,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TYPE_HISTORY__TAXPAYER_01"), insertable = true,
      updatable = false)
  private List<TaxpayerTypeHistory> taxPayerTypeHistories;

  @Column(name = "PRE_GEN_TIN_SOURCE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String preGenTinSourceId;

  @Column(name = "PRE_GEN_TIN_SOURCE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String preGenTinSourceDescription;

  /** The data source created. */
  @Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceCreated;

  /** The data source updated. */
  @Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceUpdated;

  /** The created by. */
  @Column(name = "CREATED_BY", columnDefinition = "VARCHAR2(50 BYTE)", nullable = false)
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  /** The document locator number. */
  @Transient
  private String documentLocatorNumber;

  /** The reason id. */
  @Transient
  private String reasonId;

  /** The reason description. */
  @Transient
  private String reasonDescription;

  /** The nickname. */
  @Column(name = "NICKNAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String nickName;

  /** The is tamp. */
  @Column(name = "IS_EXEMPTED_FROM_REGISTRATION_FEE", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isExemptedFromRegistrationFee;

  /** The registration type. */
  @Column(name = "TAXPAYER_REGISTRATION_TYPE", columnDefinition = "VARCHAR2(14 BYTE)",
      nullable = false)
  private String registrationType;

  /** The tin source. */
  @Column(name = "TIN_SOURCE", columnDefinition = "VARCHAR2(50 BYTE)")
  private String tinSource;

  /** The tin source. */
  /* @Column(name = "REMARKS") */
  @Transient
  private String remarks;

  @Column(name = "IS_VAT_EXCEEDING", nullable = false, columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isVatExceeding = false;

  @Column(name = "INDUSTRY_GROUP_ID", nullable = true, columnDefinition = "VARCHAR2(30 BYTE)")
  private String industryGroupId;

  @Column(name = "INDUSTRY_GROUP_DESCRIPTION", nullable = true,
      columnDefinition = "VARCHAR2(115 BYTE)")
  private String industryGroupDescription;

  @Column(name = "PSIC_ID", nullable = true, columnDefinition = "VARCHAR2(30 BYTE)")
  private String psicId;

  @Column(name = "PSIC_DESCRIPTION", nullable = true, columnDefinition = "VARCHAR2(200 BYTE)")
  private String psicDescription;

  /** The transaction number. */
  @Transient
  private String transactionNumber;

  /** The correspondence signatory. */
  @Transient
  private String correspondenceSignatory;

  /** The correspondence position. */
  @Transient
  private String correspondencePosition;

  /** The Office region. */
  @Transient
  private String officeRegionDescription;

  @Transient
  private DocumentLocatorNumberHistory documentLocatorNumberHistory;

  @Transient
  private Boolean isFromPreGenTIN;

  @Transient
  private String businessStatus;

  @Transient
  private String transactionType;

  @Transient
  private List<ITSRegAddress> regAddresses;

  public TaxPayer() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public TaxPayer(Long id, String tin, String branchCode, Long officeId, String officeDescription,
      String officeCode, Boolean officeLargeTaxpayerOfficeFlag) {
    super();
    this.id = id;
    this.tin = tin;
    this.branchCode = branchCode;
    this.officeId = officeId;
    this.officeDescription = officeDescription;
    this.officeCode = officeCode;
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
  }

  public TaxPayer(TaxPayer tp) {
    super();
    this.id = tp.getId();
    this.tin = tp.getTin();
    this.branchCode = tp.getBranchCode();
    this.taxpayerClassificationId = tp.getTaxpayerClassificationId();
    this.taxpayerClassificationDescription = tp.getTaxpayerClassificationDescription();
    this.taxpayerTypeId = tp.getTaxpayerTypeId();
    this.taxpayerTypeDescription = tp.getTaxpayerTypeDescription();
    this.taxpayerTypeIsBusiness = tp.getTaxpayerTypeIsBusiness();
    this.taxpayerGroupId = tp.getTaxpayerGroupId();
    this.taxpayerGroupDescription = tp.getTaxpayerGroupDescription();
    this.tinIssuanceDate = tp.getTinIssuanceDate();
    this.isVIP = tp.getIsVIP();
    this.isTamp = tp.getIsTamp();
    this.isCBL = tp.getIsCBL();
    this.cblSource = tp.getCblSource();
    this.cblPeriodCovered = tp.getCblPeriodCovered();
    this.cblPublishedDate = tp.getCblPublishedDate();
    this.submissionDate = tp.getSubmissionDate();
    this.officeId = tp.getOfficeId();
    this.officeDescription = tp.getOfficeDescription();
    this.officeCode = tp.getOfficeCode();
    this.officeLargeTaxpayerOfficeFlag = tp.getOfficeLargeTaxpayerOfficeFlag();
    this.registeringOfficeId = tp.getRegisteringOfficeId();
    this.registeringOfficeDescription = tp.getRegisteringOfficeDescription();
    this.tinStatus = tp.getTinStatus();
    this.officeCoverageId = tp.getOfficeCoverageId();
    this.officeCoverageDescription = tp.getOfficeCoverageDescription();
    this.facilityTypeId = tp.getFacilityTypeId();
    this.facilityTypeDescription = tp.getFacilityTypeDescription();
    this.accountingPeriodId = tp.getAccountingPeriodId();
    this.accountingPeriodDescription = tp.getAccountingPeriodDescription();
    this.accountingYearStartMonthId = tp.getAccountingYearStartMonthId();
    this.accountingYearStartMonthDescription = tp.getAccountingYearStartMonthDescription();
    this.accountingEffectiveDate = tp.getAccountingEffectiveDate();
    this.cancellationDate = tp.getCancellationDate();
    this.registeredName = tp.getRegisteredName();
    this.incorporationDate = tp.getIncorporationDate();
    this.regulatoryBodyId = tp.getRegulatoryBodyId();
    this.regulatoryBodyDescription = tp.getRegulatoryBodyDescription();
    this.registrationNumber = tp.getRegistrationNumber();
    this.isBIRInitiated = tp.getIsBIRInitiated();
    this.contactPersons = tp.getContactPersons();
    this.incorporator = tp.getIncorporator();
    this.titleId = tp.getTitleId();
    this.titleDescription = tp.getTitleDescription();
    this.lastName = tp.getLastName();
    this.firstName = tp.getFirstName();
    this.middleName = tp.getMiddleName();
    this.trustName = tp.getTrustName();
    this.estateName = tp.getEstateName();
    this.otherCitizenshipId = tp.getOtherCitizenshipId();
    this.otherCitizenshipDescription = tp.getOtherCitizenshipDescription();
    this.citizenshipId = tp.getCitizenshipId();
    this.citizenshipDescription = tp.getCitizenshipDescription();
    this.suffixId = tp.getSuffixId();
    this.suffixDescription = tp.getSuffixDescription();
    this.genderId = tp.getGenderId();
    this.genderDescription = tp.getGenderDescription();
    this.maritalStatusId = tp.getMaritalStatusId();
    this.maritalStatusDescription = tp.getMaritalStatusDescription();
    this.birthDate = tp.getBirthDate();
    this.organizationDate = tp.getOrganizationDate();
    this.placeOfBirth = tp.getPlaceOfBirth();
    this.motherMaidenName = tp.getMotherMaidenName();
    this.fatherName = tp.getFatherName();
    this.purposeOfTinId = tp.getPurposeOfTinId();
    this.purposeOfTinDescription = tp.getPurposeOfTinDescription();
    this.philSysNumber = tp.getPhilSysNumber();
    this.spouseEmploymentStatusId = tp.getSpouseEmploymentStatusId();
    this.spouseEmploymentStatusDescription = tp.getSpouseEmploymentStatusDescription();
    this.spouseTIN = tp.getSpouseTIN();
    this.spouseTINBranchCode = tp.getSpouseTINBranchCode();
    this.spouseLastName = tp.getSpouseLastName();
    this.spouseFirstName = tp.getSpouseFirstName();
    this.spouseMiddleName = tp.getSpouseMiddleName();
    this.spouseSuffixId = tp.getSpouseSuffixId();
    this.spouseSuffixDescription = tp.getSpouseSuffixDescription();
    this.spouseEmployerTIN = tp.getSpouseEmployerTIN();
    this.spouseEmployerBranchCode = tp.getSpouseEmployerBranchCode();
    this.spouseEmployerName = tp.getSpouseEmployerName();
    this.trusteeFullName = tp.getTrusteeFullName();
    this.trusteeAddressTypeId = tp.getTrusteeAddressTypeId();
    this.trusteeAddressTypeDescription = tp.getTrusteeAddressTypeDescription();
    this.trusteeTownDistrict = tp.getTrusteeTownDistrict();
    this.trusteeSubdivisionVillage = tp.getTrusteeSubdivisionVillage();
    this.trusteeStreet = tp.getTrusteeStreet();
    this.trusteeLotBlockPhaseNo = tp.getTrusteeLotBlockPhaseNo();
    this.trusteeBuildingTower = tp.getTrusteeBuildingTower();
    this.trusteeUnitRoomFloorNo = tp.getTrusteeUnitRoomFloorNo();
    this.trusteeProvinceId = tp.getTrusteeProvinceId();
    this.trusteeProvinceDescription = tp.getTrusteeProvinceDescription();
    this.trusteeBarangayId = tp.getTrusteeBarangayId();
    this.trusteeBarangayDescription = tp.getTrusteeBarangayDescription();
    this.trusteeZipCodeId = tp.getTrusteeZipCodeId();
    this.trusteeZipCodeDescription = tp.getTrusteeZipCodeDescription();
    this.trusteeMunicipalityId = tp.getTrusteeMunicipalityId();
    this.trusteeMunicipalityDescription = tp.getTrusteeMunicipalityDescription();
    this.trusteeCountryId = tp.getTrusteeCountryId();
    this.trusteeCountryDescription = tp.getTrusteeCountryDescription();
    this.children = tp.getChildren();
    this.tpTaxTypes = tp.getTpTaxTypes();
    this.professionalInformations = tp.getProfessionalInformations();
    this.businessSummaryDetails = tp.getBusinessSummaryDetails();
    this.incentiveDetails = tp.getIncentiveDetails();
    this.documentaryRequirements = tp.getDocumentaryRequirements();
    this.tpSpecialCodes = tp.getTpSpecialCodes();
    this.relationshipDetails = tp.getRelationshipDetails();
    this.addresses = tp.getAddresses();
    this.contactInformation = tp.getContactInformation();
    this.identifications = tp.getIdentifications();
    this.bookOfAccounts = tp.getBookOfAccounts();
    this.accountingPeriodHistories = tp.getAccountingPeriodHistories();
    this.transferHistories = tp.getTransferHistories();
    this.tagUntagTaxPayerHistories = tp.getTagUntagTaxPayerHistories();
    this.taxPayerHistories = tp.getTaxPayerHistories();
    this.preGenTinSourceId = tp.getPreGenTinSourceId();
    this.preGenTinSourceDescription = tp.getPreGenTinSourceDescription();
    this.dataSourceCreated = tp.getDataSourceCreated();
    this.dataSourceUpdated = tp.getDataSourceUpdated();
    this.createdBy = tp.getCreatedBy();
    this.createdDate = tp.getCreatedDate();
    this.updatedBy = tp.getUpdatedBy();
    this.updatedDate = tp.getUpdatedDate();
    this.documentLocatorNumber = tp.getDocumentLocatorNumber();
    this.reasonId = tp.getReasonId();
    this.reasonDescription = tp.getReasonDescription();
    this.nickName = tp.getNickName();
    this.isExemptedFromRegistrationFee = tp.getIsExemptedFromRegistrationFee();
    this.registrationType = tp.getRegistrationType();
    this.tinSource = tp.getTinSource();
    this.remarks = tp.getRemarks();
    this.isVatExceeding = tp.getIsVatExceeding();
    this.industryGroupId = tp.getIndustryGroupId();
    this.industryGroupDescription = tp.getIndustryGroupDescription();
    this.psicId = tp.getPsicId();
    this.psicDescription = tp.getPsicDescription();
    this.transactionNumber = tp.getTransactionNumber();
    this.correspondenceSignatory = tp.getCorrespondenceSignatory();
    this.correspondencePosition = tp.getCorrespondencePosition();
    this.officeRegionDescription = tp.getOfficeRegionDescription();
    this.documentLocatorNumberHistory = tp.getDocumentLocatorNumberHistory();
    this.isFromPreGenTIN = tp.getIsFromPreGenTIN();
  }


  public TaxPayer(TaxPayer tp, Long id) {
    super();
    this.id = id;
    this.tin = tp.getTin();
    this.branchCode = tp.getBranchCode();
    this.taxpayerClassificationId = tp.getTaxpayerClassificationId();
    this.taxpayerClassificationDescription = tp.getTaxpayerClassificationDescription();
    this.taxpayerTypeId = tp.getTaxpayerTypeId();
    this.taxpayerTypeDescription = tp.getTaxpayerTypeDescription();
    this.taxpayerTypeIsBusiness = tp.getTaxpayerTypeIsBusiness();
    this.taxpayerGroupId = tp.getTaxpayerGroupId();
    this.taxpayerGroupDescription = tp.getTaxpayerGroupDescription();
    this.tinIssuanceDate = tp.getTinIssuanceDate();
    this.isVIP = tp.getIsVIP();
    this.isTamp = tp.getIsTamp();
    this.isCBL = tp.getIsCBL();
    this.cblSource = tp.getCblSource();
    this.cblPeriodCovered = tp.getCblPeriodCovered();
    this.cblPublishedDate = tp.getCblPublishedDate();
    this.submissionDate = tp.getSubmissionDate();
    this.officeId = tp.getOfficeId();
    this.officeDescription = tp.getOfficeDescription();
    this.officeCode = tp.getOfficeCode();
    this.officeLargeTaxpayerOfficeFlag = tp.getOfficeLargeTaxpayerOfficeFlag();
    this.registeringOfficeId = tp.getRegisteringOfficeId();
    this.registeringOfficeDescription = tp.getRegisteringOfficeDescription();
    this.tinStatus = tp.getTinStatus();
    this.officeCoverageId = tp.getOfficeCoverageId();
    this.officeCoverageDescription = tp.getOfficeCoverageDescription();
    this.facilityTypeId = tp.getFacilityTypeId();
    this.facilityTypeDescription = tp.getFacilityTypeDescription();
    this.accountingPeriodId = tp.getAccountingPeriodId();
    this.accountingPeriodDescription = tp.getAccountingPeriodDescription();
    this.accountingYearStartMonthId = tp.getAccountingYearStartMonthId();
    this.accountingYearStartMonthDescription = tp.getAccountingYearStartMonthDescription();
    this.accountingEffectiveDate = tp.getAccountingEffectiveDate();
    this.cancellationDate = tp.getCancellationDate();
    this.registeredName = tp.getRegisteredName();
    this.incorporationDate = tp.getIncorporationDate();
    this.regulatoryBodyId = tp.getRegulatoryBodyId();
    this.regulatoryBodyDescription = tp.getRegulatoryBodyDescription();
    this.registrationNumber = tp.getRegistrationNumber();
    this.isBIRInitiated = tp.getIsBIRInitiated();
    this.titleId = tp.getTitleId();
    this.titleDescription = tp.getTitleDescription();
    this.lastName = tp.getLastName();
    this.firstName = tp.getFirstName();
    this.middleName = tp.getMiddleName();
    this.trustName = tp.getTrustName();
    this.estateName = tp.getEstateName();
    this.otherCitizenshipId = tp.getOtherCitizenshipId();
    this.otherCitizenshipDescription = tp.getOtherCitizenshipDescription();
    this.citizenshipId = tp.getCitizenshipId();
    this.citizenshipDescription = tp.getCitizenshipDescription();
    this.suffixId = tp.getSuffixId();
    this.suffixDescription = tp.getSuffixDescription();
    this.genderId = tp.getGenderId();
    this.genderDescription = tp.getGenderDescription();
    this.maritalStatusId = tp.getMaritalStatusId();
    this.maritalStatusDescription = tp.getMaritalStatusDescription();
    this.birthDate = tp.getBirthDate();
    this.organizationDate = tp.getOrganizationDate();
    this.placeOfBirth = tp.getPlaceOfBirth();
    this.motherMaidenName = tp.getMotherMaidenName();
    this.fatherName = tp.getFatherName();
    this.purposeOfTinId = tp.getPurposeOfTinId();
    this.purposeOfTinDescription = tp.getPurposeOfTinDescription();
    this.philSysNumber = tp.getPhilSysNumber();
    this.spouseEmploymentStatusId = tp.getSpouseEmploymentStatusId();
    this.spouseEmploymentStatusDescription = tp.getSpouseEmploymentStatusDescription();
    this.spouseTIN = tp.getSpouseTIN();
    this.spouseTINBranchCode = tp.getSpouseTINBranchCode();
    this.spouseLastName = tp.getSpouseLastName();
    this.spouseFirstName = tp.getSpouseFirstName();
    this.spouseMiddleName = tp.getSpouseMiddleName();
    this.spouseSuffixId = tp.getSpouseSuffixId();
    this.spouseSuffixDescription = tp.getSpouseSuffixDescription();
    this.spouseEmployerTIN = tp.getSpouseEmployerTIN();
    this.spouseEmployerBranchCode = tp.getSpouseEmployerBranchCode();
    this.spouseEmployerName = tp.getSpouseEmployerName();
    this.trusteeFullName = tp.getTrusteeFullName();
    this.trusteeAddressTypeId = tp.getTrusteeAddressTypeId();
    this.trusteeAddressTypeDescription = tp.getTrusteeAddressTypeDescription();
    this.trusteeTownDistrict = tp.getTrusteeTownDistrict();
    this.trusteeSubdivisionVillage = tp.getTrusteeSubdivisionVillage();
    this.trusteeStreet = tp.getTrusteeStreet();
    this.trusteeLotBlockPhaseNo = tp.getTrusteeLotBlockPhaseNo();
    this.trusteeBuildingTower = tp.getTrusteeBuildingTower();
    this.trusteeUnitRoomFloorNo = tp.getTrusteeUnitRoomFloorNo();
    this.trusteeProvinceId = tp.getTrusteeProvinceId();
    this.trusteeProvinceDescription = tp.getTrusteeProvinceDescription();
    this.trusteeBarangayId = tp.getTrusteeBarangayId();
    this.trusteeBarangayDescription = tp.getTrusteeBarangayDescription();
    this.trusteeZipCodeId = tp.getTrusteeZipCodeId();
    this.trusteeZipCodeDescription = tp.getTrusteeZipCodeDescription();
    this.trusteeMunicipalityId = tp.getTrusteeMunicipalityId();
    this.trusteeMunicipalityDescription = tp.getTrusteeMunicipalityDescription();
    this.trusteeCountryId = tp.getTrusteeCountryId();
    this.trusteeCountryDescription = tp.getTrusteeCountryDescription();
    this.preGenTinSourceId = tp.getPreGenTinSourceId();
    this.preGenTinSourceDescription = tp.getPreGenTinSourceDescription();
    this.dataSourceCreated = tp.getDataSourceCreated();
    this.dataSourceUpdated = tp.getDataSourceUpdated();
    this.createdBy = tp.getCreatedBy();
    this.createdDate = tp.getCreatedDate();
    this.updatedBy = tp.getUpdatedBy();
    this.updatedDate = tp.getUpdatedDate();
    this.nickName = tp.getNickName();
    this.isExemptedFromRegistrationFee = tp.getIsExemptedFromRegistrationFee();
    this.registrationType = tp.getRegistrationType();
    this.tinSource = tp.getTinSource();
    this.isVatExceeding = tp.getIsVatExceeding();
    this.industryGroupId = tp.getIndustryGroupId();
    this.industryGroupDescription = tp.getIndustryGroupDescription();
    this.psicId = tp.getPsicId();
    this.psicDescription = tp.getPsicDescription();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public Boolean getIsVIP() {
    if (null == this.isVIP) {
      isVIP = false;
    }
    return isVIP;
  }

  public void setIsVIP(Boolean isVIP) {
    this.isVIP = isVIP;
    if (null == this.isVIP) {
      this.isVIP = false;
    }
  }

  public Boolean getIsTamp() {
    if (null == this.isTamp) {
      isTamp = false;
    }
    return isTamp;
  }

  public void setIsTamp(Boolean isTamp) {
    this.isTamp = isTamp;
    if (null == this.isTamp) {
      this.isTamp = false;
    }
  }

  public Boolean getIsCBL() {
    if (null == this.isCBL) {
      isCBL = false;
    }
    return isCBL;
  }

  public void setIsCBL(Boolean isCBL) {
    this.isCBL = isCBL;
    if (null == this.isCBL) {
      this.isCBL = false;
    }
  }

  public String getCblSource() {
    return cblSource;
  }

  public void setCblSource(String cblSource) {
    this.cblSource = cblSource;
  }

  public String getCblPeriodCovered() {
    return cblPeriodCovered;
  }

  public void setCblPeriodCovered(String cblPeriodCovered) {
    this.cblPeriodCovered = cblPeriodCovered;
  }

  public Date getCblPublishedDate() {
    return cblPublishedDate;
  }

  public void setCblPublishedDate(Date cblPublishedDate) {
    this.cblPublishedDate = cblPublishedDate;
  }

  public Date getSubmissionDate() {
    return submissionDate;
  }

  public void setSubmissionDate(Date submissionDate) {
    this.submissionDate = submissionDate;
  }

  public String getTinStatus() {
    return tinStatus;
  }

  public void setTinStatus(String tinStatus) {
    this.tinStatus = tinStatus;
  }

  public Date getAccountingEffectiveDate() {
    return accountingEffectiveDate;
  }

  public void setAccountingEffectiveDate(Date accountingEffectiveDate) {
    this.accountingEffectiveDate = accountingEffectiveDate;
  }

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public String getRegisteredName() {
    return registeredName;
  }

  public void setRegisteredName(String registeredName) {
    this.registeredName = registeredName;
  }

  public Date getIncorporationDate() {
    return incorporationDate;
  }

  public void setIncorporationDate(Date incorporationDate) {
    this.incorporationDate = incorporationDate;
  }

  public String getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(String registrationNumber) {
    this.registrationNumber = registrationNumber;
  }

  public Boolean getIsBIRInitiated() {
    if (null == this.isBIRInitiated) {
      isBIRInitiated = false;
    }
    return isBIRInitiated;
  }

  public void setIsBIRInitiated(Boolean isBIRInitiated) {
    this.isBIRInitiated = isBIRInitiated;
    if (null == this.isBIRInitiated) {
      this.isBIRInitiated = false;
    }
  }

  public List<ContactPerson> getContactPersons() {
    return contactPersons;
  }

  public void setContactPersons(List<ContactPerson> contactPersons) {
    this.contactPersons = contactPersons;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String placeOfBirth) {
    this.placeOfBirth = placeOfBirth;
  }

  public String getMotherMaidenName() {
    return motherMaidenName;
  }

  public void setMotherMaidenName(String motherMaidenName) {
    this.motherMaidenName = motherMaidenName;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String fatherName) {
    this.fatherName = fatherName;
  }

  public String getPhilSysNumber() {
    return philSysNumber;
  }

  public void setPhilSysNumber(String philSysNumber) {
    this.philSysNumber = philSysNumber;
  }

  public String getSpouseTIN() {
    return spouseTIN;
  }

  public void setSpouseTIN(String spouseTIN) {
    this.spouseTIN = spouseTIN;
  }

  public String getSpouseTINBranchCode() {
    return spouseTINBranchCode;
  }

  public void setSpouseTINBranchCode(String spouseTINBranchCode) {
    this.spouseTINBranchCode = spouseTINBranchCode;
  }

  public String getSpouseLastName() {
    return spouseLastName;
  }

  public void setSpouseLastName(String spouseLastName) {
    this.spouseLastName = spouseLastName;
  }

  public String getSpouseFirstName() {
    return spouseFirstName;
  }

  public void setSpouseFirstName(String spouseFirstName) {
    this.spouseFirstName = spouseFirstName;
  }

  public String getSpouseMiddleName() {
    return spouseMiddleName;
  }

  public void setSpouseMiddleName(String spouseMiddleName) {
    this.spouseMiddleName = spouseMiddleName;
  }

  public String getSpouseEmployerTIN() {
    return spouseEmployerTIN;
  }

  public void setSpouseEmployerTIN(String spouseEmployerTIN) {
    this.spouseEmployerTIN = spouseEmployerTIN;
  }

  public String getSpouseEmployerBranchCode() {
    return spouseEmployerBranchCode;
  }

  public void setSpouseEmployerBranchCode(String spouseEmployerBranchCode) {
    this.spouseEmployerBranchCode = spouseEmployerBranchCode;
  }

  public String getSpouseEmployerName() {
    return spouseEmployerName;
  }

  public void setSpouseEmployerName(String spouseEmployerName) {
    this.spouseEmployerName = spouseEmployerName;
  }

  public String getTrusteeFullName() {
    return trusteeFullName;
  }

  public void setTrusteeFullName(String trusteeFullName) {
    this.trusteeFullName = trusteeFullName;
  }

  public String getTrusteeTownDistrict() {
    return trusteeTownDistrict;
  }

  public void setTrusteeTownDistrict(String trusteeTownDistrict) {
    this.trusteeTownDistrict = trusteeTownDistrict;
  }

  public String getTrusteeSubdivisionVillage() {
    return trusteeSubdivisionVillage;
  }

  public void setTrusteeSubdivisionVillage(String trusteeSubdivisionVillage) {
    this.trusteeSubdivisionVillage = trusteeSubdivisionVillage;
  }

  public String getTrusteeStreet() {
    return trusteeStreet;
  }

  public void setTrusteeStreet(String trusteeStreet) {
    this.trusteeStreet = trusteeStreet;
  }

  public String getTrusteeLotBlockPhaseNo() {
    return trusteeLotBlockPhaseNo;
  }

  public void setTrusteeLotBlockPhaseNo(String trusteeLotBlockPhaseNo) {
    this.trusteeLotBlockPhaseNo = trusteeLotBlockPhaseNo;
  }

  public String getTrusteeBuildingTower() {
    return trusteeBuildingTower;
  }

  public void setTrusteeBuildingTower(String trusteeBuildingTower) {
    this.trusteeBuildingTower = trusteeBuildingTower;
  }

  public String getTrusteeUnitRoomFloorNo() {
    return trusteeUnitRoomFloorNo;
  }

  public void setTrusteeUnitRoomFloorNo(String trusteeUnitRoomFloorNo) {
    this.trusteeUnitRoomFloorNo = trusteeUnitRoomFloorNo;
  }

  public List<Children> getChildren() {
    return children;
  }

  public void setChildren(List<Children> children) {
    this.children = children;
  }

  public List<TaxPayerTaxType> getTpTaxTypes() {
    return tpTaxTypes;
  }

  public void setTpTaxTypes(List<TaxPayerTaxType> tpTaxTypes) {
    this.tpTaxTypes = tpTaxTypes;
  }

  public List<ProfessionalInformation> getProfessionalInformations() {
    return professionalInformations;
  }

  public void setProfessionalInformations(List<ProfessionalInformation> professionalInformations) {
    this.professionalInformations = professionalInformations;
  }

  public List<BusinessSummaryDetail> getBusinessSummaryDetails() {
    return businessSummaryDetails;
  }

  public void setBusinessSummaryDetails(List<BusinessSummaryDetail> businessSummaryDetails) {
    this.businessSummaryDetails = businessSummaryDetails;
  }

  public List<IncentiveDetail> getIncentiveDetails() {
    return incentiveDetails;
  }

  public void setIncentiveDetails(List<IncentiveDetail> incentiveDetails) {
    this.incentiveDetails = incentiveDetails;
  }

  public List<TaxpayerAttachment> getDocumentaryRequirements() {
    return documentaryRequirements;
  }

  public void setDocumentaryRequirements(List<TaxpayerAttachment> documentaryRequirements) {
    this.documentaryRequirements = documentaryRequirements;
  }

  public List<TaxPayerSpecialCode> getTpSpecialCodes() {
    return tpSpecialCodes;
  }

  public void setTpSpecialCodes(List<TaxPayerSpecialCode> tpSpecialCodes) {
    this.tpSpecialCodes = tpSpecialCodes;
  }

  public List<RelationshipDetail> getRelationshipDetails() {
    return relationshipDetails;
  }

  public void setRelationshipDetails(List<RelationshipDetail> relationshipDetails) {
    this.relationshipDetails = relationshipDetails;
  }

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public List<ContactInformation> getContactInformation() {
    return contactInformation;
  }

  public void setContactInformation(List<ContactInformation> contactInformation) {
    this.contactInformation = contactInformation;
  }

  public List<Identification> getIdentifications() {
    return identifications;
  }

  public void setIdentifications(List<Identification> identifications) {
    this.identifications = identifications;
  }

  public List<BookOfAccount> getBookOfAccounts() {
    return bookOfAccounts;
  }

  public void setBookOfAccounts(List<BookOfAccount> bookOfAccounts) {
    this.bookOfAccounts = bookOfAccounts;
  }

  public List<AccountingPeriodHistory> getAccountingPeriodHistories() {
    return accountingPeriodHistories;
  }

  public void setAccountingPeriodHistories(
      List<AccountingPeriodHistory> accountingPeriodHistories) {
    this.accountingPeriodHistories = accountingPeriodHistories;
  }

  public List<TransferHistory> getTransferHistories() {
    return transferHistories;
  }

  public void setTransferHistories(List<TransferHistory> transferHistories) {
    this.transferHistories = transferHistories;
  }

  public List<TagUntagTaxPayerHistory> getTagUntagTaxPayerHistories() {
    return tagUntagTaxPayerHistories;
  }

  public void setTagUntagTaxPayerHistories(
      List<TagUntagTaxPayerHistory> tagUntagTaxPayerHistories) {
    this.tagUntagTaxPayerHistories = tagUntagTaxPayerHistories;
  }

  public List<TaxPayerStatusHistory> getTaxPayerHistories() {
    return taxPayerHistories;
  }

  public void setTaxPayerHistories(List<TaxPayerStatusHistory> taxPayerHistories) {
    this.taxPayerHistories = taxPayerHistories;
  }

  public String getDataSourceCreated() {
    return dataSourceCreated;
  }

  public void setDataSourceCreated(String dataSourceCreated) {
    this.dataSourceCreated = dataSourceCreated;
  }

  public String getDataSourceUpdated() {
    return dataSourceUpdated;
  }

  public void setDataSourceUpdated(String dataSourceUpdated) {
    this.dataSourceUpdated = dataSourceUpdated;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getTransactionNumber() {
    return transactionNumber;
  }

  public void setTransactionNumber(String transactionNumber) {
    this.transactionNumber = transactionNumber;
  }

  public String getDocumentLocatorNumber() {
    return documentLocatorNumber;
  }

  public String getReasonId() {
    return reasonId;
  }

  public void setReasonId(String reasonId) {
    this.reasonId = reasonId;
  }

  public String getReasonDescription() {
    return reasonDescription;
  }

  public void setReasonDescription(String reasonDescription) {
    this.reasonDescription = reasonDescription;
  }


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Boolean getIsExemptedFromRegistrationFee() {
    if (null == this.isExemptedFromRegistrationFee) {
      isExemptedFromRegistrationFee = false;
    }
    return isExemptedFromRegistrationFee;
  }

  public void setIsExemptedFromRegistrationFee(Boolean isExemptedFromRegistrationFee) {
    this.isExemptedFromRegistrationFee = isExemptedFromRegistrationFee;
    if (null == this.isExemptedFromRegistrationFee) {
      this.isExemptedFromRegistrationFee = false;
    }
  }

  public String getTrustName() {
    return trustName;
  }

  public void setTrustName(String trustName) {
    this.trustName = trustName;
  }

  public String getEstateName() {
    return estateName;
  }

  public void setEstateName(String estateName) {
    this.estateName = estateName;
  }

  public String getTinSource() {
    return tinSource;
  }

  public void setTinSource(String tinSource) {
    this.tinSource = tinSource;
  }

  public Date getTinIssuanceDate() {
    return tinIssuanceDate;
  }

  public void setTinIssuanceDate(Date tinIssuanceDate) {
    this.tinIssuanceDate = tinIssuanceDate;
  }

  public Date getOrganizationDate() {
    return organizationDate;
  }

  public void setOrganizationDate(Date organizationDate) {
    this.organizationDate = organizationDate;
  }

  public String getTaxpayerClassificationId() {
    return taxpayerClassificationId;
  }

  public void setTaxpayerClassificationId(String taxpayerClassificationId) {
    this.taxpayerClassificationId = taxpayerClassificationId;
  }

  public String getTaxpayerClassificationDescription() {
    return taxpayerClassificationDescription;
  }

  public void setTaxpayerClassificationDescription(String taxpayerClassificationDescription) {
    this.taxpayerClassificationDescription = taxpayerClassificationDescription;
  }

  public String getTaxpayerTypeId() {
    return taxpayerTypeId;
  }

  public void setTaxpayerTypeId(String taxpayerTypeId) {
    this.taxpayerTypeId = taxpayerTypeId;
  }

  public String getTaxpayerTypeDescription() {
    return taxpayerTypeDescription;
  }

  public void setTaxpayerTypeDescription(String taxpayerTypeDescription) {
    this.taxpayerTypeDescription = taxpayerTypeDescription;
  }

  public String getTaxpayerGroupId() {
    return taxpayerGroupId;
  }

  public void setTaxpayerGroupId(String taxpayerGroupId) {
    this.taxpayerGroupId = taxpayerGroupId;
  }

  public String getTaxpayerGroupDescription() {
    return taxpayerGroupDescription;
  }

  public void setTaxpayerGroupDescription(String taxpayerGroupDescription) {
    this.taxpayerGroupDescription = taxpayerGroupDescription;
  }

  public Long getOfficeCoverageId() {
    return officeCoverageId;
  }

  public void setOfficeCoverageId(Long officeCoverageId) {
    this.officeCoverageId = officeCoverageId;
  }

  public String getOfficeCoverageDescription() {
    return officeCoverageDescription;
  }

  public void setOfficeCoverageDescription(String officeCoverageDescription) {
    this.officeCoverageDescription = officeCoverageDescription;
  }

  public String getFacilityTypeId() {
    return facilityTypeId;
  }

  public void setFacilityTypeId(String facilityTypeId) {
    this.facilityTypeId = facilityTypeId;
  }

  public String getFacilityTypeDescription() {
    return facilityTypeDescription;
  }

  public void setFacilityTypeDescription(String facilityTypeDescription) {
    this.facilityTypeDescription = facilityTypeDescription;
  }

  public String getAccountingPeriodId() {
    return accountingPeriodId;
  }

  public void setAccountingPeriodId(String accountingPeriodId) {
    this.accountingPeriodId = accountingPeriodId;
  }

  public String getAccountingPeriodDescription() {
    return accountingPeriodDescription;
  }

  public void setAccountingPeriodDescription(String accountingPeriodDescription) {
    this.accountingPeriodDescription = accountingPeriodDescription;
  }

  public String getAccountingYearStartMonthId() {
    return accountingYearStartMonthId;
  }

  public void setAccountingYearStartMonthId(String accountingYearStartMonthId) {
    this.accountingYearStartMonthId = accountingYearStartMonthId;
  }

  public String getAccountingYearStartMonthDescription() {
    return accountingYearStartMonthDescription;
  }

  public void setAccountingYearStartMonthDescription(String accountingYearStartMonthDescription) {
    this.accountingYearStartMonthDescription = accountingYearStartMonthDescription;
  }

  public String getRegulatoryBodyId() {
    return regulatoryBodyId;
  }

  public void setRegulatoryBodyId(String regulatoryBodyId) {
    this.regulatoryBodyId = regulatoryBodyId;
  }

  public String getRegulatoryBodyDescription() {
    return regulatoryBodyDescription;
  }

  public void setRegulatoryBodyDescription(String regulatoryBodyDescription) {
    this.regulatoryBodyDescription = regulatoryBodyDescription;
  }

  public List<IncorporatorInformation> getIncorporator() {
    return incorporator;
  }

  public void setIncorporator(List<IncorporatorInformation> incorporator) {
    this.incorporator = incorporator;
  }

  public String getTitleId() {
    return titleId;
  }

  public void setTitleId(String titleId) {
    this.titleId = titleId;
  }

  public String getTitleDescription() {
    return titleDescription;
  }

  public void setTitleDescription(String titleDescription) {
    this.titleDescription = titleDescription;
  }

  public String getOtherCitizenshipId() {
    return otherCitizenshipId;
  }

  public void setOtherCitizenshipId(String otherCitizenshipId) {
    this.otherCitizenshipId = otherCitizenshipId;
  }

  public String getOtherCitizenshipDescription() {
    return otherCitizenshipDescription;
  }

  public void setOtherCitizenshipDescription(String otherCitizenshipDescription) {
    this.otherCitizenshipDescription = otherCitizenshipDescription;
  }

  public String getCitizenshipId() {
    return citizenshipId;
  }

  public void setCitizenshipId(String citizenshipId) {
    this.citizenshipId = citizenshipId;
  }

  public String getCitizenshipDescription() {
    return citizenshipDescription;
  }

  public void setCitizenshipDescription(String citizenshipDescription) {
    this.citizenshipDescription = citizenshipDescription;
  }

  public String getSuffixId() {
    return suffixId;
  }

  public void setSuffixId(String suffixId) {
    this.suffixId = suffixId;
  }

  public String getSuffixDescription() {
    return suffixDescription;
  }

  public void setSuffixDescription(String suffixDescription) {
    this.suffixDescription = suffixDescription;
  }

  public String getGenderId() {
    return genderId;
  }

  public void setGenderId(String genderId) {
    this.genderId = genderId;
  }

  public String getGenderDescription() {
    return genderDescription;
  }

  public void setGenderDescription(String genderDescription) {
    this.genderDescription = genderDescription;
  }

  public String getMaritalStatusId() {
    return maritalStatusId;
  }

  public void setMaritalStatusId(String maritalStatusId) {
    this.maritalStatusId = maritalStatusId;
  }

  public String getMaritalStatusDescription() {
    return maritalStatusDescription;
  }

  public void setMaritalStatusDescription(String maritalStatusDescription) {
    this.maritalStatusDescription = maritalStatusDescription;
  }

  public String getPurposeOfTinId() {
    return purposeOfTinId;
  }

  public void setPurposeOfTinId(String purposeOfTinId) {
    this.purposeOfTinId = purposeOfTinId;
  }

  public String getPurposeOfTinDescription() {
    return purposeOfTinDescription;
  }

  public void setPurposeOfTinDescription(String purposeOfTinDescription) {
    this.purposeOfTinDescription = purposeOfTinDescription;
  }

  public String getSpouseEmploymentStatusId() {
    return spouseEmploymentStatusId;
  }

  public void setSpouseEmploymentStatusId(String spouseEmploymentStatusId) {
    this.spouseEmploymentStatusId = spouseEmploymentStatusId;
  }

  public String getSpouseEmploymentStatusDescription() {
    return spouseEmploymentStatusDescription;
  }

  public void setSpouseEmploymentStatusDescription(String spouseEmploymentStatusDescription) {
    this.spouseEmploymentStatusDescription = spouseEmploymentStatusDescription;
  }

  public String getSpouseSuffixId() {
    return spouseSuffixId;
  }

  public void setSpouseSuffixId(String spouseSuffixId) {
    this.spouseSuffixId = spouseSuffixId;
  }

  public String getSpouseSuffixDescription() {
    return spouseSuffixDescription;
  }

  public void setSpouseSuffixDescription(String spouseSuffixDescription) {
    this.spouseSuffixDescription = spouseSuffixDescription;
  }

  public String getTrusteeProvinceId() {
    return trusteeProvinceId;
  }

  public void setTrusteeProvinceId(String trusteeProvinceId) {
    this.trusteeProvinceId = trusteeProvinceId;
  }

  public String getTrusteeProvinceDescription() {
    return trusteeProvinceDescription;
  }

  public void setTrusteeProvinceDescription(String trusteeProvinceDescription) {
    this.trusteeProvinceDescription = trusteeProvinceDescription;
  }

  public String getTrusteeBarangayId() {
    return trusteeBarangayId;
  }

  public void setTrusteeBarangayId(String trusteeBarangayId) {
    this.trusteeBarangayId = trusteeBarangayId;
  }

  public String getTrusteeBarangayDescription() {
    return trusteeBarangayDescription;
  }

  public void setTrusteeBarangayDescription(String trusteeBarangayDescription) {
    this.trusteeBarangayDescription = trusteeBarangayDescription;
  }

  public String getTrusteeZipCodeId() {
    return trusteeZipCodeId;
  }

  public void setTrusteeZipCodeId(String trusteeZipCodeId) {
    this.trusteeZipCodeId = trusteeZipCodeId;
  }

  public String getTrusteeZipCodeDescription() {
    return trusteeZipCodeDescription;
  }

  public void setTrusteeZipCodeDescription(String trusteeZipCodeDescription) {
    this.trusteeZipCodeDescription = trusteeZipCodeDescription;
  }

  public String getTrusteeMunicipalityId() {
    return trusteeMunicipalityId;
  }

  public void setTrusteeMunicipalityId(String trusteeMunicipalityId) {
    this.trusteeMunicipalityId = trusteeMunicipalityId;
  }

  public String getTrusteeMunicipalityDescription() {
    return trusteeMunicipalityDescription;
  }

  public void setTrusteeMunicipalityDescription(String trusteeMunicipalityDescription) {
    this.trusteeMunicipalityDescription = trusteeMunicipalityDescription;
  }

  public String getTrusteeCountryId() {
    return trusteeCountryId;
  }

  public void setTrusteeCountryId(String trusteeCountryId) {
    this.trusteeCountryId = trusteeCountryId;
  }

  public String getTrusteeCountryDescription() {
    return trusteeCountryDescription;
  }

  public void setTrusteeCountryDescription(String trusteeCountryDescription) {
    this.trusteeCountryDescription = trusteeCountryDescription;
  }

  public String getPreGenTinSourceId() {
    return preGenTinSourceId;
  }

  public void setPreGenTinSourceId(String preGenTinSourceId) {
    this.preGenTinSourceId = preGenTinSourceId;
  }

  public String getPreGenTinSourceDescription() {
    return preGenTinSourceDescription;
  }

  public void setPreGenTinSourceDescription(String preGenTinSourceDescription) {
    this.preGenTinSourceDescription = preGenTinSourceDescription;
  }

  public String getRegistrationType() {
    return registrationType;
  }

  public void setRegistrationType(String registrationType) {
    this.registrationType = registrationType;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public String getOfficeDescription() {
    return officeDescription;
  }

  public void setOfficeDescription(String officeDescription) {
    this.officeDescription = officeDescription;
  }

  public Boolean getTaxpayerTypeIsBusiness() {
    if (null == this.taxpayerTypeIsBusiness) {
      taxpayerTypeIsBusiness = false;
    }
    return taxpayerTypeIsBusiness;
  }

  public void setTaxpayerTypeIsBusiness(Boolean taxpayerTypeIsBusiness) {
    this.taxpayerTypeIsBusiness = taxpayerTypeIsBusiness;
    if (null == this.taxpayerTypeIsBusiness) {
      this.taxpayerTypeIsBusiness = false;
    }
  }

  public String getOfficeCode() {
    return officeCode;
  }

  public void setOfficeCode(String officeCode) {
    this.officeCode = officeCode;
  }

  public String getTrusteeAddressTypeId() {
    return trusteeAddressTypeId;
  }

  public void setTrusteeAddressTypeId(String trusteeAddressTypeId) {
    this.trusteeAddressTypeId = trusteeAddressTypeId;
  }

  public String getTrusteeAddressTypeDescription() {
    return trusteeAddressTypeDescription;
  }

  public void setTrusteeAddressTypeDescription(String trusteeAddressTypeDescription) {
    this.trusteeAddressTypeDescription = trusteeAddressTypeDescription;
  }

  public String getRegisteringOfficeId() {
    return registeringOfficeId;
  }

  public void setRegisteringOfficeId(String registeringOfficeId) {
    this.registeringOfficeId = registeringOfficeId;
  }

  public String getRegisteringOfficeDescription() {
    return registeringOfficeDescription;
  }

  public void setRegisteringOfficeDescription(String registeringOfficeDescription) {
    this.registeringOfficeDescription = registeringOfficeDescription;
  }

  public String getCorrespondenceSignatory() {
    return correspondenceSignatory;
  }

  public void setCorrespondenceSignatory(String correspondenceSignatory) {
    this.correspondenceSignatory = correspondenceSignatory;
  }

  public String getCorrespondencePosition() {
    return correspondencePosition;
  }

  public void setCorrespondencePosition(String correspondencePosition) {
    this.correspondencePosition = correspondencePosition;
  }

  public String getOfficeRegionDescription() {
    return officeRegionDescription;
  }

  public void setOfficeRegionDescription(String officeRegionDescription) {
    this.officeRegionDescription = officeRegionDescription;
  }

  public Boolean getOfficeLargeTaxpayerOfficeFlag() {
    if (null == this.officeLargeTaxpayerOfficeFlag) {
      officeLargeTaxpayerOfficeFlag = false;
    }
    return officeLargeTaxpayerOfficeFlag;
  }

  public void setOfficeLargeTaxpayerOfficeFlag(Boolean officeLargeTaxpayerOfficeFlag) {
    this.officeLargeTaxpayerOfficeFlag = officeLargeTaxpayerOfficeFlag;
    if (null == this.officeLargeTaxpayerOfficeFlag) {
      this.officeLargeTaxpayerOfficeFlag = false;
    }
  }

  public DocumentLocatorNumberHistory getDocumentLocatorNumberHistory() {
    return documentLocatorNumberHistory;
  }

  public void setDocumentLocatorNumberHistory(
      DocumentLocatorNumberHistory documentLocatorNumberHistory) {
    this.documentLocatorNumberHistory = documentLocatorNumberHistory;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Boolean getIsVatExceeding() {
    if (null == this.isVatExceeding) {
      isVatExceeding = false;
    }
    return isVatExceeding;
  }

  public void setIsVatExceeding(Boolean isVatExceeding) {
    this.isVatExceeding = isVatExceeding;
    if (null == this.isVatExceeding) {
      this.isVatExceeding = false;
    }
  }

  public Boolean getIsFromPreGenTIN() {
    if (null == this.isFromPreGenTIN) {
      isFromPreGenTIN = false;
    }
    return isFromPreGenTIN;
  }

  public void setIsFromPreGenTIN(Boolean isFromPreGenTIN) {
    this.isFromPreGenTIN = isFromPreGenTIN;
    if (null == this.isFromPreGenTIN) {
      isFromPreGenTIN = false;
    }
  }

  public String getIndustryGroupId() {
    return industryGroupId;
  }

  public void setIndustryGroupId(String industryGroupId) {
    this.industryGroupId = industryGroupId;
  }

  public String getIndustryGroupDescription() {
    return industryGroupDescription;
  }

  public void setIndustryGroupDescription(String industryGroupDescription) {
    this.industryGroupDescription = industryGroupDescription;
  }

  public String getPsicId() {
    return psicId;
  }

  public void setPsicId(String psicId) {
    this.psicId = psicId;
  }

  public String getPsicDescription() {
    return psicDescription;
  }

  public void setPsicDescription(String psicDescription) {
    this.psicDescription = psicDescription;
  }

  public String getBusinessStatus() {
    return businessStatus;
  }

  public void setBusinessStatus(String businessStatus) {
    this.businessStatus = businessStatus;
  }

  public List<TaxpayerTypeHistory> getTaxPayerTypeHistories() {
    return taxPayerTypeHistories;
  }

  public void setTaxPayerTypeHistories(List<TaxpayerTypeHistory> taxPayerTypeHistories) {
    this.taxPayerTypeHistories = taxPayerTypeHistories;
  }

  public String getTransactionType() {
    return transactionType;
  }

  public void setTransactionType(String transactionType) {
    this.transactionType = transactionType;
  }

  public List<ITSRegAddress> getRegAddresses() {
    return regAddresses;
  }

  public void setRegAddresses(List<ITSRegAddress> regAddresses) {
    this.regAddresses = regAddresses;
  }

  @Override
  public String toString() {
    return "TaxPayer [id=" + id + ", tin=" + tin + ", branchCode=" + branchCode
        + ", taxpayerClassificationId=" + taxpayerClassificationId
        + ", taxpayerClassificationDescription=" + taxpayerClassificationDescription
        + ", taxpayerTypeId=" + taxpayerTypeId + ", taxpayerTypeDescription="
        + taxpayerTypeDescription + ", taxpayerTypeIsBusiness=" + taxpayerTypeIsBusiness
        + ", taxpayerGroupId=" + taxpayerGroupId + ", taxpayerGroupDescription="
        + taxpayerGroupDescription + ", tinIssuanceDate=" + tinIssuanceDate + ", officeId="
        + officeId + ", officeDescription=" + officeDescription + ", officeCode=" + officeCode
        + ", officeLargeTaxpayerOfficeFlag=" + officeLargeTaxpayerOfficeFlag + ", tinStatus="
        + tinStatus + ", facilityTypeId=" + facilityTypeId + ", facilityTypeDescription="
        + facilityTypeDescription + ", accountingPeriodId=" + accountingPeriodId
        + ", accountingPeriodDescription=" + accountingPeriodDescription
        + ", accountingYearStartMonthId=" + accountingYearStartMonthId
        + ", accountingYearStartMonthDescription=" + accountingYearStartMonthDescription
        + ", accountingEffectiveDate=" + accountingEffectiveDate + ", cancellationDate="
        + cancellationDate + ", registeredName=" + registeredName + ", titleId=" + titleId
        + ", titleDescription=" + titleDescription + ", lastName=" + lastName + ", firstName="
        + firstName + ", middleName=" + middleName + ", trustName=" + trustName + ", estateName="
        + estateName + ", suffixId=" + suffixId + ", suffixDescription=" + suffixDescription
        + ", genderId=" + genderId + ", genderDescription=" + genderDescription
        + ", maritalStatusId=" + maritalStatusId + ", maritalStatusDescription="
        + maritalStatusDescription + ", birthDate=" + birthDate + ", registrationType="
        + registrationType + ", tinSource=" + tinSource + ", transactionNumber=" + transactionNumber
        + "]";
  }


}
