package ThinkInJava.chapter17.codeTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Array.asList方法测试
 *
 * @author liukang_lc on 2019/1/3
 */
public class ArrayAsListTest {

    public static void main(String[] arg0){
        String[] array=new String[]{"1","2","3","4","5","6","7","8"};
        // 当直接使用Arrays.asList直接转为List时
        // 会基于一个固定大小的数组生成一个List
        // 在这个List中仅支持那些不会改变数组大小的操作
        // 对于会引起数组大小变化的操作会报UnsupportedOperationException错误
        List<String> listArray= Arrays.asList(array);
        testListArray(listArray);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 直接创建的List对象则上述问题
        List<String> list=new ArrayList<String>();
        list.addAll(Arrays.asList(array));
        testListArray(list);
    }

    public static void testListArray(List<String> list){
        try{
            list.add("9");
            System.out.println("添加成功");
            list.remove("8");
            System.out.println("删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
