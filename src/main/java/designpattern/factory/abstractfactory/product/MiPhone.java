package designpattern.factory.abstractfactory.product;

/**
 * 小米手机实体类
 *
 * @author liukang_lc on 2020/10/22
 */
public class MiPhone extends MobilePhone{

    public MiPhone(Cpu cpu, Memory memory) {
        super(cpu, memory);
    }

    @Override
    public void showMobilePhoneInfo() {
        cpu.showCpuInfo();
        memory.showMemoryInfo();
        System.out.println("小米手机");
    }
}
