package org.jaxss.util;

import org.apache.commons.validator.CreditCardValidator;

/**
 *
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class CBlanche implements CreditCardValidator.CreditCardType {
    private static final String PREFIX = "38";
    public boolean matches(String card) {
        String prefix2 = card.substring(0, 2);
        return (PREFIX.equals (prefix2) && (card.length() == 14));
    }
}
