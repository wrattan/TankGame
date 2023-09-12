package edu.csc413.tankgame.model;

import edu.csc413.tankgame.GameDriver;
import edu.csc413.tankgame.view.RunGameView;

/**
 * Model class representing a shell that has been fired by a tank. A shell has a position and an angle, as well as a
 * speed. Shells by default should be unable to turn and only move forward.
 */
// TODO: Notice that Shell has a lot in common with Tank. For full credit, you will need to find a way to share code
// between the two classes so that the logic for e.g. moveForward, etc. are not duplicated.
public class Shell extends GameObject {
    private static final String SHELL_ID_PREFIX = "shell-";
    private static final double MOVEMENT_SPEED = 4.0;
    private static long uniqueId = 0L;


    public Shell(String id, double x, double y, double angle){
        super(id,x,y,angle);
    }

    @Override
    public void move(GameState gameState) {
        moveForward();




        Tank CushionAi = gameState.returnCushionAi(GameState.AI_TANK_ID);

        if(getShellBounds().intersects(CushionAi.getBounds())){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
            RunGameView.CushionTankHP -=100;
        }

        Tank turretAi = gameState.returnTurretTank(GameState.TURRET_TANK_ID);
        if(getShellBounds().intersects(turretAi.getBounds())){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
            RunGameView.TurretHP -=100;
        }


        GameObject Wall1 = gameState.returnWall1(GameState.WALL_ID1);
        if(getShellBounds().intersects(Wall1.getBoundsWalls()) && getObjX()>= Wall1.getObjX()){

            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);

        }else if(getShellBounds().intersects(Wall1.getBoundsWalls()) && getObjX() <= Wall1.getObjX()){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);

        }
        GameObject Wall2 = gameState.returnWall2(GameState.WALL_ID2);
        if(getShellBounds().intersects(Wall2.getBoundsWalls()) && getObjX()>= Wall2.getObjX()){

            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }else if(getShellBounds().intersects(Wall2.getBoundsWalls()) && getObjX() <= Wall2.getObjX()){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }

        GameObject Wall3 = gameState.returnWall3(GameState.WALL_ID3);
        if(getShellBounds().intersects(Wall3.getBoundsWalls()) && getObjX()>= Wall3.getObjX()){

            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }else if(getShellBounds().intersects(Wall3.getBoundsWalls()) && getObjX() <= Wall3.getObjX()){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }

        GameObject Wall4 = gameState.returnWall4(GameState.WALL_ID4);
        if(getShellBounds().intersects(Wall4.getBoundsWalls()) && getObjX()>= Wall4.getObjX()){

            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }else if(getShellBounds().intersects(Wall4.getBoundsWalls()) && getObjX() <= Wall4.getObjX()){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
        }

        GameObject BreakWall1 = gameState.returnBreakWall1(GameState.BREAK_ABLE_WALL1_ID);
        if(getShellBounds().intersects(BreakWall1.getBreakWallBounds())){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
            RunGameView.BreakWall1HP -=100;
        }

        GameObject BreakWall2 = gameState.returnBreakWall2(GameState.BREAK_ABLE_WALL1_ID);
        if(getShellBounds().intersects(BreakWall2.getBreakWallBounds())){
            setObjX(1500);
            setObjY(1500);
            setObjAngle(0.0);
            RunGameView.BreakWall2HP -= 100;
        }




        if(getObjX() > 1000){
            setObjX(1500);
            setObjAngle(0.0);

        }

        if(getObjY() > 1000){
            setObjY(1500);
            setObjAngle(0.0);

        }









    }


    private String getUniqueId() {
        return SHELL_ID_PREFIX + uniqueId++;
    }






}
