package ru.stqa.pft.sandbox;

public class Equation {

    private double a;
    private double b;
    private double c;

    private int n;

    public Equation(double a, double b, double C) {

        this.a = a;
        this.b = b;
        this.c = C;

        double d = b*b - 4*a*c;

        if (d > 0) {
            n = 2;
        } else {
            if (d==0) {
                n=1;
            } else {
                n = 0;
            }
        }
    }

    public int rootNumber() {
        return n;
    }
}
