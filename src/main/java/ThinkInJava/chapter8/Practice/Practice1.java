package ThinkInJava.chapter8.Practice;

public class Practice1 {

    public static void main(String[] args){
        Unicycle unicycle=new Unicycle();
        ride(unicycle);

        Bicycle bicycle=new Bicycle();
        ride(bicycle);

        Tricycle tricycle=new Tricycle();
        ride(tricycle);
    }

    private static void ride(Cycle cycle){
        cycle.ride();
    }

    private static class Cycle{
        public void ride(){
            System.out.println("Cycle.ride()");
        }
    }

    private static class Unicycle extends Cycle{
        public void ride(){
            System.out.println("Unicycle.ride()");
        }
    }

    private static class Bicycle extends Cycle{
        public void ride(){
            System.out.println("Bicycle.ride()");
        }
    }

    private static class Tricycle extends Cycle{
        public void ride(){
            System.out.println("Tricycle.ride()");
        }
    }
}
