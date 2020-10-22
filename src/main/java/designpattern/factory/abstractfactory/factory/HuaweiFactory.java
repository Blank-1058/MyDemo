package designpattern.factory.abstractfactory.factory;

import designpattern.factory.abstractfactory.product.*;

/**
 * 华为工厂
 *
 * @author liukang_lc on 2020/10/22
 */
public class HuaweiFactory implements IMobilePhoneAbstractFactory{
    /**
     * 生产Cpu
     *
     * @return
     */
    @Override
    public Cpu createCpu() {
        return new KylinCpu();
    }

    /**
     * 生产内存
     *
     * @return
     */
    @Override
    public Memory createMemory() {
        return new KingstonMemory();
    }

    /**
     * 生产手机
     *
     * @param cpu
     * @param memory
     * @return
     */
    @Override
    public MobilePhone createPhone(Cpu cpu, Memory memory) {
        return new HuaweiPhone(cpu,memory);
    }
}
