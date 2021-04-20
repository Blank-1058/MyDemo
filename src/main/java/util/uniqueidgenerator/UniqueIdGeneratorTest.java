package util.uniqueidgenerator;

import util.uniqueidgenerator.generator.UniqueIdGeneratorBySnowflake;

/**
 * 唯一id生成器测试
 *
 * @author liukang_lc on 2021/4/8
 */
public class UniqueIdGeneratorTest {
    public static void main(String[] args) {
        UniqueIdGeneratorBySnowflake generator = (UniqueIdGeneratorBySnowflake) UniqueIdGeneratorFactory.createGenerator(UniqueIdGeneratorType.SNOWFLAKE);
        generator.setWorkerIdAndDatacenterId(1L,1L);
        for(int i=0;i<10000;i++){
            Thread thread=new Thread(new Runnable() {
                @Override
                public void run() {
                    String id = generator.generateId();
                    System.out.println(id);
                }
            });
            thread.start();
        }
    }
}
