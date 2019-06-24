package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint1() {

    Point p1 = new Point(-50.5, 34.7, 5, 10.5);
    Assert.assertEquals(p1.distance(),60.54659362837847);

  }

  @Test
  public void testPoint2() {

    Point p2 = new Point(5, 5, 6, 5);
    Assert.assertEquals(p2.distance(), 1.0);

  }

  @Test
  public void testPoint3() {

    Point p3 = new Point(40, 50, 10, 50);
    Assert.assertEquals(p3.distance(), 30.0);

  }

}
