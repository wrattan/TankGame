package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

public class BreakAbleWall1 extends GameObject{
    public BreakAbleWall1(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    @Override
    public void move(GameState gameState) {

        if(RunGameView.BreakWall1HP <0){
            setObjX(-1500);
            setObjY(-1500);
        }


    }
}
