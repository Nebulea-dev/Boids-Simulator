package main.boids;

import main.geometry.vect.Vect;

import java.util.ArrayList;
import java.util.Iterator;

public class Boids implements Iterable<Boid> {

    private final ArrayList<Boid> boids;

    private final double SEUIL_REPOUSSEMENT;

    private final double SEUIL_VISION;

    private final int MAX_WIDTH;

    private final int MAX_HEIGHT;

    public Boids(double distanceRepoussement, double distanceVision, int MAX_WIDTH, int MAX_HEIGHT) {
        this.boids = new ArrayList<>();
        this.SEUIL_REPOUSSEMENT = distanceRepoussement;
        this.SEUIL_VISION = distanceVision;
        this.MAX_WIDTH = MAX_WIDTH;
        this.MAX_HEIGHT = MAX_HEIGHT;
    }

    @Override
    public Iterator<Boid> iterator() {
        return boids.iterator();
    }

    /**
     * Pour chaque boid de la masse donné, calcules les forces et les multiplies par leur coefficient
     * Applique la somme des forces au boid, modifie leur position et verifier qu'il ne sorte pas de la fenetre
     * @param mass des boid qu'on update
     */
    public void update(int mass)
    {
        Vect sumOfForces = new Vect(0 ,0);
        for (Boid boid: boids) {

            // We only update boids with a certain mass
            if(boid.getMass() != mass)
            {
                continue;
            }
            sumOfForces.setX(0);
            sumOfForces.setY(0);

            Vect forceOfCohesion = Vect.mulStatic(cohesion(boid), boid.getCoefOfCohesion());
            Vect forceOfAlignment = Vect.mulStatic(alignment(boid), boid.getCoefOfAlignment());
            Vect forceOfSeparation = Vect.mulStatic(separation(boid), boid.getCoefOfSeparation());
            Vect forceOfFleeing = Vect.mulStatic(flee(boid), boid.getCoefOfFleeing());
            Vect forceOfHunting = Vect.mulStatic(hunt(boid), boid.getCoefOfHunting());

            sumOfForces.addVect(forceOfCohesion);
            sumOfForces.addVect(forceOfAlignment);
            sumOfForces.addVect(forceOfSeparation);
            sumOfForces.addVect(forceOfFleeing);
            sumOfForces.addVect(forceOfHunting);
            boid.applyForce(sumOfForces);

            boid.updatePosition();

            double newX = ((boid.getPosition().getX() % MAX_WIDTH) + MAX_WIDTH) % MAX_WIDTH;  // date : 2h26 resoud le pb de disparition par le haut
            double newY = ((boid.getPosition().getY() % MAX_HEIGHT) + MAX_HEIGHT) % MAX_HEIGHT;  //jsp pas comment c possible
            boid.getPosition().setVector(new Vect(newX, newY));
        }
    }

    /**
     * renvoie la force de cohésion du boid, comme le vecteur vers le barycentre des boids de meme masse
     * @param boid pour lequel on calcul la force
     * @return force de cohésion
     */
    private Vect cohesion(Boid boid)
    {
        int totalBoids = 0;
        Vect barycentre = new Vect(0,0);
        for (Boid b: boids){
            if (b.getMass() == boid.getMass()) {
                double distance = b.getPosition().getDistance(boid.getPosition());
                if(distance > 0 && distance < SEUIL_VISION)
                {
                    totalBoids += 1;

                    // b - boid
                    Vect tempVect = Vect.subStatic(b.getPosition(), boid.getPosition());
                    Vect attractionVect = Vect.normalize(tempVect);
                    barycentre.addVect(attractionVect);
                }
            }
        }

        if(totalBoids > 0)
        {
            barycentre.mulVect((double) 1 /totalBoids);
        }
        return Vect.normalize(barycentre);
    }

    /**
     * renvoie la force d'alignement qui pousse les boid à aller dans le meme sens en calculant l'angle moyen des boids de meme masse
     * @param boid pour lequel on calcul la force
     * @return force d'alignement
     */
    private Vect alignment(Boid boid)
    {
        double moyenneAngle = 0;
        int nbBoidsMemeMass = 0;
        for (Boid b: boids){
            if (b.getMass() == boid.getMass()) {
                if(boid.getPosition().getDistance(b.getPosition()) < SEUIL_VISION){
                    nbBoidsMemeMass++;
                    moyenneAngle += b.getAngle();
                }
            }
        }
        moyenneAngle = moyenneAngle / nbBoidsMemeMass;
        return new Vect(moyenneAngle) ;
    }

    /**
     * renvoie la force de separation pour que les boid ne se superpose pas
     * renvoie une moyenne de la différence entre les vecteurs position des boids proches
     * @param boid pour lequel on calcul la force
     * @return force de separation
     */
    private Vect separation(Boid boid)
    {
        Vect somme = new Vect(0,0);
        for (Boid b: boids){
            if (b != boid){
                if (b.getMass() == boid.getMass())
                {
                    if(boid.getPosition().getDistance(b.getPosition()) < SEUIL_REPOUSSEMENT)
                    {
                        Vect tempVect = Vect.subStatic(boid.getPosition(), b.getPosition());
                        Vect repulsionVect = Vect.mulStatic(tempVect, tempVect.getNorme()/1.5);
                        somme.addVect(repulsionVect);
                    }
                }
            }
        }
        return somme;
    }

    /**
     * S'il y a des boids plus lourd alors renvoie une force qui éloigne le boid (comme cohésion)
     * @param boid pour lequel on calcul la force
     * @return force de flee
     */
    private Vect flee(Boid boid)
    {
        Vect somme = new Vect(0,0);
        for (Boid b: boids){
            if (b.getMass() > boid.getMass())
            {
                if(boid.getPosition().getDistance(b.getPosition()) < SEUIL_VISION)
                {
                    Vect tempVect = Vect.subStatic(boid.getPosition(), b.getPosition());
                    Vect fleeVect = Vect.mulStatic(tempVect, tempVect.getNorme()/1.5);
                    somme.addVect(fleeVect);
                }
            }
        }
        return somme;
    }

    /**
     * S'il y a des boids plus léger renvoie une force qui pousse le boid vers ceux ci
     * @param boid pour lequel on calcul la force
     * @return force de chasse
     */
    private Vect hunt(Boid boid)
    {
        Vect somme = new Vect(0,0);
        for (Boid b: boids){
            if (b.getMass() < boid.getMass())
            {
                if(boid.getPosition().getDistance(b.getPosition()) < SEUIL_VISION)
                {
                    Vect tempVect = Vect.subStatic(b.getPosition(), boid.getPosition());
                    Vect huntVect = Vect.mulStatic(tempVect, tempVect.getNorme()/1.5);
                    somme.addVect(huntVect);
                }
            }
        }
        return somme;
    }


    /**
     * ajoute un boid a l' array des boids
     * @param boid boid a ajouter
     */
    public void add(Boid boid) {
        boids.add(boid);
    }
}
