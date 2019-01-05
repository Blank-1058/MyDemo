package ThinkInJava.chapter18.codeTest;

import java.util.concurrent.Callable;

/**
 * Callable测试
 *
 * @author liukang_lc on 2019/1/4
 */
public class CallableTest implements Callable {

    private int i=0;

    public CallableTest(int i) {
        this.i = i;
    }

    public Object call() throws Exception {
        return this.i;
    }
}
