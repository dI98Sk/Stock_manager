
package ru.sfedu.productturnover.constant;

/**
 * Энумератор типов статусов, используемых классом Result
 * @author Дмитрий
 */
public enum StatusType {

    /**
     *
     */
    OK("OK"),

    /**
     *
     */
    WARNING("Warning"),

    /**
     *
     */
    ERROR("Error");
    
    String description;
    private StatusType() {
    }

    private StatusType(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return super.toString(); //To change body of generated methods, choose Tools | Templates.
    }
     
}
