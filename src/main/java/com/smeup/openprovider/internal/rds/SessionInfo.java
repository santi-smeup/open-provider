/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.openprovider.internal.rds;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author pase
 */
@SessionScoped
public class SessionInfo implements Serializable {

  private static final long serialVersionUID = 1L;
  
  private final static String WAROOT = "waRoot";
  private String waRoot;
  private final static String ENVIRONMENT = "environment";
  private String env;
  private final static String SIGLA = "sigla";
  private String sigla;
  private String user;
  private String userRds;
  private String lang; 

  private String jSessionId;
  private String jSessionIdCookieName;


  public SessionInfo() {
  }

  @PostConstruct
  public void configure() {
    try {
      loadProperties();
      setLang("ITA");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private void loadProperties() throws Exception {
  
  }    

  public String getWaRoot() {
    return waRoot;
  }

  public void setWaRoot(String waRoot) {
    this.waRoot = waRoot;
  }

  public String getEnv() {
    return env;
  }

  public void setEnv(String env) {
    this.env = env;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public String getLang() {
    return lang;
  }

  public void setLang(String lang) {
    this.lang = lang;
  }

  public String getjSessionId() {
    return jSessionId;
  }

  public void setjSessionId(String jSessionId) {
    this.jSessionId = jSessionId;
  }

  public String getjSessionIdCookieName() {
    return jSessionIdCookieName;
  }

  public void setjSessionIdCookieName(String jSessionIdCookieName) {
    this.jSessionIdCookieName = jSessionIdCookieName;
  }

  @PreDestroy
  public void destroy() {
  }

  public String getUserRds() {
    return userRds;
  }

  public void setUserRds(String userRds) {
    this.userRds = userRds.replaceAll("_", "");
  }
}
