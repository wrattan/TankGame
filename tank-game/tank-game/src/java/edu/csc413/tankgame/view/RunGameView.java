package edu.csc413.tankgame.view;

import edu.csc413.tankgame.model.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * RunGameView is the view representing the screen shown when the game is actually playing. As a result, RunGameView is
 * responsible for tracking what is being drawn on the screen in the form of DrawableEntities. The GameDriver class
 * should interact with RunGameView by asking it to draw a specific entity, named by its ID, at a location and angle.
 */
public class RunGameView extends JPanel {
    public static final Dimension SCREEN_DIMENSIONS = new Dimension(1024, 768);

    public static final String PLAYER_TANK_IMAGE_FILE = "edu/csc413/tankgame/view/player-tank.png";
    public static final double PLAYER_TANK_INITIAL_X = 200.0;
    public static final double PLAYER_TANK_INITIAL_Y = 150.0;
    public static final double PLAYER_TANK_INITIAL_ANGLE = Math.toRadians(0.0);

    public static final String AI_TANK_IMAGE_FILE = "edu/csc413/tankgame/view/ai-tank.png";
    public static final double AI_TANK_INITIAL_X = 750.0;
    public static final double AI_TANK_INITIAL_Y = 500.0;
    public static final double AI_TANK_INITIAL_ANGLE = Math.toRadians(180.0);

    public static final double SHELL_INITIAL_X = 200.0;
    public static final double SHELL_INITIAL_Y = 150.0;
    public static final double SHELL_INITIAL_ANGLE = Math.toRadians(0.0);


    public static final String SHELL_IMAGE_FILE = "edu/csc413/tankgame/view/shell.png";
    public static final String BRICK_IMAGE_FILE = "edu/csc413/tankgame/view/Wall1.png";
    public static final String SPEED_UP_IMAGE_FILE = "edu/csc413/tankgame/view/Speed.png";
    private static final int RECT_X = 10;
    private static final int RECT_Y = 10;
    private static final int RECT_WIDTH = 200;
    private static final int RECT_HEIGHT = 40;
    public static int Ammo = 0;
    public static int DumbAiAmmo = 0;
    public static int TurretAiAmmo =0;
    public static int PlayerTankHP = 2000;
    public static int CushionTankHP = 200;
    public static int TurretHP = 200;
    public static final String HP_BOOST_FILE = "edu/csc413/tankgame/view/Health.png";
    public static final String BREAKABLE_WALL_FILE = "edu/csc413/tankgame/view/BreakableWall.png";
    public static int BreakWall1HP = 100;
    public static int BreakWall2HP = 100;


    // TODO: Feel free to add more constants if you would like multiple AI tanks -- just be sure to set them at
    // different initial locations.



    private final BufferedImage worldImage;
    private final Map<String, DrawableEntity> drawableEntitiesById = new HashMap<>();
    private static final String Game_Background = "edu/csc413/tankgame/view/Background.png";
    private final BufferedImage GameBackground;



