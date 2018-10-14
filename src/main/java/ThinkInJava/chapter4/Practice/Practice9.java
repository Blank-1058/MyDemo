package ThinkInJava.chapter4.Practice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Practice9 {

    public static void main(String[] arg0){
        Fibonacci fibonacci=new Fibonacci(50);
        List<BigInteger> result=fibonacci.getFibonacciArray();
        System.out.println("====斐波那契数列结果====");
        for(BigInteger i:result){
            System.out.println(i);
        }
    }

    //斐波那契数列：由数字1 1 2 3 5 8 13等组成，其计算公式为：
    //F(1)=1;F(2)=1;F(n)=F(n-1)+F(n-2)
    private static class Fibonacci{

        private List<BigInteger> fibonacciArray=new ArrayList<BigInteger>();

        //输入一个数字表示数列中数的个数，输出数列中所有数字
        public Fibonacci(int num){
               if(num==0){
                   fibonacciArray.clear();
               }else if(num==1){
                   fibonacciArray.add(new BigInteger("1"));
               }else  if(num==2){
                   fibonacciArray.add(new BigInteger("1"));
                   fibonacciArray.add(new BigInteger("1"));
               }else{
                   for(int i=1;i<=num;i++){
                       if(i==1 || i==2){
                           fibonacciArray.add(new BigInteger("1"));
                       }else{
                           fibonacciArray.add(calcuFibonacciArray());
                       }
                   }
               }
        }

        private BigInteger calcuFibonacciArray(){
            return fibonacciArray.get(fibonacciArray.size()-1).add(fibonacciArray.get(fibonacciArray.size()-2));
        }

        public List<BigInteger> getFibonacciArray() {
            return fibonacciArray;
        }
    }

}
