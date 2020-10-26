package designpattern.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理类
 *
 * @author liukang_lc on 2020/10/26
 */
public class UserProxy implements MethodInterceptor {

    private Enhancer enhancer=new Enhancer();

    public Object getProxy(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("用户"+objects[0]+"开始登陆");
        Object result = methodProxy.invokeSuper(o,objects);
        if((boolean)result){
            System.out.println("用户"+objects[0]+"登陆成功");
        }else{
            System.out.println("用户"+objects[0]+"登陆失败");
        }
        return result;
    }
}
