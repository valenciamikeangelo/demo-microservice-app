/*
 * Last modified by: feliped
 * Last updated date: Oct 25, 2019 8:32:52 AM
 */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_CASE_TYPE_CASE_EVENT_DETAIL")
@JsonInclude(Include.NON_NULL)
public class MaintCaseTypeCaseEventDetail implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "MCTCD", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CASE_TYPE_CASE_EVENT_DETAIL_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @OneToOne
  @JoinColumn(name = "CASE_EVENT_GROUP_DETAIL_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MN_CS_TYP_CS_EVNT_DTL__MAIN_CS_EVNT_GRP_DTL_01"))
  private MaintCaseEventGroupDetail maintCaseEventGroupDetail;

  @Column(name = "CASE_TYPE_CASE_EVENT_DETAIL_SEQUENCE", nullable = false)
  private int sequence;

  public MaintCaseTypeCaseEventDetail() {
    super();

  }

  public MaintCaseTypeCaseEventDetail(String id,
      MaintCaseEventGroupDetail maintCaseEventGroupDetail, int sequence) {
    super();
    this.id = id;
    this.maintCaseEventGroupDetail = maintCaseEventGroupDetail;
    this.sequence = sequence;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MaintCaseEventGroupDetail getMaintCaseEventGroupDetail() {
    return maintCaseEventGroupDetail;
  }

  public void setMaintCaseEventGroupDetail(MaintCaseEventGroupDetail maintCaseEventGroupDetail) {
    this.maintCaseEventGroupDetail = maintCaseEventGroupDetail;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  @Override
  public String toString() {
    return "MaintCaseTypeCaseEventDetail [id=" + id + ", maintCaseEventGroupDetail="
        + maintCaseEventGroupDetail + ", sequence=" + sequence + "]";
  }

}
