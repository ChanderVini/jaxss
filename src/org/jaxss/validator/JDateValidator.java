package org.jaxss.validator;

import com.jaxss.exception.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.jaxss.util.Types;

/**
 * Class for validating String representation of date against pattern specified.
 * Allows for user to set it's own date pattern, else uses default pattern defined in the API for validating.
 * @author Chander Singh
 * @created.on December 21, 2007
 */
public class JDateValidator extends JValidator {       
    /**
     * Default constructor for JDateValidator. 
     * Creates instance of JDateValidator with default pattern for date.
     */
    public JDateValidator () {
        this (null);
    }
    
    /**
     *  Constructor for JDateValidator. 
     * Creates instance of JDateValidator with pattern specified.
     * @param charPatterns Pattern against which String representation of date needs to be validated.
     */
    public JDateValidator (String charPatterns) {
        super ();
        if (charPatterns == null || "".equals(charPatterns.trim())) {
            charPatterns = getString (Types.DATE_FORMAT);
        } 
        super.setCharPatterns (charPatterns);
    }    
    
    /**
     * Return minimum length of String representation of date allowed.
     * 0 implies no restriction on minimum length.
     * @return minimum length of String representation of date allowed.
     * 0 implies no restriction on minimum length.
     */
    public int getMinlen() {
        return super.getMinlen ();
    }

    /**
     * Sets minimum length of String representation of date allowed.
     * 0 implies no restriction on minimum length.
     * @param minlen minimum length of String representation of date allowed.
     * 0 implies no restriction on minimum length.
     */
    public void setMinlen(int minlen) {
        super.setMinlen(minlen);
    }

    /**
     * Returns maximum length of String representation of date allowed.
     * 0 implies no restriction on maximum length.
     * @return maximum length of String representation of date allowed.
     * 0 implies no restriction on maximum length.
     */
    public int getMaxlen() {
        return super.getMaxlen();
    }

    /**
     * Sets maximum length of String representation of date allowed.
     * 0 implies no restriction on maximum length.
     * @param maxlen maximum length of String representation of date allowed.
     * 0 implies no restriction on maximum length.
     */
    public void setMaxlen(int maxlen) {
        super.setMaxlen (maxlen);
    }

    /**
     * Returns allowed characters for validating String representation of date.
     * @return allowed characters for validating String representation of date.
     */
    public String getAllowedchars() {
        return super.getAllowedchars();
    }

    /**
     * Sets allowed characters for validating String representation of date.
     * @param allowedchars allowed characters for validating String representation of date.
     */
    public void setAllowedchars(String allowedchars) {
        super.setAllowedchars (allowedchars);
    }

    /**
     * Returns Pattern against which String representation of date needs to be validated.
     * @return Pattern against which String representation of date needs to be validated.
     */
    public String getCharPatterns() {
        return super.getCharPatterns ();
    }

    /**
     * Sets Pattern against which String representation of date needs to be validated.
     * @param charPatterns against which String representation of date needs to be validated.
     */
    public void setCharPatterns(String charPatterns) {
        super.setCharPatterns (charPatterns);
    }
    
    /**
     * Method for validating if data passed conforms as per charPatterns specified for date validation. 
     * <br>
     * It checks for characters allowed in String representation of date specified.
     * @param data String representation of date to be validated.
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
            SimpleDateFormat sdf = new SimpleDateFormat (super.getCharPatterns());
            sdf.setLenient(false);
            sdf.parse(data);
        } catch (ParseException pexp) {
            return false;
        }
        return true;
    }
}