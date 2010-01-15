package com.jaxss.exception;

import com.jaxss.error.JXSSError;
import java.util.ArrayList;

/**
 *
 * @author Chander Singh
 * @created.on December 27, 2007
 */
public class JXSSFilterException extends Exception {
    private static final long serialVersionUID = -8306354826663194196L;
	private ArrayList<JXSSError> jxssErrorList = new ArrayList<JXSSError> (10);
    
    /**
     * Constructs a new exception with the specified detail message. 
     * <br>The cause is not initialized, and may subsequently be initialized by a call to Throwable.initCause(java.lang.Throwable). 
     * @param message The detail message. 
     * The detail message is saved for later retrieval by the Throwable.getMessage() method.
     */
    public JXSSFilterException() {        
    }
    
    /**
     * 
     * @param jxssError
     */
    public void addError (JXSSError jxssError) {
        jxssErrorList.add (jxssError);
    }
    
    /**
     * 
     * @param name
     * @param type
     * @return
     */
    public JXSSError getError (String name, String type) {
        JXSSError jxssError = new JXSSError (name, type, null);
        int index = jxssErrorList.indexOf(jxssError);
        if (index > -1) {
            jxssError = null;
            return (JXSSError)jxssErrorList.get(index);
        }
        return null;
    }
    
    /**
     * 
     * @return
     */
    public JXSSError[] getAllErrors () {
        JXSSError[] jxssErrors = (JXSSError[]) jxssErrorList.toArray(new JXSSError [jxssErrorList.size()]);
        return jxssErrors;
    }
    
    /**
     * 
     * @return
     */
    public boolean isEmpty () {
        return jxssErrorList.isEmpty();
    }
}