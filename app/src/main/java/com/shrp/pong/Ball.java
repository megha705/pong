package com.shrp.pong;

import android.graphics.drawable.Drawable;

/**
 * Created by shriroop on 12-Jun-17.
 */

public class Ball extends Sprite {
    private VelocityGenerator velocityGenerator;

    Ball(Drawable ball, int canvasWidth, int canvasHeight, VelocityGenerator velocityGenerator) {
        super(ball, canvasWidth, canvasHeight, 12, true);
        this.velocityGenerator = velocityGenerator;
    }

    void reverseXVelocity() {
        this.velocity.ReverseX();
        this.reverseAnimation();
    }

    void setInitialVelocity() {
        this.velocity = velocityGenerator.GenerateInitialVelocity();
    }

    void generateNewVelocityDown() {
        this.velocity = this.velocityGenerator.GenerateNewReverseDown(velocity);
    }

    void generateNewVelocityUp() {
        this.velocity = this.velocityGenerator.GenerateNewReverseUp(velocity);
    }

    boolean isOutOfYBounds(double frameTime) {
        return isOutOfLowerBounds(frameTime) || isOutOfUpperBounds(frameTime);
    }
}
