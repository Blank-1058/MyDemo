package ThinkInJava.chapter5.Practice;

/**
 * 方法重载
 */
public class Practice5and6 {

    public static void main(String[] arg0){
        Dog dog=new Dog();
        dog.bark(0);
        dog.bark("0");
        dog.bark(0,"0");
        dog.bark("0",0);
    }

    private static class Dog{
        public void bark(int i){
            System.out.println("barking");
        }

        public void bark(String str){
            System.out.println("howling");
        }

        public void bark(int i,String str){
            System.out.println("barking");
        }

        public void bark(String str,int i){
            System.out.println("howling");
        }
    }
}
