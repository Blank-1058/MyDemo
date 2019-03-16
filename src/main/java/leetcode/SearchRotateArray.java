package leetcode;

/**
 * 搜索旋转排序数组
 *
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 *
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 *
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 你可以假设数组中不存在重复的元素。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * @author liukang_lc on 2019/3/16
 */
public class SearchRotateArray {

    public static void main(String[] arg0){
        int[] nums=new int[]{};
        int result=search(nums,3);
        System.out.println("目标值："+result);
    }

    public static int search(int[] nums, int target) {
        //查找旋转点的位置
        int rotateIndex=findRotetePoint(nums);
        if(rotateIndex==-1){
            return -1;
        }
        System.out.println("旋转点："+rotateIndex+"--"+nums[rotateIndex]);
        int targetIndex=binarySearch(nums,rotateIndex,target);
        System.out.println("目标值："+targetIndex);
        return targetIndex;
    }

    /**
     * 先用二分法找出旋转点
     * @param nums
     * @return
     */
    private static int findRotetePoint(int[] nums){
        int lowIndex=0,highIndex=nums.length-1;
        if(nums.length==0){
            return -1;
        }
        while(lowIndex!=highIndex && highIndex-lowIndex!=1){
            int mid=(lowIndex+highIndex)/2;
            if(nums[lowIndex]<nums[highIndex]){
                return lowIndex;
            }
            if(nums[mid]>nums[lowIndex]){
                //当数组中心点数值大于低位数值时，说明旋转点在数组右侧
                lowIndex=mid;
            }else{
                //当数组中心点数值小于低位数值时，说明旋转点在数组左侧
                highIndex=mid;
            }
        }
        if(nums[lowIndex]<nums[highIndex]){
            return lowIndex;
        }else{
            return highIndex;
        }
    }

    /**
     * 二分法查找目标值
     * @param nums
     * @param rotateIndex
     * @param target
     * @return
     */
    private static int binarySearch(int[] nums,int rotateIndex,int target){
        int lowIndex=0,highIndex=nums.length-1;
        if(rotateIndex!=0){

            if(target>nums[lowIndex]){
                //目标值大于数组第一个值，说明目标值在旋转点左侧
                highIndex=rotateIndex-1;
            }else if(target<nums[lowIndex]){
                //目标值小于数组第一个值，说明目标值在旋转点右侧
                lowIndex=rotateIndex;
            }else{
                return lowIndex;
            }
        }
        while(lowIndex<highIndex){
            int mid=(lowIndex+highIndex)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                highIndex=mid-1;
            }else{
                lowIndex=mid+1;
            }
        }
        if(nums[lowIndex]!=target){
            return -1;
        }
        return lowIndex;
    }
}