    public RunGameView() {
        worldImage = new BufferedImage(SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height, BufferedImage.TYPE_INT_RGB);
        setBackground(Color.BLACK);





        URL imageUrl = getClass().getClassLoader().getResource(Game_Background);
        if (imageUrl == null) {
            throw new RuntimeException("Unable to create an image URL from: " + Game_Background);
        }
        try {
            GameBackground = ImageIO.read(imageUrl);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /** Clears the DrawableEntities map. This should be invoked if the game is reset. */
    public void reset() {
        drawableEntitiesById.clear();
    }

    /**
     * Adds a new DrawableEntity to the view. The DrawableEntity must be identified by an ID, so that it can be updated
     * later on by that same ID. It must be initialized with an image file name, as well as an initial location and
     * angle.
     */
    public void addDrawableEntity(
            String id, String entityImageFile, double initialX, double initialY, double initialAngle) {
        DrawableEntity drawableEntity = new DrawableEntity(entityImageFile);
        drawableEntity.setLocationAndAngle(initialX, initialY, initialAngle);
        drawableEntitiesById.put(id, drawableEntity);
    }

    /**
     * Removes the DrawableEntity identified by the provided ID from this view. This should be invoked if an entity has
     * been removed from the game and should no longer be drawn.
     */
    public void removeDrawableEntity(String id) {
        drawableEntitiesById.remove(id);
    }

    /** Updates the DrawableEntity with the provided ID to a new location and angle. */
    public void setDrawableEntityLocationAndAngle(String id, double x, double y, double angle) {
        drawableEntitiesById.get(id).setLocationAndAngle(x, y, angle);
    }


    @Override
    public void paintComponent(Graphics g) {
        // The "image" on which we draw the game screen is just a buffer colored entirely black. We can then draw the
        // DrawableEntities on top of the buffer.

       if(GameState.SetSpeedNotVisible()){
           removeDrawableEntity(RunGameView.SPEED_UP_IMAGE_FILE);
       }


        Graphics2D buffer = worldImage.createGraphics();
        buffer.setColor(Color.BLACK);
        buffer.fillRect(0, 0, SCREEN_DIMENSIONS.width, SCREEN_DIMENSIONS.height);
        buffer.drawImage(GameBackground,0,0,null);


        for (DrawableEntity drawableEntity : drawableEntitiesById.values()) {
            buffer.drawImage(drawableEntity.getEntityImage(), drawableEntity.getAffineTransform(), null);
        }


        g.drawImage(worldImage, 0, 0, null);
      //  g.drawImage(GameBackground,0,0,null);

        g.drawRect(0,0,300,40);
        g.fillRect(0,0,300,40);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(255, 0, 0));
        g2d.fillRect(0, 0, Ammo, 40);


        if(!GameState.DeadCushion()){
            DumbAiAmmo++;
        }

        if(DumbAiAmmo > 300){
            DumbAiAmmo = 300;
        }


        if(!GameState.TurretDead()) {
            TurretAiAmmo++;

        }
        if(TurretAiAmmo > 300){
            TurretAiAmmo = 300;
        }

        if(!GameState.PlayerIsDead()) {
            Ammo++;
        }

        if(Ammo > 300){
            Ammo = 300;
        }
        if(Ammo>= 300) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("Shell Ready to Fire", 0, 23);
        }else if(Ammo <= 300){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("Reloading Shell...", 0, 23);
        }

        if(!GameState.HealthBoost()){
            g.setColor(Color.GREEN);
            g.fillRect(750,0,225,40);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("Health", 810, 23);

            if(PlayerTankHP == 100){

                g.setColor(Color.RED);
                g.fillRect(862,0,113,40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("Health", 810, 23);
            }
            if(PlayerTankHP <= 0){
                g.setColor(Color.RED);
                g.fillRect(750,0,225,40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("DEAD", 810, 23);
            }



        } else if(GameState.HealthBoost()) {
            g.setColor(Color.YELLOW);
            g.fillRect(750, 0, 225, 40);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("Health Boosted", 750, 23);

            if (PlayerTankHP == 300) {

                g.setColor(Color.RED);
                g.fillRect(750, 0, 57, 40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("Health Boosted", 750, 23);

            }
            if (PlayerTankHP == 200) {

                g.setColor(Color.RED);
                g.fillRect(750, 0, 113, 40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("Health Boosted", 750, 23);

            }
            if (PlayerTankHP == 100) {

                g.setColor(Color.RED);
                g.fillRect(750, 0, 170, 40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("Health Boosted", 750, 23);

            }
            if(PlayerTankHP <= 0){
                g.setColor(Color.RED);
                g.fillRect(750,0,225,40);
                g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
                g.setColor(Color.BLACK);
                g.drawString("DEAD", 810, 23);

            }

        }


        if(!GameState.SetSpeedNotVisible()){
            g.setColor(Color.BLUE);
            g.drawRect(400,0,225,40);
            g.fillRect(400,0,225,40);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("No Power Up", 400, 23);


        }else if (GameState.SetSpeedNotVisible()){
            g.setColor(Color.BLUE);
            g.drawRect(400,0,225,40);
            g.fillRect(400,0,225,40);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 29));
            g.setColor(Color.BLACK);
            g.drawString("Speed Power Up", 400, 23);
        }

        if(GameState.PlayerIsDead()){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.RED);
            g.drawString("YOU LOSE", 300, 400);
        }else if(GameState.TurretDead()&&GameState.DeadCushion()){
            g.setFont(new Font("TimesRoman", Font.PLAIN, 100));
            g.setColor(Color.ORANGE);
            g.drawString("YOU WIN", 300, 400);
        }





    }

}
