package util.uniqueidgenerator.generator;

/**
 * 分布式唯一ID生成器--snowflake算法
 *
 * snowflake是Twitter开源的分布式ID生成算法，结果是一个long型的ID。
 * 其核心思想是：
 * 使用41bit作为毫秒数，10bit作为机器的ID（5个bit是数据中心，5个bit的机器ID），12bit作为毫秒内的流水号（意味着每个节点在每毫秒可以产生 4096 个 ID），最后还有一个符号位，永远是0。
 * 这个算法单机每秒内理论上最多可以生成1000*(2^12)，也就是409.6万个ID
 *
 * id结构：
 * 0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000
 * 1位标识，最高位是符号位，正数是0，负数是1，id一般是正数，所以最高位是0
 * 41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)，这里的的开始时间截，一般是我们的id生成器开始使用的时间，由我们程序来指定的
 * 41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69
 * 10位的数据机器位，可以部署在1024个节点，包括5位数据中心Id和5位工作节点Id
 * 12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号
 * 加起来刚好64位，为一个Long型
 *
 * 优点：
 * 不依赖于数据库，灵活方便，高性能
 * 纯单机执行，没有网络延迟
 * 单机节点生成id按时间递增，一般不会造成id冲突
 *
 * 缺点：
 * 强依赖机器时钟，每台机器上时钟不可能完全同步，所以有时候会出现不是全局递增的情况
 * 需要机器标识位，每个节点需要使用不同的配置，部署麻烦（可以使用zookeeper/mysql等中间件存储节点配置，每次工程启动时进行获取）
 *
 * 参考文章：
 * https://www.cnblogs.com/jiangxinlingdu/p/8440413.html
 *
 * @author blank
 */
public class UniqueIdGeneratorBySnowflake extends UniqueIdGenerator{

    /**
     * 开始时间戳
     */
    private final long startTime = 1617865948492L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据中心id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器ID，结果是(2^workerIdBits)-1，默认是31
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据中心ID，结果是(2^datacenterIdBits)-1，默认是31
     */
    private final long maxDataCenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 自增序列在id中所占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移动的位数
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据中心ID向左移动的位数
     */
    private final long dataCenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间戳向左移动的位数
     */
    private final long timestampShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 自增序列最大值，结果是(2^sequenceBits)-1，默认是4095
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 机器ID（默认范围0-31）
     */
    private long workerId = -1L;

    /**
     * 数据中心ID（默认范围0-31）
     */
    private long datacenterId = -1L;

    /**
     * 自增序列（默认范围0-4095）
     */
    private long sequence = 0L;

    /**
     *
     */
    private long lastTimestamp = -1L;

    /**
     * 生成唯一ID
     *
     * @return
     */
    @Override
    public String generateId() {
        if(workerId == -1L){
            throw new IllegalArgumentException("未设置机器id");
        }
        if(datacenterId == -1L){
            throw new IllegalArgumentException("未设置数据中心id");
        }
        long id = nextId();
        return String.valueOf(id);
    }

    /**
     * 获取下一个ID（保证线程安全）
     * @return
     */
    private synchronized long nextId(){
        long timestamp = System.currentTimeMillis();
        // 判断当前时间戳是否正常
        if(timestamp<lastTimestamp){
            // 当前时间戳早于上一次的时间戳吗，说明发生过系统时钟回退，继续生成id会造成id冲突，抛出异常
            throw new RuntimeException("系统时钟回退，生成id失败");
        }

        if(timestamp == lastTimestamp){
            // 如果当前时间戳上一次已经生成过序列了，序列加一
            sequence = (sequence + 1) & sequenceMask;
            if(sequence == 0){
                // 如果等于0，说明这个毫秒内序列溢出了，阻塞到下一个毫秒，获取新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }else{
            // 时间戳改变了，序列从0开始
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        // 进行移位运算生成id
        long id = ((timestamp-startTime) << timestampShift)
                | (datacenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;
        return id;
    }

    /**
     * 阻塞到下一个毫秒，知道获取新的时间戳
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp){
        long timestamp = System.currentTimeMillis();
        while(timestamp<=lastTimestamp){
            timestamp = System.currentTimeMillis();
        }
        return timestamp;
    }

    /**
     * 设置机器ID和数据中心ID
     * @param workerId
     * @param datacenterId
     */
    public void setWorkerIdAndDatacenterId(long workerId,long datacenterId) {
        if(this.workerId != -1L){
            // 只允许设置一次workerId
            throw new IllegalArgumentException("禁止再次设置workerId");
        }
        if(this.datacenterId != -1L){
            // 只允许设置一次datacenterId
            throw new IllegalArgumentException("禁止再次设置datacenterId");
        }

        if(workerId > maxWorkerId || workerId < 0){
            throw new IllegalArgumentException(String.format("workerId 只能在[0,%d]之间",maxWorkerId));
        }
        if(datacenterId > maxDataCenterId || datacenterId < 0){
            throw new IllegalArgumentException(String.format("datacenterId 只能在[0,%d]之间",maxDataCenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }
}
