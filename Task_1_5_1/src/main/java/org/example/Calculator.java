package org.example;
import java.util.Stack;

public class Calculator {
    public static double calculation (String statement) throws
            IncorrectFunctionException, IncorrectStatementException {
        /*
        IncorrectFunctionException - the original line contained a word
         that could not be deciphered into a number or function
        IncorrectStatementException - the number of numbers and functions in the original string
         do not match each other to obtain the answer
         */
        double ans = 0;
        String[] units = statement.split(" ");
        Stack <Double> stack = new Stack<>();
        double x, y, z;
        for (int i = units.length-1; i >= 0; i--){
            switch(units[i]){
                 case "+":
                    x = stack.pop();
                    y = stack.pop();
                    ans = x + y;
                    stack.push(ans);
                     break;

                case "-":
                    x = stack.pop();
                    y = stack.pop();
                    ans = x - y;
                    stack.push(ans);
                    break;

                case "*":
                    x = stack.pop();
                    y = stack.pop();
                    ans = x * y;
                    stack.push(ans);
                    break;

                case "/":
                    x = stack.pop();
                    y = stack.pop();
                    ans = x / y;
                    stack.push(ans);
                    break;

                case "pow":
                    x = stack.pop();
                    y = stack.pop();
                    ans = Math.pow(x, y);
                    stack.push(ans);
                    break;

                case "log":
                    x = stack.pop();
                    y = stack.pop();
                    ans = Math.log(x) / Math.log(y);
                    stack.push(ans);
                    break;

                case "sqrt":
                    x = stack.pop();
                    ans = Math.sqrt(x);
                    stack.push(ans);
                    break;

                case "sin":
                    x = stack.pop();
                    ans = Math.sin(x);
                    stack.push(ans);
                    break;

                case "cos":
                    x = stack.pop();
                    ans = Math.cos(x);
                    stack.push(ans);
                    break;

                default:
                    try {
                        stack.push(Double.valueOf(units[i]));
                    }catch (Exception e){
                        throw new IncorrectFunctionException("Your function is incorrect");
                    }
                    break;
            }
        }
        if (!stack.isEmpty())
            throw new IncorrectStatementException("Original statement is incorrect");
        return ans;
    }
}