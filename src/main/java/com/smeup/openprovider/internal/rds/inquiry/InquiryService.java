package com.smeup.openprovider.internal.rds.inquiry;

import com.smeup.interpreter.assignments.AssigmentExecutor;
import com.smeup.interpreter.assignments.AssignmentList;
import com.smeup.interpreter.assignments.VariableAssignment;
import com.smeup.openprovider.internal.rds.SessionInfo;
import com.smeup.service.Function;
import com.smeup.service.FunctionDescription;
import com.smeup.service.Service;
import com.smeup.service.ServiceDescription;
import com.smeup.smeup.connector.fun.FUN;
import it.smea.transformer.smeupobj.SmeupObject;
import it.smea.transformer.table.SmeupTable;
import it.smea.transformer.tree.SmeupTreeNode;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.rmi.RemoteException;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author sant
 */
@ServiceDescription("RDS inquiry service")
@Service(value = "RD_00_03")
public class InquiryService implements Serializable{
  private static final long serialVersionUID = 1L;
  
  @Inject
  private SessionInfo sessionInfo;
  
  @FunctionDescription("QOW matrix inquiry provider")
  @Function("QOW")
  public SmeupTable qowInquiryTable(final FUN fun) throws Exception {    
    return getQowInquiryResponse(fun).toTable();
  }
  
  @FunctionDescription("QOW tree inquiry provider")
  @Function("QOW")
  public SmeupTreeNode qowInquiryTree(final FUN fun) throws Exception {    
    return getQowInquiryResponse(fun).toTree();
  }  
  
  private InquiryResponse getQowInquiryResponse(final FUN fun) throws Exception{
    String queryId = getParameter(fun.getSmeupObjs(), 0, true);
    Integer from = getNumericParameter(fun.getSmeupObjs(), 1, false);
    Integer len = getNumericParameter(fun.getSmeupObjs(), 2, false);  
    WebTarget wt = getWebTarget("rest/FUNInquiry/QOW/" + getSessionInfo().getEnv() + "/" + getSessionInfo().getSigla());
    wt = wt.queryParam("queryId", queryId).queryParam("from", from).queryParam("len", len);
    
    if(fun.getInput() != null && !fun.getInput().trim().equals("")){
      AssignmentList list = AssigmentExecutor.compile(fun.getInput());
      for(VariableAssignment va : list.getAssignments()) {
        wt = wt.queryParam(va.getVariableName().getValue(), va.getVariableValue().getValue());
      }   
    }
    Invocation.Builder builder = wt.request(MediaType.APPLICATION_JSON_TYPE);
    if (getSessionInfo() != null && getSessionInfo().getjSessionId() != null && !getSessionInfo().getjSessionId().equals("")) {
      builder = builder.cookie(getSessionInfo().getjSessionIdCookieName(), getSessionInfo().getjSessionId());           
    }      
    Response response = builder.get();
    if(response.getStatus() != HttpURLConnection.HTTP_OK){
      throw new RemoteException(response.readEntity(String.class));
    }
    return response.readEntity(InquiryResponse.class);    
  }
  
  private  WebTarget getWebTarget(String path) throws Exception{
    Client client = ClientBuilder.newClient(); 
    WebTarget wt = client.target(getSessionInfo().getWaRoot()).path(path);
    return wt;
  }  
  
  private Integer getNumericParameter(SmeupObject[] params, int index, boolean mandatory) throws Exception{
    String p = getParameter(params, index, mandatory);
    return p==null || p.equals("")?null:Integer.parseInt(p);    
  }
  
  private String getParameter(SmeupObject[] params, int index, boolean mandatory) throws Exception{
    String value = null;
    if(params != null && params.length > index){
      value = params[index].getCodice();
    }
    if((value == null || value.equals("")) && mandatory){
      throw new Exception("Parametro " + index + " obbligatorio");
    }
    return value;
  }  
  
  public SessionInfo getSessionInfo() {
    return sessionInfo;
  }  

}
