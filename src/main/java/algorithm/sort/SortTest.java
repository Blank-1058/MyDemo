package algorithm.sort;

import java.util.Stack;

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

        //冒泡排序
        int[] resultBubble=SortUtils.bubbleSort(nums);
        System.out.println("bunbleSort:");
        printArrays(resultBubble);

        //插入排序
        int[] resultInsertion=SortUtils.insertionSort(nums);
        System.out.println("insertionSort:");
        printArrays(resultInsertion);

        //选择排序
        int[] resultSelection=SortUtils.selectionSort(nums);
        System.out.println("selectionSort:");
        printArrays(resultSelection);

        //快速排序
        int[] resultQuick=SortUtils.quickSort(nums);
        System.out.println("quickSort:");
        printArrays(resultQuick);

        //归并排序
        SortUtils.mergeSort(nums);
        System.out.println("mergeSort:");
        printArrays(nums);
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
