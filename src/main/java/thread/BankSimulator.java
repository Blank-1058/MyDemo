package thread;

/**
 * 银行模拟
 * 一个人在柜台取钱，一个人在ATM取钱
 *
 * @author liukang_lc on 2019/6/20
 */
public class BankSimulator {

    private class Bank{
        public double money=0;

        public Bank(double money) {
            this.money = money;
        }

        /**
         * 取钱
         * @param money
         */
        public void drawMoney(double money){
            this.money-=money;
        }
    }

}
