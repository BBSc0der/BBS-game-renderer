package pl.bbscoder.renderers;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pl.bbscoder.helpers.ImageDivider;
import pl.bbscoder.sprites.Sprite;
import pl.bbscoder.structures.GameObject;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SpriteRenderer extends Renderer {
    List<GameObject> gameObjects;
    Point mapSize;

    public SpriteRenderer(Canvas canvas, Point locationKeeper, Point mapSize) {
        super(canvas, locationKeeper);
        gameObjects = new ArrayList<>();
        this.mapSize = mapSize;
    }

    @Override
    public void render() {
        for(GameObject gameObject : gameObjects){
            Sprite spriteObj = gameObject.getSprite();
            Point goPosition = gameObject.getPossition();
            Image sprite = ImageDivider.getPartOfImage(spriteObj.getSpriteSheet(),0,0,spriteObj.getSpriteWidth(),spriteObj.getSpriteHeight());
            int spriteMidX = (int) gc.getCanvas().getWidth()/2;
            int spriteMidY = (int) gc.getCanvas().getHeight()/2;
            int spriteX = spriteMidX - spriteObj.getSpriteWidth()/2;
            int spriteY = spriteMidY - spriteObj.getSpriteHeight()/2;

            if(goPosition.x < spriteMidX) spriteX = goPosition.x - spriteObj.getSpriteWidth()/2;
            if(goPosition.y < spriteMidY) spriteY = goPosition.y - spriteObj.getSpriteHeight()/2;

            if(goPosition.x > mapSize.x - spriteMidX) spriteX = 2*spriteMidX + goPosition.x - mapSize.x - spriteObj.getSpriteWidth()/2;
            if(goPosition.y > mapSize.y - spriteMidY) spriteY = 2*spriteMidY + goPosition.y - mapSize.y - spriteObj.getSpriteHeight()/2;

            gc.drawImage(sprite,spriteX,spriteY);
        }
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }


    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public Point getMapSize() {
        return mapSize;
    }

    public void setMapSize(Point mapSize) {
        this.mapSize = mapSize;
    }
}
