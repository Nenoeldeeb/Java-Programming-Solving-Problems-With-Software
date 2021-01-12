import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import edu.duke.FileResource;
import java.io.File;
public class batchInversion {
    public ImageResource invertImage(File f){
        ImageResource orgImg = new ImageResource(f);
        ImageResource grayImg = new ImageResource(orgImg.getWidth(),orgImg.getHeight());
        grayImg.setFileName(orgImg.getFileName());
        for(Pixel px : orgImg.pixels()){
            Pixel gpx = grayImg.getPixel(px.getX(),px.getY());
            gpx.setRed(255-px.getRed());
            gpx.setGreen(255-px.getGreen());
            gpx.setBlue(255-px.getBlue());
        }
        return grayImg;
    }
    public void saveInverted(ImageResource img){
        String fileName = "inverted-"+img.getFileName();
        img.setFileName(fileName);
        img.save();
    }
    public void imageColorInversion(){
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            ImageResource img = invertImage(f);
            saveInverted(img);
        }
    }
}
