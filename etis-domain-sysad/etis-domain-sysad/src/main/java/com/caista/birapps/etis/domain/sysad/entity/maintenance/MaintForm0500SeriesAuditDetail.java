/*
  * Modified by: santosj
  * Last updated: Oct 8, 2019 12:20:58 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceForm0500SeriesAuditBasis;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_FORM_0500_SERIES_AUDIT_DETAIL")
@JsonInclude(Include.NON_NULL)
public class MaintForm0500SeriesAuditDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "MF0AD", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "FORM_0500_AUDIT_DETAIL_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "FORM_0500_SERIES_AUDIT_BASIS_ID", nullable = false, foreignKey = @ForeignKey(
      name = "FK_REF_FORM_0500_SERIES_AUDIT_BASIS__MAIN_FORM_0500_SERIES_AUDIT_DETAIL_01"))
  private ReferenceForm0500SeriesAuditBasis formAuditBasis;


  public MaintForm0500SeriesAuditDetail() {
    super();
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ReferenceForm0500SeriesAuditBasis getFormAuditBasis() {
    return formAuditBasis;
  }

  public void setFormAuditBasis(ReferenceForm0500SeriesAuditBasis formAuditBasis) {
    this.formAuditBasis = formAuditBasis;
  }


}
