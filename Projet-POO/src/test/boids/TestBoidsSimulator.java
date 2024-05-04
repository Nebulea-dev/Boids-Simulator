package test.boids;

import gui.GUISimulator;
import main.boids.Boid;
import main.boids.BoidPredateur;
import main.eventManager.BoidsMovementEvent;
import main.boids.BoidsSimulator;
import main.boids.TypeBoid;
import main.eventManager.EventList;
import main.geometry.vect.Vect;

import java.awt.*;
import java.util.*;

public class TestBoidsSimulator {

    public static void main (String [] args) {

        // These windows values work very well on a 1440p monitor
        // If you have a smaller screen, you may want to change these values
        GUISimulator window = new GUISimulator(2487 , 1323 , Color.WHITE);

        ArrayList<Integer> nbBoidsList = new ArrayList<>();
        nbBoidsList.add(100);
        nbBoidsList.add(5);

        ArrayList<Integer> massList = new ArrayList<>();
        massList.add(25);
        massList.add(200);

        ArrayList<Color> colorList = new ArrayList<>();
        colorList.add(Color.green);
        colorList.add(Color.red);

        ArrayList<EventList> updateList = new ArrayList<>();

        EventList smallBoidEvents = new EventList();
        smallBoidEvents.add(new BoidsMovementEvent(1));
        smallBoidEvents.add(new BoidsMovementEvent(2));

        EventList bigBoidEvents = new EventList();
        bigBoidEvents.add(new BoidsMovementEvent(1));

        updateList.add(smallBoidEvents);
        updateList.add(bigBoidEvents);


        ArrayList<TypeBoid> typeBoid = new ArrayList<>();
        typeBoid.add(TypeBoid.PROIE);
        typeBoid.add(TypeBoid.PREDATEUR);


        BoidsSimulator boidsSimulator = new BoidsSimulator(window, nbBoidsList, massList, colorList, updateList, typeBoid, 2);

        window.setSimulable(boidsSimulator);
        boidsSimulator.drawFlock();
    }
}
