package com.smeup.openprovider.commons;

import com.smeup.service.commons.LanguageService;

/**
 * Actually it is a fake one. In future it can be real code or a rest client to a i18n microservice...
 */
public class LanguageManager implements LanguageService {

    private static final long serialVersionUID = 1L;

    public String getTranslation(final String value, final String defaultValue, final Object... params) {
        StringBuilder b = new StringBuilder();
        b.append(value);
        if (params != null && params.length > 0) {
            b.append(" with params: ");
            for (Object arg : params) {
                b.append(arg).append(" ");
            }
        }
        return b.toString().trim();
    }

}
