package ThinkInJava.chapter12.codeTest;

/**
 * 重新抛出异常
 *
 * @author liukang_lc on 2018/12/29
 */
public class ExceptionReThrow {

    public static void main(String[] arg0){
        try {
            exception2();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exception1() throws Exception {
        try{
            throw new Exception("this is exception1");
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public static void exception2() throws Exception {
        try{
            exception1();
        }catch (Exception e){
            e.printStackTrace();
            //fillInStackTrace()会将异常信息重定向
            //如果不加的话会定向到最早抛出异常的位置
            throw (Exception)e.fillInStackTrace();
        }
    }
}
