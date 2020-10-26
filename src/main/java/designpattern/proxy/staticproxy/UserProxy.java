package designpattern.proxy.staticproxy;

/**
 * 用户代理类
 * 代理类需要实现和实体类一样的接口，但是具体执行逻辑还是实体类去执行
 * @author liukang_lc on 2020/10/26
 */
public class UserProxy implements IUser{

    IUser user=new UserImpl();

    /**
     * 登录
     *
     * @param userName
     * @return
     */
    @Override
    public boolean login(String userName) {
        // 记录日志
        System.out.println("用户"+userName+"开始登陆");
        boolean result=user.login(userName);
        if(result){
            System.out.println("用户"+userName+"登陆成功");
        }else{
            System.out.println("用户"+userName+"登陆失败");

        }
        return result;
    }
}
