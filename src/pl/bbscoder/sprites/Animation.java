package pl.bbscoder.sprites;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.Point;
import java.util.List;

public class Animation {
    private String name;
    private List<Point> sequence;
    private int frameDuration;

    public Animation(String name, List<Point> sequence, int frameDuration) {
        this.name = name;
        this.sequence = sequence;
        this.frameDuration = frameDuration;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Point> getSequence() {
        return sequence;
    }

    public void setSequence(List<Point> sequence) {
        this.sequence = sequence;
    }

    public int getFrameDuration() {
        return frameDuration;
    }

    public void setFrameDuration(int frameDuration) {
        this.frameDuration = frameDuration;
    }
}
