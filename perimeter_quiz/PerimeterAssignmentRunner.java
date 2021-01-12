import edu.duke.Shape;
import edu.duke.Point;
import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point curpt : s.getPoints()){
            count += 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double avg = getPerimeter(s) / getNumPoints(s);
        return avg;
    }

    public double getLargestSide(Shape s) {
        double lasid = 0.0;
        Point prept = s.getLastPoint();
        for(Point curpt : s.getPoints()){
            double dist = prept.distance(curpt);
            if(dist > lasid){
                lasid = dist;
            }
            prept = curpt;
        }
        return lasid;
    }

    public double getLargestX(Shape s) {
        double larx = 0.0;
        Point prept = s.getLastPoint();
        double x = prept.getX();
        for(Point curpt : s.getPoints()){
            double nx = curpt.getX();
            if(nx > larx){
                larx = nx;
            }
        }
        return larx;
    }

    public double getLargestPerimeterMultipleFiles() {
        double larfp = 0.0;
        DirectoryResource dir = new DirectoryResource();
        for(File f : dir.selectedFiles()){
            FileResource nf = new FileResource(f);
            Shape ns = new Shape(nf);
            double peri = getPerimeter(ns);
            if(peri > larfp){
                larfp = peri;
            }
        }
        return larfp;
    }

    public String getFileWithLargestPerimeter() {
        DirectoryResource dir = new DirectoryResource();
        File temp = null;
        double larfp = 0.0;
        for(File f : dir.selectedFiles()){
            FileResource nf = new FileResource(f);
            Shape ns = new Shape(nf);
            double peri = getPerimeter(ns);
            if(peri > larfp){
                larfp = peri;
                temp = f;
            }
        }
      
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int ptnum = getNumPoints(s);
        double avlesi = getAverageLength(s);
        double lasi = getLargestSide(s);
        double lax = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of points = " + ptnum);
        System.out.println("The average of sides length = " + avlesi);
        System.out.println("The largest side of the shape is = " + lasi);
        System.out.println("The largest x of points is = " + lax);
    }
    
    public void testPerimeterMultipleFiles() {
        double largestfp = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter of these shepes is = " + largestfp);
    }

    public void testFileWithLargestPerimeter() {
        String fname = getFileWithLargestPerimeter();
        System.out.println("The largest perimeter shape name is = " + fname);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
