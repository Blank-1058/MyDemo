package leetcode;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class MaxSubArray {

    public static void main(String[] arg0){
        int[] nums=new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int result=maxSubArray(nums);
        System.out.println(result);
    }

    /**
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int maxSum=nums[0];
        int currentSum=nums[0];
        for(int i=1;i<nums.length;i++){
            if(currentSum>=0){
                //如果当前的和为正数，继续计算
                currentSum+=nums[i];
            }else{
                //如果当前和为负数，则舍弃之前的值，重新开始计算
                currentSum=nums[i];
            }
            if(currentSum>maxSum){
                //更新已经缓存的最大和
                maxSum=currentSum;
            }
        }
        return maxSum;
    }

}
