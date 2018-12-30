package pl.bbscoder.sprites;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.*;

public class Animator {
    private Timeline timeline;
    private int currentFrame = 0;

    public void runAnimation(Sprite sprite){
        Animation animation = sprite.getAnimation();
        timeline = new Timeline(new KeyFrame(Duration.millis(animation.getFrameDuration()), event -> {
            if(currentFrame < animation.getSequence().size() - 1){
                currentFrame++;
            }
            else{
                currentFrame = 0;
            }
            Point currentSprite = animation.getSequence().get(currentFrame);
            sprite.setCurrentSprite(currentSprite);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    public void stopAnimation(){
        currentFrame = 0;
        if(timeline != null)
            timeline.stop();
    }
}
