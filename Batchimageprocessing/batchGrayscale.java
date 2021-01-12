import edu.duke.DirectoryResource;
import edu.duke.ImageResource;
import edu.duke.Pixel;
import edu.duke.FileResource;
import java.io.File;
public class batchGrayscale {
    public ImageResource convertToGray(File f){
        ImageResource orgImg = new ImageResource(f);
        ImageResource grayImg = new ImageResource(orgImg.getWidth(),orgImg.getHeight());
        for(Pixel px : orgImg.pixels()){
            int avg = (px.getRed()+px.getGreen()+px.getBlue())/3;
            Pixel gpx = grayImg.getPixel(px.getX(),px.getY());
            gpx.setRed(avg);
            gpx.setGreen(avg);
            gpx.setBlue(avg);
        }
        grayImg.setFileName(orgImg.getFileName());
        return grayImg;
    }
    public void saveConverted(ImageResource img){
        String fileName = "gray-"+img.getFileName();
        img.setFileName(fileName);
        img.save();
    }
    public void grayscaleConverter(){
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            ImageResource img = convertToGray(f);
            saveConverted(img);
        }
    }
}
