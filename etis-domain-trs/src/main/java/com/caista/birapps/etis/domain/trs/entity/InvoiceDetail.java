/*
  * Modified by: adzuara
  * Last updated: Jan 4, 2019 4:14:27 PM
  */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class InvoiceDetail.
 */
@Entity
@Table(name = "ATP_INVOICE_DETAIL", uniqueConstraints = @UniqueConstraint(columnNames = { "ATP_INVOICE_DETAIL_ID",
		"ATP_CORRESPONDENCE_ID" }, name = "UC_ATP_INVOICE_DETAIL_01"))
@Check(constraints = "MANNER IN (1, 0)")
// @JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class InvoiceDetail implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(generator = "INVOICE_DETAIL_SEQUENCESTYLEGENERATOR")
	@GenericGenerator(name = "INVOICE_DETAIL_SEQUENCESTYLEGENERATOR", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "SEQ_ATP_INVOICE_DETAIL"),
			@Parameter(name = "initial_value", value = "1"),
			@Parameter(name = "increment_size", value = "1") })
	@Column(name = "ATP_INVOICE_DETAIL_ID", unique = true, nullable = false)
	private Long id;

	/** The atp correspondence id. */
	@Column(name = "ATP_CORRESPONDENCE_ID")
	private Long atpCorrespondenceId;

	@Column(name = "RECEIPT_INVOICE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
	private String receiptInvoiceId;

	@Column(name = "RECEIPT_INVOICE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String receiptInvoiceDescription;

	@Column(name = "RECEIPT_INVOICE_KIND_ID", columnDefinition = "VARCHAR2(20 BYTE)")
	private String receiptInvoiceKindId;

	@Column(name = "RECEIPT_INVOICE_KIND_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
	private String receiptInvoiceKindDescription;

	/** The specify. */
	@Column(name = "SPECIFY", columnDefinition = "VARCHAR2(100 BYTE)")
	private String specify;

	/** The type. */
	@Column(name = "ATP_TYPE", columnDefinition = "VARCHAR2(50 BYTE)")
	private String type; // VAT or Non-VAT

	/** The bound or loose. */
	@Column(name = "MANNER", columnDefinition = "NUMBER(1,0) DEFAULT 0")
	private Boolean manner;// Bound or Loose

	/** The loose no. */
	@Column(name = "PTU_LOOSE_NUMBER", columnDefinition = "VARCHAR2(30 BYTE)")
	private String ptuLooseNumber;

	/** The no of booklets. */
	@Column(name = "NO_OF_BOOKLETS")
	private Long noOfBooklets;

	/** The no of sets per booklet. */
	@Column(name = "NO_OF_SETS_PER_BOOKLETS")
	private Long noOfSetsPerBooklet;

	/** The no of copies per set. */
	@Column(name = "NO_OF_COPIES_PER_BOOKLETS")
	private Long noOfCopiesPerSet;

	/** The starting serial no. */
	@Column(name = "STARTING_SERIAL_NO", columnDefinition = "VARCHAR2(15 BYTE)", nullable = false)
	private String startingSerialNo;

	/** The ending serial no. */
	@Column(name = "ENDING_SERIAL_NO", columnDefinition = "VARCHAR2(15 BYTE)", nullable = false)
	private String endingSerialNo;

	@Column(name = "PREFIX", columnDefinition = "VARCHAR2(5 BYTE)")
	private String prefix;

	@Column(name = "SUFFIX", columnDefinition = "VARCHAR2(5 BYTE)")
	private String suffix;
	
	/** The created by. */
    @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
    private String createdBy;

    /** The created date. */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
    private Date createdDate;

    /** The updated by. */
    @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
    private String updatedBy;

    /** The updated date. */
    @Column(name = "UPDATED_DATE")
    private Date updatedDate;


	public InvoiceDetail() {
		super();
	}

	public InvoiceDetail(Long id, Long atpCorrespondenceId, String receiptInvoiceId, String receiptInvoiceDescription,
			String receiptInvoiceKindId, String receiptInvoiceKindDescription, String specify, String type,
			Boolean manner, String ptuLooseNumber, Long noOfBooklets, Long noOfSetsPerBooklet,
			Long noOfCopiesPerSet, String startingSerialNo, String endingSerialNo, String prefix, String suffix) {
		super();
		this.id = id;
		this.atpCorrespondenceId = atpCorrespondenceId;
		this.receiptInvoiceId = receiptInvoiceId;
		this.receiptInvoiceDescription = receiptInvoiceDescription;
		this.receiptInvoiceKindId = receiptInvoiceKindId;
		this.receiptInvoiceKindDescription = receiptInvoiceKindDescription;
		this.specify = specify;
		this.type = type;
		this.manner = manner;
		this.ptuLooseNumber = ptuLooseNumber;
		this.noOfBooklets = noOfBooklets;
		this.noOfSetsPerBooklet = noOfSetsPerBooklet;
		this.noOfCopiesPerSet = noOfCopiesPerSet;
		this.startingSerialNo = startingSerialNo;
		this.endingSerialNo = endingSerialNo;
		this.prefix = prefix;
		this.suffix = suffix;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAtpCorrespondenceId() {
		return atpCorrespondenceId;
	}

	public void setAtpCorrespondenceId(Long atpCorrespondenceId) {
		this.atpCorrespondenceId = atpCorrespondenceId;
	}

	public String getReceiptInvoiceId() {
		return receiptInvoiceId;
	}

	public void setReceiptInvoiceId(String receiptInvoiceId) {
		this.receiptInvoiceId = receiptInvoiceId;
	}

	public String getReceiptInvoiceKindId() {
		return receiptInvoiceKindId;
	}

	public void setReceiptInvoiceKindId(String receiptInvoiceKindId) {
		this.receiptInvoiceKindId = receiptInvoiceKindId;
	}

	public String getReceiptInvoiceKindDescription() {
		return receiptInvoiceKindDescription;
	}

	public void setReceiptInvoiceKindDescription(String receiptInvoiceKindDescription) {
		this.receiptInvoiceKindDescription = receiptInvoiceKindDescription;
	}

	public Boolean getManner() {
		if (null == this.manner) {
			manner = false;
		    }
		return manner;
	}

	public void setManner(Boolean manner) {
		this.manner = manner;
		if (null == this.manner) {
		      this.manner = false;
		    }
	}

	public String getReceiptInvoiceDescription() {
		return receiptInvoiceDescription;
	}

	public void setReceiptInvoiceDescription(String receiptInvoiceDescription) {
		this.receiptInvoiceDescription = receiptInvoiceDescription;
	}

	public String getSpecify() {
		return specify;
	}

	public void setSpecify(String specify) {
		this.specify = specify;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPtuLooseNumber() {
		return ptuLooseNumber;
	}

	public void setPtuLooseNumber(String ptuLooseNumber) {
		this.ptuLooseNumber = ptuLooseNumber;
	}

	public Long getNoOfBooklets() {
		return noOfBooklets;
	}

	public void setNoOfBooklets(Long noOfBooklets) {
		this.noOfBooklets = noOfBooklets;
	}

	public Long getNoOfSetsPerBooklet() {
		return noOfSetsPerBooklet;
	}

	public void setNoOfSetsPerBooklet(Long noOfSetsPerBooklet) {
		this.noOfSetsPerBooklet = noOfSetsPerBooklet;
	}

	public Long getNoOfCopiesPerSet() {
		return noOfCopiesPerSet;
	}

	public void setNoOfCopiesPerSet(Long noOfCopiesPerSet) {
		this.noOfCopiesPerSet = noOfCopiesPerSet;
	}

	public String getStartingSerialNo() {
		return startingSerialNo;
	}

	public void setStartingSerialNo(String startingSerialNo) {
		this.startingSerialNo = startingSerialNo;
	}

	public String getEndingSerialNo() {
		return endingSerialNo;
	}

	public void setEndingSerialNo(String endingSerialNo) {
		this.endingSerialNo = endingSerialNo;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public InvoiceDetail(String endingSerialNo) {
		super();
		this.endingSerialNo = endingSerialNo;
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
    
}
