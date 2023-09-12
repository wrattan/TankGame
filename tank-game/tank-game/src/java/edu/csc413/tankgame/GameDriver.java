package edu.csc413.tankgame;

import edu.csc413.tankgame.model.*;
import edu.csc413.tankgame.view.MainView;
import edu.csc413.tankgame.view.RunGameView;

import java.awt.event.ActionListener;


/**
 * GameDriver is the primary controller class for the tank game. The game is launched from GameDriver.main, and
 * GameDriver is responsible for running the game loop while coordinating the views and the data models.
 */
public class GameDriver {
    // TODO: Implement.
    // Add the instance variables, constructors, and other methods needed for this class. GameDriver is the centerpiece
    // for the tank game, and should store and manage the other components (i.e. the views and the models). It also is
    // responsible for running the game loop.
    private final MainView mainView;
    private final RunGameView runGameView;
    private ActionListener Actions;

    private final GameState gameState;
    public static int StartButtonPressed;




    public GameDriver() {
        mainView = new MainView();

        runGameView = mainView.getRunGameView();

        gameState = new GameState();


    }



    public static void setStartButtonPressed(int SBP) {
        StartButtonPressed = SBP;
    }


    public static int getStartButtonPressed(){
        return StartButtonPressed;
    }




    public boolean GameNotRunning(){
        if(update()){

           return true;
        }else return false;
    }


    public void start() {
        // TODO: Implement.
        // This should set the MainView's screen to the start menu screen.

        mainView.setScreen(MainView.Screen.START_MENU_SCREEN);




        while(GameNotRunning()) {

            if (StartButtonPressed == 1) {
                mainView.setScreen(MainView.Screen.RUN_GAME_SCREEN);
                runGame();
                break;
            }
        }

}





    private void runGame() {


        Tank playerTank = new PlayerTank(GameState.PLAYER_TANK_ID,RunGameView.PLAYER_TANK_INITIAL_X,
                RunGameView.PLAYER_TANK_INITIAL_Y,RunGameView.PLAYER_TANK_INITIAL_ANGLE);


        Tank AITank = new CushionAiTank(GameState.AI_TANK_ID,RunGameView.AI_TANK_INITIAL_X,
                RunGameView.AI_TANK_INITIAL_Y,RunGameView.AI_TANK_INITIAL_ANGLE);


        Tank TurretAITank = new TurretAiTank(GameState.TURRET_TANK_ID,900,200,0);


        GameObject Wall1 = new Wall(GameState.WALL_ID1,100,325,0);


        GameObject Wall2 = new Wall(GameState.WALL_ID2,300,0,0);


        GameObject Wall3 = new Wall(GameState.WALL_ID3,500,325,0);

        GameObject Wall4 = new Wall(GameState.WALL_ID4,700,0,0);

        GameObject SpeedUp = new SpeedPowerUp(GameState.SPEED_UP_ID, 400, 400,0);

        GameObject HPBoost = new HealthBoost(GameState.HP_UP_ID, 50,100,0);

        GameObject BreakableWall1 = new BreakAbleWall1(GameState.BREAK_ABLE_WALL1_ID,300,600,0);

        GameObject BreakableWall2 = new BreakAbleWall2(GameState.BREAK_ABLE_WALL2_ID,700,600,0);



        runGameView.addDrawableEntity(gameState.WALL_ID1,RunGameView.BRICK_IMAGE_FILE,Wall1.getObjX(),Wall1.getObjY(), Wall1.getObjAngle());
        runGameView.addDrawableEntity(gameState.WALL_ID2,RunGameView.BRICK_IMAGE_FILE,Wall2.getObjX(),Wall2.getObjY(), Wall2.getObjAngle());
        runGameView.addDrawableEntity(gameState.WALL_ID3,RunGameView.BRICK_IMAGE_FILE,Wall3.getObjX(),Wall3.getObjY(), Wall3.getObjAngle());
        runGameView.addDrawableEntity(gameState.WALL_ID4,RunGameView.BRICK_IMAGE_FILE,Wall4.getObjX(),Wall4.getObjY(), Wall4.getObjAngle());

        runGameView.addDrawableEntity(gameState.TURRET_TANK_ID,RunGameView.AI_TANK_IMAGE_FILE,TurretAITank.getX(),TurretAITank.getY(),TurretAITank.getAngle());

        runGameView.addDrawableEntity(GameState.SPEED_UP_ID,RunGameView.SPEED_UP_IMAGE_FILE,SpeedUp.getObjX(),SpeedUp.getObjY(),SpeedUp.getObjAngle());

        runGameView.addDrawableEntity(GameState.HP_UP_ID,RunGameView.HP_BOOST_FILE,HPBoost.getObjX(),HPBoost.getObjY(),HPBoost.getObjAngle());

        runGameView.addDrawableEntity(GameState.BREAK_ABLE_WALL1_ID,RunGameView.BREAKABLE_WALL_FILE,BreakableWall1.getObjX(),BreakableWall1.getObjY(),BreakableWall1.getObjAngle());

        runGameView.addDrawableEntity(GameState.BREAK_ABLE_WALL2_ID,RunGameView.BREAKABLE_WALL_FILE,BreakableWall2.getObjX(),BreakableWall2.getObjY(),BreakableWall2.getObjAngle());


        runGameView.addDrawableEntity(
                GameState.PLAYER_TANK_ID,
                RunGameView.PLAYER_TANK_IMAGE_FILE,playerTank.getX(),playerTank.getY(),
                playerTank.getAngle());



        runGameView.addDrawableEntity(
                    GameState.AI_TANK_ID,
                    RunGameView.AI_TANK_IMAGE_FILE,
                AITank.getX(),AITank.getY(),
                    AITank.getAngle());

        gameState.AddTank(playerTank);
        gameState.AddTank(AITank);
        gameState.AddTank(TurretAITank);


        gameState.AddGameObject(Wall1);
        gameState.AddGameObject(Wall2);
        gameState.AddGameObject(Wall3);
        gameState.AddGameObject(Wall4);
        gameState.AddGameObject(SpeedUp);
        gameState.AddGameObject(HPBoost);
        gameState.AddGameObject(BreakableWall1);
        gameState.AddGameObject(BreakableWall2);



        Runnable gameRunner = () -> {
            while (update()) {
                runGameView.repaint();

                try {
                    Thread.sleep(8L);
                } catch (InterruptedException exception) {
                    throw new RuntimeException(exception);
                }
            }
        };
        new Thread(gameRunner).start();

    }

