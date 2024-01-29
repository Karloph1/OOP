import org.example.IncorrectFunctionException;
import org.example.IncorrectStatementException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

class CalculatorTest {
    @Test
    void simpleCheck() throws Exception {
        String h = "+ 1 2";
        Assertions.assertEquals(3, org.example.Calculator.calculation(h));

        String h1 = "- 2 2";
        Assertions.assertEquals(0, org.example.Calculator.calculation(h1));

        String h2 = "* 4 2";
        Assertions.assertEquals(8, org.example.Calculator.calculation(h2));

        String h3 = "/ 1 2";
        Assertions.assertEquals(0.5, org.example.Calculator.calculation(h3));
    }

    @Test
    void multipleCheck() throws Exception {
        String h = "/ * + - 1 2 3 15 3";
        Assertions.assertEquals(10, org.example.Calculator.calculation(h));
    }

    @Test
    void simpleCheck2() throws Exception {
        String h = "log 16 4";
        Assertions.assertEquals(2, org.example.Calculator.calculation(h));

        String h1 = "sqrt 16";
        Assertions.assertEquals(4, org.example.Calculator.calculation(h1));

        String h2 = "pow 10 2";
        Assertions.assertEquals(100, org.example.Calculator.calculation(h2));

        String h3 = "sin 0";
        Assertions.assertEquals(0, org.example.Calculator.calculation(h3));

        String h4 = "cos 0";
        Assertions.assertEquals(1, org.example.Calculator.calculation(h4));
    }

    @Test
    void multipleCheck2() throws Exception {
        String h = "sin + - 1 2 1";
        Assertions.assertEquals(0, org.example.Calculator.calculation(h));

        String h1 = "cos sin - sqrt pow log 49 7 0 1";
        Assertions.assertEquals(1, org.example.Calculator.calculation(h1));
    }

    @Test
    void incorrectStatementExceptionCheck() throws Exception {
        String h = "/ 1 0 1";
        try{
            Assertions.assertEquals(0, org.example.Calculator.calculation(h));
        }catch (Exception e){
            Assertions.fail();
        }
    }

    @Test
    void incorrectFunctionExceptionCheck() throws Exception {
        String h = "a";
        try{
            Assertions.assertEquals(0, org.example.Calculator.calculation(h));
        }catch (Exception e){
            Assertions.fail();
        }
    }
}
