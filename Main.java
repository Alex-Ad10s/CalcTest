import java.io.IOException;
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
        boolean romanOrInt = false;
        Main intToRoman = new Main();
        Main romanExamination = new Main();
        int result = 0;
        String[] inputSplit = input.split(" ");
        if (inputSplit.length < 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "Недостаточно данных для расчета или пропущен пробел";
            }
        } else if (inputSplit.length != 3) {
            try {
                throw new IOException();
            } catch (IOException e) {
                return "Превышено допустимое количество операций";
            }
        }
        int a, b;
        try {
            a = Integer.parseInt(inputSplit[0]);
            b = Integer.parseInt(inputSplit[2]);
        } catch (NumberFormatException e) {
                a = romanExamination.romanToInt(inputSplit[0]);
                b = romanExamination.romanToInt(inputSplit[2]);
                romanOrInt = true;
                if ((a < 1) || (b < 1)) {
                    try {
                        throw new NumberFormatException();
                    } catch (NumberFormatException ex) {
                        return "Введена некорректная переменная для данной системы счисления";
                    }
                }
        }
        if ((a < 1) || (a > 10) || (b < 1) || (b > 10)) {
            try {
                throw new Exception();
            } catch (Exception e){
                return "Допустимые значения переменных от 1 до 10";
            }
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
                try {
                throw new Exception();
            } catch (Exception e) {
                return "Некорректный математический знак";
            }
        }
        String output;
        if (romanOrInt) {
            if (result < 1) {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    return "Данная система счисления не допускает отрицательных значений";
                }
            } else {
                output = intToRoman.intToRome(result);
            }
        } else {
            output = Integer.toString(result);
        }
        return output;
    }
    Integer romanToInt(String romanInput) {
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
    String intToRome(int intInput) {
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++) {
            value = intInput / arab[i];
            for (int j = 0; j < value; j++) {
                result = result.concat(roman[i]);
            }
            intInput = intInput % arab[i];
        }
        return result;
    }
}
