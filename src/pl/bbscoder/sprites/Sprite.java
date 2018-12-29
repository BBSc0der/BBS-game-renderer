package pl.bbscoder.sprites;

import javafx.scene.image.Image;

import java.util.List;

public class Sprite {
    private Image spriteSheet;
    private int columns;
    private int lines;
    private List<Animation> animations;

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

    public List<Animation> getAnimations() {
        return animations;
    }

    public void setAnimations(List<Animation> animations) {
        this.animations = animations;
    }
}
