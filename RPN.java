import java.util.Stack;

public class RPN {
    private static boolean checkOperator(char c) {
        String operators = "+-*:";
        return operators.contains(c + "");
    }

    private static int getPriority(char c) {
        if (c == '(')
            return 0;
        if (c == '+' || c == '-')
            return 1;
        if (c == '*' || c == ':')
            return 2;
        return 100;
    }

    private static String convertToPolishNotation(String expression) {
        Stack<Character> stack = new Stack<>();
        String result = "";

        expression = expression.trim();
        boolean checkMinus = false;

        for (int i = 0; i < expression.length(); i++) {
            if (i < expression.length() - 1 && checkOperator(expression.charAt(i)) && checkOperator(expression.charAt(i+1))) {
                throw new IllegalArgumentException("Ошибка. Некорректное выражение");
            }

            if (expression.charAt(i) == '-' && !checkMinus || Character.isDigit(expression.charAt(i))) {
                checkMinus = true;
                String p = "";
                if (expression.charAt(i) == '-') {
                    p = "-";
                    i++;
                }
                boolean findSlash = false;
                boolean findNumberAfterSlash = false;
                while (i < expression.length() && (!findNumberAfterSlash || Character.isDigit(expression.charAt(i)))) {
                    p += expression.charAt(i);
                    if (expression.charAt(i) == '/')
                        findSlash = true;
                    if (findSlash && Character.isDigit(expression.charAt(i))) {
                        findNumberAfterSlash = true;
                    }
                    i++;
                }
                result += p + " ";
                i--;
            }
            else if (expression.charAt(i) == '(') {
                stack.push('(');
            }
            else if (expression.charAt(i) == ')') {
                while (!stack.empty() && stack.peek() != '(') {
                    result += stack.pop();
                    result += " ";
                }
                if (stack.empty()) {
                    throw new IllegalArgumentException("Ошибка. Некорректное выражение");
                }
                stack.pop();
            }
            else if (checkOperator(expression.charAt(i))) {
                if (stack.empty()) {
                    stack.push(expression.charAt(i));
                }
                else {
                    while (!stack.empty() && getPriority(expression.charAt(i)) <= getPriority(stack.peek())) {
                        result += stack.pop();
                        result += " ";
                    }
                    stack.push(expression.charAt(i));
                }
            }
        }

        while (!stack.empty()) {
            if (stack.peek() == '(') {
                throw new IllegalArgumentException("Ошибка. Некорректное выражение");
            }
            result += stack.pop();
            result += " ";
        }

        return result;
    }

    private static Fraction calculateRPN(String s) {
        Stack<Fraction> fractions = new Stack<>();
        String[] p = s.split(" ");
        for (String value : p) {
            if (value.length() == 1 && checkOperator(value.charAt(0))) {
                Fraction a = fractions.pop();
                Fraction b = fractions.pop();

                Fraction res = switch (value) {
                    case "+" -> a.sum(b);
                    case "-" -> b.subtract(a);
                    case ":" -> b.divide(a);
                    default -> a.multiplication(b);
                };
                fractions.push(res);
            } else {
                Fraction fraction = new Fraction(value);
                fractions.push(fraction);
            }
        }
        return fractions.pop();
    }

    public static Fraction calculate(String expression) {
        String p = convertToPolishNotation(expression);
        return calculateRPN(p);
    }
}
