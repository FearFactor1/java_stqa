package ru.stqa.pft.sandbox;

public class RunPoint {
    public static void main(String[] args) {
        Point p = new Point(7, 5);
        System.out.println("расстояния между точками " + p.p1 + " и " + p.p2 + " = " + p.distance());
    }
}
