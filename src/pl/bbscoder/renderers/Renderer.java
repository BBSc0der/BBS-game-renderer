package pl.bbscoder.renderers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import pl.bbscoder.structures.Screen;

import java.awt.*;

public abstract class Renderer {
    protected GraphicsContext gc;

    public Renderer(Canvas canvas) {
        this.gc = canvas.getGraphicsContext2D();
    }
    public abstract void render(Point location);
}
