package ThinkInJava.chapter9.Practice;

public class Practice3 {

    public static void main(String[] args){
        Son son=new Son();
        son.print();
    }

    private static abstract class Father{
        public Father(){
            print();
        }
        abstract void print();
    }

    private static class Son extends Father{
        private int i=10;
        void print() {
            System.out.println(i);
        }
    }
}
