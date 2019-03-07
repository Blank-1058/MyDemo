package leetcode;

/**
 * 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理
 * @author liukang_lc on 2019/3/7
 */
public class multiplyStr {

    public static void main(String[] arg0){
        System.out.println(multiply("969","56"));
    }

    public static String multiply(String num1, String num2) {
        if("0".equals(num1) || "0".equals(num2)){
            return "0";
        }
        int size1=num1.length(),size2=num2.length();
        int[] resultArray=new int[size1+size2];
        int multiplyFlag=0;
        int carryFlag=0;
        for(int i=size1-1;i>=0;i--){
            for(int j=size2-1;j>=0;j--){
                int multiply=(num1.charAt(i)-'0')*(num2.charAt(j)-'0');
                multiplyFlag=multiply%10;
                carryFlag=multiply/10+(multiplyFlag+resultArray[i+j+1])/10;
                resultArray[i+j+1]=(multiplyFlag+resultArray[i+j+1])%10;
                resultArray[i+j]=carryFlag;
            }
        }
        StringBuilder result=new StringBuilder();
        for(int i=0;i<resultArray.length;i++){
            if(result.length()==0 && resultArray[i]==0){
                continue;
            }
            result.append(resultArray[i]);
        }
        return result.toString();
    }
}
