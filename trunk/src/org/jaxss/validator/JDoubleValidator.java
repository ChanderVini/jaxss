package org.jaxss.validator;

import org.jaxss.exception.ValidationException;

/**
 *
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class JDoubleValidator {
     /**
     * Maximum value allowed.
     */
    private double maxval = Double.MAX_VALUE;
    /**
     * Minimum value allowed.
     */
    private double minval = Double.MIN_VALUE;    
    /**
     * Maximum number of character allowed after decimal
     */
    private int charsafterdecimal = 2;
    
    /**
     * Default constructor for JDoubleValidator.
     * Sets minimum value allowed as Double.MIN_VALUE
     * Sets maximum value allowed as Double.MAX_VALUE
     * Sets characters allowed after decimal as 2
     */
    public JDoubleValidator() {
        this (Double.MAX_VALUE, Double.MIN_VALUE, 2);
    }
    
    /**
     * Constructor for JDoubleValidator allowing user to set minimum and maximum value for double.
     * @param maxval Maximum value for double.
     * @param minval Minimum value for double.
     * @param charsafterdecimal Maximum number of characters allowed after decimal.
     */
    public JDoubleValidator (double maxval, double minval, int charsafterdecimal) {
        this.maxval = maxval;
        this.minval = minval;
        this.charsafterdecimal = charsafterdecimal;
    }
    
    /**
     * Method for validating if data passed conforms as per minimum and maximum value specified.
     * @param data String representation of int to be validated.
     * @throws org.jaxss.exception.ValidationException If data is Null or Blank.
     * @return true if validation passes, else false.
     */
    public boolean validate (String data) throws ValidationException {
        if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        try {
            double doubledata = Double.parseDouble (data);
            if (doubledata > maxval) {
                return false;
            }
            if (doubledata < minval) {
                return false;
            }
            int index = data.indexOf(".");
            String decimalval = data.substring(index + 1);
            System.out.println ("Decimal Value: " + decimalval);
            return (charsafterdecimal == decimalval.length());
        } catch (NumberFormatException nfexp) {
            return false;
        }        
    }
}