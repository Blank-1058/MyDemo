package ThinkInJava.chapter5.Practice;

/**
 * 默认构造器以及含参构造器
 */
public class Practice3and4 {

    public static void main(String[] arg0){
        Test test=new Test();
        Test test1=new Test("测试");
    }

    private static class Test{
        public Test(){
            System.out.println("无参构造器");
        }

        public Test(String test){
            System.out.println("含参构造器："+test);
        }
    }
}
