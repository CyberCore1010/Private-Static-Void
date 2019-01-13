package Objects;

import Game.GameView;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

abstract class Entity extends GameObject {
    ObjectHandler objectHandler;

    BufferedImage defaultSprite;
    BufferedImage currentSprite;

    int walkTime = 0, walkStage = 1;
    int velX = 0, velY = 0;
    String direction = "left";

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
    Entity(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler) {
        super(x, y, Z, width, height, id, blocking);
        this.objectHandler = objectHandler;
    }

    /**This method will check if a character is colliding with a object that can block. If it is it will check which
     * side it is colliding with, and reverse the characters velocity in that direction
     */
    void collision() {
        for(int i = 0; i < objectHandler.object.size(); i++) {
            GameObject tempObject = objectHandler.object.get(i);
            if(tempObject.getBlocking()) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    if(objectHandler.isLeft() || objectHandler.isRight()) {
                        x += velX * -1;
                    } else {
                        if((x+(width/2)) <= (tempObject.getBounds().x+(tempObject.getBounds().width/2))) x -= 1;
                        else x += 1;
                    }
                }
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        return boundingBox;
    }
}
