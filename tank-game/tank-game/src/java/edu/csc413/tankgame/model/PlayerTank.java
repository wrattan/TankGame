package edu.csc413.tankgame.model;


import edu.csc413.tankgame.view.RunGameView;

public class PlayerTank extends Tank{

    private double x;
    private double y;
    private double angle;
    private String id;
    private static double MOVEMENT_SPEED = 2.0;
    public static int EnemyMoving;
    public static int TurretFire;



    public PlayerTank(String id, double x, double y, double angle){
        super(id,x,y,angle);
    }

    @Override
    public void move(GameState gameState) {

        if(gameState.upPressed()){
            moveForward();
            
        }
        if(gameState.downPressed()){
            moveBackward();
        }
        if(gameState.leftPressed()){
            turnLeft();
        }
        if(gameState.rightPressed()){
            turnRight();

        }
        if(gameState.SpaceBarPressed() && RunGameView.Ammo >= 300){
            RunGameView.Ammo = 0;
            GameObject PlayerShell = new Shell(GameState.SHELL_ID,getShellX(),getShellY(),getAngle());
            gameState.AddGameObject(PlayerShell);

        }
        if(getX()<=0 ){
            setX(0);
        }
        if (getX() >= 950){
            setX(950);
        }
        if(getY() <=0){
            setY(0);
        }
        if(getY() >= 665){
            setY(665);
        }


        if(getX() >= 300){
            setEnemyMove(1);
        }
        if(getX() >= 400){
            setEnemyTurretFire(1);
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



        GameObject Wall1 = gameState.returnWall1(GameState.WALL_ID1);
        if(getBounds().intersects(Wall1.getBoundsWalls()) && getX()>= Wall1.getObjX()){

            setX(getX()+2);
           setY(getY()+2);
        }else if(getBounds().intersects(Wall1.getBoundsWalls()) && getX() <= Wall1.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }


        GameObject Wall2 = gameState.returnWall2(GameState.WALL_ID2);
        if(getBounds().intersects(Wall2.getBoundsWalls()) && getX()>= Wall2.getObjX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(Wall2.getBoundsWalls()) && getX() <= Wall2.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }

        GameObject Wall3 = gameState.returnWall3(GameState.WALL_ID3);
        if(getBounds().intersects(Wall3.getBoundsWalls()) && getX()>= Wall3.getObjX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(Wall3.getBoundsWalls()) && getX() <= Wall3.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }

        GameObject Wall4 = gameState.returnWall4(GameState.WALL_ID4);
        if(getBounds().intersects(Wall4.getBoundsWalls()) && getX()>= Wall4.getObjX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(Wall4.getBoundsWalls()) && getX() <= Wall4.getObjX()){
            setX(getX()-2);
            setY(getY()-2);
        }

        Tank turretAi = gameState.returnTurretTank(GameState.TURRET_TANK_ID);
        if(getBounds().intersects(turretAi.getBounds()) && getX()>= turretAi.getX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(turretAi.getBounds()) && getX() <= turretAi.getX()){
            setX(getX()-2);
            setY(getY()-2);
        }

        Tank CushionAi = gameState.returnCushionAi(GameState.AI_TANK_ID);
        if(getBounds().intersects(CushionAi.getBounds()) && getX()>= CushionAi.getX()){

            setX(getX()+2);
            setY(getY()+2);
        }else if(getBounds().intersects(CushionAi.getBounds()) && getX() <= CushionAi.getX()){
            setX(getX()-2);
            setY(getY()-2);
        }





        GameObject SpeedUp = gameState.returnSpeedPower(GameState.SPEED_UP_ID);
        if(getBounds().intersects(SpeedUp.getSpeedPowerBounds())){
            setMovementSpeed(2);
            GameState.setVisible(1);
        }

        GameObject HeathBoost = gameState.returnHPBoost(GameState.HP_UP_ID);
        if(getBounds().intersects(HeathBoost.getHealthBoostBounds())){
            RunGameView.PlayerTankHP = 400;
            GameState.setHPBoost(1);
        }

        if(RunGameView.PlayerTankHP <= 0){
            RunGameView.Ammo = 0;
            setX(0);
            setY(4000);
            setMovementSpeed(0);
            GameState.setDeadPlayer(1);
        }
        if(GameState.DeadCushion()&&GameState.TurretDead()){
            RunGameView.Ammo = 0;
            setMovementSpeed(0);
        }

    }



    public void setEnemyMove(int EM) {
        EnemyMoving = EM;
    }
    public int getEnemyOnHunt(){
        return EnemyMoving;
    }

    public void setEnemyTurretFire(int TF){
        TurretFire = TF;
    }
    public int getTurretFire(){
        return  TurretFire;
    }


    public static boolean MakeTurretFire(){
        if(TurretFire == 1){
            return true;
        }else return false;
    }

    public static boolean MakeEnemyShoot(){

        if(EnemyMoving == 1){
            return true;
        }else return false;
    }






    }









