package Objects;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectHandler {
    //This is the collection that stores each game object, made public, allowing the game to access them easily
    //throughout the code
    public CopyOnWriteArrayList<GameObject> object = new CopyOnWriteArrayList<>();

    //These are the values used for the player. The player can access these as they have an instance of the
    //ObjectHandler they can reference.
    private boolean canMove = false, up = false, down = false, right = false, left = false, visible = false, flashLightOn = false,
            mouseClicked = false, optionsMenu = false, dialogActive = true, gamePaused = false;

    private int dialogStage = 0, eventStage = 0;

    BufferedImage menuBackground;
    BufferedImage menuOptions;
    BufferedImage screenDirt;
    BufferedImage static0;
    BufferedImage static1;
    BufferedImage static2;
    BufferedImage static3;

    BufferedImage defaultSpritePlayer;
    BufferedImage WalkSprite1Player;
    BufferedImage WalkSprite2Player;
    BufferedImage WalkSprite3Player;
    BufferedImage WalkSprite4Player;
    BufferedImage WalkSprite5Player;
    BufferedImage WalkSprite6Player;

    BufferedImage deskChair;
    BufferedImage deskChairPlayer;

    BufferedImage deskOff;
    BufferedImage deskOn;

    BufferedImage drawer;

    BufferedImage curtain;

    BufferedImage bookShelf;
    BufferedImage lampShelf;

    BufferedImage bed;

    BufferedImage bedroomDoorClosed;
    BufferedImage bedroomDoorOpen;

    /**
     * The ObjectHandler constructor simply loads the images in to their respective objects
     */
    public ObjectHandler() {
        Backend.BufferedImageLoader loader = new Backend.BufferedImageLoader();

        menuBackground = loader.loadImage("/Textures/MenuBackground.png");
        menuOptions = loader.loadImage("/Textures/MenuOptions.png");
        screenDirt = loader.loadImage("/Textures/ScreenDirt.png");
        static0 = loader.loadImage("/Textures/Static0.png");
        static1 = loader.loadImage("/Textures/Static1.png");
        static2 = loader.loadImage("/Textures/Static2.png");
        static3 = loader.loadImage("/Textures/Static3.png");

        defaultSpritePlayer = loader.loadImage("/Sprites/Player/Stand.png");
        BufferedImage walkSpriteSheetPlayer = loader.loadImage("/Sprites/Player/Walk.png");
        WalkSprite1Player = walkSpriteSheetPlayer.getSubimage(0, 0, 38, 38);
        WalkSprite2Player = walkSpriteSheetPlayer.getSubimage(38, 0, 38, 38);
        WalkSprite3Player = walkSpriteSheetPlayer.getSubimage(76, 0, 38, 38);
        WalkSprite4Player = walkSpriteSheetPlayer.getSubimage(0, 38, 38, 38);
        WalkSprite5Player = walkSpriteSheetPlayer.getSubimage(38, 38, 38, 38);
        WalkSprite6Player = walkSpriteSheetPlayer.getSubimage(76, 38, 38, 38);

        BufferedImage deskChairSpriteSheet = loader.loadImage("/Sprites/Furniture/DeskChair.png");
        deskChair = deskChairSpriteSheet.getSubimage(0, 0, 38, 38);
        deskChairPlayer = deskChairSpriteSheet.getSubimage(38, 0, 38, 38);

        BufferedImage deskSpriteSheet = loader.loadImage("/Sprites/Furniture/Desk.png");
        deskOff = deskOff = deskSpriteSheet.getSubimage(0, 0, 38, 38);
        deskOn = deskSpriteSheet.getSubimage(38, 0, 38, 38);

        drawer = loader.loadImage("/Sprites/Furniture/Drawer.png");

        curtain = loader.loadImage("/Sprites/Furniture/Curtain.png");

        BufferedImage shelfSpriteSheet = loader.loadImage("/Sprites/Furniture/Shelf.png");
        bookShelf = shelfSpriteSheet.getSubimage(0, 0, 38, 38);
        lampShelf = shelfSpriteSheet.getSubimage(38, 0, 38, 38);

        bed = loader.loadImage("/Sprites/Furniture/Bed.png");

        BufferedImage bedroomDoorSpriteSheet = loader.loadImage("/Sprites/Furniture/BedroomDoor.png");
        bedroomDoorClosed = bedroomDoorSpriteSheet.getSubimage(0, 0, 38, 38);
        bedroomDoorOpen = bedroomDoorSpriteSheet.getSubimage(38, 0, 38, 38);
    }

    /**
     * The update method will just loop through all objects in the handler and update them
     */
    public void update() {
        for (GameObject tempObject : object) {
            tempObject.update();
        }
    }

    /**
     * The render method will just loop through all objects in the handler and render them
     *
     * @param g - g is just a Graphics object given to this method from the repaint method
     */
    public void render(Graphics g) {
        for (GameObject tempObject : object) {
            tempObject.render(g);
        }
    }

    /**
     * @param tempObject - The object to add to the list
     */
    public void addObject(GameObject tempObject) {
        //Adds object to list
        object.add(tempObject);
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFlashLightOn() {
        return flashLightOn;
    }

    public void setFlashLightOn(boolean flashLightOn) {
        this.flashLightOn = flashLightOn;
    }

    public boolean isMouseClicked() {
        return mouseClicked;
    }

    public void setMouseClicked(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }

    public boolean isOptionsMenu() {
        return optionsMenu;
    }

    public void setOptionsMenu(boolean optionsMenu) {
        this.optionsMenu = optionsMenu;
    }

    public boolean isDialogActive() {
        return dialogActive;
    }

    public void setDialogActive(boolean dialogActive) {
        this.dialogActive = dialogActive;
    }

    public int getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(int dialogStage) {
        this.dialogStage = dialogStage;
    }

    public int getEventStage() {
        return eventStage;
    }

    public void setEventStage(int eventStage) {
        this.eventStage = eventStage;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(boolean gamePaused) {
        this.gamePaused = gamePaused;
    }
}
