package util.uniqueidgenerator;

import util.uniqueidgenerator.generator.UniqueIdGenerator;
import util.uniqueidgenerator.generator.UniqueIdGeneratorByLeaf;
import util.uniqueidgenerator.generator.UniqueIdGeneratorBySnowflake;

/**
 * 分布式唯一id生成器工厂类
 * @author blank
 */
public class UniqueIdGeneratorFactory {

    /**
     * 创建生成器
     * @param type
     * @return
     */
    public static UniqueIdGenerator createGenerator(UniqueIdGeneratorType type){
        if(UniqueIdGeneratorType.SNOWFLAKE == type){
            return new UniqueIdGeneratorBySnowflake();
        }else if(UniqueIdGeneratorType.LEAF == type){
            return new UniqueIdGeneratorByLeaf();
        }else{
            throw new IllegalArgumentException("不支持的算法");
        }
    }
}
