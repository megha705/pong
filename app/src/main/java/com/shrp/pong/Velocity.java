package com.shrp.pong;

/**
 * Created by shriroop on 12-Jun-17.
 */

public class Velocity {
    double xVelocity;
    double yVelocity;

    public Velocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
    }

    public void ReverseX() {
        this.xVelocity = -this.xVelocity;
    }

    public double getNewXPosition(double xPosition, double frameTime) {
        return xPosition + frameTime * xVelocity;
    }

    public double getNewYPosition(double yPosition, double frameTime) {
        return yPosition + frameTime * yVelocity;
    }

    public double getPreviousXPosition(double xPosition, double frameTime) {
        return xPosition - frameTime * xVelocity;
    }

    public double getPreviousYPosition(double yPosition, double frameTime) {
        return yPosition - frameTime * yVelocity;
    }
}
