package algorithm.search;

import java.util.Arrays;

/**
 * 查找算法
 */
public class SearchUtils {

    /**
     * 二分查找
     * @param array
     * @param target
     * @return
     */
    public static int binarySearch(int[] array,int target){
        //对数组排序
        Arrays.sort(array);
        int targetIndex=-1;
        int lowIndex=0,highIndex=array.length-1;
        while(lowIndex!=highIndex){
            int mid=(lowIndex+highIndex)/2;
            int x=array[mid];
            if(x==target){
                targetIndex=mid;
                break;
            }else if(x>target){
                highIndex=mid-1;
            }else{
                lowIndex=mid+1;
            }
        }
        return targetIndex;
    }



}
