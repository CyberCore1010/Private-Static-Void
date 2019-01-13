package Objects;

import Backend.SFXplayer;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import static Game.GameView.debug;

public class Furniture extends GameObject {
    private ObjectHandler objectHandler;
    private boolean playerHere, interactive, canInteract = false, hover = false, active = false;
    private BufferedImage sprite;
    private BufferedImage spritePlayer;
    private BufferedImage spriteActive;

    private int cooldownTime = 0;

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
    public Furniture(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler, boolean playerHere, boolean interactive) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
        this.playerHere = playerHere;
        this.interactive = interactive;
        boundingBox = new Rectangle(this.x, this.y, this.width, this.height);

        if(id == ObjectID.deskChair) {
            sprite = objectHandler.deskChair;
            spritePlayer = objectHandler.deskChairPlayer;
        } else if(id == ObjectID.desk) {
            sprite = objectHandler.deskOff;
            spritePlayer = objectHandler.deskOn;
        } else if(id == ObjectID.drawer) {
            sprite = objectHandler.drawer;
        } else if(id == ObjectID.curtain) {
            sprite = objectHandler.curtain;
        } else if(id == ObjectID.bookShelf) {
            sprite = objectHandler.bookShelf;
        } else if(id == ObjectID.lampShelf) {
            sprite = objectHandler.lampShelf;
        } else if(id == ObjectID.bed) {
            sprite = objectHandler.bed;
        } else if(id == ObjectID.bedroomDoor) {
            boundingBox.x = x+((width/2)-15);
            boundingBox.y = y+18;
            boundingBox.width = 28;
            boundingBox.height = 365;
            sprite = objectHandler.bedroomDoorClosed;
            spriteActive = objectHandler.bedroomDoorOpen;
        }
    }

    @Override
    public void update() {
        if(this.interactive) {
            for(GameObject object : objectHandler.object) {
                if(object.getId() == ObjectID.player) {
                    if(object.x+(object.width/2) >= boundingBox.x-200 && object.x+(object.width/2) <= boundingBox.x+boundingBox.width+200) {
                        canInteract = true;
                        if((GameObject.mousePoint.x >= boundingBox.x && GameObject.mousePoint.x <= boundingBox.x+boundingBox.width)
                                && (GameObject.mousePoint.y >= boundingBox.y && GameObject.mousePoint.y <= boundingBox.y+boundingBox.height)) {
                            hover = true;
                            if(objectHandler.isMouseClicked()) {
                                activate();
                            }
                        } else {
                            hover = false;
                        }
                    } else {
                        canInteract = false;
                        hover = false;
                    }
                }
            }
        }
        if(cooldownTime > 0) {
            cooldownTime--;
        }
    }

    @Override
    public void render(Graphics g) {
        if(playerHere) {
            g.drawImage(spritePlayer, x, y, width, height, null);
        } else {
            if(!active) {
                g.drawImage(sprite, x, y, width, height, null);
            } else {
                g.drawImage(spriteActive, x, y, width, height, null);
            }
        }
        Graphics2D g2d = (Graphics2D) g;
        if(canInteract) {
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.WHITE);
            if(hover) g2d.setColor(Color.RED);
            g2d.drawRect(boundingBox.x-5, boundingBox.y-5, boundingBox.width+10, boundingBox.height+10);
        }
        g.setColor(Color.RED);
        if(interactive && debug) g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }

    @Override
    public void activate() {
        if(cooldownTime == 0) {
            cooldownTime = 10;
            if(this.getId() == ObjectID.bedroomDoor) {
                for(GameObject object : objectHandler.object) {
                    if(object.getId() == ObjectID.player) {
                        if(getBounds().intersects(object.getBounds())) {
                            SFXplayer.playSFX("ButtonClick");
                            return;
                        }
                    }
                }
                active ^= true;
                if(active) {
                    SFXplayer.playSFX("WoodDoorOpen");
                    boundingBox.x = x+((width/2)-15-169);
                    boundingBox.width = 28+169;
                } else {
                    SFXplayer.playSFX("WoodDoorClose");
                    boundingBox.x = x+((width/2)-15);
                    boundingBox.width = 28;
                }
                setBlocking(!getBlocking());
            }
        }
    }

    @Override
    public void reset() {
        playerHere = false;
    }
}
