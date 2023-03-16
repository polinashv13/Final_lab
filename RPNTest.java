import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class RPNTest {
    @Test
    void calculateSum() {
        String expression = "1/2+1/3";
        Fraction expected = new Fraction(5, 6);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateMultiplication() {
        String expression = "1/2 * 1/3";
        Fraction expected = new Fraction(1, 6);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateSubtract() {
        String expression = "3/5 - 1/5";
        Fraction expected = new Fraction(2, 5);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateDivide() {
        String expression = "3/5 : 1/5";
        Fraction expected = new Fraction(3, 1);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateSamePriority() {
        String expression = "3/5 * 1/5 : 3/4";
        Fraction expected = new Fraction(4, 25);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateDifferentPriority() {
        String expression = "-1/-3 * 3/4 - 1/2";
        Fraction expected = new Fraction(-1, 4);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateBrackets() {
        String expression = "(3/5 + 1/5) * 3/4";
        Fraction expected = new Fraction(3, 5);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateBrackets2() {
        String expression = "(-3/5 + 1/5) * 3/4";
        Fraction expected = new Fraction(-3, 10);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateHardBrackets() {
        String expression = "(3/5 + 1/5) * (3/4 + 1/4) - 2/1";
        Fraction expected = new Fraction(-6, 5);
        Fraction actual = RPN.calculate(expression);

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void calculateDivideZero() {
        String expression = "2/3 + 4/0";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RPN.calculate(expression);
        });

        assertEquals("Ошибка. Деление на ноль", exception.getMessage());
    }

    @Test
    void calculateIncorrectExpression() {
        String expression = "--2/3 * 4/3";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RPN.calculate(expression);
        });

        assertEquals("Ошибка. Некорректное выражение", exception.getMessage());
    }

    @Test
    void calculateIncorrectExpression2() {
        String expression = "-2/3+*4/3";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RPN.calculate(expression);
        });

        assertEquals("Ошибка. Некорректное выражение", exception.getMessage());
    }

    @Test
    void calculateIncorrectBrackets() {
        String expression = "(-2/3+4/3) - 2/1)";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RPN.calculate(expression);
        });

        assertEquals("Ошибка. Некорректное выражение", exception.getMessage());
    }

    @Test
    void calculateIncorrectBrackets2() {
        String expression = "((-2/3+4/3) - 2/1";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            RPN.calculate(expression);
        });

        assertEquals("Ошибка. Некорректное выражение", exception.getMessage());
    }

}
