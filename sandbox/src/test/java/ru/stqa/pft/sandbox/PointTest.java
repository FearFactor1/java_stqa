package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTest {

    @Test
    public void testDistance() {
        Point a1 = new Point(5,6);
        Point a2 = new Point(5, 5);
        Assert.assertEquals(a1.distance(a2), 1.0);


    }
    @Test
    public void testDistanceZero() {
        Point c1 = new Point(5, 5);
        Assert.assertEquals(c1.distance(c1), 0.0);
    }
}
