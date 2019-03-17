package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 *
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 */
public class GetPermutation {


    public static void main(String[] arg0){
        int n=4,k=12;
        String result=getPermutation(n,k);
        System.out.println(result);
    }

    public static String getPermutation(int n, int k) {
        List<Integer> indexs=new ArrayList<>();
        k=k-1;
        for(int i=1;i<=n;i++){
            int multi=calMulti(n-i);
            int index=k/multi;
            indexs.add(index);
            k=k%multi;
        }
        return apendStr(n,indexs);
    }

    /**
     * 计算阶乘
     * @param n
     * @return
     */
    private static int calMulti(int n){
        if(n==0){
            return 1;
        }
        int result=1;
        for(int i=1;i<=n;i++){
            result*=i;
        }
        return result;
    }

    private static String apendStr(int n,List<Integer> indexs){
        StringBuilder result=new StringBuilder();
        List<Integer> nNums=new ArrayList<>();
        for(int i=1;i<=n;i++){
            nNums.add(i);
        }
        for(int index:indexs){
            result.append(nNums.get(index));
            nNums.remove(index);
        }
        return result.toString();
    }

}
