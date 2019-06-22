package ru.stqa.pft.sandbox;

public class Distance {

  public static void main(String[] args) {

    Point p1 = new Point(-100, 0, 100, 0);
    Point p2 = new Point(100, 100, -100, -100);
    Point p3 = new Point(-555, 1000, 765, -40);


    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p1.distance());
    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p2.distance());
    System.out.println("Расстояние между двумя точками на двумерной плоскости = " + p3.distance());

  }

}
