//   ІТІНФ-22-2 Іщенко Руслан, Варіант 10,Лабороторна робота 1.1
//Find the number in which the digits are in strict increasing order.
// If there are several such numbers, find the first one.
//Please provide implementation with tests and main terminal application to enable manual testing.


package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IncreasingDigits {
    private List<Integer> numbers;

    public IncreasingDigits(int n) {
        this.numbers = generateRandomNumbers(n);
    }

    private List<Integer> generateRandomNumbers(int n) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            numbers.add(random.nextInt(900) + 100); // Generate random 3-digit numbers
        }
        return numbers;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public boolean hasStrictlyIncreasingDigits(int number) {
        String numStr = Integer.toString(number);
        for (int i = 0; i < numStr.length() - 1; i++) {
            if (numStr.charAt(i) >= numStr.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public Integer findFirstIncreasingDigitsNumber() {
        for (int number : numbers) {
            if (hasStrictlyIncreasingDigits(number)) {
                return number;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Numbers: " + numbers;
    }

    public static void main(String[] args) {
        IncreasingDigits increasingDigits = new IncreasingDigits(10);
        System.out.println(increasingDigits);

        Integer result = increasingDigits.findFirstIncreasingDigitsNumber();
        if (result != null) {
            System.out.println("The first number with strictly increasing digits is: " + result);
        } else {
            System.out.println("No number with strictly increasing digits found.");
        }
    }
}
