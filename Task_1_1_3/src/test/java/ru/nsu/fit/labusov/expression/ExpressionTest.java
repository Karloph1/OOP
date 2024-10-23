package ru.nsu.fit.labusov.expression;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Expression tests.
 */
public class ExpressionTest {

    @Test
    void expressionOneSentenceTest() {
        Expression e = new Add(new Sub(new Div(new Variable("x"), new Variable("yy")),
                new Number(100)), new Mul(new Number(15), new Variable("a")));

        Assertions.assertEquals("(((x/yy)-100)+(15*a))", e.showExpression());
    }

    @Test
    void expressionEvaluationTest() {
        Expression e = new Add(new Mul(new Sub(new Number(3), new Variable("x")),
                new Variable("xan")), new Div(new Variable("n"), new Number(2)));

        Assertions.assertEquals(30, e.eval("xan = 10; x = 5; n = 100; a = 1"));
    }

    @Test
    void expressionDerivativeTest() {
        Expression e = new Add(new Mul(new Div(new Variable("x"), new Variable("y")),
                new Number(10)), new Sub(new Number(5), new Mul(new Variable("x"),
                new Number(6))));
        Expression devE = e.derivative("x");

        Assertions.assertEquals("((((((1*y)-(x*0))/(y*y))*10)+((x/y)*0))+(0-((1*6)+(x*0))))",
                devE.showExpression());
    }

    @Test
    void numberTest() {
        Expression e = new Number(10);

        Assertions.assertEquals("10", e.showExpression());
        Assertions.assertEquals(new Number(0), e.derivative(""));
        Assertions.assertEquals(10, e.eval(""));
    }

    @Test
    void variableTest() {
        Expression e = new Variable("x");

        Assertions.assertEquals("x", e.showExpression());
        Assertions.assertEquals(new Number(1), e.derivative("x"));
        Assertions.assertEquals(new Number(0), e.derivative("y"));
        Assertions.assertEquals(10, e.eval("x = 10"));
    }

    @Test
    void addTest() {
        Expression e = new Add(new Number(1), new Variable("x"));

        Assertions.assertEquals("(1+x)", e.showExpression());
        Assertions.assertEquals(new Add(new Number(0), new Number(1)), e.derivative("x"));
        Assertions.assertEquals(11, e.eval("x = 10"));

        e = new Add("(3+2)");
        Assertions.assertEquals("(3+2)", e.showExpression());
    }

    @Test
    void subTest() {
        Expression e = new Sub(new Number(10), new Variable("y"));

        Assertions.assertEquals("(10-y)", e.showExpression());
        Assertions.assertEquals(new Sub(new Number(0), new Number(1)), e.derivative("y"));
        Assertions.assertEquals(-90, e.eval("y = 100"));

        e = new Sub("(x-10)");
        Assertions.assertEquals("(x-10)", e.showExpression());
    }

    @Test
    void mulTest() {
        Expression e = new Mul(new Variable("x"), new Variable("y"));

        Assertions.assertEquals("(x*y)", e.showExpression());
        Assertions.assertEquals(new Add(new Mul(new Number(1), new Variable("y")),
                new Mul(new Variable("x"), new Number(0))), e.derivative("x"));
        Assertions.assertEquals(1000, e.eval("x = 10; y = 100"));

        e = new Mul("(1*x)");
        Assertions.assertEquals("(1*x)", e.showExpression());
    }

    @Test
    void divTest() {
        Expression e = new Div(new Variable("abc"), new Variable("xyz"));

        Assertions.assertEquals("(abc/xyz)", e.showExpression());
        Assertions.assertEquals(new Div(new Sub(new Mul(new Number(0), new Variable("xyz")),
                new Mul(new Variable("abc"), new Number(1))),
                new Mul(new Variable("xyz"), new Variable("xyz"))), e.derivative("xyz"));
        Assertions.assertEquals(5, e.eval("abc = 100; xyz = 20"));

        e = new Div("(x/u)");
        Assertions.assertEquals("(x/u)", e.showExpression());
    }

    @Test
    void readLongSentenceTest() {
        Expression e = new Add("(((2+t)/x)+(a*(1-(9/1))))");

        Assertions.assertEquals("(((2+t)/x)+(a*(1-(9/1))))", e.showExpression());
    }
}