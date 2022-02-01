/*
  * Modified by: romerov
  * Last updated: 05 4, 20 2:46:59 PM
*/
package com.caista.birapps.etis.domain.trs.utils;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.caista.birapps.etis.domain.trs.entity.Address;
import com.caista.birapps.etis.domain.trs.entity.BusinessSummaryDetail;
import com.caista.birapps.etis.domain.trs.entity.ContactInformation;
import com.caista.birapps.etis.domain.trs.entity.TaxPayer;
import com.caista.birapps.etis.domain.trs.utils.enums.BusinessStatusEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.FilingFrequencyEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.ReferenceCodesEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.TaxpayerRegistrationTypeEnum;

public class TaxpayerUtil {

	public static String getTaxPayerName(String registeredName, String estateName, String trustName, String fName,
			String mName, String lName, String suffix) {
		String tpName = StringUtils.EMPTY;
		if (!StringUtils.isBlank(registeredName)) {
			tpName = registeredName;
		} else if (!StringUtils.isBlank(estateName)) {
			tpName = estateName;
		} else if (!StringUtils.isBlank(trustName)) {
			tpName = trustName;
		} else {
			tpName = lName + ", " + fName + StringUtils.SPACE
					+ ((!StringUtils.isBlank(suffix)) ? suffix + StringUtils.SPACE : StringUtils.EMPTY)
					+ ((!StringUtils.isBlank(mName)) ? mName + StringUtils.SPACE : StringUtils.EMPTY);
		}
		return tpName;
	}

	public static Boolean checkIfTaxPayerIsHeadOfficeOrBranch(String registrationType, String branchCode) {
		return (StringUtils.equalsIgnoreCase(registrationType, String.valueOf(TaxpayerRegistrationTypeEnum.INDIVIDUAL))
				&& StringUtils.equalsIgnoreCase(branchCode, "00000"))
				|| (StringUtils.equalsIgnoreCase(registrationType,
						String.valueOf(TaxpayerRegistrationTypeEnum.NONINDIVIDUAL))
						&& StringUtils.equalsIgnoreCase(branchCode, "00000"))
				|| (StringUtils.equalsIgnoreCase(registrationType,
						String.valueOf(TaxpayerRegistrationTypeEnum.BRANCH)));
	}

	public static String listAggTradeName(List<BusinessSummaryDetail> bsd) {
		String tradeNamesCommaSeparated = StringUtils.EMPTY;
		for (BusinessSummaryDetail business : bsd) {
			if (StringUtils.isNotBlank(business.getTradeName())
					&& (StringUtils.equals(BusinessStatusEnum.ACTIVE.toString(), business.getStatus())
							|| StringUtils.equals(BusinessStatusEnum.INACTIVE.toString(), business.getStatus()))) {
				tradeNamesCommaSeparated += (!tradeNamesCommaSeparated.equals(StringUtils.EMPTY))
						? ", " + business.getTradeName()
						: business.getTradeName();
			}

		}
		return tradeNamesCommaSeparated;
	}

	public static Boolean hasActiveBusiness(List<BusinessSummaryDetail> bsd) {
		Boolean hasActiveBusiness = false;

		for (BusinessSummaryDetail business : bsd) {
			if (StringUtils.equals(BusinessStatusEnum.ACTIVE.toString(), business.getStatus())) {
				hasActiveBusiness = true;
				break;
			}
		}
		return hasActiveBusiness;
	}

	public static String listAggContactInfoEmail(List<ContactInformation> contact) {
		String emailsCommaSeparated = StringUtils.EMPTY;
		for (ContactInformation info : contact) {
			if (StringUtils.isNotBlank(info.getContactDetails()) && (info.getContactTypeId() != null
					&& StringUtils.equals(ReferenceCodesEnum.COMPTTYPE_EMAIL.getId(), info.getContactTypeId()))) {
				emailsCommaSeparated += (!emailsCommaSeparated.equals(StringUtils.EMPTY))
						? ", " + info.getContactDetails()
						: info.getContactDetails();
			}
		}
		return emailsCommaSeparated;
	}

	public static String concatAddress(Address address) {
		return (address != null) ? checkFirstValue(address.getUnitRoomFloorNo())
				+ appendWithSpace(address.getBuildingTower()) + appendWithSpace(address.getLotBlockPhaseNo())
				+ appendWithSpace(address.getStreet()) + appendWithSpace(address.getSubdivisionVillage())
				+ appendWithSpace(address.getBarangayDescription()) + appendWithSpace(address.getTownDistrict())
				+ appendWithSpace(address.getZipCode()) + appendWithSpace(address.getMunicipalityDescription())
				+ appendWithSpace(address.getProvinceDescription()) + appendWithSpace(address.getCountryDescription())
				: null;

	}

	public static String checkFirstValue(String str) {
		return StringUtils.isNotBlank(str) ? str : "";
	}

	public static String appendWithSpace(String str) {
		return StringUtils.isNotBlank(str) ? " " + str : "";
	}

