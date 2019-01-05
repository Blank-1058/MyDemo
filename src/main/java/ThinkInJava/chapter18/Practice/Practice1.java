package ThinkInJava.chapter18.Practice;

/**
 * @author liukang_lc on 2019/1/4
 */
public class Practice1 {

    public static void main(String[] arg0){
        for(int i=0;i<20;i++){
            Thread thread=new Thread(new MyRunnable(i));
            thread.start();
        }
    }


    private static class MyRunnable implements Runnable {

        private int id=0;

        public MyRunnable(int id) {
            this.id=id;
            System.out.println("start runnable::"+id);
        }

        public void run() {
            for(int i=0;i<3;i++){
                System.out.println("message::::"+id+"::::"+i);
                Thread.yield();
            }
            System.out.println("end runnable::"+id);
        }
    }
}
