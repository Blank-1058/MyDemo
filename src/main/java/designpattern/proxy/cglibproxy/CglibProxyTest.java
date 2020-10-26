package designpattern.proxy.cglibproxy;

/**
 * cglib动态代理测试
 *
 * @author liukang_lc on 2020/10/26
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        UserProxy proxy=new UserProxy();
        MyUser myUser= (MyUser) proxy.getProxy(MyUser.class);
        myUser.login("admin");
    }
}
