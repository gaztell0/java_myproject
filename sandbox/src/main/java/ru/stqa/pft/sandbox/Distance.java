package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(100,100);
    Point p2 = new Point(10,10);
    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p1.distance(p2));

    Point p3 = new Point(50,0);
    Point p4 = new Point(10,0);
    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p3.distance(p4));

    Point p5 = new Point(0,0);
    Point p6 = new Point(0,1000);
    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p5.distance(p6));

  }

}