public class Fraction {
    private int numerator;
    private int denominator;


    public Fraction(int numerator, int denominator) {
        if (denominator == 0)
            throw new IllegalArgumentException("Ошибка. Деление на ноль");

        if (numerator < 0 && denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }

        this.numerator = numerator;
        this.denominator = denominator;

        int gcd = gcdByEuclidsAlgorithm(Math.abs(numerator), Math.abs(denominator));
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    private int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

    public Fraction() {
        numerator = 1;
        denominator = 1;
    }

    public Fraction(String s) {
        this(Integer.parseInt(s.split("/")[0]), Integer.parseInt(s.split("/")[1]));
    }

    public static Fraction sum(Fraction fraction1, Fraction fraction2) {
        int resNumerator = fraction1.numerator * fraction2.denominator + fraction2.numerator * fraction1.denominator;
        int resDenominator = fraction1.denominator * fraction2.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction sum(Fraction fraction) {
        int resNumerator = numerator * fraction.denominator + fraction.numerator * denominator;
        int resDenominator = denominator * fraction.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public static Fraction subtract(Fraction fraction1, Fraction fraction2) {
        int resNumerator = fraction1.numerator * fraction2.denominator - fraction2.numerator * fraction1.denominator;
        int resDenominator = fraction1.denominator * fraction2.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction subtract(Fraction fraction) {
        int resNumerator = numerator * fraction.denominator - fraction.numerator * denominator;
        int resDenominator = denominator * fraction.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public static Fraction multiplication(Fraction fraction1, Fraction fraction2) {
        int resNumerator = fraction1.numerator * fraction2.numerator;
        int resDenominator = fraction1.denominator * fraction2.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction multiplication(Fraction fraction) {
        int resNumerator = numerator * fraction.numerator;
        int resDenominator = denominator * fraction.denominator;

        return new Fraction(resNumerator, resDenominator);
    }

    public static Fraction divide(Fraction fraction1, Fraction fraction2) {
        int resNumerator = fraction1.numerator * fraction2.denominator;
        int resDenominator = fraction1.denominator * fraction2.numerator;

        return new Fraction(resNumerator, resDenominator);
    }

    public Fraction divide(Fraction fraction) {
        int resNumerator = numerator * fraction.denominator;
        int resDenominator = denominator * fraction.numerator;

        return new Fraction(resNumerator, resDenominator);
    }


    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}


