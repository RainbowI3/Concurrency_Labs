package org.example;
import java.util.ArrayList;
import java.util.List;
public class RationalFraction {
    private int numerator;
    private int denominator;


    public RationalFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Denominator cannot be zero.");
        }
        int gcd = gcd(numerator, denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }


    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }


    public int getNumerator() {
        return numerator;
    }


    public int getDenominator() {
        return denominator;
    }


    public RationalFraction add(RationalFraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new RationalFraction(newNumerator, newDenominator);
    }


    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }


    public static void modifyEvenIndexedElements(List<RationalFraction> fractions) {

        List<RationalFraction> originalFractions = new ArrayList<>(fractions);


        for (int i = 0; i < fractions.size() - 1; i += 2) {
            RationalFraction modified = originalFractions.get(i).add(originalFractions.get(i + 1));
            fractions.set(i, modified);
        }
}   }

