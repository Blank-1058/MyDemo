package designpattern.factory.abstractfactory.product;

/**
 * 手机抽象类
 *
 * @author liukang_lc on 2020/10/22
 */
public abstract class MobilePhone {
    protected Cpu cpu;
    protected Memory memory;

    private MobilePhone(){};

    public MobilePhone(Cpu cpu,Memory memory){
        this.cpu=cpu;
        this.memory=memory;
    }

    public abstract void showMobilePhoneInfo();
}