	public static String concatWithSpaceAndComma(String str) {
		return StringUtils.isNotBlank(str) ? ", " + str : "";
	}

	public static String maskTinBranch(String tin, String branchCode) {
		String tinBranch = "";
		if (StringUtils.isNotBlank(tin)) {
			tinBranch = StringUtils.substring(tin, 0, 3) + "-" + StringUtils.substring(tin, 3, 6) + "-"
					+ StringUtils.substring(tin, 6, 9) + "-" + branchCode;
		}
		return tinBranch;
	}

	public static String getClientIp(HttpServletRequest request) {
		String remoteAddr = "";
		if (request != null) {
			remoteAddr = request.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		remoteAddr = remoteAddr != null && remoteAddr.contains(",") ? remoteAddr.split(",")[0] : remoteAddr;

		return remoteAddr;
	}

	public static TaxPayer booleanBuilder(TaxPayer taxPayer) {

		// TAXPAYER TABLE
		if (taxPayer.getIsBIRInitiated() == null)
			taxPayer.setIsBIRInitiated(Boolean.FALSE);
		if (taxPayer.getIsCBL() == null)
			taxPayer.setIsCBL(Boolean.FALSE);
		if (taxPayer.getIsExemptedFromRegistrationFee() == null)
			taxPayer.setIsExemptedFromRegistrationFee(Boolean.FALSE);
		if (taxPayer.getIsTamp() == null)
			taxPayer.setIsTamp(Boolean.FALSE);
		if (taxPayer.getIsVIP() == null)
			taxPayer.setIsVIP(Boolean.FALSE);
		if (taxPayer.getIsVatExceeding() == null)
			taxPayer.setIsVatExceeding(Boolean.FALSE);
		if (taxPayer.getIsVatExceeding() == null)
			taxPayer.setIsVatExceeding(Boolean.FALSE);
		if (taxPayer.getOfficeLargeTaxpayerOfficeFlag() == null)
			taxPayer.setOfficeLargeTaxpayerOfficeFlag(Boolean.FALSE);
		if (taxPayer.getTaxpayerTypeIsBusiness() == null)
			taxPayer.setTaxpayerTypeIsBusiness(Boolean.FALSE);

		// TAXPAYER ADDRESS TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getAddresses())) {
			taxPayer.getAddresses().forEach(address -> {
				if (address.getIsForeign() == null)
					address.setIsForeign(Boolean.FALSE);
				if (address.getIsPrimary() == null)
					address.setIsPrimary(Boolean.FALSE);
			});
		}

		// TAXPAYER CONTACT INFORMATION TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getContactInformation())) {
			taxPayer.getContactInformation().forEach(contactInfo -> {
				if (contactInfo.getIsPreferred() == null)
					contactInfo.setIsPreferred(Boolean.FALSE);
			});
		}

