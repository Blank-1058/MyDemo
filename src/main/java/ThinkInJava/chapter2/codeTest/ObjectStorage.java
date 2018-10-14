package ThinkInJava.chapter2.codeTest;

/**
 * Java中数据对象的存储位置
 */
public class ObjectStorage {
    //int, short, long, byte, float, double, boolean, char等8种基本数据类型存储在栈中
    private static int aInt=0;
    private static float bFloat=0f;
    //包装类数据存于堆中
    private static Tmp tmp=new Tmp();
    private static String strObject=new String("字符串");
    //String是Java中一种特殊的包装类，保存的是一个指向存在于栈中数据的引用
    private static String str="字符串";

    //类
    private static class Tmp{

    }

    public static void main(String[] arg0){
        //打印出来的是对象的值
        System.out.println(aInt);
        System.out.println(bFloat);
        //打印出来的是对象的引用地址
        System.out.println(tmp);
        //打印出来的是对象的值
        System.out.println(strObject);
        System.out.println(str);
    }
}
