package designpattern.single;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

/**
 * 双重检测机制(DCL)懒汉式
 * 使用双重锁对实例化的过程进行检验，避免多线程下重复创建对象
 * @author liukang_lc on 2020/10/19
 */
public class SingleDoubleCheckLazy {

    private SingleDoubleCheckLazy(){

    }

    private static volatile SingleDoubleCheckLazy singleDoubleCheckLazy = null;

    public static SingleDoubleCheckLazy getInstance(){
        if(singleDoubleCheckLazy==null){
            // 在代码块上加锁，比直接在方法上加锁效率高，因为锁的范围减小了
            synchronized (SingleDoubleCheckLazy.class){
                if(singleDoubleCheckLazy==null){
                    singleDoubleCheckLazy = new SingleDoubleCheckLazy();
                }
            }
        }
        return singleDoubleCheckLazy;
    }

}
