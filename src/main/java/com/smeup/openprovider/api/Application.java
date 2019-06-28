package com.smeup.openprovider.api;

import com.smeup.openprovider.api.utils.FBKDTOMessageBodyProvider;
import com.smeup.openprovider.api.utils.InputPanelResponseDTOMessageBodyProvider;
import com.smeup.openprovider.api.utils.SmeupTableDTOMessageBodyProvider;
import com.smeup.openprovider.api.utils.SmeupTreeNodeDTOMessageBodyProvider;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;



@ApplicationPath(Application.API_CONTEXT)
public class Application extends javax.ws.rs.core.Application {

    public static final String API_CONTEXT = "";

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<Class<?>>();

        resources.add(FunApi.class);
        resources.add(FunctionsApi.class);
        resources.add(Login.class);
        resources.add(TestService.class);
        resources.add(SmeupTableDTOMessageBodyProvider.class);
        resources.add(SmeupTreeNodeDTOMessageBodyProvider.class);
        resources.add(InputPanelResponseDTOMessageBodyProvider.class);
        resources.add(FBKDTOMessageBodyProvider.class);

        return resources;
    }
}
