/*
 * Modified by: fuentem
 * Last updated: Oct 12, 2018 11:06:47 AM
 */
package com.caista.birapps.etis.domain.tas;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * The Class Ledger.
 */
@Entity
@Table(name = "TAXPAYER_LEDGER",
    uniqueConstraints = @UniqueConstraint(columnNames = "LEDGER_ID", name = "TAXPAYER_ID"))
public class Ledger {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @Column(name = "LEDGER_ID")
  private String id;

  // @OneToOne
  // @JoinColumn(name = "LEDGER_TP_ID", nullable = false, referencedColumnName = "TAXPAYER_ID",
  // foreignKey = @ForeignKey(name = "FK_LEDGER_REF_TAXPAYER"))
  private TASTaxPayer taxPayer;

  public Ledger() {
    this.id = UUID.randomUUID().toString();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TASTaxPayer getTaxPayer() {
    return taxPayer;
  }

  public void setTaxPayer(TASTaxPayer taxPayer) {
    this.taxPayer = taxPayer;
  }

}
