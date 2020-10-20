package designpattern.factory.factorymethod;

/**
 * 小米手机工厂
 *
 * @author liukang_lc on 2020/10/20
 */
public class MiFactory implements MobilePhoneFactory{
    /**
     * 生产手机
     *
     * @return
     */
    @Override
    public MobilePhone generateMobilePhone() {
        System.out.println("开始生产小米手机");
        return new MiMobilePhone();
    }
}
