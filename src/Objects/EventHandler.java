package Objects;

import Backend.SFXplayer;
import Backend.Window;
import Game.GameHandler;
import Game.GameView;

import java.awt.*;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

public class EventHandler extends GameObject {
    private ObjectHandler objectHandler;

    private String currentDialog = "I will kill you";

    private Map<Integer, String> dialogMapTutorial;

    /**
     * This is the abstract GameObject class. All other classes extend to this one in some manor. It contains abstract
     * methods to be used with every game object. The constructor simply sets the variables that will be used by each
     * GameObject, such as the x and y position.
     *
     * @param x        - x location of the object
     * @param y        - y location of the object
     * @param Z        - Z location of the object
     * @param width    - width of the object
     * @param height   - height of the object
     * @param id       - the ID of the object, stored in a enum
     * @param blocking - if the object is a blocking one
     */
    public EventHandler(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;

        dialogMapTutorial = new HashMap<>();
        dialogMapTutorial.put(0, "... *Sigh*");
        dialogMapTutorial.put(1, "I should probably stop programming.");
        dialogMapTutorial.put(2, "But I'm too hungry to go to bed");
        dialogMapTutorial.put(3, "...");
        dialogMapTutorial.put(4, "I'll get something to eat first");
        dialogMapTutorial.put(5, "--Move in any direction with [A] and [D] to\nget out of the chair--");
        dialogMapTutorial.put(6, "--Press [F] to switch your phone light on--");
        dialogMapTutorial.put(7, "--When near an object, click its outline to\ninteract with it. Try opening the door--");
        dialogMapTutorial.put(8, "--Leave the room--");
    }

    @Override
    public void update() {
        x = (int)GameView.camera.getX()+100;
        y = (int)GameView.camera.getY()+(Window.gameHeight-260);
        if(!objectHandler.isGamePaused()) {
            if(GameHandler.level == 1) {
                if(objectHandler.getEventStage() == 0) {
                    if(objectHandler.getDialogStage() >= 5) {
                        if(objectHandler.getDialogStage() == 5) {
                            objectHandler.setCanMove(true);
                            if(objectHandler.isRight() || objectHandler.isLeft()) {
                                SFXplayer.playSFX("ButtonClick");
                                objectHandler.setVisible(true);
                                for (GameObject object : objectHandler.object) {
                                    if (object.getId() == ObjectID.deskChair || object.getId() == ObjectID.desk) {
                                        object.reset();
                                    }
                                }
                                int temp = objectHandler.getDialogStage();
                                temp++;
                                objectHandler.setDialogStage(temp);
                            }
                        }

                        if(objectHandler.getDialogStage() == 6 && objectHandler.isFlashLightOn()) {
                            int temp = objectHandler.getDialogStage();
                            temp++;
                            objectHandler.setDialogStage(temp);
                        }

                        if(objectHandler.getDialogStage() == 7 && objectHandler.isMouseClicked()) {
                            SFXplayer.playSFX("ButtonClick");
                            int temp = objectHandler.getDialogStage();
                            temp++;
                            objectHandler.setDialogStage(temp);
                        }

                        if(objectHandler.getDialogStage() == 8) {
                            for(GameObject object : objectHandler.object) {
                                if(object.getId() == ObjectID.player && object.getX() >= 800) {
                                    objectHandler.setEventStage(1);
                                    objectHandler.setDialogActive(false);
                                    objectHandler.setDialogStage(0);
                                }
                            }
                        }
                    }
                    currentDialog = dialogMapTutorial.get(objectHandler.getDialogStage());
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(objectHandler.isDialogActive()) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, width, height);
            g.setColor(Color.LIGHT_GRAY);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(5));
            g2d.drawRect(x, y, width, height);
            g.setFont(GameView.DialogFont);
            String[] temp = currentDialog.split("\n");
            g.drawString(temp[0], x+20, y+65);
            try {
                g.drawString(temp[1], x + 20, y + 125);
            } catch(Exception ignored) {}
            if((objectHandler.getEventStage() == 0 && objectHandler.getDialogStage() < 5) || objectHandler.getEventStage() != 0) {
                g.drawString("[ENTER]", x+1380, y+190);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
