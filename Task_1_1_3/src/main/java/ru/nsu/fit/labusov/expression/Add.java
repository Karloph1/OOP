package ru.nsu.fit.labusov.expression;

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
}