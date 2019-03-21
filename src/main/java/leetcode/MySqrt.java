package leetcode;

/**
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 * @author liukang_lc on 2019/3/21
 */
public class MySqrt {

    public static void main(String[] arg0){
        int x=2147395599;
        int result=mySqrt(x);
    }

    /**
     * 利用二分查找找出答案
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        //使用long避免溢出
        long left=0,right=x;
        long ans=0;
        while(left<=right){
            long mid=(left+right)/2;
            if(mid*mid>x){
                right=mid-1;
            }else if(mid*mid<=x){
                ans=mid;
                left=mid+1;
            }
        }
        return (int)ans;
    }
}
