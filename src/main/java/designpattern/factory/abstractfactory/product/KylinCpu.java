package designpattern.factory.abstractfactory.product;

/**
 * 麒麟CPU
 *
 * @author liukang_lc on 2020/10/22
 */
public class KylinCpu extends Cpu{
    @Override
    void showCpuInfo() {
        System.out.println("麒麟CPU");
    }
}
