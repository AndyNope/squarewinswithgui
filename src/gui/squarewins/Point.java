/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.squarewins;

/**
 *
 * @author kbwschuler
 */
public class Point {
   /**
    * color: b = blue r = red c = blank
    * pointTaken: check if point is occupied
    */
   private double x;
   private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
     
}
