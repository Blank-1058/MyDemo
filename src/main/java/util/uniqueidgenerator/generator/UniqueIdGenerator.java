package util.uniqueidgenerator.generator;

/**
 * 分布式唯一ID生成器抽象类
 *
 * @author blank
 */
public abstract class UniqueIdGenerator {

    /**
     * 生成唯一ID
     * @return
     */
    public abstract String generateId();
}
