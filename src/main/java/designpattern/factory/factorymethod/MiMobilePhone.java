package designpattern.factory.factorymethod;

/**
 * 小米手机实体类
 *
 * @author liukang_lc on 2020/10/20
 */
public class MiMobilePhone extends MobilePhone{
    /**
     * 显示手机信息
     */
    @Override
    public void showMobilePhoneInfo() {
        System.out.println("小米手机");
    }
}
