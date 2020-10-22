package designpattern.factory.abstractfactory.product;

/**
 * 三星内存
 *
 * @author liukang_lc on 2020/10/22
 */
public class SamsungMemory extends Memory{
    @Override
    void showMemoryInfo() {
        System.out.println("三星内存");
    }
}
