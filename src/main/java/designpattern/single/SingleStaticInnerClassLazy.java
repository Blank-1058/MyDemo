package designpattern.single;

/**
 * 静态内部类懒汉式
 *
 * @author liukang_lc on 2020/10/19
 */
public class SingleStaticInnerClassLazy {
    private SingleStaticInnerClassLazy(){

    }
    private static class ClassHolder{
        private static SingleStaticInnerClassLazy singleStaticInnerClassLazy=new SingleStaticInnerClassLazy();
    }

    public static SingleStaticInnerClassLazy getInstance(){
        return ClassHolder.singleStaticInnerClassLazy;
    }
}
