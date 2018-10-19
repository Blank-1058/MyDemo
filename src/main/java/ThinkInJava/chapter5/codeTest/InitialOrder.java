package ThinkInJava.chapter5.codeTest;

/**
 * Java中各类数据的初始化顺序
 * 总体顺序是：静态变量>>静态方法>>非静态变量>>非静态方法
 * 父类>>子类
 * 详细顺序为：父类静态变量/代码段>>子类静态变量/代码段>>父类非静态变量/代码段>>父类无参构造函数>>子类非静态变量/代码段>>子类无参构造函数
 * 注：无论调用子类的无参构造函数还是含参构造函数，都会先执行父类的无参构造函数,除非使用super()方法调用父类的含参构造函数
 */
public class InitialOrder {

    public static void main(String[] arg0){
//        new Son();
        new Son("");
    }

    private static class Father{
        private static String s=print();
        static {
            System.out.println("父类静态代码段");
        }
        {
            System.out.println("父类非静态代码段");
        }
        private static String print(){
            System.out.println("父类静态方法");
            return "父类静态成员变量初始化";
        }
        public Father(){
            System.out.println("父类无参构造函数");
            show();
        }
        public Father(String str){
            System.out.println("父类含参构造函数");
        }
        public void show(){
            System.out.println("父类show()方法");
        }
    }

    private static class Son extends Father{
        static {
            System.out.println("子类静态代码段");
        }
        {
            System.out.println("子类非静态代码段");
        }
        private static String m=printSon();
        private static String printSon(){
            System.out.println("子类静态方法");
            return "子类静态成员变量初始化";
        }
        public Son(){
            System.out.println("子类无参构造函数");
            show();
        }
        public Son(String str){
            super(str);
            System.out.println("子类含参构造函数");
        }
        public void show(){
            System.out.println("子类show()方法");
        }
    }

}
