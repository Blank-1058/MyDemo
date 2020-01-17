package thread;

/**
 * 三个售票窗口同时售票
 * @author liukang_lc on 2019/6/13
 */
public class TicketSell {

    public static TicketNum ticketNum=new TicketNum(50);

    public static void main(String[] arg0){
        Station1 station1=new Station1();
        station1.start();
        Station2 station2=new Station2();
        station2.start();
        Station3 station3=new Station3();
        station3.start();
    }

    private static class Station1 extends Thread{
        @Override
        public void run() {
            synchronized (ticketNum){
                while(ticketNum.num!=0){
                    ticketNum.num--;
                    System.out.println("station1卖出了一张票，还剩"+ticketNum.num+"张");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("station1发现票卖完了");
            }
        }
    }

    private static class Station2 extends Thread{
        @Override
        public void run() {
            synchronized (ticketNum){
                while(ticketNum.num!=0){
                    ticketNum.num--;
                    System.out.println("station2卖出了一张票，还剩"+ticketNum.num+"张");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("station2发现票卖完了");
            }
        }
    }

    private static class Station3 extends Thread{
        @Override
        public void run() {
            synchronized (ticketNum){
                while(ticketNum.num!=0){
                    ticketNum.num--;
                    System.out.println("station3卖出了一张票，还剩"+ticketNum.num+"张");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("station3发现票卖完了");
            }
        }
    }

    // 创建一个剩余票数的实体类，因为synchronized不支持对Integer/Double等基本数据类型封装类的同步，与这些封装类的特性有关
    // 如有一个对象test=new Integer(50)，当执行test--时，实际执行的结果是test=new Integer(test.intvalue()-1),重新生成了一个对象
    private static class TicketNum{

        int num=0;

        public TicketNum(int num) {
           this.num=num;
        }
    }

}
