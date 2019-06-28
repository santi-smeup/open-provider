/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.openprovider.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author sant
 */
@Path("test")
public class TestService {
  
  @GET
  @Path("helloworld/{pathParam}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)  
  public String helloWorld(@PathParam("pathParam") String myPathParam, @QueryParam("myParam") String myParam) throws Exception{
    return myPathParam + myParam;
  }
  
  @GET
  @Path("helloworld2")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)  
  public String helloWorld2() throws Exception{
    return "Hello world2";
  }  
  
  @GET
  @Path("helloworld3")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)  
  public String helloWorld3() throws Exception{
    return "Hello world3";
  }    
}
