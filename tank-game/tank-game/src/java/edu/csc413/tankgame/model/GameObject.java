package edu.csc413.tankgame.model;

import java.awt.*;

public abstract class GameObject {

    private double MOVEMENT_SPEED = 4.0;
    private final String id;
    private double x;
    private double y;
    private double angle;
    private double width;
    private double height;


    public GameObject(String id, double x, double y, double angle) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }


    public void setObjX(double XVel){
        x = XVel;
    }

    public void setObjY(double YVel){
        y = YVel;
    }

    public void setObjAngle(Double ObjAngle){
        angle = ObjAngle;
    }


    public String getObjId() {
        return id;
    }

    public double getObjX() {
        return x;
    }

    public double getObjY() {
        return y;
    }

    public void setMoveSpeed(double MS){
        MOVEMENT_SPEED = MS;
    }

    public double getMovementSpeed(){
        return MOVEMENT_SPEED;
    }


    public double getObjAngle() {
        return angle;
    }
    protected void moveForward() {
        x += MOVEMENT_SPEED * Math.cos(angle);
        y += MOVEMENT_SPEED * Math.sin(angle);
    }



    public abstract void move(GameState gameState);



    public Rectangle getBoundsWalls() {
        return new Rectangle((int)getObjX(),(int)getObjY(),80,390);
    }
    public Rectangle getSpeedPowerBounds(){
        return new Rectangle((int)getObjX(),(int)getObjY(),33,22);
    }

    public Rectangle getShellBounds(){
        return new Rectangle((int)getObjX(),(int)getObjY(),10,10);
    }

    public Rectangle getHealthBoostBounds(){
        return new Rectangle((int)getObjX(),(int)getObjY(),40,23);
    }

    public Rectangle getBreakWallBounds(){
        return  new Rectangle((int)getObjX(),(int)getObjY(),55,46);
    }

}
