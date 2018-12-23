package pl.bbscoder.helpers;

import pl.bbscoder.structures.MapBackground;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MapReader {
    private final String LAYER = "LAYER";
    private final String LAYER0 = "LAYER0";
    private final String LAYER1 = "LAYER1";
    private final String LAYER2 = "LAYER2";

    public MapBackground readMapFromFile(String filePath){
        List<String> lines = readLinesFromFile(filePath);
        List<List<Point>> firstLayer = new ArrayList<>();
        List<List<Point>> secondLayer = new ArrayList<>();
        List<List<Point>> thirdLayer = new ArrayList<>();
        int columnsCount = lines.get(1).split("\\|").length;

        for(int i = 0; i < columnsCount ; i++){
            firstLayer.add(new ArrayList<>());
            secondLayer.add(new ArrayList<>());
            thirdLayer.add(new ArrayList<>());
        }
        String currentLayer = "";
        for(String line : lines){
            if(line.startsWith(LAYER)){
                currentLayer = line;
            }else{
                String[] points = line.split("\\|");
                for(int i = 0; i < points.length ; i++){
                    String[] coordinates = points[i].split("\\:");
                    Point point = readPointFromCoordinates(coordinates);
                    if(currentLayer.equals(LAYER0)){
                        firstLayer.get(i).add(point);
                    }else if(currentLayer.equals(LAYER1)){
                        secondLayer.get(i).add(point);
                    }else if(currentLayer.equals(LAYER2)){
                        thirdLayer.get(i).add(point);
                    }
                }
            }
        }
        MapBackground mapBackground = new MapBackground(firstLayer,secondLayer,thirdLayer);
        return mapBackground;
    }
    private List<String> readLinesFromFile(String filePath){
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(filePath))) {

            lines = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    private Point readPointFromCoordinates(String[] coordinates){
        int x,y;
        try{
            x = Integer.valueOf(coordinates[0]);
            y = Integer.valueOf(coordinates[1]);
            if(x < 0 || y < 0) throw new NumberFormatException();
        }catch (NumberFormatException exp){
            x = -1;
            y = -1;
        }
        Point point = new Point();
        point.setLocation(x,y);
        return point;
    }
}
