
public class part1 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int stop = dna.indexOf(stopCodon,startIndex+3);
        while(stop != -1){
            if((stop - startIndex) % 3 == 0){
                return stop;
            }
            else{
                stop = dna.indexOf(stopCodon,stop+3);
            }
        }
        return -1;
    }
    public String findGene(String dna,int startFrom){
        int start = dna.indexOf("ATG",startFrom);
        if(start == -1){
            return "";
        }
        int stoptaa = findStopCodon(dna,start+3,"TAA");
        int stoptga = findStopCodon(dna,start+3,"TGA");
        int stoptag = findStopCodon(dna,start+3,"TAG");
        int min = 0;
        //String gene = "";
        if(stoptaa == -1 || (stoptga != -1 && stoptga < stoptaa)){
            min = stoptga;
        }
        else{
            min = stoptaa;
        }
        if(min == -1 || (stoptag != -1 && stoptag < min)){
            min = stoptag;
        }
        if(min == -1){
            return "";
        }
        return dna.substring(start,min+3);
    }
    public void findAllGenes(String dna){
        int start = 0;
        while(true){
            String gene = findGene(dna,start);
            if(gene.isEmpty()){
                break;
            }
            System.out.println("The Gene is "+gene);
            start = dna.indexOf(gene,start)+gene.length();
        }
    }
    public void testFindStopCodon(){
        String DNA = "CGGCATGCGCAATGGTACGTAAGGCCAT";
        int stopCodon = findStopCodon(DNA,DNA.indexOf("ATG"),"TAA");
        System.out.println("19 "+stopCodon);
        
        DNA = "CGGCATGCGCAATGGTACGCAAGGCCAT";
        stopCodon = findStopCodon(DNA,DNA.indexOf("ATG"),"TAA");
        System.out.println("28 "+stopCodon);
        
        DNA = "CGGCATGCGCAATGGTACTAAGGCCAT";
        stopCodon = findStopCodon(DNA,DNA.indexOf("ATG"),"TAA");
        System.out.println("27 "+stopCodon);
    }
    public void testFindGene(){
        String DNA = "CGGCCTGCGCAATGGTACGTAAGGCCAT";
        String gene = findGene(DNA,0);
        if(gene.isEmpty()){
            System.out.println("The Gene is empty");
        }
        else{
            System.out.println("The Gene is "+gene);
        }
        DNA = "CGGCATGCGCAATGGTACGTAAGGCCAT";
        gene = findGene(DNA,0);
        if(gene.isEmpty()){
            System.out.println("The Gene is empty");
        }
        else{
            System.out.println("The Gene is "+gene);
        }
        DNA = "CGGCATGCGCAATTCCTAGGGTACGTAAGGCCAT";
        gene = findGene(DNA,0);
        if(gene.isEmpty()){
            System.out.println("The Gene is empty");
        }
        else{
            System.out.println("The Gene is "+gene);
        }
        DNA = "CGGCATGCGCAATTCGGTCCTCTGATAGGGTACGTAAGGCCAT";
        gene = findGene(DNA,0);
        if(gene.isEmpty()){
            System.out.println("The Gene is empty");
        }
        else{
            System.out.println("The Gene is "+gene);
        }
        DNA = "CGGCATGCGCAATCTCGGTCCGTTGATAGGGTACGTAAGGCCAT";
        gene = findGene(DNA,0);
        if(gene.isEmpty()){
            System.out.println("The Gene is empty");
        }
        else{
            System.out.println("The Gene is "+gene);
        }
    }
}
