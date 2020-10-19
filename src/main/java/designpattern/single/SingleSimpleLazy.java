package designpattern.single;

/**
 * 简单懒汉式单例模式
 *
 * @author liukang_lc on 2020/10/19
 */
public class SingleSimpleLazy {

    private static SingleSimpleLazy singleSimpleLazy = null;

    private SingleSimpleLazy(){

    }

    /**
     * 使用时才去实例化对象，多线程下会创建多个对象
     * @return
     */
    public static SingleSimpleLazy getInstance(){
        if(singleSimpleLazy==null){
            singleSimpleLazy = new SingleSimpleLazy();
        }
        return singleSimpleLazy;
    }
}
