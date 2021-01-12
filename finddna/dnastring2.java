public class dnastring2 {
    public String findGene(String d,String strc,String stpc){
        int start = d.indexOf(strc);
        if(start == -1){
            return "";
        }
        int stop = d.indexOf(stpc, start + 3);
        if(stop == -1){
            return "";
        }
        String gene = d.substring(start , stop + 3);
        if(gene.length() % 3 != 0){
            return "";
        } 
         if(d.indexOf("A") >= 0 ){
             gene.toUpperCase();
            }
            else {
                gene.toLowerCase();
            }
        return gene;
    }
    public void testFindGene(){
        String gene = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("The Gene is "+gene);
        String dna = findGene(gene,"ATG","TAA");
        System.out.println("The DNA is "+dna);
        
        gene = "CTATGCGCGGTAGTTCCGCTAACGA";
        System.out.println("The Gene is "+gene);
        dna = findGene(gene,"ATG","TAA");
        System.out.println("The DNA is "+dna);
        
        gene = "CTATACGCTAGTTCCGCTAACGA";
        System.out.println("The Gene is "+gene);
        dna = findGene(gene,"ATG","TAA");
        System.out.println("The DNA is "+dna);
        
        gene = "CTATGCGCTAGTTCCGCTAGCGA";
        System.out.println("The Gene is "+gene);
        dna = findGene(gene,"ATG","TAA");
        System.out.println("The DNA is "+dna);
        
        gene = "CTATCCGCTAGTTCCGCTACCGA";
        System.out.println("The Gene is "+gene);
        dna = findGene(gene,"ATG","TAA");
        System.out.println("The DNA is "+dna);
    }

}
