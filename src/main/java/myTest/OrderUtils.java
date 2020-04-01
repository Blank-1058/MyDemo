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
        for(int i=1;i<=array.length;i++){
            for(int j=0;j<array.length-i;j++){
                if(array[j]>array[j+1]){
                    int tmp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=tmp;
                }
            }
        }
        return array;
    }

    public static void main(String[] args) {
        int[] a= getRandom(1,100,20);
        printArray(a);

        int[] tmp=bubleSort(a);
        printArray(tmp);
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
        StringBuilder sb=new StringBuilder();
        sb.append("[");
        for(int i=0;i<array.length;i++){
            sb.append(array[i]).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
