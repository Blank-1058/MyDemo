package leetcode;

import java.util.List;

/**
 * 复原IP地址
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 *
 * 示例:
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 */
public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        return null;
    }

    public void restoreIpAddresses(String s,int numOfPoint,int startIndex,String currentIp,List<String> ips){

        //判断剩余的字符串长度，如果超过ip地址的最大长度，则返回
        if(s.length()>(4-numOfPoint)*3){
            return;
        }
        //判断剩余字符串的长度，如果小于ip地址的长度，则返回
        if(s.length()-startIndex<4-numOfPoint){
            return;
        }



    }

}
