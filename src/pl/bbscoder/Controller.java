package pl.bbscoder;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import pl.bbscoder.renderers.MapRenderer;
import pl.bbscoder.helpers.MapReader;
import pl.bbscoder.structures.MapBackground;

import java.awt.*;

public class Controller implements EventHandler<KeyEvent> {
    private int x = 955;//320;
    private int y = 240;

    @FXML
    private Canvas canvas;

    @FXML
    private void initialize(){


        MapReader mapReader = new MapReader();

        Image tileset = new Image("file:C:\\my-projects\\tileset.png",false);
        MapBackground mapBackground = mapReader.readMapFromFile("C:\\Users\\kamil.bolesta\\Desktop\\example2.map");

        MapRenderer mapRenderer = new MapRenderer(canvas,32);
        //mapRenderer.renderMap(mapBackground,tileset);

        AnimationTimer animator = new AnimationTimer(){
            @Override
            public void handle(long arg0) {
                int dotX = (int) canvas.getWidth()/2;
                int dotY = (int) canvas.getHeight()/2;
                if(x < dotX) dotX = dotX - (dotX -x);
                if(y < dotY) dotY = dotY - (dotY -y);

                int maxX = mapBackground.getFirstLayer().size()*32;
                int maxY = mapBackground.getFirstLayer().get(0).size()*32;
                if(x > maxX - dotX) dotX = dotX + (x - maxX + dotX);
                if(y > maxY - dotY) dotY = dotY + (y - maxY + dotY);

                mapRenderer.renderMap(mapBackground,tileset,new Point(x,y));
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
             y=y+10;
        }
        if (arg0.getCode() == KeyCode.UP ){
            if(y > 0) y= y-10;
        }
        if (arg0.getCode() == KeyCode.RIGHT){
            x=x+10;
        }
        if (arg0.getCode() == KeyCode.LEFT){
            if(x > 0) x= x-10;
        }
        System.out.println("  x: " + x + " y: " +y);
    }

}
