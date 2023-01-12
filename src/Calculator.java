import java.util.Scanner;
public class Calculator {
    static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {

            System.out.println("main : Введите два числа от 0 до 10 арабских или римских от I до X");
            String input = keyboard.nextLine();
            //gameOver();
            System.out.println(calculator(input));
        }
    }
    public static String calculator(String input) {
        //gameOver();

        String output = "";
        String math = operator(input);  //Определяем математическое действие
        String num1 = numbers(input, math, 0);   //  разбиваем строку на два числа(String)+убираем пробелы
        String num2 = numbers(input, math, 1);   //  разбиваем строку на два числа(String)+убираем пробелы
        boolean control = isArabic(num1, num2);   //проверка на арабские цифры

        if (control == true) {
            int numArabic1 = Integer.parseInt(num1);//Преобразуем строку в цифру
            int numArabic2 = Integer.parseInt(num2); // Преобразуем строку в цифру
            String result = result(numArabic1, numArabic2, math);  // арифметическое действие
            output = result;
        } else {
            //int arabicNumberLeft = fromRomanToArabic(num1);   // из римских цифр в арабские через массив
            //int arabicNumberRight = fromRomanToArabic(num2);  // из римских цифр в арабские через массив
            int arabCharLeft = romanMethod(num1);   // из римских цифр в арабские через ENUM
            int arabCharRight = romanMethod(num2);  // из римских цифр в арабские через ENUM
            String result = result(arabCharLeft, arabCharRight, math); // арифметическое действие
            output = fromArabicToRoman(Integer.parseInt(result));   // из арабских в римские при вводе римских для получени результата
        }
        return output;
    }
    // определение арифм действия
    static String operator(String input) {
        String[] array = {"+", "-", "*", "/"};
        String symb = " ";
        int j = 0;
        int k = 0;

        for (int i = 0; i < 4; i++) {
            if(input.contains("q")){
                gameOver();
            }else if (input.indexOf(array[i]) > -1) {    // определяем первое вхождение символа из массива в строку
                symb = array[i];
                j = i;
                break;
            }
        }

        for (int i = 3; i >= 0; i--) {                  // проходим массив с конца+
            if (input.lastIndexOf(array[i]) > -1) {    // последнее вхождение
                k = i;
                break;
            }
        }

        if ((j != k) || (input.indexOf(array[j])) != (input.lastIndexOf(array[k]))) {
            throw new IllegalArgumentException("Допустимо только одно арифметическое действие");
        }

        if (symb == " ") {
            throw new IllegalArgumentException("Нет допустимого арифметического действия");
        }

        return symb;
    }
    // обработка ввода значений в консоли
    static String numbers(String input, String math, int leftOrRight) {

        if (math == "+") {
            math = "\\+";
        }
        // "борьба" с метасимволами + и *
        // для корректной работы split()
        if (math == "*") {
            math = "\\*";
        }
        String[] number = input.split(math);
        number[leftOrRight] = number[leftOrRight].trim();
        return number[leftOrRight];
    }
    // выполнение действия , получение result
    static String result(int num1, int num2, String math) {
        int z = 0;
        if (((num1 > 10) || (num1 < 1)) || ((num2 > 10) || (num2 < 1))) {
            throw new IllegalArgumentException("Число(а) не входят в промежуток[1;10]");
        }
        switch (math) {
            case "+":
                z = num1 + num2;
                break;

            case "-":
                z = num1 - num2;
                break;

            case "*":
                z = num1 * num2;
                break;

            case "/":
                z = num1 / num2;
                break;
        }

        String text = ("" + z);
        return text;
    }
    // проверка системы счисления если арабские цифры
    static boolean isArabic(String numberLeft, String numberRight) {      // проверка системы счисления
                boolean arabicLeft;
        boolean arabicRight ;
        boolean isArabic = false;
        try {                                                        // проверка левого числа
            Integer.parseInt(numberLeft);
            arabicLeft = true;
        } catch (java.lang.NumberFormatException ex) {
            arabicLeft = false;
        }
        try {                                                         // проверка правого числа
            Integer.parseInt(numberRight);
            arabicRight = true;
        } catch (NumberFormatException ex2) {
            arabicRight = false;
        }
        if (arabicLeft != arabicRight) {
            throw new IllegalArgumentException(" Нельзя вводить и арабские и римские цифры");
        }
        if (arabicLeft == true) {
            isArabic = true;
        }
        return isArabic;
    }
    //    перевод из арабских в римские для вывожа результата в римских
    static String fromArabicToRoman(int arabic) {
        StringBuilder roman = new StringBuilder();
        if (arabic < 1) {
            throw new IllegalArgumentException(arabic + " Не бывает арабских цифр меньше 1");
        }
        while (arabic == 100) {
            roman = new StringBuilder("C");
            arabic -= 100;
        }
        while (arabic >= 90) {
            roman.append("XC");
            arabic -= 90;
        }
        while (arabic >= 50) {
            roman.append("L");
            arabic -= 50;
        }
        while (arabic >= 40) {
            roman.append("XL");
            arabic -= 40;
        }
        while (arabic >= 10) {
            roman.append("X");
            arabic -= 10;
        }
        while (arabic >= 9) {
            roman.append("IX");
            arabic -= 9;
        }
        while (arabic >= 5) {
            roman.append("V");
            arabic -= 5;
        }
        while (arabic >= 4) {
            roman.append("IV");
            arabic -= 4;
        }
        while (arabic >= 1) {
            roman.append("I");
            arabic -= 1;
        }
        return roman.toString();
    }
    // перевод из римских в арабские
    public static int romanMethod(String roman)  {
        for (int i = 0; i < EnumClass.values().length; i++) {
            if (EnumClass.values()[i].name().equalsIgnoreCase(roman)) {
                return EnumClass.values()[i].getValue();
            }
            if (i == EnumClass.values().length - 1) {
                System.out.println("ronanMethod : Вы ввели не допустимое число");

            }
        }
        return 0;
    }
    public static void gameOver() {
        System.out.println("gameOver : завершение при вводе символа \"q\"");
        keyboard.close();
        System.exit(0);

    }
}