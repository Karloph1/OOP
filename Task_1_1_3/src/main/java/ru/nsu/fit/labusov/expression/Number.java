package ru.nsu.fit.labusov.expression;

/**
 * Number class.
 */
public class Number extends Expression {
    private final int number;

    /**
     * Constructor number method.
     */
    public Number(int number) {
        this.number = number;
    }

    @Override
    public String showExpression() {
        return Integer.toString(number);
    }

    @Override
    public void print() {
        System.out.print(number);
    }

    @Override
    public Expression derivative(String string) {
        return new Number(0);
    }

    @Override
    public int eval(String string) {
        return number;
    }
}