package org.jaxss.exception;

/**
 * Provides information on error occured while performing validation.
 * @author Chander Singh
 * @created.on December 21, 2007
 */
public class ValidationException extends Exception {    
    private static final long serialVersionUID = 5231398326110927835L;

	/**
     * Constructs a new exception with the specified detail message. 
     * <br>The cause is not initialized, and may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable). 
     * @param message The detail message. 
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public ValidationException (String message) {
        super (message);
    }
    
    /**
     * Constructs a new exception with the specified detail message and cause. 
     * Note that the detail message associated with cause is not automatically incorporated in this exception's detail message. 
     * @param message The detail message. 
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method). (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ValidationException (String message, Throwable cause) {
        super (message, cause);
    }
}
