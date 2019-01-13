package Objects;

import Game.GameView;

import java.awt.*;

import static Backend.SFXplayer.footstepSound;

public class Player extends Entity{

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
    public Player(int x, int y, int Z, int width, int height, ObjectID id, boolean blocking, ObjectHandler objectHandler) {
        super(x, y, Z, width, height, id, blocking, objectHandler);

        this.objectHandler = objectHandler;

        defaultSprite = objectHandler.defaultSpritePlayer;
        currentSprite = defaultSprite;

        boundingBox = new java.awt.Rectangle((x+width/4)+50, y, (width-width/2)-100, height);

    }

    @Override
    public void update() {
        boundingBox.x = (this.x+width/4)+50;
        boundingBox.y = this.y;

        x += velX;
        y += velY;

        collision();
        movement();
    }

    private void movement() {
        if(objectHandler.isCanMove()) {
            int walkSpeed = 4;
            if(objectHandler.isLeft()) {
                direction = "left";
                velX = -walkSpeed;
            } else if(!objectHandler.isRight()){
                velX = 0;
            }
            if(objectHandler.isRight()) {
                direction = "right";
                velX = walkSpeed;
            } else if(!objectHandler.isLeft()) {
                velX = 0;
            }

            if(objectHandler.isLeft() || objectHandler.isRight()) {
                if (walkTime == 0) {
                    if (walkStage == 1) {
                        currentSprite = objectHandler.WalkSprite1Player;
                        walkStage++;
                        footstepSound();
                    } else if (walkStage == 2) {
                        currentSprite = objectHandler.WalkSprite2Player;
                        walkStage++;
                    } else if (walkStage == 3) {
                        currentSprite = objectHandler.WalkSprite3Player;
                        walkStage++;
                    } else if (walkStage == 4) {
                        currentSprite = objectHandler.WalkSprite4Player;
                        walkStage++;
                        footstepSound();
                    } else if (walkStage == 5) {
                        currentSprite = objectHandler.WalkSprite5Player;
                        walkStage++;
                    } else if (walkStage == 6) {
                        currentSprite = objectHandler.WalkSprite6Player;
                        walkStage = 1;
                    }
                }
                walkTime++;
                if (walkTime == 15) {
                    walkTime = 0;
                }
            } else {
                currentSprite = objectHandler.defaultSpritePlayer;
            }
        } else {
            velX = 0;
        }
    }

    @Override
    public void render(Graphics g) {
        if(GameView.debug) {
            System.out.println(x);
            g.setColor(Color.red);
            g.drawRect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
        }
        if(objectHandler.isVisible()) {
            if(direction.equals("left")) {
                g.drawImage(currentSprite, x, y, width, height, null);
            } else if(direction.equals("right")) {
                g.drawImage(currentSprite, x+width, y, -width, height, null);
            }
        }
    }

    @Override
    public void activate() {

    }

    @Override
    public void reset() {

    }
}
