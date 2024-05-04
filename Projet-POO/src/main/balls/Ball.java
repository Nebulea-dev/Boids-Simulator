package main.balls;

import java.awt.*;

public class Ball extends Point {
    private final int defaultX;
    private final int defaultY;

    private int offsetX;
    private int offsetY;


    public int getOffsetX() {
        return offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public int getDefaultX() {
        return defaultX;
    }

    public int getDefaultY() {
        return defaultY;
    }


    public Ball(int defaultX, int defaultY, int offsetX, int offsetY) {
        super(defaultX, defaultY);
        this.defaultX = defaultX;
        this.defaultY = defaultY;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    /**
     * change offssetX en -offsetX
     */
    public void swapOffsetX()
    {
        this.offsetX = -this.offsetX;
    }

    /**
     * change offssetY en -offsetY
     */
    public void swapOffsetY()
    {
        this.offsetY = -this.offsetY;
    }



}
