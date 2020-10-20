package designpattern.factory.factorymethod;

/**
 * 简单手机工厂模式
 */
public interface MobilePhoneFactory {
    /**
     * 生产手机
     * @return
     */
    MobilePhone generateMobilePhone();
}
