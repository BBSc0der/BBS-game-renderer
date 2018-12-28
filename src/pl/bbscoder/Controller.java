package pl.bbscoder;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import pl.bbscoder.flow.RenderingFlow;
import pl.bbscoder.renderers.MapRenderer;
import pl.bbscoder.helpers.MapReader;
import pl.bbscoder.structures.MapBackground;

import java.awt.*;

public class Controller implements EventHandler<KeyEvent> {
    Point location;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize(){


        MapReader mapReader = new MapReader();

        //Image tileset = new Image("file:C:\\my-projects\\tileset.png",false);
        //MapBackground mapBackground = mapReader.readMapFromFile("C:\\Users\\kamil.bolesta\\Desktop\\example2.map");
        Image tileset = new Image("file:D:\\game-map-renderer-project\\tileset.png",false);
        MapBackground mapBackground = mapReader.readMapFromFile("D:\\game-map-renderer-project\\example2.map");
        location = new Point();
        location.x = 955;
        location.y = 240;
        MapRenderer mapRenderer = new MapRenderer(canvas, mapBackground, tileset, location,32);
        RenderingFlow renderingFlow = new RenderingFlow();
        renderingFlow.addRenderer(mapRenderer);
        renderingFlow.startRendering();

        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0) {
                int dotX = (int) canvas.getWidth()/2;
                int dotY = (int) canvas.getHeight()/2;
                if(location.x < dotX) dotX = dotX - (dotX - location.x);
                if(location.y < dotY) dotY = dotY - (dotY - location.y);

                int maxX = mapBackground.getFirstLayer().size()*32;
                int maxY = mapBackground.getFirstLayer().get(0).size()*32;
                if(location.x > maxX - dotX) dotX = dotX + (location.x - maxX + dotX);
                if(location.y > maxY - dotY) dotY = dotY + (location.y - maxY + dotY);

                canvas.getGraphicsContext2D().setFill(Color.BLACK);
                canvas.getGraphicsContext2D().fillOval(dotX, dotY, 10, 10);
            }
        };
        animator.start();
        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this);
    }
    @Override
    public void handle(KeyEvent arg0) {

        if (arg0.getCode() == KeyCode.DOWN ){
            location.y=location.y+10;
        }
        if (arg0.getCode() == KeyCode.UP ){
            if(location.y > 0) location.y= location.y-10;
        }
        if (arg0.getCode() == KeyCode.RIGHT){
            location.x=location.x+10;
        }
        if (arg0.getCode() == KeyCode.LEFT){
            if(location.x > 0) location.x= location.x-10;
        }
        System.out.println("  x: " + location.x + " y: " +location.y);
    }

}
