package edu.csc413.tankgame.model;


import edu.csc413.tankgame.view.RunGameView;

public class TurretAiTank extends Tank{

    public TurretAiTank(String id, double x, double y, double angle) {
        super(id, x, y, angle);
    }

    public void move(GameState gameState) {


            super.move(gameState);

            Tank playerTank = gameState.returnTank(GameState.PLAYER_TANK_ID);
            double dx = playerTank.getX() - getX();
            double dy = playerTank.getY() - getY();
            double angleToPlayer = Math.atan2(dy, dx);
            double angleDifference = getAngle() - angleToPlayer;
            angleDifference -=
                    Math.floor(angleDifference / Math.toRadians(360.0) + 0.5) * Math.toRadians(360.0);
            if (angleDifference < -Math.toRadians(3.0)) {
                turnRight();
            } else if (angleDifference > Math.toRadians(3.0)) {
                turnLeft();
            }

            if (getX() <= 0) {
                setX(0);
            }
            if (getX() >= 950) {
                setX(950);
            }
            if (getY() <= 0) {
                setY(0);
            }
            if (getY() >= 665) {
                setY(665);
            }


            GameObject Wall1 = gameState.returnWall1(GameState.WALL_ID1);
            if (getBounds().intersects(Wall1.getBoundsWalls()) && getX() >= Wall1.getObjX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(Wall1.getBoundsWalls()) && getX() <= Wall1.getObjX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }


            GameObject Wall2 = gameState.returnWall2(GameState.WALL_ID2);
            if (getBounds().intersects(Wall2.getBoundsWalls()) && getX() >= Wall2.getObjX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(Wall2.getBoundsWalls()) && getX() <= Wall2.getObjX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }

            GameObject Wall3 = gameState.returnWall3(GameState.WALL_ID3);
            if (getBounds().intersects(Wall3.getBoundsWalls()) && getX() >= Wall3.getObjX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(Wall3.getBoundsWalls()) && getX() <= Wall3.getObjX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }

            GameObject Wall4 = gameState.returnWall4(GameState.WALL_ID4);
            if (getBounds().intersects(Wall4.getBoundsWalls()) && getX() >= Wall4.getObjX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(Wall4.getBoundsWalls()) && getX() <= Wall4.getObjX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }

        GameObject BreakWall1 = gameState.returnBreakWall1(GameState.BREAK_ABLE_WALL1_ID);

        if(getBounds().intersects(BreakWall1.getBreakWallBounds()) && getX()>= BreakWall1.getObjX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(BreakWall1.getBreakWallBounds()) && getX() <= BreakWall1.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }


        GameObject BreakWall2 = gameState.returnBreakWall2(GameState.BREAK_ABLE_WALL2_ID);

        if(getBounds().intersects(BreakWall2.getBreakWallBounds()) && getX()>= BreakWall2.getObjX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(BreakWall2.getBreakWallBounds()) && getX() <= BreakWall2.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }



            Tank CushionAi = gameState.returnCushionAi(GameState.AI_TANK_ID);
            if (getBounds().intersects(CushionAi.getBounds()) && getX() >= CushionAi.getX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(CushionAi.getBounds()) && getX() <= CushionAi.getX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }


            if (getBounds().intersects(playerTank.getBounds()) && getX() >= playerTank.getX()) {

                setX(getX() + 2);
                setY(getY() + 2);
            } else if (getBounds().intersects(playerTank.getBounds()) && getX() <= playerTank.getX()) {
                setX(getX() - 2);
                setY(getY() - 2);
            }


            if (PlayerTank.MakeTurretFire() && RunGameView.TurretAiAmmo >= 300) {
                RunGameView.TurretAiAmmo = 0;
                GameObject TurretEnemyShell = new EnemyShell(GameState.ENEMY_TURRET_SHELL,

                        getShellX(),

                        getShellY(),

                        getAngle());

                gameState.AddGameObject(TurretEnemyShell);


            }
            if (GameState.PlayerIsDead()) {
                RunGameView.TurretAiAmmo = 0;
            }

            if (RunGameView.TurretHP <= 0) {
                GameState.setDeadTurret(1);
            }
            if (GameState.TurretDead()) {
                RunGameView.TurretAiAmmo = 0;
                setX(2000);
                setY(2000);
                moveForward();
                turnRight();
            }



        }



    }



