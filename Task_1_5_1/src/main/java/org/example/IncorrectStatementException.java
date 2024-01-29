package org.example;

/**
 * main class.
 */
public class IncorrectStatementException extends Exception{

    /**
     * the number of numbers and functions in the original string
     *          do not match each other to obtain the answer.
     */
    public IncorrectStatementException(String pe) {
        super(pe);
    }
}
