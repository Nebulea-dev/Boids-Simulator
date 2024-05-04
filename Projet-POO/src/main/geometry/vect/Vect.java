package main.geometry.vect;

public class Vect {
    private double x, y;

    @Override
    public String toString() {
        return "Vect{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * getter de la valeur x
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * setter de x
     * @param x x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * getter de la valeur y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * setter de la valeur y
     * @param y y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Remplace le vecteur par celui donnée en paramètre
     * @param vect vect a modifier
     */
    public void setVector(Vect vect)
    {
        this.x = vect.getX();
        this.y = vect.getY();
    }


    public Vect(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vect(double angle){
        this(Math.cos(angle*2*Math.PI/360), Math.sin(angle*2*Math.PI/360));

    }

    /**
     * renvoie un nouveau vecteur qui est la somme avec le vecteur en paramètre
     * @param vectorToAdd
     * @return vecteur qui est la somme avec le vecteur en paramètre
     */
    public Vect add(Vect vectorToAdd)   // on a ecrit ca a 1h22 parce que je sais pas codé
    {
       double a = this.x + vectorToAdd.getX();  // ducoup quand j'écrit n'importe quoi intelij remonte une erreur
       double b = this.y + vectorToAdd.getY();  // et je suis sur de ce que je modifie


       return new Vect(a, b);
    }

    /**
     * modifie le vecteur en la somme de celui-ci avec le vecteur donné en paramètre
     * @param vect
     */
    public void addVect(Vect vect){
        this.x += vect.getX();
        this.y += vect.getY();
    }

    /**
     * méthode static qui renvoie la somme des deux vecteurs
     * @param a
     * @param b
     * @return la somme des deux vecteurs
     */
    public static Vect addStatic(Vect a, Vect b)
    {
        return new Vect(a.getX() - b.getX(), a.getY() - b.getY());
    }

    /**
     * renvoie un nouveau vecteur qui est le produit du vecteur avec le scalaire donné en paramètre
     * @param factorToMultiplyBy scalaire
     * @return produit du vecteur avec le scalaire donné en paramètre
     */
    public Vect mul(double factorToMultiplyBy)
    {
        double a = this.x * factorToMultiplyBy;
        double b = this.y * factorToMultiplyBy;
        return new Vect(a, b);
    }

    /**
     * modifie le vecteur en son produit par le scalaire donné en paramètre
     * @param facteur
     */
    public void mulVect(double facteur){
        this.x *= facteur;
        this.y *= facteur;
    }

    /**
     * méthode statique qui multiplie le vecteur donnée en paramètre par le scalaire donné en paramètre
     * @param a le vecteur
     * @param factorToMultiplyBy le scalaire
     * @return (int)factorToMultiplyBy * (vect)a
     */
    public static Vect mulStatic(Vect a, double factorToMultiplyBy)
    {
        return new Vect(a.getX() * factorToMultiplyBy, a.getY() * factorToMultiplyBy);
    }

    /**
     * renvoie un nouveau vecteur qui est la différence avec le vecteur en paramètre
     * @param vect
     * @return vecteur qui est la somme avec le vecteur en paramètre
     */
    public Vect sub(Vect vect){
        double a = this.x - vect.getX();
        double b = this.y - vect.getY();
        return new Vect(a, b);
    }

    /**
     * modifie le vecteur en la différence de celui-ci avec le vecteur donné en paramètre
     * @param vect
     */
    public void subVect(Vect vect){
        this.x -= vect.getX();
        this.y -= vect.getY();
    }

    /**
     * méthode static qui renvoie la différence des deux vecteurs
     * @param a
     * @param b
     * @return la différence des deux vecteurs
     */
    public static Vect subStatic(Vect a, Vect b)
    {
        return new Vect(a.getX() - b.getX(), a.getY() - b.getY());
    }


    /**
     * renvoie l'angle du vecteur en degré
     * /!\ le repère et en haut a gauche
     * Si x et y sont nulles alors getAngle renvoie 0
     * @return angle du vecteur en degré
     */
    public double getAngle(){
        if (this.x == 0 && this.y == 0){
            return 0;
        }
        if (this.x == 0 && this.y < 0){
            return 90;
        }
        if (this.x == 0 && this.y > 0){
            return 270;
        }

        double rad = Math.atan2(-this.y, this.x);
        if(rad < 0)
        {
            rad += 2*Math.PI;
        }
        rad = (rad * 360) / (2*Math.PI);
        return rad;
    }

    /**
     * renvoie la distance entre les 2 vecteurs
     * calculé comme la norme euclidienne de leur différence
     * @param vect
     * @return distance entre les 2 vecteurs
     */
    public double getDistance(Vect vect){
        return Math.sqrt(Math.pow((this.getX() - vect.getX()), 2) + Math.pow((this.getY() - vect.getY()), 2));
    }

    /**
     * calcul la norme euclidienne du vecteur
     * @return norme du vecteur
     */
    public double getNorme(){
        return this.getDistance(new Vect(0,0));
    }

    /**
     * methode static qui renvoie un nouveau vecteur normalisé
     * @param vect vecteur a normalisé
     * @return nouveau vecteur normalisé
     */
    public static Vect normalize(Vect vect){
        double norm = vect.getNorme();
        if(norm == 0)
        {
            return new Vect(0, 0);
        }
        return new Vect(vect.getX()/norm, vect.getY()/norm);
    }

}
