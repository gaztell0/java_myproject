package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testPoint1() {

    Point p1 = new Point(0,10);
    Point p2 = new Point( 0, 40);
    Assert.assertEquals(p1.distance(p2),30.0);

  }

  @Test
  public void testPoint2() {

    Point p1 = new Point(0,0);
    Point p2 = new Point( 0, 1000);
    Assert.assertEquals(p1.distance(p2),1000.0);

  }

  @Test
  public void testPoint3() {

    Point p1 = new Point(0,10);
    Point p2 = new Point( 0, 2000);
    Assert.assertEquals(p1.distance(p2),1990.0);

  }

}
