package designpattern.factory.simplefactory;

import designpattern.factory.factorymethod.MobilePhone;

/**
 * 简单工厂模式测试类
 *
 * @author liukang_lc on 2020/10/20
 */
public class SimpleFactoryTest {
    public static void main(String[] args) {
        MobilePhone mi = SimpleMobilePhoneFactory.generateMobile(SimpleMobilePhoneFactory.MI);
        mi.showMobilePhoneInfo();

        MobilePhone huawei = SimpleMobilePhoneFactory.generateMobile(SimpleMobilePhoneFactory.HUAWEI);
        huawei.showMobilePhoneInfo();
    }
}
