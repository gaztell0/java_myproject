package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(100,100);
    Point p2 = new Point(10,10);

    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p1.distance(p2));
  }

}