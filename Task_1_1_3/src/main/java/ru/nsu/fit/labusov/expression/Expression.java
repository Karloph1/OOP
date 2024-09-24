package ru.nsu.fit.labusov.expression;

/**
 * Abstract expression class.
 */
public abstract class Expression {
    public abstract String showExpression();

    public abstract void print();

    public abstract Expression derivative(String string);

    public abstract int eval(String string);
}