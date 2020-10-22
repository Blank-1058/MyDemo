package designpattern.factory.abstractfactory.factory;

import designpattern.factory.abstractfactory.product.Cpu;
import designpattern.factory.abstractfactory.product.Memory;
import designpattern.factory.abstractfactory.product.MobilePhone;

/**
 * 抽象手机工厂
 */
public interface IMobilePhoneAbstractFactory {
    /**
     * 生产Cpu
     * @return
     */
    Cpu createCpu();

    /**
     * 生产内存
     * @return
     */
    Memory createMemory();

    /**
     * 生产手机
     * @param cpu
     * @param memory
     * @return
     */
    MobilePhone createPhone(Cpu cpu,Memory memory);
}
