import java.util.*;

public class Calculator {
    private static final Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите выражение (например: 1 + 5, либо X * III):");
            System.out.println("Для выхода введите 'q'");
            String input = keyboard.nextLine().trim();

            if (input.equalsIgnoreCase("q")) {
                System.out.println("Завершение программы.");
                break;
            }

            try {
                System.out.println("Результат: " + calculate(input));
            } catch (Exception e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
        keyboard.close();
    }

    public static String calculate(String input) {
        String operator = findOperator(input);
        String[] operands = input.split("\\s*" + escapeOperator(operator) + "\\s*");

        if (operands.length != 2) {
            throw new IllegalArgumentException("Некорректный ввод. Убедитесь, что вы ввели два числа и оператор.");
        }

        String num1 = operands[0].trim();
        String num2 = operands[1].trim();
        boolean isArabic = isArabicSystem(num1, num2);

        if (isArabic) {
            int operand1 = parseArabic(num1);
            int operand2 = parseArabic(num2);
            int result = performOperation(operand1, operand2, operator);
            return String.valueOf(result);
        } else {
            int operand1 = romanToArabic(num1);
            int operand2 = romanToArabic(num2);
            int result = performOperation(operand1, operand2, operator);
            return arabicToRoman(result);
        }
    }

    private static String findOperator(String input) {
        List<String> operators = Arrays.asList("+", "-", "*", "/");
        return operators.stream()
                .filter(input::contains)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Оператор не найден или отсутствует."));
    }

    private static String escapeOperator(String operator) {
        return operator.equals("+") || operator.equals("*") ? "\\" + operator : operator;
    }

    private static boolean isArabicSystem(String num1, String num2) {
        boolean isNum1Arabic = isArabic(num1);
        boolean isNum2Arabic = isArabic(num2);

        if (isNum1Arabic != isNum2Arabic) {
            throw new IllegalArgumentException("Нельзя смешивать арабские и римские числа.");
        }

        return isNum1Arabic;
    }

    private static boolean isArabic(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int parseArabic(String num) {
        return Integer.parseInt(num);
    }

    static int romanToArabic(String roman) {
        try {
            return Roman.valueOf(roman.toUpperCase()).getValue();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Некорректное римское число: " + roman);
        }

    }

    static String arabicToRoman(int num) {
        if (num < 1) {
            throw new IllegalArgumentException("Результат не может быть меньше 1 для римских чисел.");
        }

        StringBuilder result = new StringBuilder();
        for (Roman roman : Roman.values()) {
            while (num >= roman.getValue()) {
                result.append(roman.name());
                num -= roman.getValue();
            }
        }
        return result.toString();
    }

    static int performOperation(int num1, int num2, String operator) {
        if (operator.equals("/") && num2 == 0) {
            throw new ArithmeticException("Деление на ноль невозможно.");
        }

        if (num1 < 1 || num1 > 100 || num2 < 1 || num2 > 100) {
            throw new IllegalArgumentException("Число должно быть в диапазоне от 1 до 100.");
        }

        return switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неизвестный оператор: " + operator);
        };
    }



}
