package util.uniqueidgenerator.generator;

/**
 * 分布式唯一ID生成器--uid
 * uid是百度内部分布式唯一id的生成方案
 *
 * uid也是在snowflake算法的基础上进行的改进
 * 使用mysql进行workId的分配
 * 通过消费未来时间克服snowflake的并发限制，提前生成一批id缓存到RingBuffer中，解决了时钟回退的问题
 *
 * 参考文章：
 * https://mp.weixin.qq.com/s/8NsTXexf03wrT0tsW24EHA
 *
 * @author liukang_lc on 2021/4/8
 */
public class UniqueIdGeneratorByUid extends UniqueIdGenerator{
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
