package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 *
 * 满足要求的三元组集合为：
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2]
 * ]
 */
public class ThreeSum {

    public static void main(String[] arg0){
        int[] nums=new int[]{-1,0,1,2,-1,-4};
        List<List<Integer>> result=new ArrayList<>();
        result=threeSum(nums);


    }

    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result=new ArrayList<>();
        //数组排序
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;){
            int j=i+1;
            int k=nums.length-1;

            while(j<k){
                //简化为查找后两个数字的和与（0-nums[i]）相等的逻辑
                if(nums[j]+nums[k]==-nums[i]){
                    List<Integer> list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);
                    result.add(list);

                    j++;
                    k--;
                    //避免重复，检查第二个数字的下一个数字是不是与上一个数字相等
                    while(j<k && nums[j]==nums[j-1]){
                        j++;
                    }
                    //避免重复，检查第三个数字的下一个数字是不是与上一个数字相等
                    while (j<k && nums[k]==nums[k+1]){
                        k--;
                    }
                }else if(nums[j]+nums[k]>-nums[i]){
                    //因为数组是按从小到达的顺序排列的，所以如果后两个数字之和大于(0-nums[i])
                    //说明最后一个数字过大，继续减小最后一个数字
                    k--;
                    while (j<k && nums[k]==nums[k+1]){
                        k--;
                    }
                }else {
                    //因为数组是按从小到达的顺序排列的，所以如果后两个数字之和小于(0-nums[i])
                    //说明第二个数字过小，继续增大第二个数字
                    j++;
                    while (j<k && nums[j]==nums[j-1]){
                        j++;
                    }
                }
            }
            i++;
            //避免重复，检查第一个数字是不是与上一个数字相等
            while (i<nums.length-2 && nums[i]==nums[i-1]){
                i++;
            }
        }
        return result;
    }
}
