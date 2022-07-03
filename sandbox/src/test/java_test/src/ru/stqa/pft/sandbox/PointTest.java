package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point a1 = new Point(5, 6);
        Point a2 = new Point(4, 1);
        Assert.assertEquals(a1.distance(a2), 5.0990195135927845);

        Point c1 = new Point(5, 5);
        Point c2 = new Point(5, 5);
        Assert.assertEquals(c1.distance(c2), 0.0);

    }
}
