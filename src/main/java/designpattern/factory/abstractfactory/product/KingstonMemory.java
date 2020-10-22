package designpattern.factory.abstractfactory.product;

/**
 * 金士顿内存
 *
 * @author liukang_lc on 2020/10/22
 */
public class KingstonMemory extends Memory{
    @Override
    void showMemoryInfo() {
        System.out.println("金士顿内存");
    }
}
