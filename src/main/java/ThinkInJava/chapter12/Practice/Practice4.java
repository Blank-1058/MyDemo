package ThinkInJava.chapter12.Practice;

/**
 * 自定义异常
 *
 * @author liukang_lc on 2018/12/27
 */
public class Practice4 {

    private static class MyException extends Exception{

        public MyException() {
        }
        public MyException(String message) {
            super(message);
        }
    }

    public static void main(String[] arg0){
        try{
            throw new MyException("this is my custom Exception");
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