		// TAXPAYER DEPENDENT TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getChildren())) {
			taxPayer.getChildren().forEach(children -> {
				if (children.getIsChild() == null)
					children.setIsChild(Boolean.FALSE);
				if (children.getIsIncapacitated() == null)
					children.setIsIncapacitated(Boolean.FALSE);
			});
		}

		// TAXPAYER BUSINESS SUMMARY DETAIL TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getBusinessSummaryDetails())) {
			taxPayer.getBusinessSummaryDetails().forEach(bsd -> {
				if (!CollectionUtils.isEmpty(bsd.getIndustryDetails())) {
					bsd.getIndustryDetails().forEach(id -> {
						if (id.getIsPrimary() == null)
							id.setIsPrimary(Boolean.FALSE);
					});
				}
			});
		}

		// TAXPAYER PSOC TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getProfessionalInformations())) {
			taxPayer.getProfessionalInformations().forEach(psoc -> {
				if (psoc.getIsPrimary() == null)
					psoc.setIsPrimary(Boolean.FALSE);
			});
		}

		// TAXPAYER RELATIONSHIP DETAIL TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getRelationshipDetails())) {
			taxPayer.getRelationshipDetails().forEach(rd -> {
				if (rd.getIsPrimary() == null)
					rd.setIsPrimary(Boolean.FALSE);
			});
		}

		// TAXPAYER TAX TYPE TABLE
		if (!CollectionUtils.isEmpty(taxPayer.getTpTaxTypes())) {
			taxPayer.getTpTaxTypes().forEach(tt -> {
				if (tt.getIsAdvisoryVisit() == null)
					tt.setIsAdvisoryVisit(Boolean.FALSE);
				if (tt.getIsTCVD() == null)
					tt.setIsTCVD(Boolean.FALSE);
				if (tt.getIsVisitInterview() == null)
					tt.setIsVisitInterview(Boolean.FALSE);
			});
		}

		return taxPayer;
	}

	public static Date returnPeriod(String filingFrequency, String month, Date givenDate) {
		Calendar userDate = Calendar.getInstance(TimeZone.getDefault());
		userDate.setTime(givenDate);
		userDate.set(Calendar.HOUR_OF_DAY, 0);
		userDate.set(Calendar.MINUTE, 0);
		userDate.set(Calendar.SECOND, 0);
		userDate.set(Calendar.MILLISECOND, 0);

		if (filingFrequency.equalsIgnoreCase("MONTHLY")) {
			userDate.set(Calendar.DAY_OF_MONTH, userDate.getActualMaximum(Calendar.DATE));
			return userDate.getTime();
		} else {
			Calendar fqStart = getStartingDate(month, givenDate);
			Calendar fqEnd = Calendar.getInstance();
			fqEnd.setTime(fqStart.getTime());
			if (filingFrequency.equalsIgnoreCase("QUARTERLY")) {
				fqEnd.add(Calendar.MONTH, 2);
			} else if (filingFrequency.equalsIgnoreCase("SEMI-ANNUAL")) {
				fqEnd.add(Calendar.MONTH, 5);
			} else {
				fqEnd.add(Calendar.MONTH, 11);
			}

			fqEnd.set(Calendar.DAY_OF_MONTH, fqEnd.getActualMaximum(Calendar.DATE));
			boolean found = false;
			while (!found) {
				if (userDate.after(fqStart) && userDate.before(fqEnd)) {
					found = true;
					return fqEnd.getTime();
				}
				if (filingFrequency.equalsIgnoreCase("QUARTERLY")) {
					fqStart.add(Calendar.MONTH, 3);
					fqEnd.add(Calendar.MONTH, 3);
				} else if (filingFrequency.equalsIgnoreCase("SEMI-ANNUAL")) {
					fqStart.add(Calendar.MONTH, 6);
					fqEnd.add(Calendar.MONTH, 6);
				} else {
					fqStart.add(Calendar.MONTH, 11);
					fqEnd.add(Calendar.MONTH, 11);
				}

				fqEnd.set(Calendar.DAY_OF_MONTH, fqEnd.getActualMaximum(Calendar.DATE));
			}

		}
		return null;
	}

	private static Calendar getStartingDate(String month, Date userDate) {
		String[] months = new String[] { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV",
				"DEC" };
		Calendar fiscal = Calendar.getInstance(TimeZone.getDefault());
		fiscal.set(Calendar.DAY_OF_MONTH, 1);
		fiscal.set(Calendar.HOUR_OF_DAY, 0);
		fiscal.set(Calendar.MINUTE, 0);
		fiscal.set(Calendar.SECOND, 0);
		fiscal.set(Calendar.MILLISECOND, 0);

		int index = 0;
		for (String valMonth : months) {
			if (valMonth.equalsIgnoreCase(month)) {
				fiscal.set(Calendar.MONTH, index);
			}
			index++;
		}

		Calendar givenDate = Calendar.getInstance();
		givenDate.setTime(userDate);

		if (givenDate.before(fiscal)) {
			fiscal.set(Calendar.YEAR, LocalDate.now().minusYears(1).getYear());
		}

		return fiscal;
	}

	public static List<String> getMonitoredFormTypes() {
		List<String> monitoredFormTypes = Arrays.asList("2551M", "2551Q", "1700", "1701", "1701/1701A", "1701Q",
				"2550M", "2550Q", "1602Q", "0619F", "1604F", "1602", "1604CF", "1601C", "1604C", "0619E", "1601EQ",
				"1604E", "1601E", "1603Q", "1603", "1601FQ", "1601F", "1600", "1600VT", "1600PT", "1702", "1702EX",
				"1702MX", "1702Q", "1702RT");
		return monitoredFormTypes;
	}

	public static Boolean checkForUpgrade(Boolean oldIsBusiness, Boolean newIsBusiness) {
		return (BooleanUtils.isFalse(oldIsBusiness) && BooleanUtils.isTrue(newIsBusiness));
	}

	@SuppressWarnings("deprecation")
	public static Date processFiscalDate(Date givenDate, String filingFrequency) {
		Date currDate = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(givenDate);
		if (filingFrequency.equalsIgnoreCase(FilingFrequencyEnum.ANNUALLY.getCode())
				|| filingFrequency.equalsIgnoreCase(FilingFrequencyEnum.ANNUAL.getCode())) {
			c.add(Calendar.YEAR, 1);
			c.add(Calendar.MONTH, -1);
			while (currDate.after(c.getTime())) {
				c.add(Calendar.YEAR, 1);
				c.add(Calendar.MONTH, -1);
			}
		} else if (filingFrequency.equalsIgnoreCase(FilingFrequencyEnum.QUARTERLY.getCode())) {
			c.add(Calendar.MONTH, 2);
			while (currDate.after(c.getTime())) {
				c.add(Calendar.MONTH, 3);
			}
		} else if (filingFrequency.equalsIgnoreCase(FilingFrequencyEnum.MONTHLY.getCode())) {
			while (currDate.getMonth() > c.getTime().getMonth()) {
				c.add(Calendar.MONTH, 1);
			}
		}
		return c.getTime();
	}
}
