package ThinkInJava.chapter8.Practice;

public class Practice9 {

    public static void main(String[] args){
        Son son=new Son();
        method(son);

        Father father=new Son();
        ((Son) father).method3();
    }

    private static void method(Father father){
        father.method1();
    }

    private static class Father{
        public void method1(){
            System.out.println("Father.Mehtod1()");
            method2();
        }

        public void method2(){
            System.out.println("Father.Method2()");
        }
    }

    private static class Son extends Father{
        public void method2(){
            System.out.println("Son.method2()");
        }

        public void method3(){
            System.out.println("Son.method3()");
        }
    }
}
