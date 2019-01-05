package ThinkInJava.chapter18.Practice;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 进程睡眠机制
 *
 * @author liukang_lc on 2019/1/4
 */
public class Practice6 {

    public static void main(String[] arg0){
        ExecutorService executorService= Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            executorService.execute(new MyRunnable(i));
        }
        executorService.shutdown();
    }


    private static class MyRunnable implements Runnable{

        private int id=0;

        public MyRunnable(int id) {
            this.id = id;
        }

        public void run() {
            System.out.println("start runnable===="+id);
            int time= new Random().nextInt(10);
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt===="+id);
            }
            System.out.println("end runnable===="+id+"====sleeped===="+time);
        }
    }
}
