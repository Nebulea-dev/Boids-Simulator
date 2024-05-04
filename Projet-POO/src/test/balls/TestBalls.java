package test.balls;

import main.balls.Ball;
import main.balls.Balls;

public class TestBalls {

    public static void main(String[] args) {

        Balls balls = new Balls();
        Ball ball1 = new Ball(0,1, 10, 10);
        Ball ball2 = new Ball(2,4, 10, 10);
        Ball ball3 = new Ball(3,6, 10, 10);
        Ball ball4 = new Ball(8,1, 10, 10);

        balls.getList().add(ball1);
        balls.getList().add(ball2);
        balls.getList().add(ball3);
        balls.getList().add(ball4);

        System.out.println("valeur par defaut : ");
        System.out.println(balls);

        balls.translate(2,4);

        System.out.println("valeur apres mouvement : ");
        System.out.println(balls);

        balls.reInit();

        System.out.println("valeur apres remise a defaut : ");
        System.out.println(balls);

    }
}
