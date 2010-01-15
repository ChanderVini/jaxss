package org.jaxss.validator;

import com.jaxss.exception.ValidationException;

/**
 * Class for validating String representation of int.
 * Allows for user to set maximum and minimum value allowed for int, else Integer.MIN_VALUE and Integer.MAX_VALUE is used.
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class JIntegerValidator {
    /**
     * Maximum value allowed.
     */
    private int maxval = Integer.MAX_VALUE;
    /**
     * Minimum value allowed.
     */
    private int minval = Integer.MIN_VALUE;
    
    /**
     * Default constructor for JIntegerValidator.
     * Sets minimum value allowed as Integer.MIN_VALUE
     * Sets maximum value allowed as Integer.MAX_VALUE
     */
    public JIntegerValidator() {
        this (Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    /**
     * Constructor for JIntegerValidator allowing user to set minimum and maximum value for int.
     * @param maxval Maximum value for int.
     * @param minval Minimum value for int.
     */
    public JIntegerValidator (int maxval, int minval) {
        this.maxval = maxval;
        this.minval = minval;
    }
    
    /**
     * Method for validating if data passed conforms as per minimum and maximum value specified.
     * @param data String representation of int to be validated.
     * @throws com.jaxss.exception.ValidationException If data is Null or Blank.
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
            int intdata = Integer.parseInt(data);
            if (intdata > maxval) {
                return false;
            }
            if (intdata < minval) {
                return false;
            }
            return true;
        } catch (NumberFormatException nfexp) {
            return false;
        }        
    }
}
