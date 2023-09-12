package edu.csc413.tankgame.model;

import java.awt.*;

/**
 * Model class representing a tank in the game. A tank has a position and an angle. It has a movement speed and a turn
 * speed, both represented below as constants.
 */
// TODO: Notice that Tank has a lot in common with Shell. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public abstract class Tank  {
    private static double MOVEMENT_SPEED = 1.0;
    private static final double TURN_SPEED = Math.toRadians(3.0);

    private final String id;
    private double x;
    private double y;
    private double angle;
    private double width;
    private double height;
    public static double CushionAiTankHealth;
    public static double TurretAiTankHealth;
    public static double PlayerTankHealth;



    public Tank(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }

    public String getId() {
        return id;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {
        return angle;
    }

    public void setX(double XVel){
        x = XVel;
    }

    public void setY(double YVel){
        y = YVel;
    }

    public void move(GameState gameState) {

    }


    // TODO: The methods below are provided so you don't have to do the math for movement. However, note that they are
    // protected. You should not be calling these methods directly from outside the Tank class hierarchy. Instead,
    // consider how to design your Tank class(es) so that a Tank can represent both a player-controlled tank and an AI
    // controlled tank.


    protected void moveForward() {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void moveBackward() {
        x -= MOVEMENT_SPEED * Math.cos(angle);
        y -= MOVEMENT_SPEED * Math.sin(angle);
    }

    protected void turnLeft() {
        angle -= TURN_SPEED;
    }

    protected void turnRight() {
        angle += TURN_SPEED;
    }

    // The following methods will be useful for determining where a shell should be spawned when it
    // is created by this tank. It needs a slight offset so it appears from the front of the tank,
    // even if the tank is rotated. The shell should have the same angle as the tank.

    public double getShellX() {
        return getX() + 30.0 * (Math.cos(getAngle()) + 0.5);
    }

    public double getShellY() {
        return getY() + 30.0 * (Math.sin(getAngle()) + 0.5);
    }

    public Rectangle getBounds() {
        return new Rectangle((int)getX(),(int)getY(),55, 55);
    }
    public void setMovementSpeed(double ms){
        MOVEMENT_SPEED = ms;
    }


}
