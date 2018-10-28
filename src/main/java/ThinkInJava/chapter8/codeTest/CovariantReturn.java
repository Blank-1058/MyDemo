package ThinkInJava.chapter8.codeTest;

/**
 * 协变返回
 */
public class CovariantReturn {

    public static void main(String[] args){
        Mill mill=new Mill();
        Grain grain= mill.process();
        System.out.println(grain);

        mill=new WheatMill();
        grain=mill.process();
        System.out.println(grain);
    }

    private static class Grain{

        @Override
        public String toString() {
            return "Grain{}";
        }
    }

    private static class Wheat extends Grain{

        @Override
        public String toString() {
            return "Wheat{}";
        }
    }

    private static class Mill{
        Grain process(){
            return new Grain();
        }
    }

    private static class WheatMill extends Mill{
        Wheat process(){
            return new Wheat();
        }
    }
}
