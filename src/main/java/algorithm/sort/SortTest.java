package algorithm.sort;

/**
 * 测试类
 *
 * @author liukang_lc on 2019/3/5
 */
public class SortTest {

    public static void main(String[] arg0){
        int[] nums=new int[]{1,5,3,6,9,78,42,55};
        System.out.println("orig:");
        printArrays(nums);

        int[] resultBubble=SortUtils.bubbleSort(nums);
        System.out.println("bunbleSort:");
        printArrays(resultBubble);

        int[] resultInsertion=SortUtils.insertionSort(nums);
        System.out.println("insertionSort:");
        printArrays(resultInsertion);

        int[] resultSelection=SortUtils.selectionSort(nums);
        System.out.println("selectionSort:");
        printArrays(resultSelection);
    }

    private static void printArrays(int[] nums){
        System.out.print("[");
        for(int i=0;i<nums.length;i++){
            if(i==nums.length-1){
                System.out.print(nums[i]);
            }else{
                System.out.print(nums[i]+",");
            }
        }
        System.out.println("]");
    }

}
