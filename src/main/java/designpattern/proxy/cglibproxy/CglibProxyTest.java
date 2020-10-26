package designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;

/**
 * cglib动态代理测试
 *
 * @author liukang_lc on 2020/10/26
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        // 在UserProxy类中添加了getProxy方法的情况下使用这种调用方式
        UserProxy proxy=new UserProxy();
        MyUser myUser= (MyUser) proxy.getProxy(MyUser.class);
        myUser.login("admin");

        // 在UserProxy类中没有添加getProxy方法的情况下使用这种调用方式
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(MyUser.class);
        enhancer.setCallback(new UserProxy());
        MyUser myUser1 = (MyUser) enhancer.create();
        myUser1.login("test");
    }
}
