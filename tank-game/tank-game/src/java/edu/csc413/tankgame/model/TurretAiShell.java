package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import java.awt.*;

public class TurretAiShell extends GameObject {


    private static final String SHELL_ID_PREFIX = "shell--";
    private static long uniqueId = 0L;


    public TurretAiShell(String id, double x, double y, double angle){
        super(id,x,y,angle);
    }

    @Override
    public void move(GameState gameState) {


        moveForward();

        Tank playerTank = gameState.returnTank(GameState.PLAYER_TANK_ID);

        if(getShellBounds().intersects(playerTank.getBounds())){
            setObjX(1500);
            setObjY(1500);
            RunGameView.PlayerTankHP -= 100;

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
            RunGameView.BreakWall1HP -= 100;
        }

        GameObject BreakWall2 = gameState.returnBreakWall2(GameState.BREAK_ABLE_WALL1_ID);
        if(getShellBounds().intersects(BreakWall2.getBreakWallBounds())){
            setObjX(1500);
            setObjY(1500);
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
