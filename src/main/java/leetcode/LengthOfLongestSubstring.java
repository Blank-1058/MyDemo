package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的最长子串的长度。
 *
 * @author liukang_lc on 2019/3/7
 */
public class LengthOfLongestSubstring {

    public static void main(String[] arg0){
        String s="aaaabcaa";
        System.out.println(lengthOfLongestSubstring(s));

        System.out.println(lengthOfLongestSubstring2(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int result=0,i=0,j=0;
        Set<Character> chars=new HashSet<>();
        while(i<s.length() && j<s.length()){
            if(!chars.contains(s.charAt(j))){
                chars.add(s.charAt(j));
                j++;
                result=Math.max(result,j-i);
            }else{
                chars.remove(s.charAt(i));
                i++;
            }
        }
        return result;
    }

    /**
     * ???????再研究研究
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int result=0,i=0,j=0;
        Map<Character,Integer> map=new HashMap<>();
        for(;j<s.length();j++){
            if(map.containsKey(s.charAt(j))){
                i=Math.max(map.get(s.charAt(j)),i);
            }
            result=Math.max(result,j-i+1);
            map.put(s.charAt(j),j+1);
        }
        return result;
    }
}
