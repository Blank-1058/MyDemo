package ThinkInJava.chapter18.codeTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author liukang_lc on 2019/1/4
 */
public class CallableTestMain {
    public static void main(String[] arg0){
        ExecutorService executorService= Executors.newCachedThreadPool();
        //使用executorService.submit()获取返回值，返回值类型为Future<T>
        List<Future<Object>> result=new ArrayList<Future<Object>>();
        for(int i=0;i<5;i++){
            result.add(executorService.submit(new CallableTest(i)));
        }
        for(Future<Object> future:result){
            try {
                System.out.println("result::::"+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                executorService.shutdown();
            }
        }
    }
}
