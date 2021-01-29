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
     * 分治思想：
     * 1、把长度为n的数组分成两个长度为n/2的数组
     * 2、分别对这两个数组使用归并排序
     * 3、将两个排序好的数组合并成一个有序的数组
     * @param nums
     * @return
     */
    public static void mergeSort(int[] nums){
        int[] tmp=new int[nums.length];
        mergeSort(nums,0,nums.length-1,tmp);
    }

    private static void mergeSort(int[] nums,int lowIndex,int highIndex,int[] tmp){
        if(lowIndex>=highIndex){
            return;
        }
        int mid=(lowIndex+highIndex)/2;
        mergeSort(nums,lowIndex,mid,tmp);
        mergeSort(nums,mid+1,highIndex,tmp);
        mergeArray(nums,lowIndex,highIndex,mid,tmp);
    }

    /**
     * 合并两个有序数组
     * @param nums
     * @param lowIndex
     * @param highIndex
     * @param mid
     * @param tmp
     */
    private static void mergeArray(int[] nums,int lowIndex,int highIndex,int mid,int[] tmp){
        int tmpIndex=0;
        int i=lowIndex;
        int j=mid+1;
        while(i<=mid && j<=highIndex){
            if(nums[i]<=nums[j]){
                tmp[tmpIndex++]=nums[i++];
            }else{
                tmp[tmpIndex++]=nums[j++];
            }
        }
        //当左边区间还有元素时，将剩余元素放到tmp中
        while(i<=mid){
            tmp[tmpIndex++]=nums[i++];
        }
        //当右边区间还有元素时，将剩余元素放到tmp中
        while(j<=highIndex){
            tmp[tmpIndex++]=nums[j++];
        }
        //当tmp中的元素放回原数组
        tmpIndex=0;
        while(lowIndex<=highIndex){
            nums[lowIndex++]=tmp[tmpIndex++];
        }
    }

    /**
     * 快速排序
     * 1、从数组中选出一个元素作为基准
     * 2、重新排列数组，将比基准小的元素放到基准的左侧，比基准大的元素放到右侧
     * 3、针对基准左边的数组和基准右边的数组继续递归使用快速排序
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

    /**
     * 堆排序
     * 堆的概念：
     * 堆的结构类似于完全二叉树
     * 大顶堆：每个节点的元素值必定不小于其左右子节点，所以，对于大顶堆来说，根节点的元素值是最大的
     * 小顶堆：每个节点的元素值必定不大于其左右子节点，所以，对于小顶堆来说，根节点的元素值是最小的
     * 堆排序要按从小到大的顺序排列的话，首先要构造大顶堆
     * 推排序要按从大到小的顺序排列的话，首先要构造小顶堆
     * 以下代码以按照从小到大的顺序排列为例
     * 1、将数组构造一个大顶堆
     * 2、将堆顶元素与数组最后一个元素R(n)交换，这时数组最后一个元素R(n)是最大的
     * 3、将R(1)~R(n-1)重新构造大顶堆
     * 4、将堆顶元素与R(n-1)交换，这时R(n-1)和R(n)就构成了一个从小到大的有序数组
     * 5、再将R(1)~R(n-2)构造大顶堆，这样依次构造大顶堆，将堆顶元素与当前堆的最后一个元素交换，最终构成了一个从小到大的有序数组
     *
     * @param nums
     * @return
     */
    public static int[] heapSort(int[] nums){
        // 构造大顶堆
        buildMaxHeap(nums);
        for(int i=nums.length-1;i>=0;i--){
            // 将堆顶元素与最后一个元素交换
            swap(nums,0,i);
            // 继续将剩下的元素调整成一个大顶堆
            adjustArr(nums,i,0);
        }
        return nums;
    }

    /**
     * 构造大顶堆
     * @param nums
     */
    private static void buildMaxHeap(int[] nums){
        // 根据完全二叉树的性质，在层序遍历结果数组中，数组一半之后的节点肯定不包含子节点
        // 所以下面的i从Math.floor(nums.length/2)开始逐个节点调整堆
        for(int i=(int)(Math.floor(nums.length/2));i>=0;i--){
            adjustArr(nums,nums.length,i);
        }
    }

    /**
     * 调整堆的结构
     * @param nums
     * @param arrLength
     * @param
     */
    private static void adjustArr(int[] nums,int arrLength,int rootIndex){
        int leftNode = 2*rootIndex+1;
        int rightNode = 2*rootIndex+2;
        int targetIndex = rootIndex;

        if(leftNode<arrLength && nums[leftNode]>nums[targetIndex]){
            targetIndex = leftNode;
        }
        if(rightNode<arrLength && nums[rightNode]>nums[targetIndex]){
            targetIndex=rightNode;
        }

        if(targetIndex!=rootIndex){
            // 进行堆节点调整
            swap(nums,rootIndex,targetIndex);
            // 继续调整子节点
            adjustArr(nums,arrLength,targetIndex);
        }

    }

    /**
     * 交换数组中两个元素的位置
     * @param nums
     * @param index1
     * @param index2
     */
    private static void swap(int[] nums,int index1,int index2){
        if(index1<0 || index1>=nums.length || index2<0 || index2>=nums.length){
            return;
        }
        int num = nums[index1];
        nums[index1]=nums[index2];
        nums[index2]=num;
    }

}
