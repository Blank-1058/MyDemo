package designpattern.factory.abstractfactory.product;

/**
 * 高通Cpu
 *
 * @author liukang_lc on 2020/10/22
 */
public class QualcommCpu extends Cpu{
    @Override
    void showCpuInfo() {
        System.out.println("高通CPU");
    }
}
