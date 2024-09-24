package ru.nsu.fit.labusov.expression;

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
}