package ThinkInJava.chapter5.Practice;

/**
 * this关键字
 */
public class Practice9 {

    public static void main(String[] arg0){
        Test test=new Test();
    }

    public static class Test{
        public Test(){
            this("测试");
        }

        public Test(String s){
            System.out.println(s);
        }
    }
}
