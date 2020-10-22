package designpattern.factory.abstractfactory;

import designpattern.factory.abstractfactory.factory.HuaweiFactory;
import designpattern.factory.abstractfactory.factory.MiFactory;
import designpattern.factory.abstractfactory.product.Cpu;
import designpattern.factory.abstractfactory.product.Memory;
import designpattern.factory.abstractfactory.product.MobilePhone;

/**
 * 抽象工厂测试类
 *
 * @author liukang_lc on 2020/10/22
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        MiFactory miFactory=new MiFactory();
        Cpu miCpu=miFactory.createCpu();
        Memory miMemory = miFactory.createMemory();

        HuaweiFactory huaweiFactory = new HuaweiFactory();
        MobilePhone huaweiPhone = huaweiFactory.createPhone(miCpu,miMemory);
        huaweiPhone.showMobilePhoneInfo();
    }
}
