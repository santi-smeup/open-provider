package com.smeup.openprovider.api.utils;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import com.smeup.service.dto.tree.SmeupTreeNodeDTO;

// Necessary in a java ee 7 container -> in a java ee 8 container can be removed
@Consumes(MediaType.APPLICATION_JSON)
public class SmeupTreeNodeDTOMessageBodyProvider extends SmeupDataStructureDTOMessageBodyProvider<SmeupTreeNodeDTO> {

    @Override
    public Class<SmeupTreeNodeDTO> getDTOClass() {
        return SmeupTreeNodeDTO.class;
    }

}