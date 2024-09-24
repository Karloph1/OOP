package ru.nsu.fit.labusov.expression;

/**
 * Subtraction class.
 */
public class Sub extends Expression {
    private final Expression firstTerm;
    private final Expression secondTerm;

    /**
     * Constructor sub method.
     */
    public Sub(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    @Override
    public String showExpression() {
        return "(" + firstTerm.showExpression() + "-" + secondTerm.showExpression() + ")";
    }

    @Override
    public void print() {
        System.out.print("(");
        firstTerm.print();
        System.out.print("-");
        secondTerm.print();
        System.out.print(")");
    }

    @Override
    public Expression derivative(String string) {
        return new Sub(firstTerm.derivative(string), secondTerm.derivative(string));
    }

    @Override
    public int eval(String string) {
        return firstTerm.eval(string) - secondTerm.eval(string);
    }
}