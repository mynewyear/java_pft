package ru.stqa.pft.sandbox;

public class Distance {
    public static void main(String[] args) {

        Point pointA = new Point(2,3);
        Point pointB = new Point(5,6);

        System.out.println("Distance between a and b = " + Point.distance(pointA, pointB));
    }
}
