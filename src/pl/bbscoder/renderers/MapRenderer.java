package pl.bbscoder.renderers;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pl.bbscoder.helpers.ImageDivider;
import pl.bbscoder.structures.MapBackground;
import pl.bbscoder.structures.Screen;

import java.awt.*;
import java.util.List;

public class MapRenderer {
    private GraphicsContext gc;
    private final int TILE_SIZE;
    private Screen screen;

    /**
     *
     * @param canvas canvas on which tile map will be rendered
     * @param TILE_SIZE size of the tile of map
     */
    public MapRenderer(Canvas canvas, int TILE_SIZE){
        this.gc = canvas.getGraphicsContext2D();
        this.TILE_SIZE = TILE_SIZE;
        this.screen = new Screen((int)canvas.getWidth(),(int)canvas.getHeight(), TILE_SIZE);
    }

    /**
     *
     * @param image image containing single tile
     * @param location coordinates of middle of rendering area
     */
    public void renderTile(Image image, Point location){
        gc.drawImage(image,location.x,location.y);
    }

    /**
     * Method renders whole map on canvas
     *
     * @param background contains tiles coordinates for all three layers
     * @param tileset image containing all possible tiles from which map can be rendered
     */
    public void renderMap(MapBackground background, Image tileset){
        clearCanvas();
        renderLayer(background.getFirstLayer(),tileset);
        renderLayer(background.getSecondLayer(),tileset);
        renderLayer(background.getThirdLayer(),tileset);
    }
    /**
     * Method renders part of map which is specified by location of middle of rendering
     * area and by size of Screen (canvas).
     *
     * @param background contains tiles coordinates for all three layers
     * @param tileset image containing all possible tiles from which map can be rendered
     */
    public void renderMap(MapBackground background, Image tileset,Point location){
        clearCanvas();
        renderLayerPart(background.getFirstLayer(),tileset,location);
        renderLayerPart(background.getSecondLayer(),tileset,location);
        renderLayerPart(background.getThirdLayer(),tileset,location);
    }

    /**
     * Creates transparent image
     * @param w width of image
     * @param h height of image
     * @return the transparent image of specified size
     */
    public static Image createBlankImage(int w, int h){
        WritableImage wi = new WritableImage(w,h);
        for(int i = 0; i < w ; i++){
            for(int j = 0; j < h ; j++){
                wi.getPixelWriter().setColor(i,j, Color.TRANSPARENT);
            }
        }
        return wi;
    }

    /**
     * Renders whole layer
     *
     * @param layer single set of coordinates of tiles on tileset
     * @param tileset image containing all possible tiles from which map can be rendered
     */
    private void renderLayer(List<List<Point>> layer, Image tileset){
        int xMap = 0;
        int yMap = 0;
        for(List<Point> row : layer){
            for(Point tile : row){
                Image image = getTileFromTileset(tileset,tile);
                renderTile(image, new Point(xMap*TILE_SIZE,yMap*TILE_SIZE));
                yMap++;
            }
            xMap++;
            yMap = 0;
        }
    }

    /**
     * Renders part of one of the map's layers.
     * Area of rendered part is defined by current location
     * and Screen (canvas) size.
     *
     * @param layer single set of coordinates of tiles on tileset
     * @param tileset image containing all possible tiles from which map can be rendered
     * @param location coordinates of middle of rendering area
     */
    private void renderLayerPart(List<List<Point>> layer, Image tileset, Point location){

        Point firstPixel = screen.getFirstPixel(layer,location);
        Point firstTile = screen.getFirstTile(layer,location);
        Point lastTile = screen.getLastTile(layer,location);

        Image tilesOfPartOfLayer = getTilesOfPartOfLayer(layer,tileset,firstTile,lastTile);
        int screen0X = firstPixel.x - firstTile.x*TILE_SIZE;
        int screen0Y = firstPixel.y - firstTile.y*TILE_SIZE;
        int canvasHeight = (int) gc.getCanvas().getHeight();
        int canvasWidth = (int) gc.getCanvas().getWidth();

        Image toRender = ImageDivider.getPartOfImage(tilesOfPartOfLayer,screen0X, screen0Y, canvasWidth, canvasHeight);
        gc.drawImage(toRender,0,0);
    }

    /**
     * Returns image that contains all tiles that will be visible on rendered frame
     *
     * @param layer single set of coordinates of tiles on tileset
     * @param tileset image containing all possible tiles from which map can be rendered
     * @param firstTile coordinates of upper left tile that is the first tile visible on the screen
     * @param lastTile coordinates of bottom right tile that is the last tile visible on the screen
     * @return image that contains all tiles visible on the screen. Even tiles that will be
     * visible only partially on result frame generated by renderer.
     */
    private Image getTilesOfPartOfLayer(List<List<Point>> layer, Image tileset, Point firstTile, Point lastTile){
        WritableImage newImage = new WritableImage((lastTile.x - firstTile.x + 1)*TILE_SIZE, (lastTile.y - firstTile.y + 1)*TILE_SIZE);

        int xMap = 0, yMap = 0, xLayer =0, yLayer =0;
        for(List<Point> row : layer){
            for(Point tile : row){
                if(xLayer >= firstTile.x && yLayer >= firstTile.y && xLayer <= lastTile.x && yLayer <= lastTile.y){
                    Image image = getTileFromTileset(tileset,tile);
                    PixelReader tilePR = image.getPixelReader();
                    newImage.getPixelWriter().setPixels(xMap*TILE_SIZE,yMap*TILE_SIZE,TILE_SIZE,TILE_SIZE,tilePR,0,0);
                    yMap++;
                }
                yLayer++;
            }
            if(yMap > 0) xMap++;
            yMap = 0;
            xLayer++;
            yLayer =0;
        }
        return newImage;
    }

    /**
     * Returns single tile from tileset
     *
     * @param tileset image containing all possible tiles from which map can be rendered
     * @param tile coordinates of single tile from tileset
     * @return single tile from tileset
     */
    private Image getTileFromTileset(Image tileset, Point tile){
        Image image;
        if(tile.x > -1 && tile.y > -1){
            try{
                image =  ImageDivider.getPartOfImage(tileset,tile.x*TILE_SIZE,tile.y*TILE_SIZE,TILE_SIZE,TILE_SIZE);
            }catch (IndexOutOfBoundsException exp){
                image = createBlankImage(TILE_SIZE,TILE_SIZE);
            }
        }else{
            image = createBlankImage(TILE_SIZE,TILE_SIZE);
        }
        return image;
    }

    /**
     * Clears the canvas
     */
    private void clearCanvas(){
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
