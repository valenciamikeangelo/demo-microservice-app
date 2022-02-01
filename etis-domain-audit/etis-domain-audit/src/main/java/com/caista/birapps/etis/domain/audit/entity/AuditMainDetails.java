/*
  * Modified by: obregoj
  * Last updated: Sep 19, 2019 3:45:47 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.module.EtisSubmodules;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;

@Entity
@Table(name = "AUDIT_MAIN")
public class AuditMainDetails implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ACTIVITY_ID")
	private String activityId;

	@Id
	@GeneratedValue(generator = "AUDIT_MAIN_SEQ_SequenceStyleGenerator")
	@GenericGenerator(name = "AUDIT_MAIN_SEQ_SequenceStyleGenerator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "AUDIT_MAIN_SEQ"),
			@Parameter(name = "initial_value", value = "1"), @Parameter(name = "increment_size", value = "1") })
	@Column(name = "SEQ_ID", unique = true, nullable = false)
	private BigInteger auditSeqId;

	@Column(name = "USERNAME", columnDefinition = "VARCHAR2(50)")
	private String username;

	@Column(name = "IP_ADDRESS", columnDefinition = "VARCHAR2(20)")
	private String ipAddress;

	@Column(name = "FIRST_NAME", columnDefinition = "VARCHAR2(50)")
	private String firstName;

	@Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(50)")
	private String lastName;

	@Column(name = "ETIS_MODULE", columnDefinition = "VARCHAR2(50)", nullable = false)
	@Enumerated(EnumType.STRING)
	private EtisModules etisModule;

	@Column(name = "ETIS_SUBMODULE", columnDefinition = "VARCHAR2(50)", nullable = false)
	@Enumerated(EnumType.STRING)
	private EtisSubmodules etisSubModule;

	@Column(name = "LOG_EVENT", columnDefinition = "VARCHAR2(50)", nullable = false)
	@Enumerated(EnumType.STRING)
	private LogEvent logEvent;

	@Column(name = "LOG_TIME")
	private Date logTime;

	@Column(name = "SOURCE_ID", columnDefinition = "VARCHAR2(255)", nullable = false)
	private String sourceId;

	@Column(name = "OFFICE", columnDefinition = "VARCHAR2(50)")
	private String office;

	@Column(name = "OFFICE_CODE", columnDefinition = "VARCHAR2(20)")
	private String officeCode;

	@Column(name = "OFFICE_TYPE", columnDefinition = "VARCHAR2(50)")
	private String officeType;

	@Column(name = "OFFICE_TYPE_CODE", columnDefinition = "VARCHAR2(20)")
	private String officeTypeCode;

	@Column(name = "DETAILS", columnDefinition = "VARCHAR2(255)")
	private String details;

	private transient String moduleDescription;

	private transient String submoduleDescription;


	public AuditMainDetails() {
		super();
		this.activityId = UUID.randomUUID().toString();
	}

	public AuditMainDetails(String activityId, BigInteger auditSeqId, String username, String ipAddress,
			String firstName, String lastName, EtisModules etisModule, EtisSubmodules etisSubModule, LogEvent logEvent,
			Date logTime, String sourceId, String office, String officeCode, String officeType, String officeTypeCode,
			String details) {
		super();
		this.activityId = activityId;
		this.auditSeqId = auditSeqId;
		this.username = username;
		this.ipAddress = ipAddress;
		this.firstName = firstName;
		this.lastName = lastName;
		this.etisModule = etisModule;
		this.etisSubModule = etisSubModule;
		this.logEvent = logEvent;
		this.logTime = logTime;
		this.sourceId = sourceId;
		this.office = office;
		this.officeCode = officeCode;
		this.officeType = officeType;
		this.officeTypeCode = officeTypeCode;
		this.details = details;
	}

	public AuditMainDetails(String username, Date logTime) {
		this.username = username;
		this.logTime = logTime;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public BigInteger getAuditSeqId() {
		return auditSeqId;
	}

	public void setAuditSeqId(BigInteger auditSeqId) {
		this.auditSeqId = auditSeqId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EtisModules getEtisModule() {
		return etisModule;
	}

	public void setEtisModule(EtisModules etisModule) {
		this.etisModule = etisModule;
	}

	public EtisSubmodules getEtisSubModule() {
		return etisSubModule;
	}

	public void setEtisSubModule(EtisSubmodules etisSubModule) {
		this.etisSubModule = etisSubModule;
	}

	public LogEvent getLogEvent() {
		return logEvent;
	}

	public void setLogEvent(LogEvent logEvent) {
		this.logEvent = logEvent;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getOfficeType() {
		return officeType;
	}

	public void setOfficeType(String officeType) {
		this.officeType = officeType;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getOfficeTypeCode() {
		return officeTypeCode;
	}

	public void setOfficeTypeCode(String officeTypeCode) {
		this.officeTypeCode = officeTypeCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getModuleDescription() {
		if (null != etisModule)
			return etisModule.getDescription();
		return moduleDescription;
	}

	public String getSubmoduleDescription() {
		if (null != etisSubModule)
			return etisSubModule.getDescription();
		return submoduleDescription;
	}

	public String getUserFullName() {
		return (this.firstName == null ? "" : this.firstName) + " " + (this.lastName == null ? "" : this.lastName);
	}

	@Override
	public String toString() {
		return "AuditMainDetails [activityId=" + activityId + ", auditSeqId=" + auditSeqId + ", username=" + username
				+ ", ipAddress=" + ipAddress + ", firstName=" + firstName + ", lastName=" + lastName + ", etisModule="
				+ etisModule + ", etisSubModule=" + etisSubModule + ", logEvent=" + logEvent + ", logTime=" + logTime
				+ ", sourceId=" + sourceId + ", office=" + office + ", officeCode=" + officeCode + ", officeType="
				+ officeType + ", officeTypeCode=" + officeTypeCode + ", details=" + details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((activityId == null) ? 0 : activityId.hashCode());
		result = prime * result + ((auditSeqId == null) ? 0 : auditSeqId.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((etisModule == null) ? 0 : etisModule.hashCode());
		result = prime * result + ((etisSubModule == null) ? 0 : etisSubModule.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((logEvent == null) ? 0 : logEvent.hashCode());
		result = prime * result + ((logTime == null) ? 0 : logTime.hashCode());
		result = prime * result + ((office == null) ? 0 : office.hashCode());
		result = prime * result + ((officeCode == null) ? 0 : officeCode.hashCode());
		result = prime * result + ((officeType == null) ? 0 : officeType.hashCode());
		result = prime * result + ((officeTypeCode == null) ? 0 : officeTypeCode.hashCode());
		result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditMainDetails other = (AuditMainDetails) obj;
		if (activityId == null) {
			if (other.activityId != null)
				return false;
		} else if (!activityId.equals(other.activityId))
			return false;
		if (auditSeqId == null) {
			if (other.auditSeqId != null)
				return false;
		} else if (!auditSeqId.equals(other.auditSeqId))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (etisModule != other.etisModule)
			return false;
		if (etisSubModule != other.etisSubModule)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (ipAddress == null) {
			if (other.ipAddress != null)
				return false;
		} else if (!ipAddress.equals(other.ipAddress))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (logEvent != other.logEvent)
			return false;
		if (logTime == null) {
			if (other.logTime != null)
				return false;
		} else if (!logTime.equals(other.logTime))
			return false;
		if (office == null) {
			if (other.office != null)
				return false;
		} else if (!office.equals(other.office))
			return false;
		if (officeCode == null) {
			if (other.officeCode != null)
				return false;
		} else if (!officeCode.equals(other.officeCode))
			return false;
		if (officeType == null) {
			if (other.officeType != null)
				return false;
		} else if (!officeType.equals(other.officeType))
			return false;
		if (officeTypeCode == null) {
			if (other.officeTypeCode != null)
				return false;
		} else if (!officeTypeCode.equals(other.officeTypeCode))
			return false;
		if (sourceId == null) {
			if (other.sourceId != null)
				return false;
		} else if (!sourceId.equals(other.sourceId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
