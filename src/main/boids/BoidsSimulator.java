package main.boids;

import gui.GUISimulator;
import gui.Simulable;
import main.eventManager.BoidsMovementEvent;
import main.eventManager.Event;
import main.eventManager.EventList;
import main.eventManager.EventManager;
import main.geometry.guiExtension.TriangleBoid;
import main.geometry.vect.Vect;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class BoidsSimulator implements Simulable {

    private final GUISimulator window;
    private final EventManager eventManager;
    private Boids boids;
    private final ArrayList<Integer> nbBoidsList;
    private final ArrayList<Integer> massList;
    private final ArrayList<Color> colorList;
    private final ArrayList<TypeBoid> typeList;
    private final int numOfTypesOfBoids;

    private final int DISTANCE_REPOUSSEMENT;
    private final int DISTANCE_VISION;

    /**
     * modifie la liste de boids avec une vitesse et une accélération nulle et une position aléatroire dans la fenetre
     * @param numOfBoids  numOfBoids nombre de boids à générer
     * @param mass mass des boids
     * @param color couleur du boid
     * @param typeBoid type du boid (PREDATEUR, PROIE)
     */
    private void generateFlock(int numOfBoids, int mass, Color color, TypeBoid typeBoid){
        Random r = new Random();
        for (int i = 0; i < numOfBoids; i++) {
            if(typeBoid == TypeBoid.PROIE)
            {
                BoidProie proie = new BoidProie(mass,
                        color,
                        new Vect(0,0),
                        new Vect(0,0),
                        new Vect(r.nextInt(window.getPanelWidth()), r.nextInt(window.getPanelHeight())));
                boids.add(proie);
            }
            if(typeBoid == TypeBoid.PREDATEUR)
            {
                BoidPredateur predateur = new BoidPredateur(mass,
                        color,
                        new Vect(0,0),
                        new Vect(0,0),
                        new Vect(r.nextInt(window.getPanelWidth()), r.nextInt(window.getPanelHeight())));
                boids.add(predateur);
            }
        }
    }

    public BoidsSimulator(GUISimulator window,
                          ArrayList<Integer> nbBoidsList,
                          ArrayList<Integer> massList,
                          ArrayList<Color> colorList,
                          ArrayList<EventList> updateList,
                          ArrayList<TypeBoid> typeList,
                          int numOfTypesOfBoids) {
        this.window = window;
        this.nbBoidsList = nbBoidsList;
        this.massList = massList;
        this.colorList = colorList;
        this.typeList = typeList;
        this.numOfTypesOfBoids = numOfTypesOfBoids;

        this.DISTANCE_REPOUSSEMENT = 25;
        this.DISTANCE_VISION = 400;

        this.boids = new Boids(DISTANCE_REPOUSSEMENT, DISTANCE_VISION, window.getPanelWidth(), window.getPanelHeight());
        this.eventManager = new EventManager(new ArrayList<>());

        for(int i = 0; i < numOfTypesOfBoids; i++) {
            generateFlock(nbBoidsList.get(i), massList.get(i), colorList.get(i), typeList.get(i));
            for (Event event: updateList.get(i)) {
                BoidsMovementEvent boidsMovementEvent = (BoidsMovementEvent) event;
                boidsMovementEvent.setBoids(this.boids);
                boidsMovementEvent.setBoidsMass(massList.get(i));
                this.eventManager.addEvent(event);
            }
        }
    }


    /**
     * renvoie le prochaine etat en applicant les forces puis en dessinant
     */
    @Override
    public void next() {
        this.eventManager.next();
        this.drawFlock();
    }

    /**
     * dessine chaque boid dans la fenetre
     */
    public void drawFlock()
    {
        this.window.reset();

        for (Boid boid: boids) {
            TriangleBoid boidRepresentation = new TriangleBoid(
                    (int)boid.getPosition().getX(),
                    (int)boid.getPosition().getY(),
                    boid.getMass(),
                    boid.getColor(),
                    new Color(
                            boid.getColor().getRed(),
                            boid.getColor().getGreen(),
                            boid.getColor().getBlue(),
                            100)
            );
            boidRepresentation.rotate(boid.getVitesse().getAngle());
            this.window.addGraphicalElement(boidRepresentation);
        }
    }

    /**
     * restart, regénere une nouvelle flock avec generateFlock puis la dessine
     */
    @Override
    public void restart() {
        this.boids = new Boids(this.DISTANCE_REPOUSSEMENT, this.DISTANCE_VISION, window.getPanelWidth(), window.getPanelHeight());
        for(int i = 0; i < this.numOfTypesOfBoids; i++)
        {
            this.generateFlock(nbBoidsList.get(i), massList.get(i), colorList.get(i), typeList.get(i));
        }

        // Re-link the events with the new boids
        for (Event event: this.eventManager.getEventList()) {
            ((BoidsMovementEvent)event).setBoids(this.boids);
        }

        this.eventManager.restart();

        this.drawFlock();
    }
}
