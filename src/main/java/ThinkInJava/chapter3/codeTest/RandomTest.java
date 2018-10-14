package ThinkInJava.chapter3.codeTest;

import java.util.Random;

/**
 * Random类测试
 */
public class RandomTest {
    public static void main(String[] arg0){
        //创建Random对象时，如果不传递参数，Java会将当前时间作为随机数生成器的种子，每次执行生成的随机数都会不一样
        Random random=new Random();
        int i=random.nextInt(100);
        float f=random.nextFloat();
        System.out.println("====不指定随机数种子====");
        System.out.println("i="+i);
        System.out.println("f="+f);

        //创建Random对象时，指定随机数生成器的种子，每次执行生成的随机数都一样
        Random random2=new Random(50);
        int i2=random2.nextInt(100);
        float f2=random2.nextFloat();
        System.out.println("====指定随机数种子====");
        System.out.println("i="+i2);
        System.out.println("f="+f2);
    }
}
