import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;
import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;
public class weather {
    public CSVRecord coldestHour(CSVParser parser){
        CSVRecord coldest = null;
        for(CSVRecord record : parser){
            if(coldest == null && Double.parseDouble(record.get("TemperatureF")) != -9999.0){
                coldest = record;
            }
            else{
                double cur = Double.parseDouble(record.get("TemperatureF"));
                double cool = Double.parseDouble(coldest.get("TemperatureF"));
                if(cur < cool && cur != -9999.0){
                    coldest = record;
                }
            }
        }
        return coldest;
    }
    public String coldestDay(){
        File temp = null;
        DirectoryResource dir = new DirectoryResource();
        CSVRecord cooler = null;
        for(File f : dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csv = fr.getCSVParser();
            CSVRecord cool = coldestHour(csv);
            if(cooler == null && Double.parseDouble(cool.get("TemperatureF")) != -9999.0){
                cooler = cool;
            }
            else{
                double cur = Double.parseDouble(cool.get("TemperatureF")); 
                double min = Double.parseDouble(cooler.get("TemperatureF")); 
                if(cur < min){
                    cooler = cool;
                    temp = f;
                }
            }
        }
        return temp.getName();
    }
    public CSVRecord lowestHumidity(CSVParser parser){
        CSVRecord lowest = null;
        for(CSVRecord record : parser){
            String na = record.get("Humidity");
            if(!na.equals("N/A")){
	           double numHumid = Double.parseDouble(record.get("Humidity"));
	           
	           if(lowest == null ||  numHumid < Double.parseDouble( lowest.get("Humidity") ) )
	           {
	               lowest = record;
	           } 
            }
        }
        return lowest;
    }
    public CSVRecord lowestHumidityDay(){
        DirectoryResource dir = new DirectoryResource();
        CSVRecord lower = null;
        for(File f : dir.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser csv = fr.getCSVParser();
            CSVRecord lowest = lowestHumidity(csv);
            if(lower == null){
                lower = lowest;
            }
            else{
                double curr = Double.parseDouble(lowest.get("Humidity"));
                double low = Double.parseDouble(lower.get("Humidity"));
                if(curr < low){
                    lower = lowest;
                }
            }
        }
        return lower;
    }
    public double averageTempe(CSVParser parser){
        double tempe = 0.0;
        int times = 0;
        for(CSVRecord record : parser){
            double curTempe = Double.parseDouble(record.get("TemperatureF"));
            if(curTempe > 0.0){
                tempe = tempe + curTempe;
                times ++;
            }
        }
        return tempe/times;
    }
    double avgTempeWithHighHum(CSVParser parser,int hum){
        double times = 0.0;
        double tempe = 0.0;
        for(CSVRecord record : parser){
            double humid = Double.parseDouble(record.get("Humidity"));
            if(humid >= hum){
                double avgTempe = Double.parseDouble(record.get("TemperatureF"));
                tempe = tempe + avgTempe;
                times ++;
            }
        }
        return tempe/times;
    }
    public void testColdestHour(){
        FileResource fr = new FileResource();
        CSVParser csv = fr.getCSVParser();
        CSVRecord cool = coldestHour(csv);
        double min = Double.parseDouble(cool.get("TemperatureF"));
        String time = cool.get("TimeEDT");
        String date = cool.get("DateUTC");
        System.out.println("The coldest hour was "+ min+ " at "+time+" in "+date);
    } 
    public void testColdestMultiple(){
        String coolerFile = coldestDay();
        FileResource file = new FileResource("nc_weather\\2013\\"+coolerFile);
        CSVParser csv = file.getCSVParser();
        CSVRecord cooler = coldestHour(csv);
        //String allTime = cooler.get("TimeEST");
        //String allTempe = cooler.get("TemperatureF");
        double coldest = Double.parseDouble(cooler.get("TemperatureF"));
        System.out.println("The coldest temperature was in file "+coolerFile);
        System.out.println("The coldest temperature was "+coldest);
        for(CSVRecord record : csv){
            String allTime = record.get("TimeEST");
            String allTempe = record.get("TemperatureF");
            System.out.println("Whole day temperatures \n"+allTime+" "+allTempe);
        }
    }
    public void testLowestHumidity(){
        FileResource fr = new FileResource();
        CSVParser csv = fr.getCSVParser();
        CSVRecord lowest = lowestHumidity(csv);
        double lowhum = Double.parseDouble(lowest.get("Humidity"));
        String time = lowest.get("DateUTC");
        System.out.println("The lowest humidity was "+lowhum+" at "+time);
    }
    public void testLowestHumidityDay(){
        CSVRecord lowerHum = lowestHumidityDay();
        double hum = Double.parseDouble(lowerHum.get("Humidity"));
        String time = lowerHum.get("DateUTC");
        System.out.println("The lowest humidity was "+hum+" at "+time);
    }
    public void testAverageTempe(){
        FileResource fr = new FileResource();
        CSVParser csv = fr.getCSVParser();
        double avg = averageTempe(csv);
        System.out.println("The average temperature was "+avg);
    }
    public void testAvgTempeWithHighHum(){
        FileResource fr = new FileResource();
        CSVParser csv = fr.getCSVParser();
        double avg = avgTempeWithHighHum(csv,80);
        System.out.println("The average temperature was "+avg);
    }
}