package designpattern.proxy.staticproxy;

/**
 * 静态代理测试
 *
 * @author liukang_lc on 2020/10/26
 */
public class StaticProxyTest {
    public static void main(String[] args) {
        // 客户端调用接口方法时不是直接调用实现类的方法，而是调用代理类的方法
        UserProxy userProxy=new UserProxy();
        userProxy.login("test");
    }
}
