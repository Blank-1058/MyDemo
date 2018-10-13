package ThinkInJava.chapter2;

/**
 * Java中数据对象的存储位置
 */
public class ObjectStorage {
    //int, short, long, byte, float, double, boolean, char等8种基本数据类型存储在栈中
    private int aInt=0;
    private float bFloat=0f;
    //包装类数据存于堆中
    private Tmp tmp=new Tmp();
    private String strObject=new String("字符串");
    //String是Java中一种特殊的包装类，保存的是一个指向存在于栈中数据的引用
    private String str="字符串";

    //类
    private class Tmp{

    }
}
