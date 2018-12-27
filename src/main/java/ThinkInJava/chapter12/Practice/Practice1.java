package ThinkInJava.chapter12.Practice;

/**
 * 抛出异常
 *
 * @author liukang_lc on 2018/12/27
 */
public class Practice1 {
    public static void main(String[] arg0){
        try{
            throw new Exception("this is a exception");
        }catch (Exception e){
            //使用e.printStackTrace(System.out)会显示在输出中
            //使用e.printStackTrace()会显示在标准错误流中
            e.printStackTrace(System.out);
        }finally {
            System.out.println("this is a finally code");
        }
    }
}
