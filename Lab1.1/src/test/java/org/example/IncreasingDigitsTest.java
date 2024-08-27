package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

public class IncreasingDigitsTest {

    @Test
    public void testGenerateRandomNumbers() {
        IncreasingDigits increasingDigits = new IncreasingDigits(10);
        List<Integer> numbers = increasingDigits.getNumbers();
        assertEquals(10, numbers.size());
        for (int number : numbers) {
            assertTrue(number >= 100 && number <= 999); // Ensure numbers are 3-digit
        }
    }

    @Test
    public void testHasStrictlyIncreasingDigits() {
        IncreasingDigits increasingDigits = new IncreasingDigits(0);
        assertTrue(increasingDigits.hasStrictlyIncreasingDigits(123));
        assertTrue(increasingDigits.hasStrictlyIncreasingDigits(135));
        assertFalse(increasingDigits.hasStrictlyIncreasingDigits(122));
        assertFalse(increasingDigits.hasStrictlyIncreasingDigits(321));
        assertFalse(increasingDigits.hasStrictlyIncreasingDigits(111));
    }

    @Test
    public void testFindFirstIncreasingDigitsNumber() {
        IncreasingDigits increasingDigits = new IncreasingDigits(0);
        List<Integer> numbers = List.of(987, 123, 456, 789);
        increasingDigits = new IncreasingDigits(numbers.size());
        increasingDigits.getNumbers().clear();
        increasingDigits.getNumbers().addAll(numbers);

        assertEquals(123, increasingDigits.findFirstIncreasingDigitsNumber());

        numbers = List.of(111, 222, 333);
        increasingDigits = new IncreasingDigits(numbers.size());
        increasingDigits.getNumbers().clear();
        increasingDigits.getNumbers().addAll(numbers);

        assertNull(increasingDigits.findFirstIncreasingDigitsNumber());

        numbers = List.of(1234, 2345, 3456);
        increasingDigits = new IncreasingDigits(numbers.size());
        increasingDigits.getNumbers().clear();
        increasingDigits.getNumbers().addAll(numbers);

        assertEquals(1234, increasingDigits.findFirstIncreasingDigitsNumber());
    }
}