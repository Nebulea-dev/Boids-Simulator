package main.balls;

import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;

public class BallsSimulator implements Simulable {

    private final Balls balls = new Balls();
    private final GUISimulator window;

    public Balls getBalls() {
        return balls;
    }

    public BallsSimulator(GUISimulator window)
    {
        this.window = window;

        Ball ball1 = new Ball(100,100, 10, 10);
        Ball ball2 = new Ball(200,400, 10, 10);
        Ball ball3 = new Ball(300,200, 10, 10);
        Ball ball4 = new Ball(500,100, 10, 10);

        balls.getList().add(ball1);
        balls.getList().add(ball2);
        balls.getList().add(ball3);
        balls.getList().add(ball4);
    }

    /**
     * *fait avancer la simulation d'une étape en translatant chaque balle
     */
    @Override
    public void next() {

        for(Ball ball : this.getBalls())
        {
            this.window.addGraphicalElement(
                    new Oval((int)ball.getX(), (int)ball.getY(), Color.BLACK, Color.BLACK, 27)
            );
        }

        for(Ball ball : this.getBalls())
        {
            ball.translate(ball.getOffsetX(), ball.getOffsetY());

            if(ball.getX() < 25 || ball.getX() > 500){
                ball.swapOffsetX();
            }
            if(ball.getY() < 25 || ball.getY() > 500){
                ball.swapOffsetY();
            }

            this.window.addGraphicalElement(
                    new Oval((int)ball.getX(), (int)ball.getY(), Color.MAGENTA, Color.MAGENTA, 25)
            );
        }
    }

    /**
     * réinitialise la simulation en reinitialisant al positions de chaque ball
     */
    @Override
    public void restart() {
        balls.reInit();
    }
}
