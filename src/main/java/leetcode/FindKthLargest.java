package leetcode;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Arrays;

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 */
public class FindKthLargest {

    public static void main(String[] arg0){
        int[] nums=new int[]{2,1};
        System.out.println("result is :"+findKthLargest(nums,2));
    }

    public static int findKthLargest(int[] nums, int k) {
//        Arrays.sort(nums);
        nums=sort(nums);
        return nums[nums.length-k];
    }

    /**
     * 对数组进行排序
     * @param nums
     * @return
     */
    private static int[] sort(int[] nums){
        quickSort(nums,0,nums.length-1);
        return nums;
    }

    private static void quickSort(int[]nums,int lowIndex,int highIndex){
        if(lowIndex>=highIndex){
            return;
        }
        int baseNum=nums[lowIndex];
        int high=highIndex,low=lowIndex;
        while(lowIndex!=highIndex){
            for(;highIndex>lowIndex;highIndex--){
                if(nums[highIndex]<baseNum){
                    int tmp=nums[highIndex];
                    nums[highIndex]=nums[lowIndex];
                    nums[lowIndex]=tmp;
                    break;
                }
            }
            for(;lowIndex<highIndex;lowIndex++){
                if(nums[lowIndex]>baseNum){
                    int tmp=nums[lowIndex];
                    nums[lowIndex]=nums[highIndex];
                    nums[highIndex]=tmp;
                    break;
                }
            }
        }
        quickSort(nums,low,lowIndex-1);
        quickSort(nums,lowIndex+1,high);
    }

}
