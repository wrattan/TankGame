package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

public class DumbAiTank extends Tank{




    public DumbAiTank(String id, double x, double y, double angle){
        super(id,x,y,angle);

    }

    @Override
    public void move(GameState gameState) {
        moveForward();
        turnRight();

        if(PlayerTank.MakeEnemyShoot() && RunGameView.DumbAiAmmo >=300){
            RunGameView.DumbAiAmmo = 0;
            GameObject EnemyShell = new EnemyShell(GameState.ENEMY_SHELL_ID,

                    getShellX(),

                    getShellY(),

                    getAngle());

                    gameState.AddGameObject(EnemyShell);


        }







    }

}
