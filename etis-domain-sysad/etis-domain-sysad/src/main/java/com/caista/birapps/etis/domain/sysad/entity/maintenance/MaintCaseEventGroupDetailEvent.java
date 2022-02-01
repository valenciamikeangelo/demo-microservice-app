/*
  * Modified by: obregoj
  * Last updated: Dec 3, 2019 2:32:47 PM
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
@Table(name = "MAIN_CASE_EVENT_GROUP_DETAIL_EVENT")
@JsonInclude(Include.NON_NULL)
public class MaintCaseEventGroupDetailEvent implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "CEGDE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CASE_EVENT_GROUP_DETAIL_EVENT_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "CASE_EVENT_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_CASE_EVENT__MAIN_CASE_EVENT_GROUP_DETAIL_EVENT_01"))
  private MaintCaseEvent caseEvent;

  @Column(name = "SEQUENCE", nullable = false)
  private int sequence;


  public MaintCaseEventGroupDetailEvent() {
    super();
  }

  public MaintCaseEventGroupDetailEvent(MaintCaseEvent caseEvent, int sequence) {
    super();
    this.caseEvent = new MaintCaseEvent(caseEvent.getId(), caseEvent.getCode(),
        caseEvent.getDescription());
    this.sequence = sequence;
  }

  public MaintCaseEvent getCaseEvent() {
    return caseEvent;
  }

  public void setCaseEvent(MaintCaseEvent caseEvent) {
    this.caseEvent = caseEvent;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MaintCaseEventGroupDetailEvent [id=" + id + ", caseEvent=" + caseEvent + ", sequence="
        + sequence + "]";
  }

}
