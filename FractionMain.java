import java.util.Scanner;

public class FractionMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression;
        while (true) {
            try {
            System.out.print("Вход: ");
            expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("quit")) {
                break;}
                Fraction fraction = RPN.calculate(expression);
                System.out.println("Выход: " + fraction);
            } catch (IllegalArgumentException exception){
                System.out.println("Выход: "+exception.getMessage());
            }
        }
    }
}
