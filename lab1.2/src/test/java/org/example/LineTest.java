package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineTest {

    @Test
    void testIsParallel() {
        Line line1 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(2, 1));
        Line line2 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(4, 1));
        Line line3 = new Line(new RationalFraction(1, 2), new RationalFraction(-1, 3), new RationalFraction(3, 4));

        assertTrue(line1.isParallel(line2), "Method isParallel does not work correctly for parallel lines.");
        assertFalse(line1.isParallel(line3), "Method isParallel does not work correctly for non-parallel lines.");
    }
}