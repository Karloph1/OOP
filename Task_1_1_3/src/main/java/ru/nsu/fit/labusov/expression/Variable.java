package ru.nsu.fit.labusov.expression;

import java.util.Objects;

/**
 * Variable class.
 */
public class Variable extends Expression {
    private final String variable;

    /**
     * Constructor variable method.
     */
    public Variable(String variable) {
        this.variable = variable;
    }

    @Override
    public String showExpression() {
        return variable;
    }

    @Override
    public void print() {
        System.out.print(variable);
    }

    @Override
    public Expression derivative(String string) {
        if (string.equals(variable)) {
            return new Number(1);
        }

        return new Number(0);
    }

    @Override
    public int eval(String string) {
        String[] strings = string.split("; ");
        int number = 0;

        for (String str : strings) {
            int index = str.indexOf("=");

            if (index == -1) {
                throw new NullPointerException("There's no '=' in variable");
            }

            if (str.substring(0, index - 1).equals(variable)) {
                try {
                    number = Integer.parseInt(str.substring(index + 2));
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Sentence in variable is incorrect");
                }
            }
        }

        return number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Variable variable = (Variable) obj;

        return this.variable.equals(variable.variable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variable);
    }
}