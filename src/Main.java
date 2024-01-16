import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Main result = new Main();
        System.out.println("Введите выражение отделяя знак пробелом:");
        String expression = scan.nextLine();
        String answer = result.calc(expression);
        System.out.println("Ответ:\n" + answer);
    }

    public static String calc(String input) {
        String exception = "Недостаточно данных для расчета или пропущен пробел";
        String exception1 = "Превышено допустимое количество операций";
        String exception2 = "Введена некорректная переменная для данной системы счисления";
        String exception3 = "Допустимые значения переменных от 1 до 10";
        String exception4 = "Некорректный математический знак";
        String exception5 = "Данная система счисления не допускает отрицательных значений";
        boolean romanOrArab = false;
        Main arabToRoman = new Main();
        Main romanExamination = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 3) {
            return exception;
        } else if (inputSplit.length != 3) {
            return exception1;
        }

        int a, b;
        try {
            a = Integer.parseInt(inputSplit[0]);
            b = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {
                a = romanExamination.romanToArab(inputSplit[0]);
                b = romanExamination.romanToArab(inputSplit[2]);
                romanOrArab = true;
                if ((a < 1) || (b < 1)) {
                return exception2;
            }
        }
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10)) {
            return exception3;
        }

        String mSing = inputSplit[1];
        switch (mSing) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                return exception4;
        }

        String output;
        if (romanOrArab) {
            if (result < 1) {
                return exception5;
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }

    Integer romanToArab(String romanInput) {
        int result = 0;
        int[] arab = {10, 9, 5, 4, 1};
        String[] roman = {"X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());
            }
        }
        return result;
    }
    String arabToRome(int arabInput) {
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++) {
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++) {
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
        return result;
    }
}
