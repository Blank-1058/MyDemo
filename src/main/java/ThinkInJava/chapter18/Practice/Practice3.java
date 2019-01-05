package ThinkInJava.chapter18.Practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用不同类型的Executor管理和执行线程
 *
 * @author liukang_lc on 2019/1/4
 */
public class Practice3 {

    public static void main(String[] arg0){
        //newCachedThreadPool会为每一个任务创建一个线程（一般情况下会使用此方法管理和执行线程池）
//        ExecutorService executorService= Executors.newCachedThreadPool();
//        for(int i=0;i<10;i++){
//            executorService.execute(new MyRunnable(i));
//        }
//        executorService.shutdown();

        //newFixedThreadPool会预先分配固定数量的线程资源
        ExecutorService executorService=Executors.newFixedThreadPool(1);
        for(int i=0;i<10;i++){
            executorService.execute(new MyRunnable(i));
        }
        executorService.shutdown();

        //newSingleThreadExecutor只会预先分配一个线程资源
//        ExecutorService executorService=Executors.newSingleThreadExecutor();
//        for(int i=0;i<10;i++){
//            executorService.execute(new MyRunnable(i));
//        }
//        executorService.shutdown();
    }


    private static class MyRunnable implements Runnable {

        private int id=0;

        public MyRunnable(int id) {
            this.id=id;
        }

        public void run() {
            System.out.println("start runnable::"+id);
            for(int i=0;i<3;i++){
                System.out.println("message::::"+id+"::::"+i);
                Thread.yield();
            }
            System.out.println("end runnable::"+id);
        }
    }
}
