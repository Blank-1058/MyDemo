package designpattern.factory.factorymethod;

/**
 * 华为手机实体类
 *
 * @author liukang_lc on 2020/10/20
 */
public class HuaWeiMobilePhone extends MobilePhone{
    /**
     * 显示手机信息
     */
    @Override
    public void showMobilePhoneInfo() {
        System.out.println("华为手机");
    }
}
