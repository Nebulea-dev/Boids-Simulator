package main.geometry.guiExtension;

import main.geometry.vect.Vect;

import java.awt.*;

public class TriangleBoid extends Triangle {

    private final int centerX;
    private final int centerY;
    private final int size;
    private final Color drawColor;
    private final Color fillColor;

    public TriangleBoid(int centerX, int centerY, int size, Color drawColor, Color fillColor) {
        super(centerX,
                centerY - size,
                centerX + size / 2,
                centerY + size,
                centerX - size / 2,
                centerY + size,
                drawColor,
                fillColor);

        this.centerX = centerX;
        this.centerY = centerY;
        this.size = size;
        this.drawColor = drawColor;
        this.fillColor = fillColor;
    }

    /**
     * renvoie le triangle tourner de theta
     * @param theta angle de la rotation en degré
     */
    public void rotate(double theta) {

        theta -= 90;
        Polygon triangleShape = getTriangleShape();
        Polygon originTriangle = new Polygon(triangleShape.xpoints, triangleShape.ypoints, triangleShape.npoints);

        originTriangle.translate(-centerX, -centerY);

        Vect point1 = rotateVector(new Vect(originTriangle.xpoints[0], originTriangle.ypoints[0]), theta);
        Vect point2 = rotateVector(new Vect(originTriangle.xpoints[1], originTriangle.ypoints[1]), theta);
        Vect point3 = rotateVector(new Vect(originTriangle.xpoints[2], originTriangle.ypoints[2]), theta);

        int[] xpoints = new int[3];
        xpoints[0] = (int)point1.getX();
        xpoints[1] = (int)point2.getX();
        xpoints[2] = (int)point3.getX();

        int[] ypoints = new int[3];
        ypoints[0] = (int)point1.getY();
        ypoints[1] = (int)point2.getY();
        ypoints[2] = (int)point3.getY();

        Polygon rotatedPolygon = new Polygon(xpoints, ypoints, 3);
        rotatedPolygon.translate(centerX, centerY);

        this.setTriangleShape(rotatedPolygon);
    }


    /**
     * tourne le vecteur (multiplication par la matrice de rotation)
     * pour un repère en haut a gauche
     * @param vector vecteur a multiplier
     * @param angle angle de rotation
     * @return le vecteur après rotation
     */
    private static Vect rotateVector(Vect vector, double angle) {
        // Convert angle to radians
        double angleRad = Math.toRadians(angle);

        // Create the rotation matrix
        double[][] rotationMatrix = {
                {Math.cos(angleRad), Math.sin(angleRad)},
                {-Math.sin(angleRad), Math.cos(angleRad)}
        };

        // Multiply the rotation matrix by the vector
        Vect rotatedVector = new Vect(0,0);
        rotatedVector.setX(rotationMatrix[0][0] * vector.getX() + rotationMatrix[0][1] * vector.getY());
        rotatedVector.setY(rotationMatrix[1][0] * vector.getX() + rotationMatrix[1][1] * vector.getY());

        return rotatedVector;
    }
}
