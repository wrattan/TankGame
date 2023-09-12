package edu.csc413.tankgame.model;

public class HealthBoost extends GameObject {


    public HealthBoost(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {


        Tank playerTank = gameState.returnTank(GameState.PLAYER_TANK_ID);
        if(getSpeedPowerBounds().intersects(playerTank.getBounds())){
            setObjX(-2000);
            setObjY(1000);
        }


    }
}
