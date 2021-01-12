public class part3 {
    public boolean twoOccurr(String a,String b){
        int p1 = b.indexOf(a);
        int len = a.length();
        int p2 = b.indexOf(a, p1+len);
        boolean r = false;
        if(p2 != -1){
            r = true;
        }
        return r;
    }
    public String lastPart(String a,String b){
        int p1 = b.indexOf(a);
        int len = a.length();
        String p = b.substring(p1+len);
        if(p1 == -1){
            return b;
        }
        else{
            return p;
        }
    }
    public void testTwoOccurr(){
        String t1 = "banana";
        boolean r1 = twoOccurr("a" , t1);
        System.out.println("reslut is = "+ r1);
        
        boolean r2 = twoOccurr("b" , t1);
        System.out.println("reslut 2 is = "+ r2);
        
        String lp = lastPart("an",t1);
        System.out.println("The last part is "+lp);
        String t2 = "bat man";
        String lp1 = lastPart("f",t2);
        System.out.println("The last part is "+lp1);
    }     
}
