package org.jaxss.error;

import java.io.Serializable;

/**
 *
 * @author Chander Singh
 */
public class JXSSError implements Serializable{
    private static final long serialVersionUID = 8791875160095388246L;
	private String name = "";
    private String type = "";
    private String message = "";
    
    /**
     * Default constructor of JXSSError
     */
    public JXSSError(String name, String type, String message) {
        this.name = name;
        this.type = type;
        this.message = message;
    }

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * 
     */
    public boolean equals(Object obj) {
        if (obj instanceof JXSSError) {
            JXSSError jxssError = (JXSSError) obj;
            return (this.name.equals(jxssError.getName()) && this.type.equals(jxssError.getType()));
        }
        return false;
    }    
}
