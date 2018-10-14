package ThinkInJava.chapter2.codeTest;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 高精度数据类型
 */
public class HighPrecisionType {

    //表示任意精度整型数据，比long的范围更大,通过字符串创建
    private static BigInteger bigInteger=new BigInteger("1234546845");
    //表示任意位整数和任意位小数的数据类型，通过字符串创建
    private static BigDecimal bigDecimal=new BigDecimal("123565896.367855");

    public static void main(String[] arg0){
        //BigInteger的运算方式
        BigInteger bigInteger2=new BigInteger("87654654654");
        System.out.println("====BigInteger====");
        System.out.println("加法："+bigInteger.add(bigInteger2));//加法
        System.out.println("减法："+bigInteger.subtract(bigInteger2));//减法
        System.out.println("乘法："+bigInteger.multiply(bigInteger2));//乘法
        System.out.println("除法："+bigInteger.divide(bigInteger2));//除法
        System.out.println("取商和余数：");//取商和余数
        BigInteger[] array=bigInteger.divideAndRemainder(bigInteger2);
        for(int i=0;i<array.length;i++){
            System.out.print(array[i]+"    ");
        }
        System.out.println();

        //BigDecimal的运算方式
        BigDecimal bigDecimal2=new BigDecimal("54512315.32643513");
        System.out.println("====BigDecimal====");
        System.out.println("加法："+bigDecimal.add(bigDecimal2));//加法
        System.out.println("减法："+bigDecimal.subtract(bigDecimal2));//减法
        System.out.println("乘法："+bigDecimal.multiply(bigDecimal2));//乘法
        System.out.println("除法："+bigDecimal.divide(bigDecimal2,5,BigDecimal.ROUND_HALF_UP));//除法,当除法出现无限循环小数时会报错，最好加上保留精度和舍入模式
        System.out.println("乘方："+bigDecimal.pow(2));//乘方
        System.out.println("取绝对值："+bigDecimal.abs());//取绝对值
        System.out.println("指定精度："+bigDecimal.setScale(3,BigDecimal.ROUND_HALF_UP));//指定精度，当小数位数大于指定精度时会报错，必须在后边加上舍入模式
    }
}
