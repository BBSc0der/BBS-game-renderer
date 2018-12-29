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
import pl.bbscoder.renderers.SpriteRenderer;
import pl.bbscoder.sprites.Sprite;
import pl.bbscoder.structures.GameObject;
import pl.bbscoder.structures.MapBackground;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
        location.x = 960;
        location.y = 720;
        MapRenderer mapRenderer = new MapRenderer(canvas, mapBackground, tileset, location,32);

        Point mapSize = new Point();
        mapSize.x = mapBackground.getFirstLayer().size()*32;
        mapSize.y = mapBackground.getFirstLayer().get(0).size()*32;
        SpriteRenderer spriteRenderer = new SpriteRenderer(canvas,location,mapSize);
        List<GameObject> gameObjects = new ArrayList<>();
        GameObject gameObject = new GameObject() {
        };
        gameObject.setPossition(location);
        Image spriteSheet = new Image("file:D:\\game-map-renderer-project\\spritesheet.png",false);
        Sprite sprite = new Sprite(spriteSheet,4,4);

        gameObject.setSprite(sprite);
        gameObjects.add(gameObject);
        spriteRenderer.setGameObjects(gameObjects);

        RenderingFlow renderingFlow = new RenderingFlow();
        renderingFlow.addRenderer(mapRenderer);
        renderingFlow.addRenderer(spriteRenderer);
        renderingFlow.startRendering();

        canvas.setFocusTraversable(true);
        canvas.setOnKeyPressed(this);
    }
    @Override
    public void handle(KeyEvent arg0) {

        if (arg0.getCode() == KeyCode.DOWN ){
            location.y=location.y+1;
        }
        if (arg0.getCode() == KeyCode.UP ){
            if(location.y > 0) location.y= location.y-1;
        }
        if (arg0.getCode() == KeyCode.RIGHT){
            location.x=location.x+1;
        }
        if (arg0.getCode() == KeyCode.LEFT){
            if(location.x > 0) location.x= location.x-1;
        }
        System.out.println("  x: " + location.x + " y: " +location.y);
    }

}
