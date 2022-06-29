package ru.stqa.pft.sandbox;

import static ru.stqa.pft.sandbox.Point.distance;


public class RunPoint {
    public static void main(String[] args) {
        Point p1 = new Point(5, 6, 8,2);
        Point p2 = new Point(8, 1, 5,2);

        System.out.println("Расстояние между точками (" + p1 + ") и (" + p2 + ") = " + distance(p1, p2));
    }
}






