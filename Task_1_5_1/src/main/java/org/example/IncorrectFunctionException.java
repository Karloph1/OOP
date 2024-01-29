package org.example;

/**
 * main class.
 */
public class IncorrectFunctionException extends Exception {

    /**
     * the original line contained a word
     *          that could not be deciphered into a number or function.
     */
    public IncorrectFunctionException(String pe){
        super(pe);
    }
}
