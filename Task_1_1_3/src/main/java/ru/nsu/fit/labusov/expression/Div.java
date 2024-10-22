package ru.nsu.fit.labusov.expression;

import java.util.Objects;

/**
 * Division class.
 */
public class Div extends Expression {
    private final Expression firstTerm;
    private final Expression secondTerm;

    /**
     * Constructor div method.
     */
    public Div(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    @Override
    public String showExpression() {
        return "(" + firstTerm.showExpression() + "/" + secondTerm.showExpression() + ")";
    }

    @Override
    public void print() {
        System.out.print("(");
        firstTerm.print();
        System.out.print("/");
        secondTerm.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String string) {
        return new Div(new Sub(new Mul(firstTerm.derivative(string), secondTerm),
                new Mul(firstTerm, secondTerm.derivative(string))),
                new Mul(secondTerm, secondTerm));
    }

    @Override
    public int eval(String string) {
        return firstTerm.eval(string) / secondTerm.eval(string);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Div div = (Div) obj;

        return this.firstTerm.equals(div.firstTerm) && this.secondTerm.equals(div.secondTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTerm, secondTerm);
    }
}