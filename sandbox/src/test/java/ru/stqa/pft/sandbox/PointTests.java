package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

import static ru.stqa.pft.sandbox.Point.distance;


/**
 * Created by IEUser on 7/21/2016.
 */
public class PointTests {

    @Test
    public void testArea(){
        Point a = new Point(2,3);
        Point b = new Point(3,3);
        Assert.assertEquals(Point.distance(a,b), 1.0);
    }
    @Test
    public void testSecondArea(){
        Point a = new Point(2,3);
        Point b = new Point(5,6);
        Assert.assertEquals(Point.distance(a,b), 4.242640687119285);
    }



}
