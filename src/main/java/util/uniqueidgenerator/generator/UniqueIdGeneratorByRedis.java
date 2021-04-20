package util.uniqueidgenerator.generator;

import util.redis.RedisConnection;

/**
 * 分布式唯一ID生成器--redis
 *
 * @author liukang_lc on 2021/4/8
 */
public class UniqueIdGeneratorByRedis extends UniqueIdGenerator{

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * id序列的长度
     */
    private long sequenceLength;

    /**
     * 生成的id中是否包含时间
     */
    private boolean containsDateTime = false;

    /**
     * redis操作类，可以替换成其他操作类，如RedisTemplate等
     */
    private RedisConnection redisConnection;

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
