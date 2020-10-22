package designpattern.factory.abstractfactory.product;

/**
 * 华为手机实体类
 *
 * @author liukang_lc on 2020/10/22
 */
public class HuaweiPhone extends MobilePhone{

    public HuaweiPhone(Cpu cpu, Memory memory) {
        super(cpu, memory);
    }

    @Override
    public void showMobilePhoneInfo() {
        this.cpu.showCpuInfo();
        this.memory.showMemoryInfo();
        System.out.println("华为手机");
    }
}
