package main.geometry.guiExtension;

import gui.GraphicalElement;

import java.awt.*;

public class Triangle implements GraphicalElement {
    private final Color drawColor;
    private final Color fillColor;
    private Polygon triangleShape;

    public Polygon getTriangleShape() {
        return triangleShape;
    }

    public void setTriangleShape(Polygon triangleShape) {
        this.triangleShape = triangleShape;
    }

    Triangle(int x1, int y1, int x2, int y2, int x3, int y3, Color drawColor, Color fillColor) {
        this.drawColor = drawColor;
        this.fillColor = fillColor;

        int[] xpoints = new int[3];
        xpoints[0] = x1;
        xpoints[1] = x2;
        xpoints[2] = x3;

        int[] ypoints = new int[3];
        ypoints[0] = y1;
        ypoints[1] = y2;
        ypoints[2] = y3;

        this.triangleShape = new Polygon(xpoints, ypoints, 3);
    }

    /**
     * dessine le triangle
     * @param g2d graphics method
     */
    public void paint(Graphics2D g2d) {
        Stroke var2 = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2.0F));
        Color var3 = g2d.getColor();
        if (this.fillColor != null) {
            g2d.setColor(this.fillColor);
            g2d.fill(this.triangleShape);
        }

        g2d.setColor(this.drawColor);
        g2d.draw(this.triangleShape);
        g2d.setColor(var3);
        g2d.setStroke(var2);
    }


    public String toString() {
        return this.drawColor.toString() + " triangle";
    }
}
