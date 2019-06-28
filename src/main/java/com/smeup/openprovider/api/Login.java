/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smeup.openprovider.api;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author sant
 */
@Path("AuthenticateService")
@Produces(MediaType.APPLICATION_XML)
@ApplicationScoped
public class Login {
  
    @POST
    public Response login(@FormParam("usr") final String user, @FormParam("pwd") final String password,
            @FormParam("env") final String environment, @FormParam("ccsid") @DefaultValue("1144") final int CCSID) {

        return Response.ok("ok").build();
    }  
}