    // TODO: Implement.
    // update should handle one frame of gameplay. All tanks and shells move one step, and all drawn entities
    // should be updated accordingly. It should return true as long as the game continues.
    private boolean update() {
        //ask all game objects to move




        // TODO
        // make Tank extend gameObject so we don't end up using 2 different loops to iterate our game Objects
        for(Tank tank: gameState.getTank()){
            tank.move(gameState);
        }
        for(GameObject Object : gameState.getObject()){
            Object.move(gameState);
        }


        if(PlayerTank.MakeTurretFire()){


            runGameView.addDrawableEntity(GameState.ENEMY_TURRET_SHELL,runGameView.SHELL_IMAGE_FILE,runGameView.getX(),runGameView.getY(),runGameView.SHELL_INITIAL_ANGLE);
        }




        if(PlayerTank.MakeEnemyShoot()){
            runGameView.addDrawableEntity(GameState.ENEMY_SHELL_ID, runGameView.SHELL_IMAGE_FILE,
                    runGameView.getX(),

                    runGameView.getY(),

                    RunGameView.SHELL_INITIAL_ANGLE);

        }
        // creating a Shell if SpaceBarPressed
        if(gameState.SpaceBarPressed()){


            runGameView.addDrawableEntity(GameState.SHELL_ID, runGameView.SHELL_IMAGE_FILE,
                    runGameView.getX(),

                    runGameView.getY(),

                    RunGameView.SHELL_INITIAL_ANGLE);
        }




        for(GameObject Object: gameState.getObject()){
            runGameView.setDrawableEntityLocationAndAngle(Object.getObjId(),Object.getObjX(),Object.getObjY(),Object.getObjAngle());

            //checkCollisions();
        }

        for(Tank tank: gameState.getTank()){
            runGameView.setDrawableEntityLocationAndAngle(tank.getId(),tank.getX(),tank.getY(),tank.getAngle());

           // checkCollisions();
        }


        //then ask all objects to check bounds




       /* if(GameState.PlayerIsDead()){

            GameDriver.setStartButtonPressed(0);
            mainView.setScreen(MainView.Screen.START_MENU_SCREEN);
            RunGameView.PlayerTankHP = 200;
            GameState.setDeadPlayer(0);

       }*/


        //ask all game objects to check collision


        return true;
    }



    public static void main(String[] args) {
        GameDriver gameDriver = new GameDriver();
        gameDriver.start();

    }
}

