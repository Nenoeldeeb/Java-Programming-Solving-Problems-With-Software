import edu.duke.FileResource;
import edu.duke.DirectoryResource;
import java.io.File;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
public class babyNames {
    public void totalBirth(FileResource fr){
        CSVParser csv = fr.getCSVParser(false);
        int total = 0;
        int girls = 0;
        int boys = 0;
        int tbn = 0;
        int tgn = 0;
        int tn = 0;
        for(CSVRecord record : csv){
            int birth = Integer.parseInt(record.get(2));
            total += birth;
            tn ++;
            if(record.get(1).equals("M")){
                boys += birth;
                tbn ++;
            }
            else{
                girls += birth;
                tgn ++;
            }
        }
        System.out.println("Total births in This year  "+total);
        System.out.println("Total boys births in This year  "+boys);
        System.out.println("Total gilrs births in This year  "+girls);
        System.out.println("Total names in This year  "+tn);
        System.out.println("Total boys names in This year  "+tbn);
        System.out.println("Total girls names in This year  "+tgn);
    }
    public int getRank(int year,String name,String gend){
        int ranking = 0;
        int rank = 0;
        String Year = "us_babynames_by_year\\yob"+year+".csv";
        FileResource fr = new FileResource(Year);
        CSVParser csv = fr.getCSVParser(false);
        for(CSVRecord rec : csv){
            if(rec.get(1).equals(gend)){
                ranking ++;
                if(rec.get(0).equals(name)){rank = ranking;break;}
                else{rank = -1;}
            }
        }
        return rank;
    }
    public String getName(int year,int rank,String gend){
        String Year = "us_babynames_by_year\\yob"+year+".csv";
        FileResource fr = new FileResource(Year);
        CSVParser csv = fr.getCSVParser(false);
        String name = "Not found";
        int ranking = 0;
        for(CSVRecord rec : csv){
            if(rec.get(1).equals(gend)){
                ranking ++;
                if(ranking == rank){
                    name = rec.get(0);
                    break;
                }
            }
        }
        return name;
    }
    public void theNameInThisYear(String name,int oldYear,int newYear,String gend){
        int rank = getRank(oldYear,name,gend);
        String newName = getName(newYear,rank,gend);
        System.out.println("If You born in "+newYear+" Your name will be "+newName);
    }
    public int highestRankOfName(String name,String gend){
        DirectoryResource dir = new DirectoryResource();
        int highest = -1;
        int rank = 0;
        int year = -1;
        int Year = 0;
        for(File f : dir.selectedFiles()){
            Year = Integer.parseInt(f.getName().substring(3,7));
            rank = getRank(Year,name,gend);
            if(highest == -1 && rank != -1){
                highest = rank;
                year = Year;
            }
            else if(rank < highest && rank != -1){
                highest = rank;
                year = Year;
            }
        }
        return year;
    }
    public double getAvgRank(String name,String gend){
        DirectoryResource dir = new DirectoryResource();
        int rank = -1;
        int year = 0;
        int totalRank = 0;
        int count = 0;
        double avg = -1.0;
        for(File f : dir.selectedFiles()){
            year = Integer.parseInt(f.getName().substring(3,7));
            rank = getRank(year,name,gend);
            count ++;
            totalRank += rank;
        }
        avg = (double)totalRank/count;
        return avg;
    }
    public int totalBirthsHigherRank(int year,String name,String gend){
        String Year = "us_babynames_by_year\\yob"+year+".csv";
        FileResource fr = new FileResource(Year);
        CSVParser csv = fr.getCSVParser(false);
        int totalBirths = 0;
        int ranking = 0;
        int total = 0;
        for(CSVRecord rec : csv){
            if(rec.get(1).equals(gend)){
                total = Integer.parseInt(rec.get(2));
                ranking ++;
                if(rec.get(0).equals(name)){break;}
            }
            totalBirths += total;
        }
        return totalBirths;
    }
    public void testTotalBirth(){
        FileResource fr = new FileResource();
        totalBirth(fr);
    }
    public void testGetRank(){
        int rank = getRank(1982,"Forrest","M");
        System.out.println("The rank of This name is "+rank);
    }
    public void testGetName(){
        String test = getName(1982,450,"M");
        System.out.println("This is your name in the rank "+test);
    }
    public void testHighestRankOfName(){
        int rank = highestRankOfName("Mich","M");
        System.out.println("The highest year ranking "+rank);
    }
}
