package org.jaxss.validator;

import org.jaxss.exception.ValidationException;

/**
 * Class for validating String representation of long.
 * Allows for user to set maximum and minimum value allowed for long, else Long.MIN_VALUE and Long.MAX_VALUE is used.
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class JLongValidator {
    /**
     * Maximum value allowed.
     */
    private long maxval = Long.MAX_VALUE;
    /**
     * Minimum value allowed.
     */
    private long minval = Long.MIN_VALUE;
    
    /**
     * Default constructor for JLongValidator.
     * Sets minimum value allowed as Long.MIN_VALUE
     * Sets maximum value allowed as Long.MAX_VALUE
     */
    public JLongValidator() {
        this (Long.MAX_VALUE, Long.MIN_VALUE);
    }
    
    /**
     * Constructor for JLongValidator allowing user to set minimum and maximum value for long.
     * @param maxval Maximum value for long.
     * @param minval Minimum value for long.
     */
    public JLongValidator (long maxval, long minval) {
        this.maxval = maxval;
        this.minval = minval;
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
            long longdata = Long.parseLong (data);
            if (longdata > maxval) {
                return false;
            }
            if (longdata < minval) {
                return false;
            }
            return true;
        } catch (NumberFormatException nfexp) {
            return false;
        }        
    }
}