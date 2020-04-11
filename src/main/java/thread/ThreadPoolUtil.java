package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池工具类
 * 使用ThreadPoolExcutor创建线程池，加深对线程池的理解
 *
 * @author liukang_lc on 2020/4/11
 */
public class ThreadPoolUtil {

    private static ThreadPoolExecutor singleThreadExecutor=null;

    public static ThreadPoolExecutor newSingleThreadExecutor(){
        if(singleThreadExecutor==null){
            singleThreadExecutor=new ThreadPoolExecutor(1,1,0L, TimeUnit.SECONDS,new LinkedBlockingDeque<>(), Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        }
        return singleThreadExecutor;
    }

}
