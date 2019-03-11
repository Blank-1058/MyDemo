package algorithm.search;

public class SearchTest {

    public static void main(String[] arg0){
        int[] array=new int[]{0,1,2,3,4,5,6,7,8,9,10};
        int result=SearchUtils.binarySearch(array,-1);
        System.out.println("binarySearch:"+result);


    }

}
