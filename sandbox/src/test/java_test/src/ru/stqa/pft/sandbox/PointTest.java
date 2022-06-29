package ru.stqa.pft.sandbox;
import static ru.stqa.pft.sandbox.Point.distance;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance() {
        Point a1 = new Point(5, 6, 8,2);
        Point a2 = new Point(8, 1, 5,2);
        Assert.assertEquals(distance(a1, a2), 4);

        Point c1 = new Point(5, 6, 8,2);
        Point c2 = new Point(8, 1, 5,2);
        Assert.assertEquals(Point.distance(c1, c2), 4);

    }
}
