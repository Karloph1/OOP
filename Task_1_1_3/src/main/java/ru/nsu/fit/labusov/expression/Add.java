package ru.nsu.fit.labusov.expression;

import java.util.Objects;

/**
 * Addition class.
 */
public class Add extends Expression {
    private final Expression firstTerm;
    private final Expression secondTerm;

    /**
     * Constructor add method.
     */
    public Add(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    @Override
    public String showExpression() {
        return "(" + firstTerm.showExpression() + "+" + secondTerm.showExpression() + ")";
    }

    @Override
    public void print() {
        System.out.print("(");
        firstTerm.print();
        System.out.print("+");
        secondTerm.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String string) {
        return new Add(firstTerm.derivative(string), secondTerm.derivative(string));
    }

    @Override
    public int eval(String string) {
        return firstTerm.eval(string) + secondTerm.eval(string);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Add add = (Add) obj;

        return this.firstTerm.equals(add.firstTerm) && this.secondTerm.equals(add.secondTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTerm, secondTerm);
    }
}