package pl.bbscoder.renderers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import pl.bbscoder.structures.Screen;

import java.awt.*;

public abstract class Renderer {
    protected GraphicsContext gc;
    protected Point locationKeeper;

    public Renderer(Canvas canvas, Point locationKeeper) {
        this.gc = canvas.getGraphicsContext2D();
        this.locationKeeper = locationKeeper;
    }
    public abstract void render();
}
