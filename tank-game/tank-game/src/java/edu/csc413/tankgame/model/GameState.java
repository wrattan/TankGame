package edu.csc413.tankgame.model;

import edu.csc413.tankgame.view.RunGameView;

import javax.swing.text.html.parser.Entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * GameState represents the state of the game "world." The GameState object tracks all of the moving entities like tanks
 * and shells, and provides the controller of the program (i.e. the GameDriver) access to whatever information it needs
 * to run the game. Essentially, GameState is the "data context" needed for the rest of the program.
 */
public class GameState {
    public static final double TANK_X_LOWER_BOUND = 30.0;
    public static final double TANK_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width - 100.0;
    public static final double TANK_Y_LOWER_BOUND = 30.0;
    public static final double TANK_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height - 120.0;

    public static final double SHELL_X_LOWER_BOUND = -10.0;
    public static final double SHELL_X_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.width;
    public static final double SHELL_Y_LOWER_BOUND = -10.0;
    public static final double SHELL_Y_UPPER_BOUND = RunGameView.SCREEN_DIMENSIONS.height;

    public static final String PLAYER_TANK_ID = "player-tank";
    public static final String TURRET_TANK_ID = "turret-ai";
    public static final String AI_TANK_ID = "ai-tank";
    public static final String SHELL_ID = "shell";
    public static final String ENEMY_SHELL_ID = "Enemy Shell";
    public static final String ENEMY_TURRET_SHELL ="Enemy turret shell";
    public static final String WALL_ID1 = "Wall1";
    public static final String WALL_ID2 = "Wall2";
    public static final String WALL_ID3 = "Wall3";
    public static final String WALL_ID4 = "Wall4";
    public static final String SPEED_UP_ID="Speed";
    public static final String HP_UP_ID = "HP";
    public static final String BREAK_ABLE_WALL1_ID = "BreakWall1";
    public static final String BREAK_ABLE_WALL2_ID = "BreakWall2";


    public static int W;
    public static int S;
    public static int D;
    public static int A;
    public static int Space;
    public static int Visible;
    public static int HealthBoost;
    public static int DeadPlayer;
    public static int DeadTurret;
    public static int DeadCushion;
    // TODO: Feel free to add more tank IDs if you want to support multiple AI tanks! Just make sure they're unique.

    // TODO: Implement.
    // There's a lot of information the GameState will need to store to provide contextual information. Add whatever
    // instance variables, constructors, and methods are needed.

    private final List<Tank> tanks = new ArrayList<>();
    private final List<GameObject> objects = new ArrayList<>();



    public void AddTank(Tank tank){
        tanks.add(tank);
    }

    public List<Tank> getTank(){
        return tanks;
    }

    public List<GameObject> getObject(){
        return objects;
    }

    public void AddGameObject(GameObject object){
        objects.add(object);
    }

    public void RemoveGameObject(GameObject object){
        objects.remove(object);
    }



    public Tank returnTank(String id){
        return tanks.get(0);
    }

    public Tank returnCushionAi(String id){
        return tanks.get(1);
    }


    public Tank returnTurretTank(String id){
        return tanks.get(2);
    }



    public GameObject returnWall1(String id){
        return objects.get(0);
    }
    public GameObject returnWall2(String id){
        return objects.get(1);
    }
    public GameObject returnWall3(String id){
        return objects.get(2);
    }
    public GameObject returnWall4(String id){
        return objects.get(3);
    }

    public GameObject returnSpeedPower(String id){
        return objects.get(4);
    }

    public GameObject returnHPBoost(String id){
        return objects.get(5);
    }

    public GameObject returnBreakWall1(String id){
        return objects.get(6);
    }
    public GameObject returnBreakWall2(String id){
        return objects.get(7);
    }





    public void setW(int w) {
        W = w;
    }
    public int getW(){
        return W;
    }

    public static void setS(int s) {
        S = s;
    }

    public int getS(){
        return S;
    }

    public static void setD(int d){
        D = d;
    }

    public int getD(){
        return D;
    }

    public int getA(){
        return A;
    }

    public static void setA(int a){
        A = a;
    }

    public int getSpace(){
        return Space;
    }
    public static void setSpace(int SP){
        Space = SP;
    }


    public static void setVisible(int VIS){
        Visible = VIS;
    }
    public int getVisible(){
        return Visible;
    }




    public static int getPlayerdDead(){
        return DeadPlayer;
    }

    public static void setDeadPlayer(int DP){
        DeadPlayer = DP;
    }

    public static boolean PlayerIsDead(){
        getPlayerdDead();
        if(DeadPlayer == 1){
            return true;
        }else return false;
    }

    public static int getTurretDead(){
        return DeadTurret;
    }

    public static void setDeadTurret(int DT){
        DeadTurret = DT;
    }

    public static boolean TurretDead(){
        getTurretDead();
        if(DeadTurret ==1){
            return true;
        }else return false;
    }

    public static int getDeadCushion(){
        return DeadCushion;
    }

    public static void setDeadCushion(int DC){
        DeadCushion = DC;
    }

    public static boolean DeadCushion(){
        getDeadCushion();
        if(DeadCushion==1){
            return true;
        }else return false;
    }











    public static boolean HealthBoost() {

       getHealthBoost();
       if(HealthBoost == 1){
           return true;
       }else  return false;

    }

    public static int getHealthBoost(){
        return HealthBoost;
    }

    public static void setHPBoost(int HP){
        HealthBoost = HP;
    }

    public boolean upPressed() {

        getW();
        if( W == 1){
            return true;
        }else return false;

    }


    public boolean downPressed() {
        getS();
        if( S == 1){
            return true;
        }else return false;
    }



    public boolean leftPressed() {
        getD();
        if( D == 1){
            return true;
        }else return false;
    }

    public boolean rightPressed() {
        getA();
        if( A == 1){
            return true;
        }else return false;
    }

    public boolean SpaceBarPressed(){
        getSpace();
        if(Space == 1){
            return true;
        }else return false;
    }

    public static boolean SetSpeedNotVisible() {

                if(Visible == 1){
                    return true;
                }else return false;
    }

    public static class ActionListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent event) {
            //useless
        }

        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_W) {
                new GameState().setW(1);
            } else if (keyCode == KeyEvent.VK_S) {
                new GameState().setS(1);
            } else if (keyCode == KeyEvent.VK_D) {
                new GameState().setD(1);
            } else if (keyCode == KeyEvent.VK_A) {
                new GameState().setA(1);
            }else if(keyCode == KeyEvent.VK_SPACE){
                new GameState().setSpace(1);
            }else if(keyCode == KeyEvent.VK_ESCAPE){
                System.exit(1);
            }
        }

        @Override
        public void keyReleased(KeyEvent event) {
            int keyCode = event.getKeyCode();
            if (keyCode == KeyEvent.VK_W) {
                new GameState().setW(0);
            } else if (keyCode == KeyEvent.VK_S) {
                new GameState().setS(0);
            } else if (keyCode == KeyEvent.VK_D) {
                new GameState().setD(0);
            } else if (keyCode == KeyEvent.VK_A) {
                new GameState().setA(0);
            }else if(keyCode == KeyEvent.VK_SPACE){
                new GameState().setSpace(0);
            }
        }

    }
}
