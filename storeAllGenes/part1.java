import edu.duke.StorageResource;
import edu.duke.FileResource;
public class part1 {
    public int findStopCodon(String dna,int startIndex,String stopCodon){
        int stop = dna.indexOf(stopCodon,startIndex+3);
        while(stop != -1){
            if((stop - startIndex) % 3 == 0){
                return stop;
            }
            else{
                stop = dna.indexOf(stopCodon,stop+1);
            }
        }
        return -1;
    }
    public String findGene(String dna,int startFrom){
        int start = dna.indexOf("ATG",startFrom);
        if(start == -1){
            return "";
        }
        int stoptaa = findStopCodon(dna,start,"TAA");
        int stoptga = findStopCodon(dna,start,"TGA");
        int stoptag = findStopCodon(dna,start,"TAG");
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
        int count = 0;
        String gene = "";
        while(true){
            gene = findGene(dna,start);
            if(gene.isEmpty()){
                break;
            }
            count ++;
            //System.out.println("The Gene is "+gene);
            start = dna.indexOf(gene,start)+gene.length();
        }
        System.out.println("Gene counter "+count);
    }
    public StorageResource storeAllGenes(String dna){
        StorageResource genes = new StorageResource();
        int start = 0;
        while(true){
            String gene = findGene(dna,start);
            if(gene.isEmpty()){
                break;
            }
            genes.add(gene);
            start = dna.indexOf(gene,start)+gene.length();
        }
        return genes;
    }
    public double cgRatio(String dna){
        int count = 0;
        int C = dna.indexOf("C");
        while(true){
            if(C == -1){break;}
            count ++;
            C = dna.indexOf("C",C+1);
        }
        int G = dna.indexOf("G");
        while(true){
            if(G == -1){break;}
            count ++;
            G = dna.indexOf("G",G+1);
        }
        double cou = count;
        double dn = dna.length();
        return cou/dn;
    }
    public int countCTG(String dna){
        int ctg = dna.indexOf("CTG");
        int count = 0;
        while(true){
            if(ctg == -1){break;}
            count ++;
            ctg = dna.indexOf("CTG",ctg+3);
        }
        return count;
    }
    public void processGenes(StorageResource sr){
        int geneCount = 0;
        int ratioCount = 0;
        int longest = 0;
        double ratio ;
        for(String srdata : sr.data()){
            ratio = cgRatio(srdata);
            if(srdata.length() > 60){
                //System.out.println("High Genes "+srdata);
                geneCount ++;
            }
            if(ratio > 0.35){
                //System.out.println("High cgRatio "+srdata);
                ratioCount ++;
            }
            if(srdata.length() > longest){
                longest = srdata.length();
            }
        }
        System.out.println("Number of Genes higher than 60 characters is "+geneCount);
        System.out.println("Number of Genes CGRatio higher than 0.35 "+ratioCount);
        System.out.println("The longest Gene length is "+longest);
    }
    public void testProcessGenes(){
        FileResource file = new FileResource("brca1line.fa");
        String DNA = file.asString();
        DNA = DNA.toUpperCase();
        StorageResource str = storeAllGenes(DNA);
        processGenes(str);
        int ctg = countCTG(DNA);
        System.out.println("ctg "+ctg);
        findAllGenes(DNA);
    }
  }