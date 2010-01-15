package org.jaxss.util;

import org.apache.commons.validator.CreditCardValidator;

/**
 *
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class JCB implements CreditCardValidator.CreditCardType {
    private static final String PREFIX1 = "3";
    private static final String PREFIX2 = "2131,1800,";
    public boolean matches(String card) {
        String prefix1 = card.substring(0, 1);
        String prefix2 = card.substring(0, 4) + ",";
        return ((PREFIX1.equals (prefix1) && (card.length() == 16)) || ((PREFIX2.indexOf(prefix2) != -1) && (card.length() == 15)));
    }
}
