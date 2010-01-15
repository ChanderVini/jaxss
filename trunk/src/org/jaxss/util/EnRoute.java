/*
 * EnRoute.java
 */

package org.jaxss.util;

import org.apache.commons.validator.CreditCardValidator;

/**
 *
 * @author Chander Singh
 * @created.on November 16, 2007
 */
public class EnRoute implements CreditCardValidator.CreditCardType {
    private static final String PREFIX = "2014, 2149";
    public boolean matches(String card) {
        String prefix4 = card.substring(0, 4) + ",";
        return ((PREFIX.indexOf(prefix4) != -1 && card.length() == 15));
    }
}
