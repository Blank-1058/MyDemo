package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 简化路径
 *
 * 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 *
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径
 *
 * 请注意，返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 *
 * @author liukang_lc on 2019/3/8
 */
public class SimplifyPath {

    public static void main(String[] arg0){
        String path="/a/../../b/../c//.//";
        String result=simplifyPath(path);
        System.out.println(result);
    }

    public static String simplifyPath(String path) {
        //因为.表示本目录，所以去掉含有.的路径
        while(path.contains("/./")){
            path=path.replace("/./","/");
        }
        //去掉双斜杠
        while(path.contains("//")){
            path=path.replace("//","/");
        }
        //将所有路径存入list
        String[] arrays=path.trim().split("/");
        List<String> paths= new ArrayList<>();
        for(int i=0;i<arrays.length;i++){
            if(arrays[i].equals("") || arrays[i].equals(".")){
                continue;
            }
            if(arrays[i].equals("..")){
                if(paths.size()!=0){
                    paths.remove(paths.size()-1);
                }
            }else{
                paths.add(arrays[i]);
            }
        }
        //拼接路径
        StringBuilder result=new StringBuilder();
        if(paths.size()==0){
            return "/";
        }else{
            for(String s:paths){
                result.append("/").append(s);
            }
        }
        return result.toString();
    }
}
