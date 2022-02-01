/*
  * Modified by: sarmier
  * Last updated: Jul 18, 2019 10:59:14 AM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import javax.persistence.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;

@Entity
@Table(name = "MAIN_TASK_EMAIL_GROUP_MEMBER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"USERNAME"}, name = "UC_MAIN_TASK_EMAIL_GROUP_MEMBER_01")})
public class MaintTaskEmailGroupMember implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1514136526574776706L;

  @Id
  @IrisIdGenerator(name = "EMAILGROUPMEMBER", type = GeneratorTypeEnum.NUMBERS_ONLY,
      uniqueProperty = "none")
  @Column(name = "TASK_EMAIL_GROUP_MEMBER_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "USERNAME", columnDefinition = "VARCHAR2(50)", nullable = false)
  private String username;

  @Column(name = "EMAIL", columnDefinition = "VARCHAR2(50)", nullable = false)
  private String email;

  @ManyToOne
  @JoinColumn(name = "TASK_EMAIL_GROUP_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_TASK_EMAIL_GROUP__MAIN_TASK_EMAIL_GROUP_MEMBER_01"))
  private MaintTaskEmailGroup emailGroup;



  public MaintTaskEmailGroupMember() {
    super();
  }


  public MaintTaskEmailGroupMember(String id, String username, String email,
      MaintTaskEmailGroup emailGroup) {
    super();
    this.id = id;
    this.username = username;
    this.email = email;
    this.emailGroup = emailGroup;
  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public MaintTaskEmailGroup getEmailGroup() {
    return emailGroup;
  }

  public void setEmailGroup(MaintTaskEmailGroup emailGroup) {
    this.emailGroup = emailGroup;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintTaskEmailGroupMember [id=" + id + ", username=" + username + ", email=" + email
        + ", emailGroup=" + emailGroup + "]";
  }



}
