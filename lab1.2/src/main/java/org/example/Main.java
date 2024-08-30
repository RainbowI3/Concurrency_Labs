package org.example;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        System.out.println("=== Working with Fractions ===");

        List<RationalFraction> fractions = new ArrayList<>();
        fractions.add(new RationalFraction(1, 2)); // 1/2
        fractions.add(new RationalFraction(3, 4)); // 3/4
        fractions.add(new RationalFraction(5, 6)); // 5/6
        fractions.add(new RationalFraction(7, 8)); // 7/8
        fractions.add(new RationalFraction(9, 10)); // 9/10

        System.out.println("Initial fractions:");
        for (RationalFraction fraction : fractions) {
            System.out.println(fraction);
        }

        RationalFraction.modifyEvenIndexedElements(fractions);

        System.out.println("Modified fractions:");
        for (RationalFraction fraction : fractions) {
            System.out.println(fraction);
        }


        System.out.println("\n=== Working with Lines ===");

        Line line1 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(2, 1));
        Line line2 = new Line(new RationalFraction(1, 1), new RationalFraction(-1, 1), new RationalFraction(4, 1));
        Line line3 = new Line(new RationalFraction(1, 2), new RationalFraction(-1, 3), new RationalFraction(3, 4));

        List<Line> lines = new ArrayList<>();
        lines.add(line1);
        lines.add(line2);
        lines.add(line3);


        for (Line line : lines) {
            System.out.println(line);
            System.out.println("X-Intercept: " + line.getXIntercept());
            System.out.println("Y-Intercept: " + line.getYIntercept());
        }


        RationalFraction[] intersection = Line.getIntersection(line1, line3);
        if (intersection != null) {
            System.out.println("Intersection of Line 1 and Line 3: (" + intersection[0] + ", " + intersection[1] + ")");
        } else {
            System.out.println("Line 1 and Line 3 are parallel or coincide.");
        }


        System.out.println("Line 1 is parallel to Line 2: " + line1.isParallel(line2));
        System.out.println("Line 1 is parallel to Line 3: " + line1.isParallel(line3));
    }
}
