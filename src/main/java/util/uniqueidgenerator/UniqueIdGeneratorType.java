package util.uniqueidgenerator;

/**
 * 支持的解决方案
 * @author blank
 */
public enum UniqueIdGeneratorType {
    // snowflake算法
    SNOWFLAKE,
    // leaf解决方案（美团）
    LEAF,
    // uid解决方案（百度）
    UID,
    // 使用redis自增生成id
    REDIS

}
