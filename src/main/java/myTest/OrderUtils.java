package myTest;

/**
 * 排序算法
 *
 * @author liukang_lc on 2020/4/1
 */
public class OrderUtils {

    /**
     * 冒泡
     * @param array
     * @return
     */
    public static int[] bubleSort(int[] array){
        if(array==null || array.length<=1){
            return array;
        }
        int[] tmpArray = copyArray(array);
        for(int i=1;i<=tmpArray.length;i++){
            for(int j=0;j<tmpArray.length-i;j++){
                if(tmpArray[j]>tmpArray[j+1]){
                    int tmp=tmpArray[j];
                    tmpArray[j]=tmpArray[j+1];
                    tmpArray[j+1]=tmp;
                }
            }
        }
        return tmpArray;
    }

    /**
     * 选择排序
     * 首先在未排序数组中找到最小(大)元素，将这个元素放置到未排序数组的头部，然后继续在剩余未排序数组中执行相同操作，直到最后一个元素
     * @param array
     * @return
     */
    public static int[] selectSort(int[] array){
        if(array==null || array.length<=1){
            return array;
        }
        int[] tmpArray = copyArray(array);
        for(int i=0;i<tmpArray.length;i++){
            int minIndex=i;
            for(int j=i+1;j<tmpArray.length;j++){
                if(tmpArray[j]<tmpArray[minIndex]){
                    minIndex=j;
                }
            }
            if(minIndex!=i){
                int tmp=tmpArray[minIndex];
                tmpArray[minIndex]=tmpArray[i];
                tmpArray[i]=tmp;
            }
        }
        return tmpArray;
    }

    /**
     * 插入排序
     * 构建有序序列，对于未排序的数据，在已排序的序列中从后往前扫描，找到相应位置插入
     * @param array
     * @return
     */
    public static int[] insertSort(int[] array){
        if(array==null || array.length<=1){
            return array;
        }
        int[] tmpArray = copyArray(array);
        for(int i=1;i<tmpArray.length;i++){
            for(int j=i;j>0;j--){
                if(tmpArray[j]<tmpArray[j-1]){
                    int tmp=tmpArray[j];
                    tmpArray[j]=tmpArray[j-1];
                    tmpArray[j-1]=tmp;
                }else{
                    break;
                }
            }
        }
        return tmpArray;
    }

    /**
     * 希尔排序（插入排序的改进版）
     * 选取增量因子为k，则将原始数组分为k个子数组，然后对每个子数组分别执行插入排序
     * 子数组排序完成之后，再次选取一个增量k1,然后对这k1个子数组再进行插入排序
     * 直到增量因子为1时，完成最后一次插入排序，排序结束
     * （一般依次取数组长度的一半作为初始增量因子，这里也是如此）
     * @param array
     * @return
     */
    public static int[] shellInsert(int[] array){
        if(array==null || array.length<=1){
            return array;
        }
        int[] tmpArray = copyArray(array);
        int increFactory=tmpArray.length/2;
        for(;increFactory>0;increFactory=increFactory/2){
            for(int i=increFactory;i<tmpArray.length;i++){
                for(int j=i;j>=increFactory;j-=increFactory){
                    if(tmpArray[j]<tmpArray[j-increFactory]){
                        int tmp=tmpArray[j];
                        tmpArray[j]=tmpArray[j-increFactory];
                        tmpArray[j-increFactory]=tmp;
                    }else{
                        break;
                    }
                }
            }
        }
        return tmpArray;
    }

    /**
     * 归并排序
     * 分治思想，先将子序列排序，然后将排序后的子序列合并
     * 需要额外的内存空间
     * @param array
     * @return
     */
    public static int[] mergeSort(int[] array){
        if(array==null || array.length<=1){
            return array;
        }
        int[] tmpArray = copyArray(array);
        return tmpArray;
    }


    /**
     * 希尔排序：网上参考
     * @param ins
     * @return
     */
    public static int[] shellsorttest(int[] ins) {

        int n = ins.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int j = gap; j < n; j++) {
                int i = j;
                while (i >= gap && ins[i - gap] > ins[i]) {
                    int temp = ins[i - gap] + ins[i];
                    ins[i - gap] = temp - ins[i - gap];
                    ins[i] = temp - ins[i - gap];
                    i -= gap;
                }
            }
            gap = gap / 2;
        }
        return ins;
    }

    public static void main(String[] args) {
        int[] a= getRandom(1,10000,200);
        System.out.println("原始数据");
        printArray(a);

        long bubStart=System.currentTimeMillis();
        int[] buble=bubleSort(a);
        long bubEnd=System.currentTimeMillis();
        System.out.println("冒泡");
        System.out.println("冒泡时间："+(bubEnd-bubStart)+"ms");
        printArray(buble);


        long selectStart=System.currentTimeMillis();
        int[] select=selectSort(a);
        long selectEnd=System.currentTimeMillis();
        System.out.println("选择");
        System.out.println("选择时间："+(selectEnd-selectStart)+"ms");
        printArray(select);

        long insertStart=System.currentTimeMillis();
        int[] insert=insertSort(a);
        long insertEnd=System.currentTimeMillis();
        System.out.println("插入");
        System.out.println("插入时间："+(insertEnd-insertStart)+"ms");
        printArray(insert);


        long shellStart=System.currentTimeMillis();
        int[] shell=shellInsert(a);
        long shellEnd=System.currentTimeMillis();
        System.out.println("希尔");
        System.out.println("希尔时间："+(shellEnd-shellStart)+"ms");
        printArray(shell);
    }

    /**
     * 随机生成整数数组
     * @param minValue 最小值
     * @param maxValue 最大值
     * @param length 数组长度
     * @return
     */
    public static int[] getRandom(int minValue,int maxValue,int length){
        int[] result=new int[length];
        for(int i=0;i<length;i++){
            int r= ((int)(Math.random()*(maxValue-minValue))+minValue);
            result[i] = r;
        }
        return result;
    }

    /**
     * 打印数组
     * @param array
     */
    public static void printArray(int[] array){
        if(array.length>100){
            return;
        }
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<array.length;i++){
            sb.append(array[i]).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /**
     * 拷贝数组
     * @param oriArray
     * @return
     */
    public static int[] copyArray(int[] oriArray){
        int[] tmp=oriArray.clone();
        return tmp;
    }
}
