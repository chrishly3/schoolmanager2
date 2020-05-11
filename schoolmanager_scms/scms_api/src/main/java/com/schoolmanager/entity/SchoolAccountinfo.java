package com.schoolmanager.entity;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;

@EntityScan
@Entity
public class SchoolAccountinfo {

  @Id
  private long id;
  private String account;
  private String pwd;
  private String userId;
  private String token;
  private java.sql.Timestamp tokentime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }


  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  public java.sql.Timestamp getTokentime() {
    return tokentime;
  }

  public void setTokentime(java.sql.Timestamp tokentime) {
    this.tokentime = tokentime;
  }

}
