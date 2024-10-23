package ru.nsu.fit.labusov.expression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Multiplication class.
 */
public class Mul extends Expression {
    private Expression firstTerm;
    private Expression secondTerm;

    /**
     * Constructor mul method.
     */
    public Mul(Expression firstTerm, Expression secondTerm) {
        this.firstTerm = firstTerm;
        this.secondTerm = secondTerm;
    }

    public Mul(String string) {
        ExpressionParser expressionParser = new ExpressionParser();
        String[][] strings = expressionParser.parseExpression(string, '*');

        switch (strings[0][1].charAt(0)) {
            case '+':
                firstTerm = new Add(strings[0][0]);
                break;
            case '-':
                firstTerm = new Sub(strings[0][0]);
                break;
            case '*':
                firstTerm = new Mul(strings[0][0]);
                break;
            case '/':
                firstTerm = new Div(strings[0][0]);
                break;
            default:
                try {
                    Integer.parseInt(strings[0][0]);
                    firstTerm = new Number(strings[0][0]);
                } catch (NumberFormatException e) {
                    firstTerm = new Variable(strings[0][0]);
                }

                break;
        }

        switch (strings[1][1].charAt(0)) {
            case '+':
                secondTerm = new Add(strings[1][0]);
                break;
            case '-':
                secondTerm = new Sub(strings[1][0]);
                break;
            case '*':
                secondTerm = new Mul(strings[1][0]);
                break;
            case '/':
                secondTerm = new Div(strings[1][0]);
                break;
            default:
                try {
                    Integer.parseInt(strings[1][0]);
                    secondTerm = new Number(strings[1][0]);
                } catch (NumberFormatException e) {
                    secondTerm = new Variable(strings[1][0]);
                }

                break;
        }
    }

    public Mul(File file) {
        try (Scanner fileScanner = new Scanner(new FileInputStream(file))) {
            String line = fileScanner.nextLine();

            ExpressionParser expressionParser = new ExpressionParser();
            String[][] strings = expressionParser.parseExpression(line, '*');

            switch (strings[0][1].charAt(0)) {
                case '+':
                    firstTerm = new Add(strings[0][0]);
                    break;
                case '-':
                    firstTerm = new Sub(strings[0][0]);
                    break;
                case '*':
                    firstTerm = new Mul(strings[0][0]);
                    break;
                case '/':
                    firstTerm = new Div(strings[0][0]);
                    break;
                default:
                    try {
                        Integer.parseInt(strings[0][0]);
                        firstTerm = new Number(strings[0][0]);
                    } catch (NumberFormatException e) {
                        firstTerm = new Variable(strings[0][0]);
                    }

                    break;
            }

            switch (strings[1][1].charAt(0)) {
                case '+':
                    secondTerm = new Add(strings[1][0]);
                    break;
                case '-':
                    secondTerm = new Sub(strings[1][0]);
                    break;
                case '*':
                    secondTerm = new Mul(strings[1][0]);
                    break;
                case '/':
                    secondTerm = new Div(strings[1][0]);
                    break;
                default:
                    try {
                        Integer.parseInt(strings[1][0]);
                        secondTerm = new Number(strings[1][0]);
                    } catch (NumberFormatException e) {
                        secondTerm = new Variable(strings[1][0]);
                    }

                    break;
            }

        } catch (FileNotFoundException e) {
            System.out.println("There's no such file");
            throw new RuntimeException(e);
        }
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