package ru.nsu.fit.labusov.expression;

import java.util.Objects;

/**
 * Division class.
 */
public class Div extends Expression {
    private Expression firstTerm;
    private Expression secondTerm;

    /**
     * Constructor div method.
     */
    public Div(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public Div(String string) {
        int index = string.indexOf("/");

        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException("Sentence doesn't contain '/'");
        }
        char expression1 = 0;
        char expression2 = 0;

        if (string.indexOf(")") != string.length() - 1) {
            char character;
            character = string.charAt(1);

            if (character == '(') {
                int stack = 1;
                index = 2;

                while (stack != 0) {
                    character = string.charAt(index);

                    if (character == '(') {
                        stack++;
                    } else if (character == ')') {
                        stack--;
                    } else if ((character == '+' || character == '-'
                            || character == '*' || character == '/') && stack == 1) {
                        expression1 = character;
                    }
                    index++;
                }
            }

            if (string.charAt(index + 1) == '(') {
                int stack = 1;
                int index1 = 4;

                while (stack != 0) {
                    character = string.charAt(index1);

                    if (character == '(') {
                        stack++;
                    } else if (character == ')') {
                        stack--;
                    } else if ((character == '+' || character == '-'
                            || character == '*' || character == '/') && stack == 1) {
                        expression2 = character;
                    }

                    index1++;
                }
            }
        }

        String string1 = string.substring(1, index);
        String string2 = string.substring(index + 1, string.length() - 1);

        switch (expression1) {
            case '+':
                firstTerm = new Add(string1);
                break;
            case '-':
                firstTerm = new Sub(string1);
                break;
            case '*':
                firstTerm = new Mul(string1);
                break;
            case '/':
                firstTerm = new Div(string1);
                break;
            default:
                try {
                    Integer.parseInt(string1);
                    firstTerm = new Number(string1);
                } catch (NumberFormatException e) {
                    firstTerm = new Variable(string1);
                }

                break;
        }

        switch (expression2) {
            case '+':
                secondTerm = new Add(string2);
                break;
            case '-':
                secondTerm = new Sub(string2);
                break;
            case '*':
                secondTerm = new Mul(string2);
                break;
            case '/':
                secondTerm = new Div(string2);
                break;
            default:
                try {
                    Integer.parseInt(string2);
                    secondTerm = new Number(string2);
                } catch (NumberFormatException e) {
                    secondTerm = new Variable(string2);
                }

                break;
        }
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