package algorithm.sort;

/**
 * 排序算法
 *
 * @author liukang_lc on 2019/3/2
 */
public class SortUtils {

    /**
     * 冒泡排序
     * @param nums
     */
    public static int[] bubbleSort(int[] nums){
        if(nums.length==0 || nums.length==1){
            return nums;
        }
        for(int i=0;i<nums.length-1;i++){
            for(int j=i;j<nums.length-1;j++){
                int tmp=0;
                if(nums[j]>nums[j+1]){
                    tmp=nums[j];
                    nums[j]=nums[j+1];
                    nums[j+1]=tmp;
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序
     *
     * 1、从第一个元素开始，该元素可以认为已经被排序；
     * 2、取出下一个元素，在已经排序的元素序列中从后向前扫描；
     * 3、如果该元素（已排序）大于新元素，将该元素移到下一位置；
     * 4、重复步骤3，直到找到已排序的元素小于或者等于新元素的位置；
     * 5、将新元素插入到该位置后；
     * 重复步骤2~5
     * @param nums
     * @return
     */
    public static int[] insertionSort(int[] nums){
        if(nums.length==0 || nums.length==1){
            return nums;
        }
        for(int i=0;i<nums.length;i++){
            for(int j=i;j>0;j--){
                int tmp=nums[j];
                if(tmp<nums[j-1]){
                    nums[j]=nums[j-1];
                    nums[j-1]=tmp;
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序
     *
     * 初始状态：无序区为R[1..n]，有序区为空；
     * 第i趟排序(i=1,2,3…n-1)开始时，当前有序区和无序区分别为R[1..i-1]和R(i..n）。该趟排序从当前无序区中-选出关键字最小的记录 R[k]，将它与无序区的第1个记录R交换，使R[1..i]和R[i+1..n)分别变为记录个数增加1个的新有序区和记录个数减少1个的新无序区；
     * n-1趟结束，数组有序化了。
     * @param nums
     * @return
     */
    public static int[] selectionSort(int[] nums){
        for(int i=0;i<nums.length;i++){
            int minIndex=i;
            for(int j=i;j<nums.length;j++){
                if(nums[j]<nums[minIndex]){
                    minIndex=j;
                }
            }
            int tmp=nums[minIndex];
            nums[minIndex]=nums[i];
            nums[i]=tmp;
        }
        return nums;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
//    public static int[] mergeSort(int[] nums){
//
//    }

    /**
     * 快速排序
     * @param nums
     * @return
     */
    public static int[] quickSort(int[] nums){
        int lowIndex=0,highIndex=nums.length-1;
        quickSort(nums,lowIndex,highIndex);
        return nums;
    }

    private static void quickSort(int[] nums,int lowIndex,int highIndex){
        if(lowIndex>=highIndex){
            return;
        }
        int base=nums[lowIndex];
        int i=lowIndex,j=highIndex;
        while (i!=j){
            for(;j>i;j--){
                if(nums[j]<base){
                    int tmp=nums[j];
                    nums[j]=nums[i];
                    nums[i]=tmp;
                    break;
                }
            }
            for(;i<j;i++){
                if(nums[i]>base){
                    int tmp=nums[i];
                    nums[i]=nums[j];
                    nums[j]=tmp;
                    break;
                }
            }
        }
        quickSort(nums,lowIndex,i-1);
        quickSort(nums,i+1,highIndex);
    }
}
