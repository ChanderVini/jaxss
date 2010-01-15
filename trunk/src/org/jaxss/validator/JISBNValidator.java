package org.jaxss.validator;

import com.jaxss.exception.ValidationException;
import org.apache.commons.validator.ISBNValidator;
import org.jaxss.util.Types;

/**
 *
 * @author Chander Singh
 * @created.on December 23, 2007
 */
public class JISBNValidator extends JValidator { 
    private String isbnType = null;
    
    /**
     * Default constructor for JISBNValidator. 
     * Creates instance of JISBNValidator with default pattern for ISBN10, ISBN13 support will be available in version 2.
     */
    public JISBNValidator() {
        this (null);
    }
    
    /**
     * Constructor for JISBNValidator.
     * Allows user to specify the type of ISBN.
     * @param isbnType  type of ISBN (ISBN10/ISBN13)
     */
    public JISBNValidator (String isbnType) {
        if (isbnType == null || "".equals(isbnType.trim())) {
            isbnType = Types.ISBN10;
        }
        this.isbnType = isbnType;
    }
    
    /**
     * Validates if ISBN is valid for the ISBN Type.
     * @param data ISBN to be validated
     * @throws com.jaxss.exception.ValidationException If data is Null or Blank or ISBN Type is invalid.
     * @return true if validation passes, else false.
     */
    public boolean validate (String data) throws ValidationException {
         if (data == null) {
            throw new ValidationException ("Data to be validated is null");
        }        
        if ("".equals(data.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        if (Types.ISBN10.equals(isbnType)) {
            ISBNValidator isbnValidator = new ISBNValidator ();
            return isbnValidator.isValid(data);
        } else if (Types.ISBN13.equals (isbnType)) {
            return (getTotal(data) % 10 == 0);
        }else {
            throw new ValidationException ("ISBN type is invalid.");
        }
    }
 
    /**
     * 
     * @param data 
     * @return 
     */
    private int getTotal (String data) {
        String cleandata = clean (data);
        int total = 0;
        for (int cnt = 1; cnt <= 12; cnt++) {
            int weight = 1;
            if (cnt % 2 == 0) {
                weight = 3;
            }
            total += (weight * Character.getNumericValue (cleandata.charAt(cnt - 1)));
        }
        total += Character.getNumericValue (cleandata.charAt(12)); 
        return total;
    }
    
    /**
     * 
     * @param isbn 
     * @return 
     */
    private String clean(String isbn) {
        StringBuffer buf = new StringBuffer(13);
        
        for (int i = 0; i < isbn.length(); i++) {
            char digit = isbn.charAt(i);
            if (Character.isDigit(digit)) {
                buf.append(digit);
            }
        }

        return buf.toString();
    }
}