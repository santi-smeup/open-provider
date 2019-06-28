package com.smeup.openprovider.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smeup.service.SmeupServiceDiscoveryUtils;
import com.smeup.service.dto.converter.SmeupTable2DTOConverter;
import com.smeup.service.dto.table.SmeupTableDTO;

import it.smea.transformer.table.SmeupTable;

@Path("functions")
public class FunctionsApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionsApi.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("service") final String service) {

        LOGGER.info("Getting functions for: {}", service);

        SmeupTable smeupTable = SmeupServiceDiscoveryUtils.discover(service);
        SmeupTableDTO smeupTableDTO = new SmeupTable2DTOConverter().convert(smeupTable);
        return Response.ok(smeupTableDTO).build();

    }

}
