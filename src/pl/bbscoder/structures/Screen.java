package pl.bbscoder.structures;

import java.awt.*;
import java.util.List;

public class Screen {
    private int height;
    private int width;
    private int TILE_SIZE;

    /**
     *
     * @param width screen width. Same as canvas width
     * @param height screen height. Same as canvas height
     * @param TILE_SIZE - size of single tile
     */
    public Screen(int width, int height,  int TILE_SIZE) {
        this.height = height;
        this.width = width;
        this.TILE_SIZE = TILE_SIZE;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns upper left tile coordinates of currently visible part of map.
     * Uses current location of middle of the screen and one of the layers.
     * @param layer one of the layers of map
     * @param location current position of middle of the screen relative to
     *                 beginning of the map
     * @return coordinates of upper left tile of currently visible part of map.
     */
    public Point getFirstTile(List<List<Point>> layer, Point location){
        int maxTileX = layer.size() -1;
        int maxTileY = layer.get(0).size() -1;
        int maxScreenFirstTileX = (int) Math.floor((double)(maxTileX*TILE_SIZE - width)/TILE_SIZE) +1;
        int maxScreenFirstTileY = (int) Math.floor((double)(maxTileY*TILE_SIZE - height)/TILE_SIZE) +1;
        int calculatedTileX = (int) Math.floor((location.x - (double)width/2)/TILE_SIZE);
        int calculatedTileY = (int) Math.floor((location.y - (double)height/2)/TILE_SIZE);

        Point firstTile = new Point();
        firstTile.x = calculatedTileX >= 0 ? calculatedTileX : 0;
        if(firstTile.x > maxScreenFirstTileX) firstTile.x = maxScreenFirstTileX;
        firstTile.y = calculatedTileY >= 0 ? calculatedTileY : 0;
        if(firstTile.y > maxScreenFirstTileY) firstTile.y = maxScreenFirstTileY;

        return firstTile;
    }

    /**
     * Returns bottom right tile coordinates of currently visible part of map.
     * Uses current location of middle of the screen and one of the layers.
     * @param layer one of the layers of map
     * @param location current position of middle of the screen relative to
     *                 beginning of the map
     * @return coordinates of bottom right tile of currently visible part of map.
     */
    public Point getLastTile(List<List<Point>> layer, Point location){
        int maxTileX = layer.size() -1;
        int maxTileY = layer.get(0).size() -1;
        int minScreenTileX = (int) Math.ceil((double)width / TILE_SIZE) -1;
        int minScreenTileY = (int) Math.ceil((double)height / TILE_SIZE) -1;
        int calculatedTileX = (int) Math.ceil((location.x + (double)width/2)/TILE_SIZE) -1;
        int calculatedTileY = (int) Math.ceil((location.y + (double)height/2)/TILE_SIZE) -1;

        Point lastTile = new Point();
        lastTile.x =  calculatedTileX <= maxTileX ? calculatedTileX : maxTileX;
        if(lastTile.x < minScreenTileX) lastTile.x = minScreenTileX;
        lastTile.y = calculatedTileY <= maxTileY ? calculatedTileY : maxTileY;
        if(lastTile.y < minScreenTileY) lastTile.y = minScreenTileY;

        return lastTile;

    }
    /**
     * Returns upper left pixel numbers of currently visible part of map.
     * The numbering is relative to the beginning of the map.
     * Uses current location of middle of the screen and one of the layers.
     * @param layer one of the layers of map
     * @param location current position of middle of the screen relative to
     *                 beginning of the map
     * @return pixel numbers of upper left pixel of currently visible part of map
     * relative to beginning of map.
     */
    public Point getFirstPixel(List<List<Point>> layer, Point location){
        int maxTileX = layer.size() -1;
        int maxTileY = layer.get(0).size() -1;
        int maxScreenFirstPixelX = (int) Math.floor((maxTileX+1)*TILE_SIZE - width);
        int maxScreenFirstPixelY = (int) Math.floor((maxTileY+1)*TILE_SIZE - height);
        int CalculatedPixelX = (int) Math.floor(location.x - (double)width/2);
        int CalculatedPixelY = (int) Math.floor(location.y - (double)height/2);

        Point firstPixel = new Point();
        firstPixel.x = CalculatedPixelX >= 0 ? CalculatedPixelX : 0;
        if(firstPixel.x > maxScreenFirstPixelX) firstPixel.x = maxScreenFirstPixelX;
        firstPixel.y =  CalculatedPixelY >= 0 ? CalculatedPixelY : 0;
        if(firstPixel.y > maxScreenFirstPixelY) firstPixel.y = maxScreenFirstPixelY;

        return firstPixel;
    }
}
