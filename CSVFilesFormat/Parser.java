import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class Parser {
    public String countryInfo(CSVParser parser,String country){
        for(CSVRecord record : parser){
            String con = record.get("Country");
            if(con.contains(country)){
                String exp = record.get("Exports");
                String val = record.get("Value (dollars)");
                System.out.println(country + ": "+exp + ": "+val);
            }
        }
        return "Done";
    }
    public void listExportersTwoProducts(CSVParser parser,String product1,String product2){
        for(CSVRecord record : parser){
            String exp = record.get("Exports");
            if(exp.contains(product1)){
                if(exp.contains(product2)){
                    String cou = record.get("Country");
                    System.out.println(cou);
                }
            }
        }
    }
    public int numberOfExporters(CSVParser parser,String product){
        int count = 0;
        for(CSVRecord record : parser){
            String exp = record.get("Exports");
            if(exp.contains(product)){
                count ++;
            }
        }
        return count;
    }
    public void bigExporters(CSVParser parser,String amount){
        for(CSVRecord record : parser){
            String val = record.get("Value (dollars)");
            if(val.length() > amount.length()){
                String con = record.get("Country");
                System.out.println(con+" "+val);
            }
        }
    }
    public void testParser(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        countryInfo(parser,"Nauru");
        parser = fr.getCSVParser();
        listExportersTwoProducts(parser,"cotton","flowers");
        parser = fr.getCSVParser();
        int noe = numberOfExporters(parser,"cocoa");
        System.out.println(noe);
        parser = fr.getCSVParser();
        bigExporters(parser,"$000,000,000,000");
    }
}
