package main.boids;

import main.geometry.vect.Vect;

import java.awt.*;

public class BoidPredateur extends Boid {

    public BoidPredateur(int mass, Color color, Vect acceleration, Vect speed, Vect position) {
        super(mass, color, acceleration, speed, position);

        this.coefOfCohesion = 0;
        this.coefOfAlignment = 0;
        this.coefOfSeparation = 0.1;
        this.coefOfFleeing = 0;
        this.coefOfHunting = 0.01;
    }
}
