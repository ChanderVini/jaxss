package org.jaxss.validator;

import org.apache.commons.validator.CreditCardValidator;
import org.jaxss.exception.ValidationException;
import org.jaxss.util.CBlanche;
import org.jaxss.util.Diner;
import org.jaxss.util.EnRoute;
import org.jaxss.util.JCB;

/**
 *
 * @author Chander Singh
 * @created.on December 24, 2007
 */
public class JCreditCardValidator extends JValidator {
    /**
     * Option specifying that American Express cards are allowed.
     */
    public static final int AMEX = 1;
    /**
     * Option specifying that Visa cards are allowed.
     */
    public static final int VISA = 2;
    /**
     * Option specifying that Mastercard cards are allowed.
     */
    public static final int MASTERCARD = 3;
    
    /**
     * Option specifying that Discover cards are allowed.
     */    
    public static final int DISCOVER = 4;
    /**
     * Option specifying that Carte Blanche cards are allowed.
     */
    public static final int CBLANCHE = 5;
    /**
     * Option specifying that EnRoute cards are allowed.
     */    
    public static final int ENROUTE = 6;
    /**
     * Option specifying that JCB cards are allowed.
     */    
    public static final int JCB = 7;
    /**
     * Option specifying that Diner Club cards are allowed.
     */    
    public static final int DINER = 8;
    
    /**
     * Credit card type for which validation needs to be performed.
     */
    private int ccType = 0;
    
    /**
     * Constructor for JCreditCardValidator.
     * Allows user to specify the type of Credit card.
     * @param ccType type of Credit Card.
     */
    public JCreditCardValidator (int ccType) {
        this.ccType = ccType;
    }
    
    /**
     * Validates if card number is valid for the Credit card type.
     * @param ccNbr Card Number to be validated
     * @throws org.jaxss.exception.ValidationException If data is Null or Blank or Credit Card Type is invalid.
     * @return true if validation passes, else false.
     */
    public boolean validate (String ccNbr) throws ValidationException {        
         if (ccNbr == null) {
            throw new ValidationException ("Data to be validated is null");
        }        
        if ("".equals(ccNbr.trim())) {
            throw new ValidationException ("Data to be validated is blank");
        }
        CreditCardValidator creditCardValidator = null;
        if (ccType == AMEX) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.AMEX);
        } else if (ccType == VISA) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.VISA);
        } else if (ccType == MASTERCARD) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.MASTERCARD);
        } else if (ccType == ENROUTE) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.NONE);
            creditCardValidator.addAllowedCardType(new EnRoute ());
        } else if (ccType == DINER) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.NONE);
            creditCardValidator.addAllowedCardType(new Diner ());
        } else if (ccType == DISCOVER) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.DISCOVER);
        } else if (ccType == CBLANCHE) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.NONE);
            creditCardValidator.addAllowedCardType(new CBlanche ());
        } else if (ccType == JCB) {
            creditCardValidator = new CreditCardValidator (CreditCardValidator.NONE);
            creditCardValidator.addAllowedCardType(new JCB ());
        } else {
            throw new ValidationException ("Credit card type is invalid.");
        }
        return creditCardValidator.isValid(ccNbr);
    }
}