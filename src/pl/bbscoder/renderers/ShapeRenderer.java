package pl.bbscoder.renderers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class ShapeRenderer {
    private Canvas canvas;
    private GraphicsContext gc;

    public ShapeRenderer(){

    }
    public ShapeRenderer(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    public void renderOval(int x, int y, int w, int h, Color color){
        gc.setFill(color);
        gc.fillOval(x, y, w, h);
    }
    public void renderRect(int x, int y, int w, int h, Color color){
        gc.setFill(color);
        gc.fillRect(x, y, w, h);
    }
    public void renderImage(Image image, int x, int y){
        gc.drawImage(image,x,y);
    }
}
