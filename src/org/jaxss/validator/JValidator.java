package org.jaxss.validator;

import com.jaxss.exception.ValidationException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Super class of the API, all validators in API extend this class.
 * Provides various methods and common functionalities which needs to be implemented for all validation classes.
 * @author Chander Singh
 * @created.on December 4, 2007
 */
public class JValidator {
    private static ResourceBundle bundle = null;
    private int minlen = 0;
    private int maxlen = 0;
    private String allowedchars = null;
    private String charPatterns = null;
    
    /**
     * Returns value for the key specified from ResourceBundle using default Locale.
     * @param key Key for which value needs to be extracted from ResourceBundle.
     * @return Value for the key specified.
     */
    protected String getString (String key) {
        return getString (key, null);
    }
    
    /**
     * Returns values for the key specified from ResourceBundle using default Locale as String[].
     * @param key Key for which values needs to be extracted from ResourceBundle.
     * @return Values for the key specified using default Locale as String[].
     */
    protected String[] getStringArray (String key) {
        return getStringArray (key, null);
    }
    
    /**
     * Returns value for the key specified from ResourceBundle using Locale specified.
     * @param key Key for which value needs to be extracted from ResourceBundle.
     * @param locale Locale for which resourceBundle needs to be queried for Value against Key.
     * @return Value for the key specified.
     */
    protected String getString (String key, Locale locale) {
        if (locale != null) {
            if (bundle == null) {
                bundle = ResourceBundle.getBundle("jaxss", locale);
            }
        } else {
            if (bundle == null) {
                bundle = ResourceBundle.getBundle("jaxss");
            }
        }
        if (key != null) {
            return bundle.getString(key);
        }
        return null;
    }
    
    /**
     * Returns value for the key specified from ResourceBundle using Locale specified as String[].
     * @param key Key for which values needs to be extracted from ResourceBundle.
     * @param locale Locale for which resourceBundle needs to be queried for Values against Key.
     * @return Values for the key specified as String[].
     */
    protected String[] getStringArray (String key, Locale locale) {
        if (locale != null) {
            if (bundle == null) {
                bundle = ResourceBundle.getBundle("jaxss", locale);
            }
        } else {
            if (bundle == null) {
                bundle = ResourceBundle.getBundle("jaxss");
            }
        }
        if (key != null) {
            return bundle.getStringArray(key);
        }
        return null;
    }    

    /**
     * Return minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     * @return minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     */
    protected int getMinlen() {
        return minlen;
    }

    /**
     * Sets minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     * @param minlen minimum length of String allowed.
     * 0 implies no restriction on minimum length.
     */
    protected void setMinlen(int minlen) {
        this.minlen = minlen;
    }

    /**
     * Returns maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     * @return maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     */
    protected int getMaxlen() {
        return maxlen;
    }

    /**
     * Sets maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     * @param maxlen maximum length of String allowed.
     * 0 implies no restriction on maximum length.
     */
    protected void setMaxlen(int maxlen) {
        this.maxlen = maxlen;
    }

    /**
     * Returns allowed characters for validating String.
     * @return allowed characters for validating String.
     */
    protected String getAllowedchars() {
        return allowedchars;
    }

    /**
     * Sets allowed characters for validating String.
     * @param allowedchars allowed characters for validating String.
     */
    protected void setAllowedchars(String allowedchars) {
        this.allowedchars = allowedchars;
    }

    /**
     * Returns Pattern against which String needs to be validated.
     * @return Pattern against which String needs to be validated.
     */
    protected String getCharPatterns() {
        return charPatterns;
    }

    /**
     * Sets Pattern against which String needs to be validated.
     * @param charPatterns Pattern against which String needs to be validated.
     */
    protected void setCharPatterns(String charPatterns) {
        this.charPatterns = charPatterns;
    }
 
    /**
     * Validates if length of String is not greater than specified maxlen.
     * If maxlen specified is 0 skips validation.
     * If data is null or blank ValidationException is thrown.
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    protected boolean validateMaxlen (String data) throws ValidationException {
        if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }
        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        if (maxlen != 0) {
            if (data.length() > maxlen) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Validates if length of String is not less than specified minlen.
     * If minlen specified is 0 skips validation.
     * If data is null or blank ValidationException is thrown.
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    protected boolean validateMinlen (String data) throws ValidationException {
        if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }
        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        if (minlen != 0) {
            if (data.length() < minlen) {
                return false;
            } 
        }
        return true;
    }
    
    /**
     * Validates if String is having characters other than allowed characters.
     * If data is null or blank ValidationException is thrown.
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    protected boolean validateAllowedchars (String data) throws ValidationException {
        if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }
        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        if (allowedchars == null || "".equals(allowedchars.trim())) {
            return true;
        }
        System.out.println ("Allowed Chars: " + allowedchars);
        
        CharSequence charSeq = data.subSequence(0, data.length());       
        Pattern pattern = Pattern.compile(allowedchars);
        Matcher matcher = pattern.matcher(charSeq);                
                
        return !matcher.find();
    }
    
    /**
     * Validates if String is following character pattern specified
     * If data is null or blank ValidationException is thrown.
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    protected boolean validateCharpatterns (String data) throws ValidationException {
        if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }
        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        CharSequence charSeq = data.subSequence(0, data.length()); 
        if (charPatterns != null && !"".equals(charPatterns)) {
            Pattern pattern = Pattern.compile(charPatterns);
            Matcher matcher = pattern.matcher (charSeq);
            return matcher.find();
        } 
        return true;
    }
    
    /**
     * Utilizes all validate methods specified in the class to validate String
     * @param data String to be validated
     * @return true if validation passes, else false.
     * @throws com.jaxss.exception.ValidationException If data is null or blank ValidationException is thrown.
     */
    protected boolean validateCommon (String data) throws ValidationException {
        boolean isValid = validateMinlen(data);        
        System.out.println ("Validate Min length: " + isValid);
        
        if (!isValid) {
            return isValid;
        }
        
        isValid = validateMaxlen(data);
        System.out.println ("Validate Max length: " + isValid);
        if (!isValid){
            return isValid;
        }
        
        isValid = validateAllowedchars(data);
        System.out.println ("Validate Allowed Characters: " + isValid);
        if (!isValid){
            return isValid;
        }
        
        isValid = validateCharpatterns(data);
        System.out.println ("Validate Character Pattern: " + isValid);
        return isValid;
    }
}