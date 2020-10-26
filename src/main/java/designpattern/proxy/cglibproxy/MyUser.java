package designpattern.proxy.cglibproxy;

/**
 * 实体类
 *
 * @author liukang_lc on 2020/10/26
 */
public class MyUser {

    public boolean login(String userName){
        if("admin".equals(userName)){
            return true;
        }else{
            return false;
        }
    }

}
