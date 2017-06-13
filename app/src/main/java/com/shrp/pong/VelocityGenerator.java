package com.shrp.pong;

import java.util.Random;

/**
 * Created by shriroop on 12-Jun-17.
 */

public class VelocityGenerator {
    private static final int MaxSpeed = 298;
    private static final int MinimumYSpeed = 123;
    private static final int MinimumXSpeed = 123;
    private Random speedRandomizer;

    public VelocityGenerator()
    {
        speedRandomizer = new Random(12313975);
    }

    public Velocity GenerateInitialVelocity() {
        return new Velocity(162.0, -152.0);
    }

    public Velocity GenerateNewReverseDown(Velocity velocity) {
        int xVelocity = GenerateNewXSpeed();
        int yVelocity = GenerateNewYSpeed();

        if (velocity.xVelocity > 0)
            xVelocity = -xVelocity;

        return new Velocity(xVelocity, yVelocity);
    }

    public Velocity GenerateNewReverseUp(Velocity velocity) {
        int xVelocity = GenerateNewXSpeed();
        int yVelocity = GenerateNewYSpeed();

        if (velocity.xVelocity > 0)
            xVelocity = -xVelocity;

        return new Velocity(xVelocity, -yVelocity);
    }

    private int GenerateNewYSpeed() {
        return speedRandomizer.nextInt(MaxSpeed) + MinimumYSpeed;
    }

    private int GenerateNewXSpeed() {
        return speedRandomizer.nextInt(MaxSpeed) + MinimumXSpeed;
    }
}
