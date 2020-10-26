package designpattern.proxy.staticproxy;

/**
 * 用户实体类
 *
 * @author liukang_lc on 2020/10/26
 */
public class UserImpl implements IUser{
    /**
     * 登录
     *
     * @param userName
     * @return
     */
    @Override
    public boolean login(String userName) {
        if("admin".equals(userName)){
            return true;
        }else{
            return false;
        }
    }
}
