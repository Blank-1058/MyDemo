package designpattern.factory.factorymethod;

/**
 * 华为手机工厂
 *
 * @author liukang_lc on 2020/10/20
 */
public class HuaWeiFactory implements MobilePhoneFactory{
    /**
     * 生产手机
     *
     * @return
     */
    @Override
    public MobilePhone generateMobilePhone() {
        System.out.println("开始生产华为手机");
        return new HuaWeiMobilePhone();
    }
}
