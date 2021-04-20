package util.uniqueidgenerator.generator;

/**
 * 分布式唯一ID生成器--leaf
 * leaf是美团内部的分布式唯一id解决方案
 *
 * leaf是一种解决方案，实际上还是基于snowflake算法，
 * 使用zookeeper解决了workerId的分配问题，
 * 但是没有完全解决时钟回退造成的id冲突问题
 *
 * 参考文章：
 * https://www.jianshu.com/p/bd6b00e5f5ac?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 * @author blank
 */
public class UniqueIdGeneratorByLeaf extends UniqueIdGenerator{
    /**
     * 生成唯一ID
     *
     * @return
     */
    @Override
    public String generateId() {
        return null;
    }
}
