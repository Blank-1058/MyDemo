package leetcode;

/**
 * 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且连续的的递增序列。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 3
 * 解释: 最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为5和7在原数组里被4隔开。
 */
public class FindLengthOfLCIS {

    public static void main(String[] arg0){
        int[] nums=new int[]{2,2,2,2,2,2,2};
        System.out.println("result:"+findLengthOfLCIS(nums));
    }

    public static int findLengthOfLCIS(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int maxLength=1;
        int maxTmp=1;
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                maxTmp++;
            }else{
                maxLength=Math.max(maxLength,maxTmp);
                maxTmp=1;
            }
        }
        return Math.max(maxLength,maxTmp);
    }
}
