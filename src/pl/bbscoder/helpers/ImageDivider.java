package pl.bbscoder.helpers;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ImageDivider {
    public static WritableImage getPartOfImage(Image image, int x, int y, int width, int height) throws IndexOutOfBoundsException{
        PixelReader reader = image.getPixelReader();
        WritableImage newImage = new WritableImage(reader, x, y, width, height);
        return newImage;
    }

}
