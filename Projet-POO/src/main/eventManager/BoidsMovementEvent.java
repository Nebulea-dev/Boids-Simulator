package main.eventManager;

import main.boids.Boids;

public class BoidsMovementEvent extends Event {

    private Boids boids;
    private int mass;
    public BoidsMovementEvent(long date) {
        super(date);
    }

    public void setBoids(Boids boids)
    {
        this.boids = boids;
    }
    public void setBoidsMass(int mass)
    {
        this.mass = mass;
    }

    /**
     * se lance quand l'événement atteint ca date d'execution
     */
    public void execute()
    {
        this.boids.update(this.mass);
    }
}
