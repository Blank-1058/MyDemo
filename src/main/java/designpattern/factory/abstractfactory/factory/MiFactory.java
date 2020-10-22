package designpattern.factory.abstractfactory.factory;

import designpattern.factory.abstractfactory.product.*;

/**
 * 小米工厂
 *
 * @author liukang_lc on 2020/10/22
 */
public class MiFactory implements IMobilePhoneAbstractFactory{
    /**
     * 生产Cpu
     *
     * @return
     */
    @Override
    public Cpu createCpu() {
        return new QualcommCpu();
    }

    /**
     * 生产内存
     *
     * @return
     */
    @Override
    public Memory createMemory() {
        return new SamsungMemory();
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
        return new MiPhone(cpu,memory);
    }
}
