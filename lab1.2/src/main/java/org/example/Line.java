package org.example;
import java.util.ArrayList;
import java.util.List;
public class Line {
    private RationalFraction a;
    private RationalFraction b;
    private RationalFraction c;


    public Line(RationalFraction a, RationalFraction b, RationalFraction c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }


    public RationalFraction getXIntercept() {
        if (a.getNumerator() == 0) {
            return null;
        }
        return new RationalFraction(c.getNumerator() * b.getDenominator(), c.getDenominator() * a.getNumerator());
    }


    public RationalFraction getYIntercept() {
        if (b.getNumerator() == 0) {
            return null;
        }
        return new RationalFraction(c.getNumerator() * a.getDenominator(), c.getDenominator() * b.getNumerator());
    }


    public static RationalFraction[] getIntersection(Line line1, Line line2) {
        RationalFraction a1 = line1.a;
        RationalFraction b1 = line1.b;
        RationalFraction c1 = line1.c;

        RationalFraction a2 = line2.a;
        RationalFraction b2 = line2.b;
        RationalFraction c2 = line2.c;


        int determinant = a1.getNumerator() * b2.getNumerator() - a2.getNumerator() * b1.getNumerator();
        if (determinant == 0) {
            return null;
        }


        int xNumerator = (c1.getNumerator() * b2.getDenominator() * b2.getNumerator()) - (c2.getNumerator() * b1.getDenominator() * b1.getNumerator());
        int yNumerator = (a1.getNumerator() * c2.getDenominator() * c2.getNumerator()) - (a2.getNumerator() * c1.getDenominator() * c1.getNumerator());
        int commonDenominator = determinant * (a1.getDenominator() * b2.getDenominator());

        RationalFraction x = new RationalFraction(xNumerator, commonDenominator);
        RationalFraction y = new RationalFraction(yNumerator, commonDenominator);

        return new RationalFraction[]{x, y};
    }


    public boolean isParallel(Line other) {
        return this.a.getNumerator() * other.b.getNumerator() == this.b.getNumerator() * other.a.getNumerator();
    }


    @Override
    public String toString() {
        return "Line: " + a + "x + " + b + "y = " + c;
    }
}
