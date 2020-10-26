package designpattern.proxy.jdkdynamicproxy;

import designpattern.proxy.staticproxy.IUser;
import designpattern.proxy.staticproxy.UserImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理测试类
 *
 * @author liukang_lc on 2020/10/26
 */
public class JdkDynamicProxyTest {
    public static void main(String[] args) {
        IUser user = new UserImpl();
        IUser userProxy = (IUser) Proxy.newProxyInstance(UserImpl.class.getClassLoader(), user.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 逐个方法进行反射
                if("login".equals(method.getName())){
                    System.out.println("用户"+args[0]+"开始登陆");
                    Object result = method.invoke(user,args);
                    if((boolean)result){
                        System.out.println("用户"+args[0]+"登陆成功");
                    }else{
                        System.out.println("用户"+args[0]+"登陆失败");
                    }
                    return result;
                }else{
                    System.out.println("调用其它方法");
                    return false;
                }
            }
        });
        userProxy.login("test");
    }
}
