package leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 *  翻转字符串里的单词
 *  给定一个字符串，逐个翻转字符串中的每个单词。
 *  无空格字符构成一个单词。
 *  输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *  如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 */
public class ReverseWords {
    public static void main(String[] arg0){
        String s="  hello world!  ";
        reverseWords(s);
    }

    public static String reverseWords(String s) {
        String[] strArray=s.split(" ");
        StringBuilder result=new StringBuilder();
        for(int i=strArray.length-1;i>=0;i--){
            if(!strArray[i].isEmpty() && !strArray[i].equals(" ")){
                result.append(strArray[i]).append(" ");
            }
        }
        if(result.toString().endsWith(" ")){
            result.deleteCharAt(result.length()-1);
        }
        return result.toString();
    }
}
