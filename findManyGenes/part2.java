public class part2 {
    public int howMany(String a,String b){
        int count = 0;
        int first = 0;
        int second = b.indexOf(a,first);
        int len = a.length();
        while(second != -1){
            first = second;
            count += 1;
            second = b.indexOf(a,first+len);
        }
        return count;
    }
    public void testHowMany(){
        String bstr = "AATTTTAAAA";
        int counter = howMany("AA",bstr);
        System.out.println("Count 3 "+counter);
        bstr = "AATTTTAAAA";
        counter = howMany("A",bstr);
        System.out.println("Count 6 "+counter);
        bstr = "AATTTTAAAA";
        counter = howMany("TT",bstr);
        System.out.println("Count 2 "+counter);
        bstr = "AATTTTAAAA";
        counter = howMany("T",bstr);
        System.out.println("Count 4 "+counter);
    }
}
