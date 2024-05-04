package test.balls;

import gui.GUISimulator;
import gui.Oval;
import main.balls.BallsSimulator;
import main.balls.Ball;
import java.awt.Color;
public class TestBallsSimulator {
public static void main (String [] args) {
        GUISimulator window = new GUISimulator(500 , 500 , Color . BLACK);
        BallsSimulator ballsSimulator = new BallsSimulator(window);

        window.setSimulable(ballsSimulator);

        for(Ball ball : ballsSimulator.getBalls())
        {
            window.addGraphicalElement(
                    new Oval((int)ball.getX(), (int)ball.getY(), Color.MAGENTA, Color.MAGENTA, 25)
            );
        }
    }
}