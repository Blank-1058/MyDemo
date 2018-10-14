package ThinkInJava.chapter4.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practice10 {

    public static void main(String[] arg0){
        VampireNum vampireNum=new VampireNum(6);
        List<String> result=vampireNum.getVampireList();
        System.out.println("====获取指定位数的吸血鬼数字====");
        if(result==null){
            System.out.println("输入的数字不是偶数");
        }else{
            for(String s:result){
                System.out.println(s);
            }
        }
    }

    //吸血鬼数字：位数为偶数的数字，可以由一对数字相乘得到
    //而这对数字各包含一半位数的最初数字，其中从最初的数字中选取的数字可以任意排序
    //以两个0结尾的数字是不允许的，如
    //1260=21*60；1827=21*87；2187=27*81
    private static class VampireNum{

        private List<String> vampireList=new ArrayList<String>();

        private List<Integer> vampireNumList=new ArrayList<Integer>();

        //找出指定位数的所有吸血鬼数
        public VampireNum(int num){
            if(num%2==1){
                vampireList=null;
            }else{
                findVampireNum(num);
            }
        }

        //根据指定位数循环拆分判断效率太低，直接将一半位数的数字相乘，判断结果是否符合要求
        //如，以获取所有四位数字的吸血鬼数为例，可以依次将两位数字相乘，判断结果是否为吸血鬼数字
        private void findVampireNum(int num){
            int halfNum=num/2;
            //根据位数获取两个乘数的最小值和最大值
            String zero="";
            for(int i=0;i<halfNum-1;i++){
                zero+="0";
            }
            int startNum=Integer.parseInt("1"+zero);
            int endNum=Integer.parseInt("1"+zero+"0");
            for(int i=startNum;i<endNum;i++){
                for(int j=i;j<endNum;j++){
                    if(String.valueOf(i*j).length()!=num){
                        //去除两个数字相乘结果位数不为指定位数的数字
                        continue;
                    }else{
                        if(checkVampireNum(i,j)){
                            vampireList.add(i+" * "+j+" = "+(i*j));
                        }
                    }
                }
            }
        }

        /**
         * 判断是否为吸血鬼数字
         * @param num1 第一个数字
         * @param num2 第二个数字
         * @return
         */
        private boolean checkVampireNum(int num1,int num2){
            int num=num1*num2;
            String[] resultNum=String.valueOf(num).split("");
            String[] originNum=(String.valueOf(num1)+String.valueOf(num2)).split("");
            //对两个数组排序，避免数字顺序不一样
            Arrays.sort(resultNum);
            Arrays.sort(originNum);
            if(vampireNumList.contains(num)){
                return false;
            }
            vampireNumList.add(num);
            return Arrays.equals(resultNum,originNum);
        }

        public List<String> getVampireList() {
            return vampireList;
        }
    }

}
