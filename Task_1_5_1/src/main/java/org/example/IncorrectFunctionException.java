package org.example;

/**
 * main exception class.
 */
public class IncorrectFunctionException extends Exception {
    /**
     * function.
     */
    public IncorrectFunctionException(String pe) {
        super(pe);
    }
}
