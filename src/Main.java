import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите операцию в формате: 2 + 3 или VI - II");
        System.out.println("Поддерживаются операции с арабскими или римскими цифрами от 1 до 10");
        System.out.println("Поддерживаемые операнды: + - * /");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        System.out.println(calc(operation));
    }

    public static String calc(String input) throws Exception {
        String[] operands = input.split(" ");

        if (operands.length < 3) {
            throw new Exception("т.к. строка не является математической операцией");
        } else if (operands.length > 3) {
            throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        Roman roman = new Roman();
        boolean check1 = roman.checkRoman(operands[0]);
        boolean check2 = roman.checkRoman(operands[2]);
        if (check1 ^ check2) {
            throw new Exception("т.к. используются одновременно разные системы счисления");
        } else if (check1 && check2) {
            operands[0] = roman.romanToArab(operands[0]);
            operands[2] = roman.romanToArab(operands[2]);
        }
        int num1, num2, result;
        try {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[2]);
        } catch (NumberFormatException ex) {
            throw new Exception("т.к неподходящий формат операндов");
        }
        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("т.к калькулятор принимает на вход числа N, 1<=N<=10");
        }
        switch (operands[1]) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "*" -> result = num1 * num2;
            case "/" -> result = num1 / num2;
            default -> throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        if (check1 && check2) {
            if (result <= 0) {
                throw new Exception("//т.к. в римской системе нет ноля и отрицательных чисел");
            }
            return roman.romanNums[result];
        } else {
            return Integer.toString(result);
        }
    }

}

class Roman{

    String[] romanNums = { "",
                "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

    boolean checkRoman(String number) {
        for(String n : romanNums) {
            if (number.equals(n)) {
                return true;
            }
        }
        return false;
    }

    String romanToArab(String number) {
        for (int i = 1; i<=100; i++) {
            if (number.equals(romanNums[i])) {
                return Integer.toString(i);
            }
        }
        return "";
    }

}