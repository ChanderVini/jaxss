package org.jaxss.validator;

import org.jaxss.exception.ValidationException;
import org.jaxss.util.Types;

/**
 * Class for validating String representation of time against pattern specified.
 * Allows for user to set it's own time pattern, else uses default pattern defined in the API for validating.
 * @author Chander Singh
 * @created.on December 22, 2007
 */
public class JTimeValidator extends JDateValidator {    
    /**
     * Default constructor for JTimeValidator. 
     * Creates instance of JTimeValidator with default pattern for time.
     */
    public JTimeValidator () {
        this (null);
    }
    
    /**
     * Constructor for JTimeValidator. 
     * Creates instance of JTimeValidator with pattern specified.
     * @param charPatterns Pattern against which String representation of time needs to be validated.
     */
    public JTimeValidator (String charPatterns) {
        super ();
        if (charPatterns == null || "".equals(charPatterns.trim ())) {
            charPatterns = getString(Types.TIME_FORMAT);
        }
        super.setCharPatterns (charPatterns);
    }
               
    /**
     * Return minimum length of String representation of time allowed.
     * 0 implies no restriction on minimum length.
     * @return minimum length of String representation of time allowed.
     * 0 implies no restriction on minimum length.
     */
    public int getMinlen() {
        return super.getMinlen ();
    }

    /**
     * Sets minimum length of String representation of time allowed.
     * 0 implies no restriction on minimum length.
     * @param minlen minimum length of String representation of time allowed.
     * 0 implies no restriction on minimum length.
     */
    public void setMinlen(int minlen) {
        super.setMinlen(minlen);
    }

    /**
     * Returns maximum length of String representation of time allowed.
     * 0 implies no restriction on maximum length.
     * @return maximum length of String representation of time allowed.
     * 0 implies no restriction on maximum length.
     */
    public int getMaxlen() {
        return super.getMaxlen();
    }

    /**
     * Sets maximum length of String representation of time allowed.
     * 0 implies no restriction on maximum length.
     * @param maxlen maximum length of String representation of time allowed.
     * 0 implies no restriction on maximum length.
     */
    public void setMaxlen(int maxlen) {
        super.setMaxlen (maxlen);
    }

    /**
     * Returns allowed characters for validating String representation of time.
     * @return allowed characters for validating String representation of time.
     */
    public String getAllowedchars() {
        return super.getAllowedchars();
    }

    /**
     * Sets allowed characters for validating String representation of time.
     * @param allowedchars allowed characters for validating String representation of time.
     */
    public void setAllowedchars(String allowedchars) {
        super.setAllowedchars (allowedchars);
    }

    /**
     * Returns Pattern against which String representation of time needs to be validated.
     * @return Pattern against which String representation of time needs to be validated.
     */
    public String getCharPatterns() {
        return super.getCharPatterns ();
    }

    /**
     * Sets Pattern against which String representation of time needs to be validated.
     * @param charPatterns against which String representation of time needs to be validated.
     */
    public void setCharPatterns(String charPatterns) {
        super.setCharPatterns (charPatterns);
    }
    
    /**
     * Method for validating if data passed conforms as per pattern specified for time validation. 
     * <br>
     * It checks for characters allowed in String representation of time specified.
     * @param data String representation of time to be validated.
     * @return true if validation passes, else false.
     * @throws org.jaxss.exception.ValidationException If data is Null or Blank.
     */
    public boolean validate (String data) throws ValidationException {
        return super.validate(data);
    }
}