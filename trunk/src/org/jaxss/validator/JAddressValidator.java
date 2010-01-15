package org.jaxss.validator;

import com.jaxss.exception.ValidationException;
import org.jaxss.util.Types;

/**
 *
 * @author Chander Singh
 * @created.on December 22, 2007
 */
public class JAddressValidator extends JStringValidator {
    /**
     * Default constructor for JPasswordValidator. 
     * Creates instance of JPasswordValidator with default pattern for password.
     */    
    public JAddressValidator () {
        super ();
        setAllowedchars (getString(Types.ADDRESS_CHARS));
    }
    
    /**
     * Return minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     * @return minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     */
    public int getMinlen() {
        return super.getMinlen ();
    }

    /**
     * Sets minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     * @param minlen minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     */
    public void setMinlen(int minlen) {
        super.setMinlen(minlen);
    }

    /**
     * Returns maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     * @return maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     */
    public int getMaxlen() {
        return super.getMaxlen();
    }

    /**
     * Sets maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     * @param maxlen maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     */
    public void setMaxlen(int maxlen) {
        super.setMaxlen (maxlen);
    }

    /**
     * Returns allowed characters for validating String.
     * @return allowed characters for validating String.
     */
    public String getAllowedchars() {
        return super.getAllowedchars();
    }

    /**
     * Sets allowed characters for validating String.
     * @param allowedchars allowed characters for validating String.
     */
    public void setAllowedchars(String allowedchars) {
        super.setAllowedchars (allowedchars);
    }

    /**
     * Returns Pattern against which String needs to be validated.
     * @return Pattern against which String needs to be validated.
     */
    public String getCharPatterns() {
        return super.getCharPatterns ();
    }

    /**
     * Sets Pattern against which String needs to be validated.
     * @param charPatterns Pattern against which String needs to be validated.
     */
    public void setCharPatterns(String charPatterns) {
        super.setCharPatterns (charPatterns);
    }
    
    /**
     * Validates if String matches for all criteria specified for validating Password.
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    public boolean validate (String data) throws ValidationException {
        return super.validate (data);
    }
}