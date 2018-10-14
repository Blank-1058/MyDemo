package ThinkInJava.chapter2.Practice;


public class Practice1 {
    public static void main(String[] arg0){
        Test test=new Test();
        System.out.println("int:"+test.a);
        System.out.println("char:"+test.c);
    }

    private static class Test{
        int a;
        char c;
    }
}
