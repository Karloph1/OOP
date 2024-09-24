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
                new ru.nsu.fit.labusov.expression.Number(100)), new Mul(new ru.nsu.fit.labusov.expression.Number(15), new Variable("a")));

        Assertions.assertEquals("(((x/yy)-100)+(15*a))", e.showExpression());
    }

    @Test
    void expressionEvaluationTest() {
        Expression e = new Add(new Mul(new Sub(new ru.nsu.fit.labusov.expression.Number(3), new Variable("x")),
                new Variable("xan")), new Div(new Variable("n"), new ru.nsu.fit.labusov.expression.Number(2)));

        Assertions.assertEquals(30, e.eval("xan = 10; x = 5; n = 100; a = 1"));
    }

    @Test
    void expressionDerivativeTest() {
        Expression e = new Add(new Mul(new Div(new Variable("x"), new Variable("y")),
                new ru.nsu.fit.labusov.expression.Number(10)), new Sub(new ru.nsu.fit.labusov.expression.Number(5), new Mul(new Variable("x"),
                    new Number(6))));
        Expression devE = e.derivative("x");

        Assertions.assertEquals("((((((1*y)-(x*0))/(y*y))*10)+((x/y)*0))+(0-((1*6)+(x*0))))",
                devE.showExpression());
    }
}