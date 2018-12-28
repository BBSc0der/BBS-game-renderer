package pl.bbscoder.flow;

import javafx.animation.AnimationTimer;
import pl.bbscoder.renderers.Renderer;

import java.util.ArrayList;
import java.util.List;

public class RenderingFlow {
    private List<Renderer> renderingQueue = new ArrayList<>();
    AnimationTimer animationTimer;

    public void addRenderer(Renderer renderer){
        renderingQueue.add(renderer);
    }
    public void addRenderer(int index, Renderer renderer){
        renderingQueue.add(index,renderer);
    }
    public void removeRenderer(Renderer renderer){
        renderingQueue.remove(renderer);
    }
    public void removeRenderer(int index){
        renderingQueue.remove(index);
    }
    public void clearRenderingQueue(){
        renderingQueue.clear();
    }
    public void getRenderer(int index){
        renderingQueue.get(index);
    }
    public void startRendering(){
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                for(Renderer renderer : renderingQueue){
                    renderer.render();
                }
            }
        };
        animationTimer.start();
    }
}
