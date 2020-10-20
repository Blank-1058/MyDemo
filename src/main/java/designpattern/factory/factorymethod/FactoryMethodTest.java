package designpattern.factory.factorymethod;

/**
 * 工厂方法模式测试
 *
 * @author liukang_lc on 2020/10/20
 */
public class FactoryMethodTest {
    public static void main(String[] args) {
        // 生产一部小米手机
        MobilePhone miPhone = new MiFactory().generateMobilePhone();
        miPhone.showMobilePhoneInfo();

        // 生产一部华为手机
        MobilePhone huaweiPhone = new HuaWeiFactory().generateMobilePhone();
        huaweiPhone.showMobilePhoneInfo();
    }
}
