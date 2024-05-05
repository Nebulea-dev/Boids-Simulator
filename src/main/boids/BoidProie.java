package main.boids;

import main.geometry.vect.Vect;

import java.awt.*;

public class BoidProie extends Boid {

    public BoidProie(int mass, Color color, Vect acceleration, Vect speed, Vect position) {
        super(mass, color, acceleration, speed, position);

        this.coefOfCohesion = 1;
        this.coefOfAlignment = 0.1;
        this.coefOfSeparation = 0.1;
        this.coefOfFleeing = 10;
        this.coefOfHunting = 0.01;
    }
}
