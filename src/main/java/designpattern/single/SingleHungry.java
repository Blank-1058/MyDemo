package designpattern.single;

/**
 * 饿汉式单例模式
 * 直接创建对象，不管实例是否被使用过
 * @author liukang_lc on 2020/10/19
 */
public class SingleHungry {

    /**
     * 直接使用成员变量实例化对象
     */
    private static SingleHungry single=new SingleHungry();

    /**
     * 私有化构造函数，不允许外部实例化对象
     */
    private SingleHungry(){

    }

    public static SingleHungry getInstance(){
        return single;
    }

}
