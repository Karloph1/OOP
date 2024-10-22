package ru.nsu.fit.labusov.expression;

import java.util.Objects;

/**
 * Multiplication class.
 */
public class Mul extends Expression {
    private final Expression firstTerm;
    private final Expression secondTerm;

    /**
     * Constructor mul method.
     */
    public Mul(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    @Override
    public String showExpression() {
        return "(" + firstTerm.showExpression() + "*" + secondTerm.showExpression() + ")";
    }

    @Override
    public void print() {
        System.out.print("(");
        firstTerm.print();
        System.out.print("*");
        secondTerm.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String string) {
        return new Add(new Mul(firstTerm.derivative(string), secondTerm),
                new Mul(firstTerm, secondTerm.derivative(string)));
    }

    @Override
    public int eval(String string) {
        return firstTerm.eval(string) * secondTerm.eval(string);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Mul mul = (Mul) obj;

        return this.firstTerm.equals(mul.firstTerm) && this.secondTerm.equals(mul.secondTerm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstTerm, secondTerm);
    }
}