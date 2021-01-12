import edu.duke.URLResource;
public class part4 {
    public String getUrl(String link){
        URLResource url = new URLResource(link);
        String yout = "";
        for(String line : url.lines()){
            String lines = line.toLowerCase();
            int yt = lines.indexOf("youtube.com",0);
            if(yt != -1){
                int start = line.indexOf("http",0);
                int stop = line.indexOf("\"",start);
                yout = line.substring(start,stop);
                System.out.println("Youtube URL is "+yout);
            }
        }
        return "Done";
    }
    public void test(){
        String link = getUrl("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
    }
}
