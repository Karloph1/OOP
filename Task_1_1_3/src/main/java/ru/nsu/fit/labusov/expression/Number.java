package ru.nsu.fit.labusov.expression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Number class.
 */
public class Number extends Expression {
    private int number;

    /**
     * Constructor number method by number.
     */
    public Number(int number) {
        this.number = number;
    }

    /**
     * Constructor number method by sentence.
     */
    public Number(String string) {
        int number;
        try {
            number = Integer.parseInt(string);
            this.number = number;
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            System.out.println("String for number is incorrect");
        }
    }

    /**
     * Constructor number method by file.
     */
    public Number(File file) {
        try (Scanner fileScanner = new Scanner(new FileInputStream(file))) {
            try {
                String string = fileScanner.nextLine();
                this.number = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("String for number is incorrect");
            }
        } catch (FileNotFoundException e) {
            System.out.println("There's no such file");
            throw new RuntimeException(e);
        }
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Number number = (Number) obj;

        return this.number == number.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}