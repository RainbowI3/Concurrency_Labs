package org.example;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

class RationalFractionTest {
    @Test
    void testConstructorAndToString() {
        RationalFraction fraction1 = new RationalFraction(1, 2);
        RationalFraction fraction2 = new RationalFraction(2, 4);

        assertEquals("1/2", fraction1.toString(), "Constructor or toString method does not work correctly.");
        assertEquals("1/2", fraction2.toString(), "Fraction simplification does not work correctly.");
    }

    @Test
    void testAddMethod() {
        RationalFraction fraction1 = new RationalFraction(1, 2);
        RationalFraction sum = fraction1.add(new RationalFraction(1, 3));

        assertEquals("5/6", sum.toString(), "Addition method add works incorrectly.");
    }


}