package pl.bbscoder.structures;

import pl.bbscoder.sprites.Sprite;

import java.awt.*;

public abstract class GameObject {
    Point possition;
    Sprite sprite;

    public Point getPossition() {
        return possition;
    }

    public void setPossition(Point possition) {
        this.possition = possition;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
