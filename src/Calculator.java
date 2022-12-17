import java.util.Scanner;
public class Calculator {
    public static void main(String[] args) {
        int num1 = 0;
        int num2 = 0;
        char symbol;
        int result;
        while (true) {
            System.out.println("Введите два целых числа от 1 до 10 в одну строку через пробел");
            Scanner scanner = new Scanner(System.in).useDelimiter("\\s");
            if (scanner.hasNextLine()) {
                   if (scanner.hasNextInt()) {
                        num1 = scanner.nextInt();
                    } else if(scanner.next().contains(Convert.getKey()))  //в этой строке не принимает getKey
                   {
                        num1 = Convert.valueOf(scanner.next()).getValue();               }
                    else {
                       System.out.println("НЕВЕРНОЕ ЧИСЛО");
                   }

                    if (scanner.hasNextInt()) {
                        num2 = scanner.nextInt();
                    } else {
                        num2 = Convert.valueOf(scanner.next()).getValue();
                    }
                }

                if (num1 >= 1 && num1 <= 10 && num2 >= 1 && num2 <= 10) {
                    System.out.println("Введите оператор (+ - / *), для выхда нажмите Х ");
                    symbol = new Scanner(System.in).next().charAt(0);
                    switch (symbol) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '/':
                            result = num1 / num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case 'X':
                            //break;
                            System.out.println("Окончание работы программы X !");
                            return;
                        default:
                            System.out.println("Неверно введен знако оператора !");
                            //return;
                            continue;
                    }
                    System.out.println("Результат = " + result);
                    //scanner.close();
                } else {
                    //System.out.println("Чило не от 1 до 10");
                }
            }
        }
    }
