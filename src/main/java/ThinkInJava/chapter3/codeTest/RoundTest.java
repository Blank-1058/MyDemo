package ThinkInJava.chapter3.codeTest;

/**
 * 数值舍入测试
 */
public class RoundTest {
    public static void main(String[] arg0){
        //float转成int类型会直接截取整数部分，不会四舍五入
        float f=12.569f;
        int i=(int)f;
        System.out.println("float直接转成int:"+i);

        //使用Math.round()函数会将float四舍五入转换成int
        float f2=45.569f;
        int i2=Math.round(f2);
        System.out.println("float四舍五入为int:"+i2);
    }
}
