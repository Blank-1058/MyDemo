package leetcode;

import java.util.Arrays;

/**
 * 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 */
public class LongestConsecutive {


    public static void main(String[] arg0){
        int[] nums=new int[]{100,4,200,1,3,2};
        System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        if(nums.length==0){
            return 0;
        }
//        Arrays.sort(nums);
        nums=sort(nums);
        int maxLength=1;
        int maxTmp=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]-nums[i-1]==1){
                maxTmp++;
            }else if(nums[i]==nums[i-1]){
                //当存在相等的数字时，也不能停止计算
                continue;
            }else{
                maxLength=Math.max(maxLength,maxTmp);
                maxTmp=1;
            }
        }
        return Math.max(maxLength,maxTmp);
    }

    private static int[] sort(int[] nums){
        inserSort(nums,1);
        return nums;
    }

    private static void inserSort(int[] nums,int index){
        if(index>=nums.length){
            return;
        }
        for(int i=index;i>=1;i--){
            if(nums[i]<nums[i-1]){
                int tmp=nums[i];
                nums[i]=nums[i-1];
                nums[i-1]=tmp;
            }else{
                break;
            }
        }
        inserSort(nums,index+1);
    }
}
