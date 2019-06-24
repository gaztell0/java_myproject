package ru.stqa.pft.sandbox;

public class Point {

  public double x1;
  public double y1;

  public Point(double x1, double y1) {
    this.x1 = x1;
    this.y1 = y1;
  }

    public double distance(Point p2) {
      return Math.sqrt(Math.pow((p2.x1 - this.x1), 2) + Math.pow((p2.y1 - this.y1), 2));
  }

}


