package com.smeup.openprovider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.smeup.service.BasicSmeupServiceGateway;
import com.smeup.service.dto.SmeupDataStructureDTO;
import com.smeup.service.dto.converter.FBK2DTOConverter;
import com.smeup.service.dto.converter.InputPanelResponse2DTOConverter;
import com.smeup.service.dto.converter.SmeupTable2DTOConverter;
import com.smeup.service.dto.converter.SmeupTreeNode2DTOConverter;

import it.smea.transformer.FunComponentType;
import it.smea.transformer.SmeupDataStructure;
import it.smea.transformer.fbk.FBKData;
import it.smea.transformer.table.SmeupTable;
import it.smea.transformer.table.form.InputPanelResponse;
import it.smea.transformer.tree.SmeupTreeNode;

@ApplicationScoped
public class FunService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FunService.class.getName());

    @Inject
    BasicSmeupServiceGateway basicSmeupServiceGateway;

    public SmeupDataStructureDTO exec(final String fun) {

        // extract datastructure from fun
        // FunComponentType funComponentType = FunComponentType.getFromCGrInFun(fun);
        // if (funComponentType == null || funComponentType.getDataStructure() == null) {
        // funComponentType = FunComponentType.getFromProgrammaInFun(fun);
        // }
        FunComponentType funComponentType = FunComponentType.getFromProgrammaInFun(fun);

        // exec fun
        SmeupDataStructure smeupDataStructure = null;
        if (funComponentType != null && funComponentType.getDataStructure() != null) {
            Class<?> dataStructureClass = funComponentType.getDataStructure().getRawType();
            if (SmeupTable.class.equals(dataStructureClass)) {
                smeupDataStructure = basicSmeupServiceGateway.get(SmeupTable.class, fun);
            } else if (SmeupTreeNode.class.equals(dataStructureClass)) {
                smeupDataStructure = basicSmeupServiceGateway.get(SmeupTreeNode.class, fun);
            } else if (FBKData.class.equals(dataStructureClass)) {
                smeupDataStructure = basicSmeupServiceGateway.get(FBKData.class, fun);
            } else if (InputPanelResponse.class.equals(dataStructureClass)) {
                smeupDataStructure = basicSmeupServiceGateway.get(InputPanelResponse.class, fun);
            } else {
                throw new RuntimeException("This datastructure isn't managed yet: " + dataStructureClass);
            }
        } else {
            // actually expected return data structure in fun.. to wide boundaries you can do something like: smeupDataStructure = smeupServiceUtils.get(null, fun);
            throw new RuntimeException("Invalid data structure when calling fun: " + fun);
        }

        // convert entity
        SmeupDataStructureDTO smeupDataStructureDTO = null;
        if (smeupDataStructure instanceof SmeupTable) {
            smeupDataStructureDTO = new SmeupTable2DTOConverter().convert((SmeupTable) smeupDataStructure);
        } else if (smeupDataStructure instanceof SmeupTreeNode) {
            smeupDataStructureDTO = new SmeupTreeNode2DTOConverter().convert((SmeupTreeNode) smeupDataStructure);
        } else if (smeupDataStructure instanceof FBKData) {
            smeupDataStructureDTO = new FBK2DTOConverter().convert((FBKData) smeupDataStructure);
        } else if (smeupDataStructure instanceof InputPanelResponse) {
            smeupDataStructureDTO = new InputPanelResponse2DTOConverter()
                    .convert((InputPanelResponse) smeupDataStructure);
        }
        LOGGER.info("SmeupDataStructureDTO: " + smeupDataStructureDTO);

        return smeupDataStructureDTO;
    }

}
