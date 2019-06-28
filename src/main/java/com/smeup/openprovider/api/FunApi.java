package com.smeup.openprovider.api;

import com.smeup.openprovider.service.FunService;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Path("fun")
@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
@Produces(MediaType.APPLICATION_JSON)
public class FunApi {

    private static final String FUN_PARAM = "fun";

    private final static Logger LOGGER = LoggerFactory.getLogger(FunApi.class);

    @Inject
    private FunService funService;

    @POST
    public Response invoke(@FormParam(FUN_PARAM) final String fun) {

        LOGGER.info("Fun called: " + fun);

        try {
            return Response.ok(funService.exec(fun)).build();
        } catch (Throwable exc) {
            LOGGER.error("Error calling fun: ", exc);
            throw new WebApplicationException(Response.serverError().entity(exc.getMessage()).build());
        }

    }

}
