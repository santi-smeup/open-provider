package com.smeup.openprovider.commons;

import java.util.HashMap;
import java.util.Map;

import com.smeup.service.commons.ConfigService;

public class ConfigManager implements ConfigService {

    private static final long serialVersionUID = 1L;

    public Map<String, String> getCodeMap() {
        Map<String, String> codeMap = new HashMap<String, String>();
        codeMap.put("PROVIDER_PATHS",
                "WIN([*TMP]) LIN([*TMP]) | WIN([*APPDATA]) LIN([*APPDATA]) | WIN(\\\\srv005.smeup.com\\smeup\\Test) LIN(/mnt/srv005/smeup/Test");
        return codeMap;
    }

    public String getConfigValue(final String code, final String defaultValue, final Object... params) {
        return getCodeMap().get(code);
    }

}
