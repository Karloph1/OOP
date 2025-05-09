package ru.nsu.fit.labusov.expression;

/**
 * ExpressionParser class.
 */
public class ExpressionParser {

    /**
     * Parsing expression method.
     */
    public String[][] parseExpression(String string, char e) {
        int index = string.indexOf(e);

        if (index == -1) {
            throw new ArrayIndexOutOfBoundsException("Sentence doesn't contain " + e);
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
                int index1 = index + 2;

                while (stack != 0) {
                    character = string.charAt(index1);

                    if (character == '(') {
                        stack++;
                    } else if (character == ')') {
                        stack--;
                    } else if ((character == '+' || character == '-'
                            || character == '*' || character == '/') && stack == 1) {
                        expression2 = character;

                        break;
                    }

                    index1++;
                }
            }
        }

        String string1 = string.substring(1, index);
        String string2 = string.substring(index + 1, string.length() - 1);

        return new String[][]{{string1, Character.toString(expression1)},
                              {string2, Character.toString(expression2)}};
    }
}
