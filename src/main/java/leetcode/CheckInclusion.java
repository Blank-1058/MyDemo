package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 *
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 *
 * @author liukang_lc on 2019/3/7
 */
public class CheckInclusion {

    public static void main(String[] arg0){
        String s1="ab";
        String s2="cddbssaooo";
        System.out.println(checkInclusion(s1,s2));
    }

    public static boolean checkInclusion(String s1, String s2) {
        if(s1==null || s2==null || s1.length()==0 || s2.length()==0){
            return false;
        }
        int[] count1=new int[26];
        int[] count2=new int[26];
        for(int i=0;i<s1.length();i++){
            count1[s1.charAt(i)-'a']++;
        }
        for(int i=0;i<s2.length();i++){
            if(isSame(count1,count2)){
                return true;
            }
            count2[s2.charAt(i)-'a']++;
            if(i>=s1.length()){
                count2[s2.charAt(i-s1.length())-'a']--;
            }
        }
        if(isSame(count1,count2)){
            return true;
        }
        return false;
    }

    private static boolean isSame(int[] count1,int[] count2){
        for(int i=0;i<count1.length;i++){
            if(count1[i]!=count2[i]){
                return false;
            }
        }
        return true;
    }
}
