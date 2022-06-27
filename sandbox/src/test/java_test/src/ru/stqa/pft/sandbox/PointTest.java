package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point p = new Point(10, 5);
        Assert.assertEquals(p.distance(), 5);

        Point c = new Point(5, 5);
        Assert.assertEquals(c.distance(), 0);

    }
}
