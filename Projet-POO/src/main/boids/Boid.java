package main.boids;


import main.geometry.vect.Vect;

import java.awt.*;

public abstract class Boid {
    private final int mass;
    private final Color color;
    double coefOfCohesion;
    double coefOfAlignment;
    double coefOfSeparation;
    double coefOfFleeing;
    double coefOfHunting;
    double coefOfFriction;

    private final Vect acceleration;
    private final Vect speed;
    private final Vect position;

    public int getMass() {
        return mass;
    }

    public Color getColor() {
        return color;
    }

    public Vect getPosition(){
        return position;
    }

    public Vect getVitesse() {
        return speed;
    }

    public double getCoefOfCohesion() {
        return coefOfCohesion;
    }

    public double getCoefOfAlignment() {
        return coefOfAlignment;
    }

    public double getCoefOfSeparation() {
        return coefOfSeparation;
    }

    public double getCoefOfFleeing() {
        return coefOfFleeing;
    }

    public double getCoefOfHunting() {
        return coefOfHunting;
    }

    public double getCoefOfFriction() {
        return coefOfFriction;
    }

    public Boid(int mass, Color color, Vect acceleration, Vect speed, Vect position) {
        this.mass = mass;
        this.color = color;
        this.acceleration = acceleration;
        this.speed = speed;
        this.position = position;
    }

    /**
     * renvoie l'angle du vecteur position du boid en degré
     * @return angle du vecteur vitesse du boid
     */
    public double getAngle()
    {
        return this.speed.getAngle();
    }

    /**
     * ajoute la somme des forces à l'accélération
     * ajoute le vecteur accélération au vecteur vitesse puis limite la vitesse
     * @param force somme des différences forces qui s'applique au boid
     */
    public void applyForce(Vect force)
    {
        this.acceleration.addVect(force);
        this.speed.addVect(acceleration);
        if(this.speed.getNorme() > 30)
        {
            this.speed.setVector(Vect.mulStatic(Vect.normalize(this.speed), 30));
        }
    }

    /**
     * ajoute le vecteur vitesse au vecteur position et remet l'accélération comme un vecteur nul
     */
    public void updatePosition()
    {
        this.position.addVect(speed);
        this.acceleration.setVector(new Vect(0, 0));
    }
}
