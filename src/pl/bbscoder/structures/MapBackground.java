package pl.bbscoder.structures;


import java.awt.*;
import java.util.List;

public class MapBackground {
    private List<List<Point>> firstLayer;
    private List<List<Point>> secondLayer;
    private List<List<Point>> thirdLayer;

    public MapBackground() {
    }

    public MapBackground(List<List<Point>> firstLayer, List<List<Point>> secondLayer, List<List<Point>> thirdLayer) {
        this.firstLayer = firstLayer;
        this.secondLayer = secondLayer;
        this.thirdLayer = thirdLayer;
    }

    public List<List<Point>> getFirstLayer() {
        return firstLayer;
    }

    public void setFirstLayer(List<List<Point>> firstLayer) {
        this.firstLayer = firstLayer;
    }

    public List<List<Point>> getSecondLayer() {
        return secondLayer;
    }

    public void setSecondLayer(List<List<Point>> secondLayer) {
        this.secondLayer = secondLayer;
    }

    public List<List<Point>> getThirdLayer() {
        return thirdLayer;
    }

    public void setThirdLayer(List<List<Point>> thirdLayer) {
        this.thirdLayer = thirdLayer;
    }
}
