package pl.bbscoder.sprites;

import javafx.scene.image.Image;
import pl.bbscoder.helpers.ImageDivider;

import java.awt.*;
import java.util.List;

public class Sprite {
    private Image spriteSheet;
    private int columns;
    private int lines;
    private Point currentSprite;
    private Animation animation;

    public Sprite(Image spriteSheet, int columns, int lines) throws ArithmeticException {
        if( spriteSheet.getWidth() % columns != 0 ){
            throw new ArithmeticException("Cannot divide given spriteSheet into " +columns+ " equal columns.");
        }
        if( spriteSheet.getHeight() % lines != 0 ){
            throw new ArithmeticException("Cannot divide given spriteSheet into " +lines+ " equal lines.");
        }
        this.spriteSheet = spriteSheet;
        this.columns = columns;
        this.lines = lines;
        this.currentSprite = new Point(0,0);
    }

    public Image getSpriteSheet() {
        return spriteSheet;
    }
    public int getColumns() {
        return columns;
    }
    public int getLines() {
        return lines;
    }
    public int getSpriteWidth(){
        return (int) (spriteSheet.getWidth() / columns);
    }
    public int getSpriteHeight(){
        return (int) (spriteSheet.getHeight() / lines);
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Point getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(Point currentSprite) {
        this.currentSprite = currentSprite;
    }
}
